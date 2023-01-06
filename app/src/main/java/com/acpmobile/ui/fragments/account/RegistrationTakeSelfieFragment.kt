package com.acpmobile.ui.fragments.account

import android.Manifest
import android.app.Activity
import android.content.ActivityNotFoundException
import android.content.ContentValues
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import androidx.fragment.app.viewModels
import com.acpmobile.BuildConfig
import com.acpmobile.R
import com.acpmobile.databinding.FragmentScanIdBinding
import com.acpmobile.databinding.FragmentTakeSelfieBinding
import com.acpmobile.ui.activity.MainActivity
import com.acpmobile.ui.fragments.account.viewmodels.VerificationViewModel
import com.acpmobile.utils.Constants
import com.acpmobile.utils.Navigation
import com.acpmobile.utils.SharedPreferencesHelper
import dagger.hilt.android.AndroidEntryPoint
import id.zelory.compressor.Compressor
import kotlinx.coroutines.async
import kotlinx.coroutines.runBlocking
import java.io.File
import javax.inject.Inject

@AndroidEntryPoint
class RegistrationTakeSelfieFragment : Fragment() {

    private var _binding: FragmentTakeSelfieBinding? = null
    private val binding get() = _binding!!
    private val CAMERA_PERMISSION_CODE = 1003
    private var attachmentBack: File? = null
    private var attachmentFront: File? = null
    private var attachmentSelfie: File? = null
    private var compressedSelfie: File? = null
    private val viewModel: VerificationViewModel by viewModels()

    @Inject
    lateinit var navigation: Navigation

    @Inject
    lateinit var helper: SharedPreferencesHelper

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentTakeSelfieBinding.inflate(inflater, container, false)
        val view = binding.root
        navigation.activity = activity as MainActivity
        val mActivity = activity as MainActivity
        binding.containerViewPersonalIdentityBankInfo.tvIdentityProof.setTextColor(
            ContextCompat.getColor(
                requireContext(),
                R.color.black
            )
        )
        binding.containerViewPersonalIdentityBankInfo.tvIdentityProof.setBackgroundResource(R.drawable.round_element_6)
        binding.containerCircleBar.ivCircle3.setBackgroundResource(R.drawable.circle_element_blue)


        attachmentBack = arguments?.getSerializable("back") as File
        attachmentFront = arguments?.getSerializable("front") as File

        binding.btnOpenCamera.setOnClickListener {
            // Request permission
            val permissionGranted = requestCameraPermission(false)
            if (permissionGranted) {
                // Open the camera interface
                getCamera()
            }
        }

        binding.btnNext.setOnClickListener {
            attachmentSelfie = null
            viewModel.userVerification(
                mActivity.userVerificationRequest!!,
                attachmentFront,
                attachmentBack,
                compressedSelfie
            )
        }

        observeViewModel()
        return view
    }


    private fun requestCameraPermission(isBack: Boolean): Boolean {
        var permissionGranted = false
// If system os is Marshmallow or Above, we need to request runtime permission
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val cameraPermissionNotGranted = ContextCompat.checkSelfPermission(
                activity as Context,
                Manifest.permission.CAMERA
            ) == PackageManager.PERMISSION_DENIED
            if (cameraPermissionNotGranted) {
                val permission = arrayOf(Manifest.permission.CAMERA)
                // Display permission dialog
                requestPermissions(permission, CAMERA_PERMISSION_CODE)
            } else {
                // Permission already granted
                permissionGranted = true
            }
        } else {
            // Android version earlier than M -&gt; no need to request permission
            permissionGranted = true
        }
        return permissionGranted
    }

    private suspend fun compress(file: File, context: Context): File {
        return Compressor.compress(context, file)
    }

    private fun getCamera() {
        val makePictureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        File(requireContext().cacheDir.path).mkdirs()
        makePictureIntent.putExtra(
            MediaStore.EXTRA_OUTPUT, FileProvider.getUriForFile(
                requireContext(),
                BuildConfig.APPLICATION_ID + ".provider",
                File(requireContext().cacheDir.path + "/" + "photoSelfie" + ".jpg")
            )
        )
        try {
            startActivityForResult(makePictureIntent, CAMERA_PERMISSION_CODE)
        } catch (e: ActivityNotFoundException) {
        }
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
// Callback from camera intent
        if (resultCode == Activity.RESULT_OK) {
            // Set image captured to image view

            attachmentSelfie = File(requireContext().cacheDir.path + "/" + "photoSelfie" + ".jpg")
            val bitmap = BitmapFactory.decodeFile(attachmentBack!!.absolutePath)
            binding.ivImage.setImageBitmap(bitmap)

            if (attachmentSelfie != null) {

                runBlocking {
                    val selfie = async { compress(attachmentSelfie!!, requireContext()) }

                    runBlocking {
                        compressedSelfie = selfie.await()

                    }
                }

                binding.btnOpenCamera.visibility = View.GONE
                binding.btnNext.visibility = View.VISIBLE
            }

        } else {
            // Failed to take picture
            Toast.makeText(
                context,
                context?.resources?.getString(R.string.failed_to_take_picture),
                Toast.LENGTH_LONG
            ).show()
        }
    }

    private fun observeViewModel() {
        viewModel.loading.observe(viewLifecycleOwner) { isLoading ->
            binding.pbTakeASelfie.visibility =
                if (isLoading) View.VISIBLE else View.GONE
        }

        viewModel.verifyUserError.observe(viewLifecycleOwner) { isError ->
            if (isError)
                Toast.makeText(
                    context,
                    context?.getString(R.string.error_message),
                    Toast.LENGTH_SHORT
                ).show()
        }

        viewModel.verifySuccess.observe(viewLifecycleOwner) { response ->
            val mActivity = activity as MainActivity
            mActivity.userVerificationRequest = null
            helper.setString(Constants.accountID, response.account.id!!)
            helper.setString(Constants.workflowExecutionID, response.workflowExecution.id!!)
            navigation.openSuccessIdentityProof()
        }
    }
}