package com.example.grzegorz.a4gastro_waiter_app.viewModel

import android.arch.lifecycle.LiveData
import com.example.grzegorz.a4gastro_waiter_app.model.OrderData

interface NewOrderViewModel {
    fun confirmOrder()
    fun getOrderList(): LiveData<List<Order>>
    fun cancelOrder()
    fun setTableID(stringExtra: String?)
    fun getTableNumberLiveData(): LiveData<String>
}