package com.example.firstapp

import android.annotation.SuppressLint
import android.os.Build.VERSION_CODES.R
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    private var canAddOperation = false
    private var canAddDecimal = true
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

       fun numberAction (view: View)
       {

           if(view is Button)
           {

            if(view.text ==".")
            {
              if(canAddDecimal)

               workingsTV.append(view.text)
               canAddOperation = true

               canAddDecimal = false
           }
               else

                workingsTV.append(view.text)
                canAddOperation = true


           }


       }
       fun operationAction (view: View)
       {
           if(view is Button & canAddOperation)

           {
               workingsTV.append(view.text)
               canAddOperation = false
               canAddDecimal = true
           }

       }

       fun allClearAction (view: View)

       {

           workingsTV.text= ""
           resultsTV.text=""
       }


    fun backSpaceAction(view: View)
    {
        val length = workingsTV.lenght()
        if(length > 0)
            workingsTV.text = workingsTV.text.subSequence(0, length - 1)

    }





}