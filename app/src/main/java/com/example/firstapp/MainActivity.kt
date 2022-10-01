package com.example.firstapp

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val button = findViewById<Button>(R.id.btn)

        val textView = findViewById<TextView>(R.id.textView1)

        button.setOnClickListener {

            Toast.makeText(this, "Hello Ulug!!!", Toast.LENGTH_SHORT).show()

            textView.text = "number 1"


        }


    }


}