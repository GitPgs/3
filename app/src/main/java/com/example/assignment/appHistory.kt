package com.example.assignment

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.FirebaseDatabase

class appHistory : AppCompatActivity(),HistoryAdapter.OnItemClickListener {

       private lateinit var  database: FirebaseDatabase
    private var HisList = mutableListOf(
        HistoryList("1","a.png","Manager","PGS","Penang","internship","12/2/2022"),
        HistoryList("2","a.png","Supervisor","PGS","Penang","parttime","12/2/2022"),
    )




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_app_history)


        val myRecyclerView = findViewById<RecyclerView>(R.id.recHis)
        database = FirebaseDatabase.getInstance()
        val myRef = database.getReference("Student")
        val appRef = database.getReference("Applicant")
        val seekerId= "3"
        val btnStatus=findViewById<Button>(R.id.status)
HisList.clear()
      appRef.get()
          .addOnSuccessListener {
              all->
              for(i in all.children){
                  if(i.child("seekerId").value.toString()==seekerId){
                      if(i.child("status").value.toString()=="applied"||i.child("status").value.toString()=="reject"||i.child("status").value.toString()=="interviewable"){
            myRef.get()
                .addOnSuccessListener {
                   all2->
                    for (k in  all2.children){
                        if(k.key.toString()==i.child("jobId").value.toString()){
                            HisList += HistoryList(
                                k.child("id").value.toString(),
                                "a.png",
                                k.child("pos").value.toString(),
                                k.child("compName").value.toString(),
                                k.child("compLocation").value.toString(),
                                k.child("date").value.toString()

                            )
                            btnStatus.text=i.child("status").value.toString()
                        }
                        var myAdapter = HistoryAdapter(HisList, this)
                        myRecyclerView.adapter = myAdapter

                        myRecyclerView.layoutManager = LinearLayoutManager(this)
                        //  myRecyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
//      myRecyclerView.layoutManager = GridLayoutManager(this,2)
                        myRecyclerView.setHasFixedSize(true)

                    }
                }


                      }
                  }
              }

          }

    }

    override fun itemClick(position: Int) {

    }
}