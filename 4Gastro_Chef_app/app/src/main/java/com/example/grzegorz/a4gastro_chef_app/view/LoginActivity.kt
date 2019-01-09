package com.example.grzegorz.a4gastro_chef_app.view

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.grzegorz.a4gastro_chef_app.R
import com.example.grzegorz.a4gastro_chef_app.model.Model
import com.example.grzegorz.a4gastro_chef_app.model.OrderData
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    private val mAuth = FirebaseAuth.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

    }

    fun loginUser(v: View) {

        val email = login_email_input.text.toString()
        val password = login_password_input.text.toString()

        if(!isEmailCorrect(email) || !isPasswordCorrect(password)) {

            Toast.makeText(this, "Nieprawidłowe dane", Toast.LENGTH_SHORT).show()
            return
        }

        mAuth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {

                    Toast.makeText(this, "Zalogowany", Toast.LENGTH_SHORT).show()
                    val intent = Intent(this, OrderListActivity::class.java)
                    startActivity(intent)
                } else {
                    Toast.makeText(this, "Błędny email bądź hasło", Toast.LENGTH_SHORT).show()
                }
            }
    }

    private fun isPasswordCorrect(password: String?): Boolean {

        if(password == null)
            return false

        if(password.length < 6)
            return false
        return true
    }

    private fun isEmailCorrect(email: String?): Boolean {

        if(email == null)
            return false

        if(!email.contains('@'))
            return false
        return true
    }
}
