package com.example.grzegorz.a4gastro_waiter_app.model

import java.io.Serializable

data class TableData(val uid: String? = null, val description: String? = null, val startTime: String? = null) :Serializable{

    companion object Factory {
        fun create(): TableData = TableData()
    }
}