package com.example.grzegorz.a4gastro_chef_app.model

import java.io.Serializable

data class OrderData(var uid: String? = null, var dishID: String? = null, var number: Int? = null, var orderID: String? = null, var description: String? = null):
    Serializable {

}
