package com.example.c2fconverter

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import java.text.DecimalFormat
class MainActivity : AppCompatActivity() {
   public var tempMode: String = "C"
    public val pereviousTempList = mutableListOf<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val modeBtn = findViewById<Button>(R.id.modeButton)
        modeBtn.setOnClickListener {
            changemode()
        }

        val convertBtn = findViewById<Button>(R.id.convertBtn)
        convertBtn.setOnClickListener{
            convertTemperature()
        }


    }




    private  fun changemode(){

//        Toast.makeText(this, tempMode.toString(), Toast.LENGTH_SHORT).show()
        val modeText=findViewById<TextView>(R.id.showMode);
        if(tempMode=="C"){
            tempMode="F";
            modeText.text = getString(R.string.fMode)

            Toast.makeText(this, "Fahrenheit to Celsius", Toast.LENGTH_SHORT).show()
        }else{
            tempMode="C"
            modeText.text = getString(R.string.cMode)
            Toast.makeText(this, "Celsius to Fahrenheit", Toast.LENGTH_SHORT).show()
        }

        Log.v("after",tempMode)

//        Toast.makeText(this, tempMode.toString(), Toast.LENGTH_SHORT).show()

    }

    private fun convertTemperature(){
        val userTemp=findViewById<EditText>(R.id.userInput).text
        val tempShow=findViewById<TextView>(R.id.tempShow);

        if (userTemp.isNotBlank()) {
//            Toast.makeText(this, userTemp, Toast.LENGTH_SHORT).show()

            if(tempMode=="C"){
//                Toast.makeText(this, "Cel", Toast.LENGTH_SHORT).show()

                val celsius = userTemp.toString().toDoubleOrNull()
                if (celsius != null) {
                    val fahrenheit = (celsius *9/5)+32;
                    Toast.makeText(this, "Temperature in Fahrenheit: $fahrenheit", Toast.LENGTH_SHORT).show()
                    val fahrenheitTemp=fahrenheit.toString()+"°F";
                    tempShow.text=fahrenheitTemp;


        val arraySize=pereviousTempList.size;
                    if(arraySize<10){
                        pereviousTempList.add("$celsius °C to $fahrenheitTemp");
                    }else{
                        pereviousTempList.removeAt(0)
                        pereviousTempList.add("$celsius °C to $fahrenheitTemp");
                    // Removes the first occurrence of 3
                    }

                    showPreviousTemperature()


                } else {
                    Toast.makeText(this, "Please enter a valid temperature", Toast.LENGTH_SHORT).show()
                }

            }else if (tempMode=="F"){
                val fahrenheit = userTemp.toString().toDoubleOrNull()
                if (fahrenheit != null) {
                    val celsius = (fahrenheit - 32) * 5 / 9
                    val formattedCelsius = DecimalFormat("#.##").format(celsius)
                    Toast.makeText(this, "Temperature in Celsius: $formattedCelsius", Toast.LENGTH_SHORT).show()
                    val CelsiusTemp=formattedCelsius.toString()+"°C";
                    tempShow.text=CelsiusTemp;

                    val arraySize=pereviousTempList.size;
                    if(arraySize<10){
                        pereviousTempList.add("$fahrenheit °F to $CelsiusTemp");
                    }else{
                        pereviousTempList.removeAt(0)
                        pereviousTempList.add("$fahrenheit °F to $CelsiusTemp");
                        // Removes the first occurrence of 3
                    }
                    showPreviousTemperature()

                } else {
                    Toast.makeText(this, "Please enter a valid temperature", Toast.LENGTH_SHORT).show()
                }

            }

        } else {
            Toast.makeText(this, "Please enter a value", Toast.LENGTH_SHORT).show()
        }

    }

    private fun showPreviousTemperature() {
        val textView = findViewById<TextView>(R.id.previousTemp)
        val stringBuilder = StringBuilder()
        val reverseList=pereviousTempList.reversed()
        for (temp in reverseList) {
            stringBuilder.append("$temp\n")
        }
        textView.text = stringBuilder.toString()
    }

}