package com.example.grzegorz.a4gastro_waiter_app.viewModel

import android.arch.lifecycle.LiveData
import com.example.grzegorz.a4gastro_waiter_app.model.DishData

interface AddDishViewModel {

    fun getDishList(): LiveData<List<DishData>>
    fun getNumberData(): LiveData<String>
    fun incrementNumber()
    fun decrementNumber()
    fun changeSortOfDishes()
    fun getChangeDishButtonText(): LiveData<String>
    fun setSortOfDishes(dishEnumVal: Int)
    fun selectDish(dish: DishData, description: String)
}