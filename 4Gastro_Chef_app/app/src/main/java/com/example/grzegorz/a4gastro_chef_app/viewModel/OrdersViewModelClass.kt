package com.example.grzegorz.a4gastro_chef_app.viewModel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import com.example.grzegorz.a4gastro_chef_app.model.Model
import com.example.grzegorz.a4gastro_chef_app.model.OrderDetailData

class OrdersViewModelClass: ViewModel(), OrdersViewModel {

    override fun getOrdersListLiveData() = Model.getOrdersListLiveData()

}
