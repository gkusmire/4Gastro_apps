package com.example.grzegorz.a4gastro_waiter_app.model

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import com.example.grzegorz.a4gastro_waiter_app.viewModel.Order
import com.google.firebase.database.*
import java.text.SimpleDateFormat
import java.util.*

object Model {
    private val database: DatabaseReference = FirebaseDatabase.getInstance().reference
    private val tablesLiveData = MutableLiveData<List<TableData>>()
    private val drinksLiveData = MutableLiveData<List<DishData>>()
    private val dishesLiveData = MutableLiveData<List<DishData>>()
    private val ordersLiveData = MutableLiveData<List<OrderData>>()
    private val ordersDetailsLiveData = MutableLiveData<List<OrderDetailData>>()
    private val readyOrdersDetailsLiveData = MutableLiveData<List<OrderDetailData>>()

    private val actualTableNumberLiveData = MutableLiveData<String>()
    private val actualOrderListLiveData = MutableLiveData<List<Order>>()
    private val actualOrderList = mutableListOf<Order>()
    private var actualTableID = ""
    private val readyOrders: MutableList<ReadyOrderData> = mutableListOf<ReadyOrderData>()
    private val readyOrdersListKey = mutableListOf<String>()

    init{
       // FirebaseDatabase.getInstance().setPersistenceEnabled(true)
        addTablesTableListener()
        addOrdersDetailsTableListener()
        addOrdersTableListener()
        addDishesTableListener()
        addDrinksTableListener()
        addReadyOrdersDetailsListener()
    }

