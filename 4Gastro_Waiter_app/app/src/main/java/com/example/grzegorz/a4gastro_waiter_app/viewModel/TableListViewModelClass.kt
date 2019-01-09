package com.example.grzegorz.a4gastro_waiter_app.viewModel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.example.grzegorz.a4gastro_waiter_app.model.Model
import com.example.grzegorz.a4gastro_waiter_app.model.TableData

class TableListViewModelClass: ViewModel(), TableListViewModel {
//    override fun add(tableData: TableData) {
//        val list2 = mutableListOf<TableData>()
////        list2.add(TableData(121, "JJJJJ"))
////        list2.add(tableData)
//        tablesLiveData.value = list2
//    }

    private val tablesLiveData = Model.getTablesLiveData()//MutableLiveData<List<TableData>>()
//    val table = TableData("11", "Sample")
//    val list = mutableListOf<TableData>()
//
//    override fun a() {
//        list.add(TableData("333", "Brzoza"))
//        list.add(table)
//        tablesLiveData.value = list
//    }


    override fun getTablesList(): LiveData<List<TableData>> = tablesLiveData
}