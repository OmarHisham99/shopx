package com.example.Firestore_objs

class User {
    var email: String = ""
    var firstname: String = ""
    var lastname: String? = null
    var id: String? = null

    constructor()
    {}
    constructor(
        ID: String,
        Email: String,
        Firstname: String,
        Lastname: String?
    )
    {
        email = Email
        id = ID
        firstname = Firstname
        lastname = Lastname
    }

}