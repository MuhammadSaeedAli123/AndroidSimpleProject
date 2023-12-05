package com.example.sampleprojectb

import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.compose.material3.AlertDialog
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import android.app.AlertDialog


class UpdateFragmentt : Fragment() {

    private val args by navArgs<UpdateFragmenttArgs>()

    private lateinit var mUserViewModel: UserViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
         val view = inflater.inflate(R.layout.fragment_update_fragmentt, container, false)

        mUserViewModel = ViewModelProvider(this).get(UserViewModel::class.java)

        view.findViewById<EditText>(R.id.firstName).setText(args.currentUser.firstName)
           view.findViewById<EditText>(R.id.lastName).setText(args.currentUser.lastName)
        view.findViewById<EditText>(R.id.updateCms).setText(args.currentUser.age.toString())

        val add_btn = view.findViewById<Button>(R.id.addButton)


        add_btn.setOnClickListener{

            updateItem(view)

        }


        setHasOptionsMenu(true)





        return view
    }

    private fun updateItem(view: View){


        val fName = view.findViewById<EditText>(R.id.firstName).text.toString()
        val lName = view.findViewById<EditText>(R.id.lastName).text.toString()
        val age = view.findViewById<EditText>(R.id.updateCms).text.toString()



        if(inputCheck(fName, lName, age.toString() ))
        {

            val updatedUser = User(args.currentUser.id, fName, lName,  Integer.parseInt(age.toString()))



            //UPDATE CURRENT USER
            mUserViewModel.updateUser(updatedUser)


            Toast.makeText(requireContext(), "Updated Successfully", Toast.LENGTH_LONG).show()

            //Navigation back
            findNavController().navigate(R.id.action_updateFragment_to_fragmentList)

        }
        else{
            Toast.makeText(requireContext(), "please fill out all fields", Toast.LENGTH_LONG).show()
        }


    }

    private fun inputCheck(firstName: String, lastName: String, age: String) : Boolean
    {
        return !(TextUtils.isEmpty(firstName) && TextUtils.isEmpty(lastName) && TextUtils.isEmpty(age))
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.delete_menu, menu)

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        if(item.itemId == R.id.menu_delete)
        {
            deleteUser()
        }

        return super.onOptionsItemSelected(item)
    }

    private fun deleteUser() {
        val builder = AlertDialog.Builder(requireContext())
        builder.setPositiveButton("Yes"){
            _, _ ->

            mUserViewModel.deleteUser(args.currentUser)
            Toast.makeText(requireContext(), "Successfully deleted ${args.currentUser.firstName}", Toast.LENGTH_SHORT).show()

            findNavController().navigate(R.id.action_updateFragment_to_fragmentList)


        }
        builder.setNegativeButton("No"){
            _, _ ->
        }

        builder.setTitle("Delete ${args.currentUser.firstName}?")
        builder.setMessage("Are u sure u want to delete ${args.currentUser.firstName}?")
        builder.create().show()


    }


}