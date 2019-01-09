package com.example.grzegorz.a4gastro_waiter_app.viewModel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import com.example.grzegorz.a4gastro_waiter_app.model.Model
import com.example.grzegorz.a4gastro_waiter_app.model.OrderDetailData

class ReadyOrdersViewModelClass: ViewModel(), ReadyOrdersViewModel {

    override fun getReadyOrdersListLiveData(): LiveData<List<OrderDetailData>> = Model.getReadyOrdersDetailsLiveData()

}
