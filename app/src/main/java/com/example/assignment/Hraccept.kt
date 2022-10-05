package com.example.assignment

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.database.FirebaseDatabase

class Hraccept : AppCompatActivity() {

    private lateinit var  database: FirebaseDatabase
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hraccept)


        val Appname  = intent.getStringExtra("App_Name")
        Toast.makeText(this, Appname, Toast.LENGTH_SHORT).show()

        val nameIn = findViewById<TextView>(R.id.nameIn)
        val ageIn = findViewById<TextView>(R.id.ageIn)
        val  eduIn= findViewById<TextView>(R.id.eduIn)
        val  courseIn= findViewById<TextView>(R.id.courseIn)
        val  jobTitleIn= findViewById<TextView>(R.id.jobTitleIn)
        val accImg = findViewById<ImageView>(R.id.accImage)
        val btnInt =findViewById<Button>(R.id.btnInterview)
        val btnReject =findViewById<Button>(R.id.btnReject)
        database = FirebaseDatabase.getInstance()
        val seekerRef = database.getReference("JobSeeker")

        seekerRef.get()
            .addOnSuccessListener {
                all->
                for(i in all.children){
                    if(i.child("name").value.toString()==Appname){
                        Toast.makeText(this, i.child("age").value.toString(), Toast.LENGTH_SHORT).show()
                        nameIn.text= i.child("name").value.toString()
                        ageIn.text= i.child("age").value.toString()
                        eduIn.text= i.child("edu").value.toString()
                    }
                }


            }






    }
}