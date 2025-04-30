 package com.example.features

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    private lateinit var cameraImg: ImageView
    private lateinit var btnCamera: Button
    private lateinit var btnShare: Button
    private lateinit var btnWeb: Button
    private lateinit var btnDesign: Button
    private lateinit var btnNetwork: Button
    private var camera_request_code:Int = 123

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        cameraImg = findViewById<ImageView>(R.id.cameraImg)
        btnCamera = findViewById<Button>(R.id.btnCamera)
        btnShare = findViewById<Button>(R.id.btnShare)
        btnWeb = findViewById<Button>(R.id.btnWeb)
        btnDesign = findViewById<Button>(R.id.btnDesign)
        btnNetwork = findViewById<Button>(R.id.btnNetwork)

        btnCamera.setOnClickListener { takePhoto() }
        btnShare.setOnClickListener {  }
        btnWeb.setOnClickListener {  }
        btnDesign.setOnClickListener {  }
        btnNetwork.setOnClickListener {
            if (checkNetwork()) Toast.makeText(
                this,
                "Internet Access",
                Toast.LENGTH_SHORT
            ).show()

            else Toast.makeText(
                this,
                "No Internet Connection",
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    fun checkNetwork(): Boolean{
        val connectivityManager: ConnectivityManager = this.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        //network ရှိလား မရှိလား စစ်မယ်
        val network = connectivityManager.activeNetwork ?: return false

        val activeNetwork = connectivityManager.getNetworkCapabilities(network) ?: return false


        //wifi or internet
        return when{
            activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> {
                 true
            }

            activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) ->{
                 true
            }

            else -> false
        }

    }

    fun takePhoto(){
        //camera app using MediaStore.ACTION_IMAGE_CAPTURE
        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
//resolveActivity(packageManager) checks if there's an app that can handle this intent
        if(intent.resolveActivity(packageManager) != null){

//            If yes, it calls startActivityForResult() with a request code. This launches the camera app and waits for a result.
            startActivityForResult(intent,camera_request_code)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == camera_request_code && resultCode == RESULT_OK){
            val bitmap = data?.extras?.get("data") as Bitmap
            cameraImg.setImageBitmap(bitmap)
        }
    }




}