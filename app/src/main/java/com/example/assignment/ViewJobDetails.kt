package com.example.assignment

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.google.firebase.database.FirebaseDatabase

class ViewJobDetails : AppCompatActivity() {
    private lateinit var  database: FirebaseDatabase
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_job_details)

        val JobId  = intent.getStringExtra("Job_Id")
        database = FirebaseDatabase.getInstance()
        val seekerRef = database.getReference("Student")
        val appRef = database.getReference("Applicant")

        val jobTitle = findViewById<TextView>(R.id.jobTitle)
        val location =findViewById<TextView>(R.id.comlocation)
        val desc  =findViewById<TextView>(R.id.JobDesc)

        val btnApply = findViewById<Button>(R.id.btnApply)
        val studentId ="3"


        btnApply.setOnClickListener(){
appRef.get()
    .addOnSuccessListener {
        all1->
        for(k in all1.children){

            if(k.child("seekerId").value.toString()==studentId){



                val apply = JobId?.let { it1 -> applied(it1,studentId,"applied") }



                appRef.child(k.key.toString()).setValue(apply)
            }
        }


    }
        }
        seekerRef.get()
            .addOnSuccessListener {

                all->
                for(i in all.children){
                    if(i.key.toString()==JobId){
//                        jobTitle.text =  i.child("")
                        location.text = i.child("compLocation").value.toString()

                    }
                }
            }
            .addOnFailureListener {


            }





    }
}