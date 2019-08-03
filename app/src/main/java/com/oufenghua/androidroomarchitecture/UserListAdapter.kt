package com.oufenghua.androidroomarchitecture

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlin.math.max

class UserListAdapter(userList: List<User>) : RecyclerView.Adapter<UserViewHolder>() {

    private var userList: MutableList<User>? = null

    init {
        setData(userList)
    }

    fun setData(userList: List<User>?) {
        if (this.userList == null) {
            this.userList = mutableListOf()
        } else if (this.userList?.isEmpty() == false) {
            this.userList?.clear()
        }
        this.userList?.addAll(userList ?: listOf())
    }

    fun addData(user: User) {
        userList?.also {
            it.add(max(it.size - 1, 0), user)
            notifyItemInserted(max(it.size - 1, 0))
        }
    }

    fun removeData(): User? {
        userList?.also {
            if (!it.isEmpty()) {
                val user = it[it.size - 1]
                it.removeAt(it.size - 1)
                notifyItemRemoved(it.size - 1)
                return user
            }
        }
        return null
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        return UserViewHolder(parent)
    }

    override fun getItemCount(): Int {
        return userList?.size ?: 0
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        userList?.also {
            holder.bindView(it[position])
        }
    }

}

class UserViewHolder(parent: ViewGroup) :
    RecyclerView.ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_view, parent, false)) {

    private val nameTv: TextView
        get() {
            return itemView.findViewById(R.id.name_tv)
        }
    private val ageTv: TextView
        get() {
            return itemView.findViewById(R.id.age_tv)
        }

    fun bindView(user: User) {
        nameTv.text = user.name
        ageTv.text = "${user.age}岁"
    }

}
