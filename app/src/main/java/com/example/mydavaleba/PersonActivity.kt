package com.example.mydavaleba

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log.d
import android.view.View
import android.view.animation.AnimationUtils
import com.example.mydavaleba.databinding.ActivityPersonBinding
import java.util.ArrayList

class PersonActivity : AppCompatActivity() {

    lateinit var binding: ActivityPersonBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_person)

        binding = ActivityPersonBinding.inflate(layoutInflater)
        setContentView(binding.root)

        init()
        animation()
    }

    private fun init() {

        val personInfo: ArrayList<String>? = intent.extras?.getStringArrayList("personInfo")

        binding.nameTextView.text = personInfo?.get(0)
        binding.lastNameTextView.text = personInfo?.get(1)
        binding.eMailTextView.text = personInfo?.get(2)
        binding.ageTextView.text = personInfo?.get(3)

        binding.backButton.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(intent)
        }
    }

    private fun animation () {
        val sideToMid = AnimationUtils.loadAnimation(this, R.anim.side_to_mid)
        binding.nameTextView.startAnimation(sideToMid)
        binding.lastNameTextView.startAnimation(sideToMid)
        binding.eMailTextView.startAnimation(sideToMid)
        binding.ageTextView.startAnimation(sideToMid)

        val topToBottom = AnimationUtils.loadAnimation(this, R.anim.top_to_bottom)
        binding.personInfo.startAnimation(topToBottom)

        val fadeIn = AnimationUtils.loadAnimation(this, R.anim.fade_in)
        binding.backButton.startAnimation(fadeIn)
    }

}