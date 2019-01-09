package com.example.grzegorz.a4gastro_waiter_app.view

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.grzegorz.a4gastro_waiter_app.R
import kotlinx.android.synthetic.main.activity_add_table_number.*

class AddTableNumberActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_table_number)
    }

    fun okButtonClick(v: View) {

        val intent = Intent(this, TableDetailsActivity::class.java)
            .putExtra("tableNumber", numberEditText.text.toString())
        startActivity(intent)
    }

    fun cancelButtonClick(v: View) {

        val intent = Intent(this, TablesListActivity::class.java)
        startActivity(intent)
    }
}
