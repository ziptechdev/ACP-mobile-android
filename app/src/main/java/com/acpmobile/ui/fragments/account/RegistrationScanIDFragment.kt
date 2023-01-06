package com.acpmobile.ui.fragments.account

import android.Manifest
import android.app.Activity
import android.content.ActivityNotFoundException
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
import androidx.core.content.FileProvider
import com.acpmobile.BuildConfig
import com.acpmobile.R
import com.acpmobile.databinding.FragmentScanIdBinding
import com.acpmobile.ui.activity.MainActivity
import com.acpmobile.utils.Navigation
import dagger.hilt.android.AndroidEntryPoint
import id.zelory.compressor.Compressor
import java.io.File
import java.io.FileOutputStream
import javax.inject.Inject
import android.graphics.BitmapFactory
import kotlinx.coroutines.async
import kotlinx.coroutines.runBlocking

@AndroidEntryPoint
class RegistrationScanIDFragment : Fragment() {

    private var _binding: FragmentScanIdBinding? = null
    private val binding get() = _binding!!
    private val CAMERA_PERMISSION_CODE = 1000
    private val CAMERA_PERMISSION_CODE_BACK = 1002
    private val IMAGE_CAPTURE_CODE = 1001
    private var imageUri: Uri? = null
    private var imageUriBack: Uri? = null
    private var attachmentFront: File? = null
    private var attachmentBack: File? = null
    private var compressedBack: File? = null
    private var compressedFront: File? = null

    @Inject
    lateinit var navigation: Navigation

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentScanIdBinding.inflate(inflater, container, false)
        val view = binding.root
        navigation.activity = activity as MainActivity

        binding.containerViewPersonalIdentityBankInfo.tvIdentityProof.setTextColor(
            ContextCompat.getColor(
                requireContext(),
                R.color.black
            )
        )
        binding.containerViewPersonalIdentityBankInfo.tvIdentityProof.setBackgroundResource(R.drawable.round_element_6)
        binding.containerCircleBar.ivCircle2.setBackgroundResource(R.drawable.circle_element_blue)

        binding.btnNext.setOnClickListener {
            attachmentBack = null
            attachmentFront = null

            val bundle = Bundle()
            bundle.putSerializable("back", compressedBack)
            bundle.putSerializable("front", compressedFront)

            navigation.openTakeSelfieFragment(bundle)
        }

        binding.btnScanFront.setOnClickListener {
            // Request permission
            val permissionGranted = requestCameraPermission(false)
            if (permissionGranted) {
                // Open the camera interface
                getCamera(false)
            }
        }

        binding.btnScanBack.setOnClickListener {
            // Request permission
            val permissionGranted = requestCameraPermission(false)
            if (permissionGranted) {
                // Open the camera interface
                getCamera(true)
            }

//            var compressedAtachment1: File? = null
//            runBlocking {
//                val jobA = async { compress(attachment!!, requireContext()) }
//                runBlocking {
//                    compressedAtachment1 = jobA.await()
//                }
//            }
//
//            viewModel.userVerification(request, compressedAtachment1, attachment, attachment)

            // Request permission
//            val permissionGranted = requestCameraPermission(true)
//            if (permissionGranted) {
//                // Open the camera interface
//                openCameraInterface(true)
//            }
        }


