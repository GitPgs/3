package com.example.assignment




import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import kotlin.math.acos


class JobAdapter(private val JobList : List<JobList>,
                 private val listener: OnItemClickListener)







    : RecyclerView.Adapter<JobAdapter.MyViewHolder> () {
    //:useradapter is type of recycler view


    inner class MyViewHolder(view: View)
        : RecyclerView.ViewHolder(view), View.OnClickListener{
        //viewholder which is we create one profile_view


        val imgLogo:ImageView =view.findViewById(R.id.compIcon)
        val jobPos: TextView = view.findViewById(R.id.jobPos)
        val location: TextView = view.findViewById(R.id.location)
        val companyName:TextView  = view.findViewById(R.id.comName)
        val date:TextView =view.findViewById(R.id.date)
        val btnSave:ImageButton =view.findViewById(R.id.saveBut)



        init {
            view.setOnClickListener(this)
        }

        override fun onClick(v: View?) {

            val position = bindingAdapterPosition
            if(position != RecyclerView.NO_POSITION){
                listener.itemClick(position)
            }
        }
    }


    interface OnItemClickListener{
        fun itemClick(position: Int)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.job_view, parent, false)

        return MyViewHolder(itemView)
    }
    private lateinit var  database:FirebaseDatabase




    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        //while user click on recycler view
        //position is current user see  on recycle view if current user see no3 column then will be display no3 record
        val currentUser = JobList[position]

//        holder.tvId.text = currentStudent.id
        holder.jobPos.text = currentUser.pos
        holder.companyName.text = currentUser.compName
        holder.location.text = currentUser.compLocation
        holder.date.text = currentUser.dateCreated
//        holder.id.text=currentUser.id

        database = FirebaseDatabase.getInstance()
        val bookRef = database.getReference("Bookmark")

        holder.btnSave.setOnClickListener(){
            val selectJob = JobList[position]
            var b =0
            var a=""

            bookRef.get()
                .addOnSuccessListener {
                    result->for(i in result.children){
//                        a =i.child("bookmarkId").value.toString()
                    a=i.key.toString()


                    }
                    b =a.toInt()
                    val jobSave = Bookmark("23",selectJob.id.toString())

                    bookRef.child((b+1).toString()).setValue(jobSave)


                }



        }

        val imgRef= FirebaseStorage.getInstance().reference.child(currentUser.companyLogo)


        imgRef.downloadUrl
            .addOnSuccessListener {

                Glide.with(holder.imgLogo.context)
                    .load(it.toString())
                    .into(holder.imgLogo)

            }
            //download pdf also can use this downloadURL
            //textview clickable such as <a> put in recycler view

            .addOnFailureListener {

            }
        //display to UI
//        holder.tvProgramme.text = currentStudent.programme

//        if (currentStudent.gender == "F"){
//            holder.imgPhoto.setImageResource(R.drawable.ic_famale)
//        }else{
//            holder.imgPhoto.setImageResource(R.drawable.ic_male)
//        }

    }

    override fun getItemCount(): Int {
        return JobList.size
    }





}