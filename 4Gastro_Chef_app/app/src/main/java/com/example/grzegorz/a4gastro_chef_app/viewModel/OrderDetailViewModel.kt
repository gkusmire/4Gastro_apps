package com.example.grzegorz.a4gastro_chef_app.viewModel

import android.arch.lifecycle.LiveData
import com.example.grzegorz.a4gastro_chef_app.viewModel.Order

interface OrderDetailViewModel {

    fun setOrderDetailID(stringExtra: String?)
    fun getOrderListLiveData(): LiveData<List<Order>>
    fun realiseOrder()

}
