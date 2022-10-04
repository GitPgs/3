package com.example.assignment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.FirebaseDatabase


class Saved : Fragment() ,SaveAdapter.OnItemClickListener{


    private lateinit var  database: FirebaseDatabase
//    private var BookmarkList = listOf(
//        Bookmark("","",""),
//
//    )

    private var JobList = listOf(
        JobList(" "," "," "," "," "," "),
//        JobList("2","a.png","Supervisor","PGS","Penang","12/2/2022"),
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
      val view =inflater.inflate(R.layout.fragment_saved, container, false)
        val myRecyclerView = view.findViewById<RecyclerView>(R.id.recSave)
        database = FirebaseDatabase.getInstance()
        val myRef = database.getReference("Bookmark")
        val jobRef = database.getReference("Student")

        val myAdapter = SaveAdapter(JobList, this)
        //this refer to main activity who click on the recycler view
        myRecyclerView.adapter = myAdapter

        myRecyclerView.layoutManager = LinearLayoutManager(activity)
        //  myRecyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
//      myRecyclerView.layoutManager = GridLayoutManager(this,2)
        myRecyclerView.setHasFixedSize(true)

        myRef.get()
            .addOnSuccessListener {
                result->
               for(i in result.children){
                   if(i.child("jobSeekerId").value.toString()=="23"){

                jobRef.get()

                    .addOnSuccessListener {
                               record->
                        for(k in  record.children){
                            if(k.child("id").value.toString()==i.child("jobId").value.toString()){
                                JobList+=JobList(k.child("id").value.toString() ,"a.png",k.child("pos").value.toString(),k.child("compName").value.toString(),k.child("compLocation").value.toString(),k.child("date").value.toString())
                                var myAdapter= SaveAdapter(JobList,this)
                                myRecyclerView.adapter=myAdapter

                                myRecyclerView.layoutManager = LinearLayoutManager(activity)
                            }
                        }

                        //  myRecyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
//      myRecyclerView.layoutManager = GridLayoutManager(this,2)
                        myRecyclerView.setHasFixedSize(true)
                    }
                    .addOnFailureListener {  }

                   }
               }
            }
            .addOnFailureListener {  }






    return view
    }

    override fun itemClick(position: Int) {
        val selectStudent = JobList[position]

        Toast.makeText(activity, selectStudent.pos, Toast.LENGTH_SHORT).show()
    }


}