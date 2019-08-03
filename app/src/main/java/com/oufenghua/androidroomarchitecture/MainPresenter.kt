package com.oufenghua.androidroomarchitecture

import androidx.room.Room

class MainPresenter(val activity: MainActivity) {

    var userDao: UserDao? = null
    var adapter: UserListAdapter? = null

    init {
        Thread {
            userDao =
                Room.databaseBuilder(activity.applicationContext, UserDatabase::class.java, "user").build().userDao()
            adapter?.setData(getAllUser())
            activity.handler.sendEmptyMessage(MSG_NOTIFY_ADAPTER)
        }.start()
        adapter = UserListAdapter(getAllUser())
    }

    fun getAllUser(): List<User> {
        return userDao?.findAll() ?: listOf()
    }

    fun addUser(user: User) {
        adapter?.addData(user)
        Thread{
            userDao?.insertAll(user)
        }.start()
    }

    fun deleteUser() {
        adapter?.removeData()?.also {
            Thread{
                userDao?.delete(it)
            }.start()
        }
    }

}