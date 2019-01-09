package com.example.grzegorz.a4gastro_waiter_app.viewModel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.example.grzegorz.a4gastro_waiter_app.model.DishData
import com.example.grzegorz.a4gastro_waiter_app.model.FoodType
import com.example.grzegorz.a4gastro_waiter_app.model.Model

class NewOrderViewModelClass : ViewModel(), NewOrderViewModel, AddDishViewModel {

    private val nbOfDishesLiveData = MutableLiveData<String>()
    private val dishListLiveData = MutableLiveData<List<DishData>>()
    private val switchButtonTextLiveData = MutableLiveData<String>()
    private var foodType: FoodType = FoodType.DISH
    private var number: Int = 1

    override fun getTableNumberLiveData(): LiveData<String> = Model.getActualTableNumber()

    override fun confirmOrder() {

        Model.addActualOrderList()
    }

    override fun getOrderList(): LiveData<List<Order>> = Model.getActualOrderListLiveData()

    override fun cancelOrder() {

        Model.cancelActualOrder()
    }

    override fun setTableID(id: String?) {

        if(id == null) return

        Model.setTableID(id)
    }

    //*******************************************

    override fun getDishList(): LiveData<List<DishData>> = dishListLiveData

    override fun getNumberData(): LiveData<String> {

        number = 1
        nbOfDishesLiveData.value = number.toString()
        return nbOfDishesLiveData
    }

    override fun incrementNumber() {

        number += 1
        nbOfDishesLiveData.value = number.toString()
    }

    override fun decrementNumber() {

        if(number < 2) return

        number -= 1
        nbOfDishesLiveData.value = number.toString()
    }

//    override fun getNbOfDishesLiveData() = nbOfDishesLiveData

    override fun changeSortOfDishes() {
        if(foodType == FoodType.DRINK) {
            foodType = FoodType.DISH
            dishListLiveData.value = Model.getDishesLiveData().value
            switchButtonTextLiveData.value = "BAR"
        } else {
            foodType = FoodType.DRINK
            dishListLiveData.value = Model.getDrinksLiveData().value
            switchButtonTextLiveData.value = "KUCHNIA"
        }
    }

    override fun getChangeDishButtonText(): LiveData<String> = switchButtonTextLiveData

    override fun setSortOfDishes(dishEnumVal: Int) {

        if(FoodType.DISH.ordinal == dishEnumVal) {
            foodType = FoodType.DISH
            dishListLiveData.value = Model.getDishesLiveData().value
            switchButtonTextLiveData.value = "BAR"
        } else {
            foodType = FoodType.DRINK
            dishListLiveData.value = Model.getDrinksLiveData().value
            switchButtonTextLiveData.value = "KUCHNIA"
        }
    }

    override fun selectDish(dish: DishData, description: String) {

        Model.selectDish(dish, description, number)
    }
}


