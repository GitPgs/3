package com.example.assignment

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.FirebaseDatabase

class Applicant : Fragment(),ApplicantAdapter.OnItemClickListener {

    private lateinit var  database: FirebaseDatabase
    private var AppList = mutableListOf(
        ApplicantList("  "," "," "),


    )

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
   val view = inflater.inflate(R.layout.fragment_applicant, container, false)
        val myRecyclerView = view.findViewById<RecyclerView>(R.id.recApp)
        database = FirebaseDatabase.getInstance()
        val apply = database.getReference("Applicant")
        val jobref = database.getReference("Student")
        val seekref = database.getReference("JobSeeker")
AppList.clear()
apply.get()
    .addOnSuccessListener {
        all->
        for (i in all.children){

            if(i.child("status").value.toString()=="pending"){
//                Toast.makeText(activity, i.child("jobId").value.toString(), Toast.LENGTH_SHORT).show()
                jobref.get()
                    .addOnSuccessListener {
all2->
                        for(k in all2.children){
                            if(i.child("jobId").value.toString() == k.key.toString()){

                                if(k.child("compName").value.toString() =="Toy"){
//                                    Toast.makeText(activity, k.key.toString(), Toast.LENGTH_SHORT).show()
                                seekref.get()
                                    .addOnSuccessListener {
                                        all3->
                                        for(d in all3.children ){
                                            if(d.key.toString() ==i.child("seekerId").value.toString()){
//                                                Toast.makeText(activity, d.child("name").value.toString(), Toast.LENGTH_SHORT).show()

                                                AppList+=ApplicantList(d.child("name").value.toString(),d.child("age").value.toString(),d.child("edu").value.toString())
                                                var myAdapter = ApplicantAdapter(AppList, this)
                                                myRecyclerView.adapter = myAdapter

                                                myRecyclerView.layoutManager = LinearLayoutManager(activity)
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

            }

//            var myAdapter = ApplicantAdapter(AppList, this)
//            myRecyclerView.adapter = myAdapter
//
//            myRecyclerView.layoutManager = LinearLayoutManager(activity)
//            //  myRecyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
////      myRecyclerView.layoutManager = GridLayoutManager(this,2)
//            myRecyclerView.setHasFixedSize(true)
        }




    }


//

        var myAdapter = ApplicantAdapter(AppList, this)
        myRecyclerView.adapter = myAdapter

        myRecyclerView.layoutManager = LinearLayoutManager(activity)
        //  myRecyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
//      myRecyclerView.layoutManager = GridLayoutManager(this,2)
        myRecyclerView.setHasFixedSize(true)




        return view
    }

    override fun itemClick(position: Int) {
        val selectApplicant= AppList[position]
        Toast.makeText(activity, selectApplicant.Appname, Toast.LENGTH_SHORT).show()


val intent = Intent(activity,Hraccept::class.java)
        intent.putExtra("App_Name",selectApplicant.Appname)

        startActivity(intent)

    }


}