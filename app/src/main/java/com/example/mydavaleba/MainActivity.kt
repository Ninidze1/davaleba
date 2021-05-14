package com.example.mydavaleba

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log.d
import android.view.View
import android.widget.Toast
import com.example.mydavaleba.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var personList = ArrayList<Any>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.addButton.setOnClickListener {

            var input = listOf<Any>(binding.editTextTextPersonName.text,
                binding.editTextTextPersonName2.text,
                binding.editTextTextEmailAddress.text,
                binding.ageEditText.text)
                personList.add(input)

            if (personList.isEmpty() && input in personList) {

                binding.succesTextView.visibility = View.INVISIBLE
                binding.failedTextView.visibility = View.INVISIBLE
                d("tag", "${personList}")

                Toast.makeText(this,"User already exists",Toast.LENGTH_SHORT).show()
                binding.failedTextView.visibility = View.VISIBLE


            }
            else {

                binding.succesTextView.visibility = View.INVISIBLE
                binding.failedTextView.visibility = View.INVISIBLE

                val input = listOf<Any>(binding.editTextTextPersonName.text,
                    binding.editTextTextPersonName2.text,
                    binding.editTextTextEmailAddress.text,
                    binding.ageEditText.text)

                personList.add(input)

                Toast.makeText(this,"User added successfully",Toast.LENGTH_SHORT).show()
                binding.succesTextView.visibility = View.VISIBLE
                d("tag", "$personList")

            }

        }

        binding.removeButton.setOnClickListener {
            if (personList.isNotEmpty()) {

            }
        }

    }
}