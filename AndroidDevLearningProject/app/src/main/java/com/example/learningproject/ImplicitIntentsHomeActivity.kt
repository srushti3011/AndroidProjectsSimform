package com.example.learningproject

import android.app.Activity
import android.content.Intent
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.FileProvider
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.learningproject.databinding.ActivityImplicitIntentsHomeBinding
import java.io.File

class ImplicitIntentsHomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityImplicitIntentsHomeBinding
    private lateinit var imageURI: Uri

    private val cameraLauncher = registerForActivityResult(
        ActivityResultContracts.TakePicture()
    ) {
        binding.imgCapturedImage.setImageURI(null)
        binding.imgCapturedImage.setImageURI(imageURI)
    }

    @RequiresApi(Build.VERSION_CODES.P)
    private val photoSelectorLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            val uri = result.data?.data
            Log.i("uri", "$uri of selected img from gallery")
            if (uri != null) {
                val source = ImageDecoder.createSource(this.contentResolver, uri)
                val bitmap = ImageDecoder.decodeBitmap(source)
                binding.imgCapturedImage.setImageBitmap(bitmap)
            }
        }
    }

    private fun createImageURI(): Uri? {
        val image = File(applicationContext.filesDir, "camera_photo.png")
        return FileProvider.getUriForFile(
            applicationContext,
            "com.example.learningproject.fileProvider",
            image
        )
    }

    @RequiresApi(Build.VERSION_CODES.P)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityImplicitIntentsHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.btnGoToBrowser.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("http://www.google.com"))
            startActivity(intent)
        }

        binding.btnSendSMS.setOnClickListener {
            val intent = Intent(Intent.ACTION_SENDTO, Uri.parse("smsto:1234567899"))
            startActivity(intent)
        }

        binding.btnSendMail.setOnClickListener {
            val intent = Intent(
                Intent.ACTION_VIEW,
                Uri.parse("mailto:?subject=Exam Date Changes&body=Body of mail")
            )
            startActivity(intent)
        }

        val uri = createImageURI()
        if (uri != null) {
            imageURI = uri
        }
        binding.btnCapturePhoto.setOnClickListener {
            cameraLauncher.launch(imageURI)
        }

        binding.btnCall.setOnClickListener {
            val intent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:1234567899"))
            startActivity(intent)
        }

        binding.imgFromGallery.setOnClickListener {
            val intent = Intent()
            intent.setType("image/*")
            intent.setAction(Intent.ACTION_GET_CONTENT)
            photoSelectorLauncher.launch(intent)
        }

        val intent = intent
        val action = intent.action
        val type = intent.type
        if (Intent.ACTION_SEND.equals(action) && type != null) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                val uri = intent.getParcelableExtra(Intent.EXTRA_STREAM, Uri::class.java)
                binding.imgCapturedImage.setImageURI(uri)
            }
        }
    }
}