package com.example.grzegorz.a4gastro_waiter_app.view

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.grzegorz.a4gastro_waiter_app.R
import com.example.grzegorz.a4gastro_waiter_app.model.DishData
import com.example.grzegorz.a4gastro_waiter_app.viewModel.*
import kotlinx.android.synthetic.main.activity_add_dish.*

class AddDishActivity : AppCompatActivity() {
    private val viewModel: NewOrderViewModelClass by lazy { ViewModelProviders.of(this).get(NewOrderViewModelClass::class.java) }
    private val adapter by lazy { DishesListAdapter(emptyList(), this::selectDish) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_dish)

        init()
        orderListRecyclerView.adapter = adapter
    }

    private fun init() {
        if(intent.extras == null) return

        viewModel.getNumberData().observe(this, Observer(this::updateNumberTextView))
        viewModel.getChangeDishButtonText().observe(this, Observer(this::changeDishSortButtonText))
        viewModel.setSortOfDishes(this.intent.getIntExtra("foodType", 999))
        viewModel.getDishList().observe(this, Observer(this::updateDishList))
    }

    private fun changeDishSortButtonText(str: String?) {

        if(str == null) return
        switchButton.text = str
    }

    fun updateDishList(list: List<DishData>?) {

        if(list == null) return

        adapter.items = list
        adapter.notifyDataSetChanged()
    }

    private fun updateNumberTextView(str: String?) {

        if(str == null) return

        numberTextView.text = str
    }

    fun incrementNumber(v: View) {
        viewModel.incrementNumber()
    }

    fun decrementNumber(v: View) {
        viewModel.decrementNumber()
    }

    private fun selectDish(dish: DishData) {

        viewModel.selectDish(dish, descriptionEditText.text.toString())

        val intent = Intent(this, NewOrderActivity::class.java)
        startActivity(intent)
    }

    fun changeDishes(v: View) {
        viewModel.changeSortOfDishes()
    }

    fun cancelAdding(v: View) {
        val intent = Intent(this, NewOrderActivity::class.java)
        startActivity(intent)
    }
}
