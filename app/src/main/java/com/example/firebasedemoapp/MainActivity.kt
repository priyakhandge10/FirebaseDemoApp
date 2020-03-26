package com.example.firebasedemoapp

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    private var mFirebaseAnalytics: FirebaseAnalytics? = null
    private  var mAuth:FirebaseAuth?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);
        mAuth= FirebaseAuth.getInstance()
    }

    fun buLoginEvent(view: View) {
        val email = etEmail.text.toString()
        val password = etPassword.text.toString()
        LoginToFirebase(email, password)
    }

    fun LoginToFirebase(email:String, password:String) {
        mAuth!!.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    Toast.makeText(applicationContext, "successful login", Toast.LENGTH_LONG).show()
                    val currentUser = mAuth!!.currentUser
                    Log.d("Login", currentUser!!.uid)
                } else {
                    Toast.makeText(applicationContext, "fail login", Toast.LENGTH_LONG).show()
                }
            }

    }
        override fun onStart() {
            super.onStart()
            val currentUser = mAuth!!.currentUser
            if (currentUser!=null){
                val intent= Intent(this,control::class.java)
                startActivity(intent)
            }
        }
    }



