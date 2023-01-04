package com.acpmobile.ui.fragments.account

import android.Manifest
import android.app.Activity
import android.content.ContentValues
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.database.Cursor
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
import androidx.core.content.ContextCompat.checkSelfPermission
import androidx.core.net.toFile
import androidx.fragment.app.viewModels
import com.acpmobile.R
import com.acpmobile.data.request.UserVerificationRequest
import com.acpmobile.databinding.FragmentIdentityProofBinding
import com.acpmobile.databinding.FragmentPersonalInfoBinding
import com.acpmobile.databinding.FragmentRegisterNewAccountBinding
import com.acpmobile.databinding.FragmentScanIdBinding
import com.acpmobile.ui.activity.MainActivity
import com.acpmobile.ui.fragments.account.viewmodels.UserVerificationViewModel
import com.acpmobile.ui.fragments.account.viewmodels.KYCViewModel
import com.acpmobile.utils.Navigation
import dagger.hilt.android.AndroidEntryPoint
import java.io.File
import java.io.FileOutputStream
import javax.inject.Inject

@AndroidEntryPoint
class RegistrationScanIDFragment : Fragment() {

    private var _binding: FragmentScanIdBinding? = null
    private val binding get() = _binding!!
    private val CAMERA_PERMISSION_CODE = 1000
    private val CAMERA_PERMISSION_CODE_BACK = 1002
    private val IMAGE_CAPTURE_CODE = 1001
    private var imageUri: Uri? = null
    private var imageUriBack: Uri? = null
    private val viewModel: UserVerificationViewModel by viewModels()
    private var attachment: File? = null

    @Inject
    lateinit var navigation: Navigation

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentScanIdBinding.inflate(inflater, container, false)
        val view = binding.root
        navigation.activity = activity as MainActivity

        binding.containerViewPersonalIdentityBankInfo.tvIdentityProof.setTextColor(ContextCompat.getColor(requireContext(), R.color.black))
        binding.containerViewPersonalIdentityBankInfo.tvIdentityProof.setBackgroundResource(R.drawable.round_element_6)
        binding.containerCircleBar.ivCircle2.setBackgroundResource(R.drawable.circle_element_blue)

        binding.btnScanFront.setOnClickListener {
//            navigation.openTakeSelfieFragment()
//            requestCameraPermission()

            // Request permission
            val permissionGranted = requestCameraPermission(false)
            if (permissionGranted) {
                // Open the camera interface
                openCameraInterface(false)
            }
        }

        binding.btnScanBack.setOnClickListener {
//            navigation.openTakeSelfieFragment()
//            requestCameraPermission()

            // Request permission
            val permissionGranted = requestCameraPermission(true)
            if (permissionGranted) {
                // Open the camera interface
                openCameraInterface(true)
            }
        }
        return view
    }

    private fun requestCameraPermission(isBack : Boolean): Boolean {
        var permissionGranted = false
// If system os is Marshmallow or Above, we need to request runtime permission
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            val cameraPermissionNotGranted = checkSelfPermission(activity as Context, Manifest.permission.CAMERA) == PackageManager.PERMISSION_DENIED
            if (cameraPermissionNotGranted){
                val permission = arrayOf(Manifest.permission.CAMERA)
                // Display permission dialog
                if(isBack) {
                    requestPermissions(permission, CAMERA_PERMISSION_CODE_BACK)
                }else{
                    requestPermissions(permission, CAMERA_PERMISSION_CODE)
                }
            }
            else{
                // Permission already granted
                permissionGranted = true
            }
        }
        else{
            // Android version earlier than M -&gt; no need to request permission
            permissionGranted = true
        }
        return permissionGranted
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        if (requestCode === CAMERA_PERMISSION_CODE) {
            if (grantResults.size === 1 && grantResults[0] ==    PackageManager.PERMISSION_GRANTED){
                // Permission was granted
                openCameraInterface(false)
            }
            else{
                // Permission was denied
                Toast.makeText(context, context?.resources?.getString(R.string.camera_permission_denied), Toast.LENGTH_LONG).show()
            }
        }

        if (requestCode === CAMERA_PERMISSION_CODE_BACK) {
            if (grantResults.size === 1 && grantResults[0] ==    PackageManager.PERMISSION_GRANTED){
                // Permission was granted
                openCameraInterface(true)
            }
            else{
                // Permission was denied
                Toast.makeText(context, context?.resources?.getString(R.string.camera_permission_denied), Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun openCameraInterface(isBack : Boolean) {
        val values = ContentValues()
        values.put(MediaStore.Images.Media.TITLE, context?.resources?.getString(R.string.take_a_picture))
//        values.put(MediaStore.Images.Media.DESCRIPTION, R.string.take_picture_description)
        if(isBack){
            imageUriBack = activity?.contentResolver?.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values)
        }else{
            imageUri = activity?.contentResolver?.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values)
        }
// Create camera intent
        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)

        if(isBack){
            intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUriBack)
        }else{
            intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri)
        }
// Launch intent
        startActivityForResult(intent, IMAGE_CAPTURE_CODE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
// Callback from camera intent
        if (resultCode == Activity.RESULT_OK){
            // Set image captured to image view
//            attachment = getFileFromURI(imageUri, requireContext(), "photo")

        val file = imageUri?.toFile()
            val request= UserVerificationRequest()
            viewModel.userVerification(request, file, file, file)
            binding.ivPlaceholder.setImageURI(imageUri)
//            navigation.openTakeSelfieFragment()

        }
        else {
            // Failed to take picture
            Toast.makeText(context, context?.resources?.getString(R.string.failed_to_take_picture),  Toast.LENGTH_LONG).show()
        }
    }

    fun getPath(uri: Uri): String? {
        val projection = arrayOf(MediaStore.Images.Media.DATA)
        val cursor: Cursor =
            activity?.contentResolver?.query(uri, projection, null, null, null) ?: return null
        val column_index: Int = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA)
        cursor.moveToFirst()
        val s: String = cursor.getString(column_index)
        cursor.close()
        return s
    }

    fun getFileFromURI(uri: Uri?, context: Context,  name: String?): File{
        var fileName = ""

            fileName = "$name.jpg"

        val file = File(context.cacheDir.absolutePath + File.separator + fileName)
        val fos = FileOutputStream(file)
        fos.write(uri.let { context.contentResolver.openInputStream(it!!)!!.readBytes()})
        return file
    }
}