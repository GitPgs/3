package com.example.assignment

import android.app.Activity
import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.view.*
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_main.view.*
import java.util.*


class Job : Fragment() ,JobAdapter.OnItemClickListener{


    private lateinit var  database: FirebaseDatabase
    private var JobList = mutableListOf(
        JobList("1","a.png","Manager","PGS","Penang","internship","12/2/2022"),
        JobList("2","a.png","Supervisor","PGS","Penang","parttime","12/2/2022"),
    )


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_job, container, false)
//        JobList+=JobList("a.png","Manager1","PGS","Penang","12/2/2022")
val filter = view.findViewById<Button>(R.id.btnFilter)

//       val not able to change value after declare  so we change to private var JobList
        val myRecyclerView = view.findViewById<RecyclerView>(R.id.recJob)
        database = FirebaseDatabase.getInstance()
        val myRef = database.getReference("Student")
        var checkedJobType = booleanArrayOf(false, false, false)
        var checkedLocation = booleanArrayOf(false, false, false)
        filter.setOnClickListener(){

            val builder = AlertDialog.Builder(context)
            builder.setTitle("Filter BY:")
            val FilterBy = arrayOf("jobType", "location", "Salary")
            builder.setSingleChoiceItems(FilterBy, -1) { dialogmain, which ->


           if(FilterBy[which]=="jobType"){


               val builder = AlertDialog.Builder(context)
               builder.setTitle("Choose jobtypes to be filter :")

// Add a checkbox list
               val JobType= arrayOf("internship","partTime","fullTime")
//               val checkedItems = booleanArrayOf(false, false, false)
               builder.setMultiChoiceItems(JobType,checkedJobType) { dialogJobtype, jobtypeSelected, isChecked ->
                   //JobType[jobtypeSelected] -> means
//                  checkedJobType[0]->false , checkedJobType -> false  false false
               }


               builder.setPositiveButton("OK") { dialogJobtype,jobtypeSelected ->

myRef.get()
    .addOnSuccessListener {
all->
//if(!checkedItems[0] && !checkedItems[1] && !checkedItems[2]){
//
//    for (i in  all.children) {
//        JobList += JobList(
//            i.child("id").value.toString(),
//            "a.png",
//            i.child("pos").value.toString(),
//            i.child("compName").value.toString(),
//            i.child("compLocation").value.toString(),
//            i.child("date").value.toString()
//        )
//
//
//        var myAdapter = JobAdapter(JobList, this)
//        myRecyclerView.adapter = myAdapter
//
//        myRecyclerView.layoutManager = LinearLayoutManager(activity)
//        //  myRecyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
////      myRecyclerView.layoutManager = GridLayoutManager(this,2)
//        myRecyclerView.setHasFixedSize(true)
//
//    }
//
//}
if(checkedJobType[0] && !checkedJobType[1] && !checkedJobType[2]){

    JobList.clear()
    for (i in  all.children) {

        if (i.child("jobType").value.toString()=="internship"){
i.child("jobType").value.toString()=="internship" && i.child("complocation").value.toString()=="penang"
            JobList += JobList(
                i.child("id").value.toString(),
                "a.png",
                i.child("pos").value.toString(),
                i.child("compName").value.toString(),
                i.child("compLocation").value.toString(),
                i.child("date").value.toString()
            )
        }



        var myAdapter = JobAdapter(JobList, this)
        myRecyclerView.adapter = myAdapter

        myRecyclerView.layoutManager = LinearLayoutManager(activity)
        //  myRecyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
//      myRecyclerView.layoutManager = GridLayoutManager(this,2)
        myRecyclerView.setHasFixedSize(true)

    }



}

    }

               }
               builder.setNegativeButton("Cancel", null)


               val dialogJobtype= builder.create()
               dialogJobtype.show()
               dialogmain.dismiss()
           }



                if(FilterBy[which]=="location"){


                    val builder = AlertDialog.Builder(context)
                    builder.setTitle("Choose location to be filter :")

// Add a checkbox list
                    val Location= arrayOf("penang","Kuala Lumpur","Perak")
//                  var checkedLocation2 =checkedLocation
                    builder.setMultiChoiceItems(Location, checkedLocation) { dialogLocation, locationSelected, isChecked ->

                    //Location[jobtypeSelected] -> means
//                   checkedLocation[0]->false , checkedLocation -> false  false false
                    }


                    builder.setPositiveButton("OK") { dialogLocation,locationSelected ->

                        myRef.get()
                            .addOnSuccessListener {
                                    all->
//                                if(!checkedItems[0] && !checkedItems[1] && !checkedItems[2]){
//
//                                    for (i in  all.children) {
//                                        JobList += JobList(
//                                            i.child("id").value.toString(),
//                                            "a.png",
//                                            i.child("pos").value.toString(),
//                                            i.child("compName").value.toString(),
//                                            i.child("compLocation").value.toString(),
//                                            i.child("date").value.toString()
//                                        )
//
//
//                                        var myAdapter = JobAdapter(JobList, this)
//                                        myRecyclerView.adapter = myAdapter
//
//                                        myRecyclerView.layoutManager = LinearLayoutManager(activity)
//                                        //  myRecyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
////      myRecyclerView.layoutManager = GridLayoutManager(this,2)
//                                        myRecyclerView.setHasFixedSize(true)
//
//                                    }
//
//                                }
                                if(checkedLocation[0] && !checkedLocation[1] && !checkedLocation[2]){
                                    JobList.clear()
                                    for (i in  all.children) {

                                        if (i.child("compLocation").value.toString()=="penang"){

                                            JobList += JobList(
                                                i.child("id").value.toString(),
                                                "a.png",
                                                i.child("pos").value.toString(),
                                                i.child("compName").value.toString(),
                                                i.child("compLocation").value.toString(),
                                                i.child("date").value.toString()
                                            )
                                        }



                                        var myAdapter = JobAdapter(JobList, this)
                                        myRecyclerView.adapter = myAdapter

                                        myRecyclerView.layoutManager = LinearLayoutManager(activity)
                                        //  myRecyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
//      myRecyclerView.layoutManager = GridLayoutManager(this,2)
                                        myRecyclerView.setHasFixedSize(true)

                                    }



                                }

                            }

                    }
                    builder.setNegativeButton("Cancel"){ dialogLocation,locationSelected ->

//                        checkedLocation= checkedLocation2
                    }


                    val dialogLocation= builder.create()
                    dialogLocation.show()
                    dialogmain.dismiss()
                }


            }
            builder.setNegativeButton("Cancel"){  dialogJobtype,jobtypeSelected ->


            }

            val dialogmain = builder.create()
            dialogmain.show()


        }



