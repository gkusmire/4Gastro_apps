package com.example.grzegorz.a4gastro_waiter_app.viewModel

import android.arch.lifecycle.LiveData
import com.example.grzegorz.a4gastro_waiter_app.model.OrderDetailData

interface ReadyOrdersViewModel {

    fun getReadyOrdersListLiveData(): LiveData<List<OrderDetailData>>

}
