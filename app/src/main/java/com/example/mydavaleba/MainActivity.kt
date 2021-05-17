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
    var active: Int = 0
    var deleted: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.addButton.setOnClickListener {

            val input = listOf<Any>(

                binding.editTextTextPersonName.text.toString(),
                binding.editTextTextPersonName2.text.toString(),
                binding.editTextTextEmailAddress.text.toString(),
                binding.ageEditText.text.toString()
            )

            if (personList.isNotEmpty() && input in personList) {

                binding.succesTextView.visibility = View.INVISIBLE
                binding.failedTextView.visibility = View.INVISIBLE
                d("tag", "$personList")

                Toast.makeText(this,"User already exists",Toast.LENGTH_SHORT).show()
                binding.failedTextView.visibility = View.VISIBLE

            }
            else {

                binding.succesTextView.visibility = View.INVISIBLE
                binding.failedTextView.visibility = View.INVISIBLE

                personList.add(input)

                Toast.makeText(this,"User added successfully",Toast.LENGTH_SHORT).show()
                binding.succesTextView.visibility = View.VISIBLE
                active += 1
                binding.active.text = active.toString()
                d("tag", "$personList")

            }

        }

        binding.removeButton.setOnClickListener {

            val input = listOf<Any>(
                binding.editTextTextPersonName.text.toString(),
                binding.editTextTextPersonName2.text.toString(),
                binding.editTextTextEmailAddress.text.toString(),
                binding.ageEditText.text.toString()
            )


            if (personList.isNotEmpty() && input in personList) {

                personList.remove(input)
                deleted += 1
                active -= 1
                binding.active.text = active.toString()
                binding.deleted.text = deleted.toString()

            }
        }

    }
}