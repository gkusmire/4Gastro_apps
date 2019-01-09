package com.example.grzegorz.a4gastro_waiter_app.viewModel

import android.arch.lifecycle.LiveData
import com.example.grzegorz.a4gastro_waiter_app.model.OrderData

interface TableDetailsViewModel {

    fun setTableID(id: String)
    fun generateNewTableID(number: String)
    fun getTableDescription(): LiveData<String>
    fun printBill()
    fun getOrderList(): LiveData<List<Order>>
    fun getTableID(): String
}