        return view
    }

    private suspend fun compress(file: File, context: Context): File {
        return Compressor.compress(context, file)
    }

    private fun requestCameraPermission(isBack: Boolean): Boolean {
        var permissionGranted = false
// If system os is Marshmallow or Above, we need to request runtime permission
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val cameraPermissionNotGranted = checkSelfPermission(
                activity as Context,
                Manifest.permission.CAMERA
            ) == PackageManager.PERMISSION_DENIED
            if (cameraPermissionNotGranted) {
                val permission = arrayOf(Manifest.permission.CAMERA)
                // Display permission dialog
                if (isBack) {
                    requestPermissions(permission, CAMERA_PERMISSION_CODE_BACK)
                } else {
                    requestPermissions(permission, CAMERA_PERMISSION_CODE)
                }
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

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        if (requestCode === CAMERA_PERMISSION_CODE) {
            if (grantResults.size === 1 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Permission was granted
                getCamera(false)
            } else {
                // Permission was denied
                Toast.makeText(
                    context,
                    context?.resources?.getString(R.string.camera_permission_denied),
                    Toast.LENGTH_LONG
                ).show()
            }
        }

        if (requestCode === CAMERA_PERMISSION_CODE_BACK) {
            if (grantResults.size === 1 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Permission was granted
                getCamera(true)
            } else {
                // Permission was denied
                Toast.makeText(
                    context,
                    context?.resources?.getString(R.string.camera_permission_denied),
                    Toast.LENGTH_LONG
                ).show()
            }
        }
    }

    private fun getCamera(isBackCamera: Boolean) {
        val makePictureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        File(requireContext().cacheDir.path).mkdirs()
        makePictureIntent.putExtra(
            MediaStore.EXTRA_OUTPUT, FileProvider.getUriForFile(
                requireContext(),
                BuildConfig.APPLICATION_ID + ".provider",
                if (isBackCamera) {
                    File(requireContext().cacheDir.path + "/" + "photoBack" + ".jpg")
                } else {
                    File(requireContext().cacheDir.path + "/" + "photoFront" + ".jpg")
                }
            )
        )
        try {
            if (isBackCamera) {
                startActivityForResult(makePictureIntent, CAMERA_PERMISSION_CODE_BACK)
            } else {
                startActivityForResult(makePictureIntent, CAMERA_PERMISSION_CODE)
            }
        } catch (e: ActivityNotFoundException) {
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
// Callback from camera intent
        if (resultCode == Activity.RESULT_OK) {
            // Set image captured to image view

//            when (requestCode) {
//                CAMERA_PERMISSION_CODE_BACK -> attachmentBack =
//                    File(requireContext().cacheDir.path + "/" + "photoBack" + ".jpg")
//                CAMERA_PERMISSION_CODE -> attachmentFront =
//                     File(requireContext().cacheDir.path + "/" + "photoFront" + ".jpg")
//            }


            if (requestCode == CAMERA_PERMISSION_CODE_BACK) {
                attachmentBack = File(requireContext().cacheDir.path + "/" + "photoBack" + ".jpg")
                val bitmap = BitmapFactory.decodeFile(attachmentBack!!.absolutePath)
                binding.ivImage.setImageBitmap(bitmap)
            }

            if (requestCode == CAMERA_PERMISSION_CODE) {
                attachmentFront = File(requireContext().cacheDir.path + "/" + "photoFront" + ".jpg")
                val bitmap = BitmapFactory.decodeFile(attachmentFront!!.absolutePath)
                binding.ivImage.setImageBitmap(bitmap)
            }

            if (attachmentBack != null && attachmentFront != null) {


                runBlocking {
                    val back = async { compress(attachmentBack!!, requireContext()) }
                    val front = async { compress(attachmentFront!!, requireContext()) }

                    runBlocking {
                        compressedBack = back.await()
                        compressedFront = front.await()

                    }
                }

                binding.btnScanBack.visibility = View.GONE
                binding.btnScanFront.visibility = View.GONE
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


    private fun openCameraInterface(isBack: Boolean) {
        val values = ContentValues()
        values.put(
            MediaStore.Images.Media.TITLE,
            context?.resources?.getString(R.string.take_a_picture)
        )
//        values.put(MediaStore.Images.Media.DESCRIPTION, R.string.take_picture_description)
        if (isBack) {
            imageUriBack = activity?.contentResolver?.insert(
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                values
            )
        } else {
            imageUri = activity?.contentResolver?.insert(
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                values
            )
        }
// Create camera intent
        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)

        if (isBack) {
            intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUriBack)
        } else {
            intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri)
        }
// Launch intent
        startActivityForResult(intent, IMAGE_CAPTURE_CODE)
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

    fun getFileFromURI(uri: Uri?, context: Context, name: String?): File {
        var fileName = ""

        fileName = "$name.jpg"

        val file = File(context.cacheDir.absolutePath + File.separator + fileName)
        val fos = FileOutputStream(file)
        fos.write(uri.let { context.contentResolver.openInputStream(it!!)!!.readBytes() })
        return file
    }

    fun getRealPathFromURI(uri: Uri): String? {
        val result: String?
        val cursor: Cursor = context?.contentResolver?.query(uri, null, null, null, null)!!
        if (cursor == null) {
            result = uri.path
            cursor.close()
            return result
        }
        cursor.moveToFirst()
        val idx = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA)
        result = cursor.getString(idx)
        cursor.close()
        return result
    }
}