//tes.text = "11"
//
// myRef.get()
//         .addOnSuccessListener {
//                all->for(i in all.children)
//        {
//
//
//          JobList+=JobList(i.child("id").value.toString() ,"a.png",i.child("pos").value.toString(),i.child("compName").value.toString(),i.child("compLocation").value.toString(),i.child("date").value.toString())
//            var myAdapter= JobAdapter(JobList,this)
//            myRecyclerView.adapter=myAdapter
//
//            myRecyclerView.layoutManager = LinearLayoutManager(activity)
//            //  myRecyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
////      myRecyclerView.layoutManager = GridLayoutManager(this,2)
//            myRecyclerView.setHasFixedSize(true)
////        Toast.makeText(activity, i.value.toString(), Toast.LENGTH_SHORT).show()
////        Toast.makeText(activity, "2", Toast.LENGTH_SHORT).show()
////        Toast.makeText(activity, i.child("pos").value.toString(), Toast.LENGTH_SHORT).show()
////        for ( k in   i.child("pos").toString())
////        {
////            Toast.makeText(activity,k.toString(), Toast.LENGTH_SHORT).show()
////        }
////    for(k in i.value.toString() ){
////
////       Toast.makeText(activity,k., Toast.LENGTH_SHORT).show()
//////        Toast.makeText(activity, "1", Toast.LENGTH_SHORT).show()
////    }
//
//
//        }
//        }


//}

//            myRef.child("23").get()
//                .addOnSuccessListener {
//                        record->
//
//                    if(record !=null){
//                            tes.text=record.child("pos").value.toString()
////                    Toast.makeText( activity,record.child("pos").value.toString(), Toast.LENGTH_SHORT).show()
//                }
//                    else{
//
//                    tes.text="3"
////                    Toast.makeText(activity, "fail to get", Toast.LENGTH_SHORT).show()
//                }
//
//                }
//                .addOnFailureListener{
//                    tes.text="4"
//                    Toast.makeText(activity, "fail", Toast.LENGTH_SHORT).show()}
////                    if u only myRef.get then will get whole Student database  so not we just one of the child


//btnSave.setOnClickListener(){
//
//}






        return view
    }