    private fun addReadyOrdersDetailsListener() {

        val listener = object : ValueEventListener {

            override fun onCancelled(databaseError: DatabaseError) {
                println("loadReadyOrders:onCancelled ${databaseError.toException()}")
            }

            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val list = mutableListOf<OrderDetailData>()
                readyOrdersListKey.clear()
                readyOrders.clear()
                dataSnapshot.children.mapNotNullTo(readyOrders) {
                    it.getValue<ReadyOrderData>(ReadyOrderData::class.java)
                }

                readyOrders.forEach {
                    val readyOrder = getOrderDetailByItsID(it.orderID!!).copy()
                   // readyOrder.tableID = getTable(readyOrder.tableID!!).description.toString()
                    list.add(readyOrder)
                }

                this@Model.readyOrdersDetailsLiveData.value = list
            }
        }
        database.child(Statics.FIREBASE_READY_ORDER).addValueEventListener(listener)
    }

    fun getTablesLiveData(): LiveData<List<TableData>> = tablesLiveData

    fun getDrinksLiveData(): LiveData<List<DishData>> = drinksLiveData

    fun getDishesLiveData(): LiveData<List<DishData>> = dishesLiveData

    fun getOrdersLiveData(): LiveData<List<OrderData>> = ordersLiveData

    fun getOrdersDetailsLiveData(): LiveData<List<OrderDetailData>> = ordersDetailsLiveData

    fun printBill(tableID: String) {

        removeAllMembersAssociatedWithTable(tableID)
    }

    private fun removeAllMembersAssociatedWithTable(tableID: String) {

        val table = database.child(Statics.FIREBASE_TABLE).child(tableID)
        table.removeValue()

        val ordersDetails = getOrderDetailsAssociatedWithTableID(tableID)

        ordersDetails.forEach {

            val orders = getOrdersAssociatedWithOrderDetail(it)

            orders.forEach {

                val order = database.child(Statics.FIREBASE_ORDER).child(it.uid!!)
                order.removeValue()
            }

            val orderDetail = database.child(Statics.FIREBASE_ORDER_DETAIL).child(it.uid!!)
            orderDetail.removeValue()
        }
    }

    fun addNewOrder(tableID: String, orderList: List<Order>) {

        val orderID = addNewOrderDetail(tableID)

        orderList.forEach {
            addNewOrder(it, orderID)
        }
    }

    private fun addNewOrderDetail(tableID: String) : String? {
        val sdf = SimpleDateFormat("hh:mm:ss")
        val startTime = sdf.format(Date())

        val newOrderDetail = database.child(Statics.FIREBASE_ORDER_DETAIL).push()
        val orderDetail = OrderDetailData(newOrderDetail.key, tableID, startTime, "")
        newOrderDetail.setValue(orderDetail)
        return newOrderDetail.key
    }

    private fun addNewOrder(order: Order, orderDetailID: String?) {

        val orderData = OrderData()
        orderData.orderID = orderDetailID
        orderData.description = order.description
        orderData.number = order.number
        orderData.dishID = getDishIDByItsName(order.name)

        val newOrder = database.child(Statics.FIREBASE_ORDER).push()
        orderData.uid = newOrder.key
        newOrder.setValue(orderData)
    }

    private fun getDishIDByItsName(dishName: String?): String? {

        dishesLiveData.value?.forEach {
            if(it.name == dishName)
                return it.uid
        }
        drinksLiveData.value?.forEach {
            if(it.name == dishName)
                return it.uid
        }
        return ""
    }

    private fun addTablesTableListener() {

        val listener = object : ValueEventListener {

            override fun onCancelled(databaseError: DatabaseError) {
                println("loadTables:onCancelled ${databaseError.toException()}")
            }

            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val list = mutableListOf<TableData>()
                dataSnapshot.children.mapNotNullTo(list) { it.getValue<TableData>(TableData::class.java) }

                this@Model.tablesLiveData.value = list
            }
        }
        database.child(Statics.FIREBASE_TABLE).addValueEventListener(listener)
    }

    private fun addOrdersDetailsTableListener() {

        val listener = object : ValueEventListener {

            override fun onCancelled(databaseError: DatabaseError) {
                println("loadOrderDetails:onCancelled ${databaseError.toException()}")
            }

            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val list = mutableListOf<OrderDetailData>()
                dataSnapshot.children.mapNotNullTo(list) { it.getValue<OrderDetailData>(OrderDetailData::class.java) }

                this@Model.ordersDetailsLiveData.value = list
            }
        }
        database.child(Statics.FIREBASE_ORDER_DETAIL).addValueEventListener(listener)
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

    fun getTableID(order: OrderData): String {

        ordersDetailsLiveData.value?.forEach {
            if(order.orderID.equals(it.uid)) {
                return it.tableID ?: ""
            }
        }
        return ""
    }

    fun generateNewTable(number: String): String {

        val sdf = SimpleDateFormat("hh:mm:ss")
        val startTime = sdf.format(Date())

        val newTable = database.child(Statics.FIREBASE_TABLE).push()
        val table = TableData(newTable.key, number, startTime)
        newTable.setValue(table)

        return newTable.key ?: ""
    }

    fun addDish(name: String, description: String, price: Float) {

        val newDish = database.child(Statics.FIREBASE_DISH).push()
        val dish = DishData(newDish.key, name, description, price)
        newDish.setValue(dish)
    }

    fun addDrink(name: String, description: String, price: Float) {

        val newDrink = database.child(Statics.FIREBASE_DRINK).push()
        val dish = DishData(newDrink.key, name, description, price)
        newDrink.setValue(dish)
    }

    fun getTable(tableID: String): TableData {

        tablesLiveData.value?.forEach {
            if(tableID.compareTo(it.uid!!) == 0)
                return it
        }
        return TableData()
    }

    fun getActualTableNumber(): LiveData<String> = actualTableNumberLiveData

    fun setTableID(id: String) {

        actualTableID = id
        actualTableNumberLiveData.value = getTable(id).description
        //actualOrderList.clear()
        //actualOrderListLiveData.value = actualOrderList
    }

    fun addActualOrderList() {

        if(actualOrderList.size == 0) return

        val orderDetailID = addNewOrderDetail(actualTableID)

        actualOrderList.forEach{

            addNewOrder(it, orderDetailID)
        }

        actualOrderList.clear()
        actualOrderListLiveData.value = actualOrderList
    }

    fun selectDish(dish: DishData, desc: String, number: Int) {

        val order = Order()

        order.name = dish.name
        order.number = number
        order.price = dish.price!! * number
        order.description = desc

        actualOrderList.add(order)
        actualOrderListLiveData.value = actualOrderList
    }

    fun getActualOrderListLiveData(): LiveData<List<Order>> = actualOrderListLiveData

    fun cancelActualOrder() {

        actualOrderList.clear()
        actualOrderListLiveData.value = actualOrderList
    }

    fun getOrdersAssociatedWithTableID(tableID: String): List<Order> {

        val orders = mutableListOf<Order>()

        val ordersDetails: List<OrderDetailData> = getOrderDetailsAssociatedWithTableID(tableID)

        ordersDetails.forEach {

            val orderDataList: List<OrderData> = getOrdersAssociatedWithOrderDetail(it)

            orderDataList.forEach {

                orders.add(getOrder(tableID, it))
            }
        }

        return orders
    }

    fun getOrder(tableID: String, orderData: OrderData): Order {

        val order = Order()
        val dishData = getDishIDByItsID(orderData.dishID)

        order.description = orderData.description
        order.price = orderData.number!! * dishData.price!!
        order.number = orderData.number
        order.name = dishData.name

        return order
    }

    private fun getDishIDByItsID(dishID: String?): DishData {

        dishesLiveData.value?.forEach {
            if(it.uid == dishID)
                return it
        }
        drinksLiveData.value?.forEach {
            if(it.uid == dishID)
                return it
        }
        return DishData()
    }

    fun getOrdersAssociatedWithOrderDetail(orderDetailData: OrderDetailData): List<OrderData> {

        val list = mutableListOf<OrderData>()

        ordersLiveData.value?.forEach {
            if(it.orderID == orderDetailData.uid)
                list.add(it)
        }
        return list
    }

    private fun getOrderDetailsAssociatedWithTableID(tableID: String): List<OrderDetailData> {

        val list = mutableListOf<OrderDetailData>()

        ordersDetailsLiveData.value?.forEach {
            if(it.tableID == tableID)
                list.add(it)
        }
        return list
    }

    fun getReadyOrdersDetailsLiveData(): LiveData<List<OrderDetailData>> = readyOrdersDetailsLiveData

    fun removeOrderDetailFromReadyOrders(orderDetailID: String) {

        readyOrders.forEach {

            if(it.orderID == orderDetailID) {
                val name: String? = it.uid
                val orderDetail = database.child(Statics.FIREBASE_READY_ORDER).child(name!!)
                orderDetail.removeValue()
            }
        }

    }

    fun getOrderDetailByItsID(orderDetailID: String): OrderDetailData {

        ordersDetailsLiveData.value?.forEach {
            if(it.uid == orderDetailID)
                return it
        }
        return OrderDetailData()
    }
}