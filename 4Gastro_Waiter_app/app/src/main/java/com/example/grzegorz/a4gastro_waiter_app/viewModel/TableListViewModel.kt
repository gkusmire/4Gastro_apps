package com.example.grzegorz.a4gastro_waiter_app.viewModel

import android.arch.lifecycle.LiveData
import com.example.grzegorz.a4gastro_waiter_app.model.TableData

interface TableListViewModel {

    fun getTablesList(): LiveData<List<TableData>>
   // fun a()
   // fun add(tableData: TableData)
}