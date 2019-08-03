package com.oufenghua.androidroomarchitecture

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class User {

    @PrimaryKey(autoGenerate = true)
    var id: Long = 0

    var name: String = ""

    var age: Int = 0

}