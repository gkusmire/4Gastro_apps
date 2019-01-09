package com.example.grzegorz.a4gastro_chef_app.viewModel

import android.arch.lifecycle.LiveData
import com.example.grzegorz.a4gastro_chef_app.model.OrderDetailData

interface OrdersViewModel {
    fun getOrdersListLiveData(): LiveData<List<OrderDetailData>>

}
