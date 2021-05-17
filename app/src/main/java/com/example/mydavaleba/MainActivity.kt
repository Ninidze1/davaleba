package com.example.mydavaleba

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log.d
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.Toast
import com.example.mydavaleba.databinding.ActivityMainBinding
import kotlin.properties.Delegates

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var personList = mutableListOf<Person>()
    private var eMails = mutableSetOf<String>()
    private var active: Int = 0
    private var deleted: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        init()
        animate()
    }

    private fun init() {

        binding.addButton.setOnClickListener {
            if (inputChecker()) {
                addUser()
            } else { Toast.makeText(this,"fill the fields to add",Toast.LENGTH_SHORT).show() }
        }

        binding.removeButton.setOnClickListener {
            if (inputChecker()) {
                removeUser()
            } else { Toast.makeText(this,"fill the fields to remove",Toast.LENGTH_SHORT).show() }
        }

        binding.updateButton.setOnClickListener {
            if (inputChecker()) {
                updateUser()
            } else { Toast.makeText(this,"fill the fields to update",Toast.LENGTH_SHORT).show() }
        }

        binding.detailsButton.setOnClickListener {
            if (inputChecker()) {
                openPersonActivity()
            } else { Toast.makeText(this,"fill the fields to see details",Toast.LENGTH_SHORT).show() }
        }
    }

    private fun addUser() {

        val newPerson = Person(
                binding.nameEditText.text.trim().toString(),
                binding.lastNameEditText.text.trim().toString(),
                binding.eMailEditText.text.trim().toString(),
                binding.ageEditText.text.trim().toString()
        )

// რადგან eMail უნიკალურია იდენტიფიკატორია მისი განმეორებით შეყვანის შემთხვევაში ახალი მომხამრებელი არ დამატდება
        if (personList.isNotEmpty() && (newPerson in personList || newPerson.eMail in eMails)) {
            d("tag", "$eMails")

            Toast.makeText(this,"User already exists",Toast.LENGTH_SHORT).show()
            failedTempAppearance()
        }
        else {
            personList.add(newPerson)
            eMails.add(newPerson.eMail)
            active += 1
            binding.activeCount.text = active.toString()

            Toast.makeText(this, "User added successfully", Toast.LENGTH_SHORT).show()
            successTempAppearance()
            d("tag", "$personList")
        }
    }

    private fun removeUser() {

        val newPerson = Person(
                binding.nameEditText.text.trim().toString(),
                binding.lastNameEditText.text.trim().toString(),
                binding.eMailEditText.text.trim().toString(),
                binding.ageEditText.text?.trim().toString()
        )

        if (personList.isNotEmpty() && newPerson in personList) {

            personList.remove(newPerson)
            deleted += 1
            active -= 1
            binding.activeCount.text = active.toString()
            binding.deletedCount.text = deleted.toString()
            Toast.makeText(this, "User deleted successful", Toast.LENGTH_SHORT).show()
            successTempAppearance()

        } else {

            Toast.makeText(this, "User doesn't exits", Toast.LENGTH_SHORT).show()
            failedTempAppearance()
        }
    }

    private fun updateUser() {

        val newPerson = Person(
                binding.nameEditText.text.trim().toString(),
                binding.lastNameEditText.text.trim().toString(),
                binding.eMailEditText.text.trim().toString(),
                binding.ageEditText.text?.trim().toString()
        )
        personList.forEach {
            if (newPerson.eMail == it.eMail) {
                d("go", "${personList.indexOf(it)}")
                personList[personList.indexOf(it)] = newPerson
                d("tag", "$personList")
            }
        }
    }

    private fun openPersonActivity() {
        val infoList = mutableListOf(
            binding.nameEditText.text.trim().toString(),
            binding.lastNameEditText.text.trim().toString(),
            binding.eMailEditText.text.trim().toString(),
            binding.ageEditText.text.trim().toString()
        )

        val intent = Intent(this, PersonActivity::class.java)
        intent.putExtra("personInfo", ArrayList(infoList))
        startActivity(intent)
    }

    private fun successTempAppearance() {
        binding.succesTextView.visibility = View.INVISIBLE
        binding.failedTextView.visibility = View.INVISIBLE
        binding.succesTextView.visibility = View.VISIBLE
        Handler().postDelayed({ binding.succesTextView.visibility = View.INVISIBLE }, 4500)
    }

    private fun failedTempAppearance() {
        binding.succesTextView.visibility = View.INVISIBLE
        binding.failedTextView.visibility = View.INVISIBLE
        binding.failedTextView.visibility = View.VISIBLE
        Handler().postDelayed({
            binding.failedTextView.visibility = View.INVISIBLE }, 4500)
    }

    private fun animate() {
        val sideToMid = AnimationUtils.loadAnimation(this, R.anim.side_to_mid)
        binding.nameEditText.startAnimation(sideToMid)
        binding.lastNameEditText.startAnimation(sideToMid)
        binding.ageEditText.startAnimation(sideToMid)
        binding.eMailEditText.startAnimation(sideToMid)

        val topToBottom = AnimationUtils.loadAnimation(this, R.anim.top_to_bottom)
            binding.activeCount.startAnimation(topToBottom)
            binding.deletedCount.startAnimation(topToBottom)
            binding.activeTextView.startAnimation(topToBottom)
            binding.deletedTextView.startAnimation(topToBottom)

        val button1PopUp = AnimationUtils.loadAnimation(this, R.anim.fade_in)
        val button2PopUp = AnimationUtils.loadAnimation(this, R.anim.fade_in_s)
        val button3PopUp = AnimationUtils.loadAnimation(this, R.anim.fade_in_t)
        binding.addButton.startAnimation(button1PopUp)
        binding.removeButton.startAnimation(button2PopUp)
        binding.updateButton.startAnimation(button3PopUp)
        binding.detailsButton.startAnimation(button1PopUp)

    }

    private fun inputChecker(): Boolean {
        return binding.nameEditText.text.isNotEmpty() &&
                binding.lastNameEditText.text.isNotEmpty() &&
                binding.eMailEditText.text.isNotEmpty() &&
                binding.ageEditText.text.isNotEmpty()
    }
}
