package com.example.grzegorz.a4gastro_chef_app.model

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import com.google.firebase.database.*
import java.text.SimpleDateFormat
import java.util.*

object Model {

    private val database: DatabaseReference = FirebaseDatabase.getInstance().reference
    private val orderTODOListLiveData = MutableLiveData<List<OrderDetailData>>()
    private val orderDetailLiveData = MutableLiveData<List<OrderDetailData>>()
    private val ordersLiveData = MutableLiveData<List<OrderData>>()
    private val drinksLiveData = MutableLiveData<List<DishData>>()
    private val dishesLiveData = MutableLiveData<List<DishData>>()
//    private val readyOrdersDetailsLiveData = MutableLiveData<List<OrderDetailData>>()

    init{
        print("AAAAA")
        addOrdersDetailsTableListener()
        addOrdersTableListener()
        addDishesTableListener()
        addDrinksTableListener()
     //   addReadyOrdersDetailsListener()
    }

//    private fun addReadyOrdersDetailsListener() {
//
//        val listener = object : ValueEventListener {
//
//            override fun onCancelled(databaseError: DatabaseError) {
//                println("loadReadyOrders:onCancelled ${databaseError.toException()}")
//            }
//
//            override fun onDataChange(dataSnapshot: DataSnapshot) {
//                val list = mutableListOf<OrderDetailData>()
//                dataSnapshot.children.mapNotNullTo(list) { it.getValue<OrderDetailData>(OrderDetailData::class.java) }
//
//                this@Model.readyOrdersDetailsLiveData.value = list
//            }
//        }
//        database.child(Statics.FIREBASE_READY_ORDER).addValueEventListener(listener)
//    }

    private fun addOrdersDetailsTableListener() {

        val listener = object : ValueEventListener {

            override fun onCancelled(databaseError: DatabaseError) {
                println("loadOrderDetails:onCancelled ${databaseError.toException()}")
            }

            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val list = mutableListOf<OrderDetailData>()
                dataSnapshot.children.mapNotNullTo(list) { it.getValue<OrderDetailData>(OrderDetailData::class.java) }
                this@Model.orderDetailLiveData.value = list
                updateOrderTODOListLiveData()
            }
        }
        database.child(Statics.FIREBASE_ORDER_DETAIL).addValueEventListener(listener)
    }

    private fun updateOrderTODOListLiveData() {

        val list = mutableListOf<OrderDetailData>()

        orderDetailLiveData.value?.forEach {
            if(it.finishTime == "")
                list.add(it)
        }

        orderTODOListLiveData.value = list
    }

    private fun addOrdersTableListener() {

        val listener = object : ValueEventListener {

            override fun onCancelled(databaseError: DatabaseError) {
                println("loadOrders:onCancelled ${databaseError.toException()}")
            }

            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val list = mutableListOf<OrderData>()
                dataSnapshot.children.mapNotNullTo(list) { it.getValue<OrderData>(OrderData::class.java) }

                this@Model.ordersLiveData.value = list
            }
        }
        database.child(Statics.FIREBASE_ORDER).addValueEventListener(listener)
    }

    private fun addDishesTableListener() {

        val listener = object : ValueEventListener {

            override fun onCancelled(databaseError: DatabaseError) {
                println("loadDishes:onCancelled ${databaseError.toException()}")
            }

            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val list = mutableListOf<DishData>()
                dataSnapshot.children.mapNotNullTo(list) { it.getValue<DishData>(DishData::class.java) }

                this@Model.dishesLiveData.value = list
            }
        }
        database.child(Statics.FIREBASE_DISH).addValueEventListener(listener)
    }

    private fun addDrinksTableListener() {

        val listener = object : ValueEventListener {

            override fun onCancelled(databaseError: DatabaseError) {
                println("loadDrinks:onCancelled ${databaseError.toException()}")
            }

            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val list = mutableListOf<DishData>()
                dataSnapshot.children.mapNotNullTo(list) { it.getValue<DishData>(DishData::class.java) }

                this@Model.drinksLiveData.value = list
            }
        }
        database.child(Statics.FIREBASE_DRINK).addValueEventListener(listener)
    }

    fun getOrdersListLiveData(): LiveData<List<OrderDetailData>> = orderTODOListLiveData

    fun removeReadyOrderDetail(orderID: String) {

        val orderDetail = getOrderDetail(orderID)

        val sdf = SimpleDateFormat("hh:mm:ss")
        val finishTime = sdf.format(Date())

        orderDetail.finishTime = finishTime

        val orderDetailRef = database.child(Statics.FIREBASE_ORDER_DETAIL).child(orderID)
        orderDetailRef.setValue(orderDetail)

        val readyOrder = database.child(Statics.FIREBASE_READY_ORDER).push()
        val x = ReadyOrderData(readyOrder.key, orderID)
        readyOrder.setValue(x)
    }

    private fun getOrderDetail(orderID: String): OrderDetailData {

        orderDetailLiveData.value?.forEach {
            if(orderID == it.uid)
                return it
        }
        return OrderDetailData()
    }

    fun getOrdersAssociatedWithOrderDetail(orderID: String): List<OrderData> {

        val list = mutableListOf<OrderData>()

        ordersLiveData.value?.forEach {

            if(orderID == it.orderID)
                list.add(it)
        }
        return list
    }

    fun getDishName(dishID: String?): String? {

        dishesLiveData.value?.forEach {
            if(it.uid == dishID)
                return it.name
        }
        drinksLiveData.value?.forEach {
            if(it.uid == dishID)
                return it.name
        }
        return ""
    }

    fun addOrder(s: String) {

        val newOrderDetail = database.child(Statics.FIREBASE_ORDER_DETAIL).push()
        val orderDetail = OrderDetailData(newOrderDetail.key, "AAA", "BBB", "")
        newOrderDetail.setValue(orderDetail)
    }
}