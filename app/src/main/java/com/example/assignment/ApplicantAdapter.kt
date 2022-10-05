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


class ApplicantAdapter(private val AppList : List<ApplicantList>,
                       private val listener: OnItemClickListener)







    : RecyclerView.Adapter<ApplicantAdapter.MyViewHolder> () {
    //:useradapter is type of recycler view


    inner class MyViewHolder(view: View)
        : RecyclerView.ViewHolder(view), View.OnClickListener{
        //viewholder which is we create one profile_view


        val imgLogo:ImageView =view.findViewById(R.id.appImage)
        val name: TextView = view.findViewById(R.id.appName)
        val age: TextView = view.findViewById(R.id.appAge)
        val eduIn:TextView  = view.findViewById(R.id.edu)

        val btnView:Button =view.findViewById(R.id.btnView)



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
            .inflate(R.layout.applicant_view, parent, false)

        return MyViewHolder(itemView)
    }





    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        //while user click on recycler view
        //position is current user see  on recycle view if current user see no3 column then will be display no3 record
        val currentUser = AppList[position]

//        holder.tvId.text = currentStudent.id
        holder.name.text = currentUser.Appname
        holder.age.text = currentUser.Appage
        holder.eduIn.text = currentUser.Appedu

//        holder.id.text=currentUser.id



//        holder.btnStatus.setOnClickListener(){
//            val selectJob = HisList[position]
//
//
//
//        }

//        val imgRef= FirebaseStorage.getInstance().reference.child(currentUser.companyLogo)
//
//
//        imgRef.downloadUrl
//            .addOnSuccessListener {
//
//                Glide.with(holder.imgLogo.context)
//                    .load(it.toString())
//                    .into(holder.imgLogo)
//
//            }
//            //download pdf also can use this downloadURL
//            //textview clickable such as <a> put in recycler view
//
//            .addOnFailureListener {
//
//            }
        //display to UI
//        holder.tvProgramme.text = currentStudent.programme

//        if (currentStudent.gender == "F"){
//            holder.imgPhoto.setImageResource(R.drawable.ic_famale)
//        }else{
//            holder.imgPhoto.setImageResource(R.drawable.ic_male)
//        }

    }

    override fun getItemCount(): Int {
        return AppList.size
    }





}