package com.example.sampleprojectb

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import androidx.compose.ui.layout.Layout
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet.Constraint
import androidx.fragment.app.ListFragment
import androidx.navigation.findNavController
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView


class ListAdapter: RecyclerView.Adapter<com.example.sampleprojectb.ListAdapter.MyViewHolder>() {

    var OnItemClick: ((User) -> Unit)? = null

    private var userList = emptyList<User>()

    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.custom_raw, parent, false))
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = userList[position]

        holder.itemView.findViewById<TextView>(R.id.id_txt).text = currentItem.id.toString()
        holder.itemView.findViewById<TextView>(R.id.firstName_txt).text = currentItem.firstName
      //  holder.itemView.findViewById<TextView>(R.id.lastName_txt).text = currentItem.lastName
       // holder.itemView.findViewById<TextView>(R.id.age_txt).text= currentItem.age.toString()

        holder.itemView.setOnClickListener {  }
        holder.itemView.findViewById<ConstraintLayout>(R.id.rawLayout).setOnClickListener {

                val action = FragmentListDirections.actionFragmentListToUpdateFragmentt3(currentItem)
                holder.itemView.findNavController().navigate(action)


        }

    }

    override fun getItemCount(): Int {
        return userList.size
    }

    fun setData(user: List<User>)
    {
        this.userList = user
        notifyDataSetChanged()
    }


}