package com.example.sampleprojectb

import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController

class FragmentAdd : Fragment() {

    private lateinit var mUserViewModel: UserViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_add, container, false)

        mUserViewModel = ViewModelProvider(this).get(UserViewModel::class.java)
3

        val add_btn = view.findViewById<Button>(R.id.addButton)


        add_btn.setOnClickListener{

            insertDataToDatabase(view)

        }

        return view
    }

    private fun insertDataToDatabase(view: View) {

        val fName = view.findViewById<EditText>(R.id.firstName).text.toString()
        val lName = view.findViewById<EditText>(R.id.lastName).text.toString()
        val age = view.findViewById<EditText>(R.id.age).text.toString()

        if(inputCheck(fName, lName, age))
        {
            //create user object
            val user = User(0, fName, lName, Integer.parseInt(age.toString()))

            //add data to database
            mUserViewModel.addUser(user)
            Toast.makeText(requireContext(), "Successfully added", Toast.LENGTH_LONG).show()
            //Navigate Back
            findNavController().navigate(R.id.action_fragmentAdd_to_fragmentList)
        }
        else{
            Toast.makeText(requireContext(), "please fill all fields", Toast.LENGTH_LONG).show()
        }

    }

    private fun inputCheck(firstName: String, lastName: String, age: String) : Boolean
    {
        return !(TextUtils.isEmpty(firstName) && TextUtils.isEmpty(lastName) && TextUtils.isEmpty(age))
    }

}