package com.satyamthakur.firebaseauthentication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.satyamthakur.firebaseauthentication.databinding.ActivitySignUpBinding

class SignUpActivity : AppCompatActivity() {

    private lateinit var bind: ActivitySignUpBinding
    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        bind = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(bind.root)

        firebaseAuth = FirebaseAuth.getInstance()

        bind.continueButton.setOnClickListener {
            val email = bind.etEmail.text.toString()
            val pass = bind.etPass.text.toString()
            val confirmPass = bind.etPassAgain.text.toString()

            if (email.isNotEmpty() && pass.isNotEmpty() && confirmPass.isNotEmpty()) {
                if (pass.equals(confirmPass)) {
                    firebaseAuth.createUserWithEmailAndPassword(email, pass).addOnCompleteListener {
                        if (it.isSuccessful) {
                            gotoSignInActivity()
                        } else {
                            Toast.makeText(this@SignUpActivity,
                                it.exception.toString(), Toast.LENGTH_SHORT).show()
                        }
                    }
                } else {
                    Toast.makeText(this@SignUpActivity,
                        "Retype Password Not Same!", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this@SignUpActivity,
                    "Fields are empty!", Toast.LENGTH_SHORT).show()
            }
        }

        bind.signInTextView.setOnClickListener {
            gotoSignInActivity()
        }


    }

    fun gotoSignInActivity() {
        val intent = Intent(this@SignUpActivity, SignInActivity::class.java)
        startActivity(intent)
        finish()
    }

}