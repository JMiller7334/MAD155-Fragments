package com.example.mad155_fragments

//Notes - Fragments are contained in the package I made called: Fragments
//1. Comm1 is needed for fragments. Created > New > Java class > Interface
//2. need a sceen element of Frame layout to be added: this holds fragments so they can be loaded
//3 check the main activity xml to see how the frame layout is setup

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import com.example.mad155_fragments.Fragments.Fragment1
import com.example.mad155_fragments.Fragments.Fragment2

class MainActivity : AppCompatActivity(), Comm1 { //make sure to include comm1 to code, context actions to fix errors can work here(generate func)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val button1 = findViewById<Button>(R.id.button1)
        val button2 = findViewById<Button>(R.id.button2)

        val frag =
            com.example.mad155_fragments.Fragments.Fragment1() //this is how intially located my fragment. Gonna go with the 2nd method.
        // fragments didn't show as below w/o error because they are imported above - via imports.

        val frag1 = Fragment1() //note this is a class
        val frag2 = Fragment2()

        //This is what pulls the fragment into view
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.fragment_container, frag1)
            commit()
        }

        button1.setOnClickListener {
            supportFragmentManager.beginTransaction().apply {
                replace(R.id.fragment_container, frag1)
                addToBackStack(null)
                commit()
            }

        }

        button2.setOnClickListener {
            val textfield = findViewById<EditText>(R.id.frag1Datafield)
            frag2.arguments = passTheData(textfield.text.toString())//calls the function and passes the value to it

            supportFragmentManager.beginTransaction().apply {
                replace(R.id.fragment_container, frag2)
                addToBackStack(null)
                commit()
            }
        }
    }

    override fun passTheData(text2Send: String): Bundle {
        val data2Send = Bundle()
        data2Send.putString("textA2B", text2Send)

        return data2Send
    }
}