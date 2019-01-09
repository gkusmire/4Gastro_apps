package com.example.grzegorz.a4gastro_chef_app.model

import java.io.Serializable

data class OrderDetailData(val uid: String? = null, val tableID: String? = null, val startTime: String? = null, var finishTime: String? = null) :
    Serializable {
}