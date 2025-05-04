package com.example.features

import android.content.Context
import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import dalvik.annotation.TestTarget
import org.w3c.dom.Text

class SharePreference : AppCompatActivity() {
    private lateinit var txtName: TextView
    private lateinit var txtAge: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_share_preference)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        txtName =findViewById<TextView>(R.id.txtName)
        txtAge=findViewById<TextView>(R.id.txtAge)

        val sharePreference = getSharedPreferences("LocalData", Context.MODE_PRIVATE)
        var resName = sharePreference.getString("Name", "Hey")
        var resAge = sharePreference.getInt("Age", 20)

        txtName.text = resName
        txtAge.text = resAge.toString()
    }
}