package com.example.grzegorz.a4gastro_waiter_app.model

import java.io.Serializable

data class OrderDetailData(val uid: String? = null, var tableID: String? = null, val startTime: String? = null, val finishTime: String? = null) :Serializable {
}