//    //enable options menu in this fragment
//    override fun onCreate(savedInstanceState: Bundle?) {
//        setHasOptionsMenu(true)
//        super.onCreate(savedInstanceState)
//    }
//    //inflate the menu
//    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
//        inflater!!.inflate(R.menu.fiter_menu, menu)
//
////        val icon = resources.getDrawable(R.drawable.filter)
////        menu.getItem(0).icon = icon
//        super.onCreateOptionsMenu(menu, inflater)
//    }
//    //handle item clicks of menu
//    override fun onOptionsItemSelected(item: MenuItem): Boolean {
//
//        //get item id to handle item clicks
//        val id = item!!.itemId
//        //handle item clicks
//        if (id == R.id.search){
//            //do your action here, im just showing toast
//            Toast.makeText(activity, "Search", Toast.LENGTH_SHORT).show()
//        }
//        if (id == R.id.filter){
//            //do your action here, im just showing toast
//            Toast.makeText(activity, "Filter", Toast.LENGTH_SHORT).show()
//        }
//        if(id ==R.id.internship){
//            item.isChecked = !item.isChecked
//            if(item.isChecked){
//                jobtype[0] = "Checked"
//                Toast.makeText(activity, "Checked", Toast.LENGTH_SHORT).show()
//
//
//            }
//            else{
//                jobtype[0] = "Unchecked"
//                Toast.makeText(activity, "UnChecked", Toast.LENGTH_SHORT).show()
//
//            }
//
//        }
//        if(id ==R.id.partime){
//            item.isChecked = !item.isChecked
//            if(item.isChecked){
//                jobtype[1] = "Checked"
//                Toast.makeText(activity, "Checked2", Toast.LENGTH_SHORT).show()
//            }
//            else{
//                jobtype[1] = "Unchecked"
//
////                Toast.makeText(activity, jobtype[1], Toast.LENGTH_SHORT).show()
//
//            }
//
//        }
//
//        if(id ==R.id.fulltime){
//            item.isChecked = !item.isChecked
//            if(item.isChecked){
//                jobtype[2] = "Checked"
//                Toast.makeText(activity, "Checked2", Toast.LENGTH_SHORT).show()
//            }
//            else{
//                jobtype[2] = "Unchecked"
//
////                Toast.makeText(activity, jobtype[1], Toast.LENGTH_SHORT).show()
//
//            }
//
//        }
//
//onCreateView(LayoutInflater.from(context),ViewGroup)
//
//        if(jobtype[0] =="Checked" && jobtype[1] =="Unchecked" && jobtype[2]=="Checked"){
//            myRef.get()
//                .addOnSuccessListener {
//                        all->for(i in all.children)
//                {
//
//                    JobList.clear()
//                    if(i.child("jobType").toString()=="internship"||i.child("jobType").toString()=="fullTime" ){
//                        JobList+=JobList(i.child("id").value.toString() ,"a.png",i.child("pos").value.toString(),i.child("compName").value.toString(),i.child("compLocation").value.toString(),i.child("date").value.toString())
//                    }
//
//                    var myAdapter= JobAdapter(JobList,this)
//                    myRecyclerView.adapter=myAdapter
//
//                    myRecyclerView.layoutManager = LinearLayoutManager(activity)
//
//                    myRecyclerView.setHasFixedSize(true)
//
//
//
//                }
//                }
//        }
//        if(jobtype[0] =="Checked" && jobtype[1] =="Checked" && jobtype[2]=="Unchecked"){
//
//        }
//        if(jobtype[0] =="Unchecked" && jobtype[1] =="Checked" && jobtype[2]=="Checked"){
//
//        }
//        if(jobtype[0] =="Checked" && jobtype[1] =="Unchecked" && jobtype[2]=="Unchecked"){
//
//        }
//        if(jobtype[0] =="Unchecked" && jobtype[1] =="Checked" && jobtype[2]=="Unchecked"){
//
//        }
//
//        if(jobtype[0] =="Unchecked" && jobtype[1] =="Unchecked" && jobtype[2]=="Checked"){
//
//        }
//
//
//
//
//
//        return super.onOptionsItemSelected(item)
//    }



    override fun itemClick(position: Int) {
        val selectStudent = JobList[position]

        Toast.makeText(activity, selectStudent.pos, Toast.LENGTH_SHORT).show()
        val intent = Intent(activity,ViewJobDetails::class.java)
        intent.putExtra("Job_Id",selectStudent.id)

        startActivity(intent)
    }


}