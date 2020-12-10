package com.example.tugasbesarrpl

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputLayout
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_edit_profile.*

class EditProfile : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    private lateinit var database: DatabaseReference

    private lateinit var editname: TextInputLayout
    private lateinit var editphone: TextInputLayout
    private lateinit var editaddress: TextInputLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_profile)

        auth = FirebaseAuth.getInstance()
        database = Firebase.database.reference

        editname = findViewById(R.id.editprof_nama)
        editphone = findViewById(R.id.editprof_phone)
        editaddress = findViewById(R.id.editprof_address)

        btn_saveeditprof.setOnClickListener {
            if (auth.currentUser != null) {
                val database = FirebaseDatabase.getInstance()
                val editname = editname.editText?.text.toString().trim()
                val editphone = editphone.editText?.text.toString().trim()
                val editaddress = editaddress.editText?.text.toString().trim()
                val myref = database.getReference("Users").child(auth.currentUser!!.uid)

                if(editname.isNotEmpty()){
                    myref.child("name").setValue(editname)
                }
                if(editphone.isNotEmpty()){
                    myref.child("phone").setValue(editphone)
                }
                if(editaddress.isNotEmpty()){
                    myref.child("address").setValue(editaddress)
                }
                Toast.makeText(
                    this,
                    "Profil berhasil diedit",
                    Toast.LENGTH_SHORT
                ).show()
                startActivity(Intent(applicationContext, ProfileActivity::class.java))
                finish()
            } else {}
        }
    }

    fun backprofile(view: View)   {
        startActivity(Intent(applicationContext, ProfileActivity::class.java))
        finish()
    }

    override fun onBackPressed() {
        startActivity(Intent(applicationContext, MainActivity::class.java))
        finish()
    }
}