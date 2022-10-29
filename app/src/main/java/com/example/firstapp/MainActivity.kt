package com.example.firstapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private var canAddOperation = false
    private var canAddDecimal = true



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }

       fun numberAction (view: View) {

           if(view is Button) {

            if(view.text.toString() == ".") {

              if(canAddDecimal)

               workingsTV.append(view.text)

               canAddDecimal = false

           } else

                workingsTV.append(view.text)
                canAddOperation = true

           }
       }


       fun operationAction (view: View) {

           if(view is Button && canAddOperation){
               workingsTV.append(view.text)
               canAddOperation = false
               canAddDecimal = true
           }

       }

       fun allClearAction (view: View) {

           workingsTV.text= ""
           resultsTV.text=""
       }


    fun backSpaceAction(view: View){
        val length = workingsTV.text.length

        if(length > 0)
            workingsTV.text = workingsTV.text.subSequence(0, length - 1)

    }

    fun equalsAction(view: View){
        resultsTV.text = calculateResults()
    }


    private fun  calculateResults(): String {

        val digitsOperator = digitsOperation()
        if(digitsOperator.isEmpty()) return ""

        val timesDivision = timesDivisionCalculate(digitsOperator)
        if (timesDivision.isEmpty()) return ""

        val result = addSubtractCalculate(timesDivision)
        return result.toString()
    }

    private fun addSubtractCalculate(passedList: MutableList<Any>) {

        var result = passedList[0] as Float

        for(i in passedList.indices)

        {
            if(passedList[i] is Char && i != passedList.lastIndex){

                val operator = passedList[i]
                val nextDigit = passedList[i + 1] as Float

                if (operator == '+')
                    result += nextDigit

                if (operator == '-')
                    result -= nextDigit

            }
        }

    }

    private fun  timesDivisionCalculate(passedList: MutableList<Any>): MutableList<Any>

    {
        var list = passedList
        while (list.contains('x') || list.contains ('/'))
        {
            list = calcTimesDiv(list)
        }
        return list
    }

    private fun calcTimesDiv(passedlist: MutableList<Any>): MutableList<Any> {

        val newlist = mutableListOf<Any>()
        var restartIndex = passedlist.size

        for(i in passedlist.indices){

            if(passedlist[i] is Char && i !=passedlist.lastIndex && i < restartIndex){

                val operator = passedlist[i]
                val prevDigit = passedlist[i - 1]  as Float
                val nextDigit = passedlist[i + 1]  as Float
                when(operator)

                {
                    'x' -> {
                        newlist.add(prevDigit * nextDigit)
                        restartIndex = i + 1
                    }

                    '/' -> {
                        newlist.add(prevDigit / nextDigit)
                        restartIndex = i + 1
                    }

                    else -> {
                        newlist.add(prevDigit)
                        newlist.add(operator)
                    }


                }
            }


            if(i > restartIndex) newlist.add(passedlist[i])
        }


        return newlist
    }

    private fun digitsOperation(): MutableList<Any>{

        val list = mutableListOf<Any>()
        var currentDigit = ""
        for(character in workingsTV.text){

            if(character.isDigit()|| character == '.' )
                currentDigit += character

            else{
                list.add(currentDigit.toFloat())
                currentDigit = ""
                list.add(character)
            }
        }

        if (currentDigit != "")
            list.add(currentDigit.toFloat())

            return list
    }




}