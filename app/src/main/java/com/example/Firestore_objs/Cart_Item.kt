package com.example.Firestore_objs

class Cart_Item {
    var Image: String?= null
    var name: String? = ""
    var price: String? = null
    var id: String? = null
    var userid: String? =null
    var count: Int? =null

    constructor()
    {}
    constructor(
        ID: String,
        name: String?,
        price: String?,
        Image: String?,
        count: Int?
    )
    {
        id = ID
        this.name = name
        this.price = price
        this.count = count
        this.Image= Image
    }
}