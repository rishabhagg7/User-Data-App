package com.example.firebase.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.firebase.models.User
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class UserRepository {
    private val databaseReference: DatabaseReference = FirebaseDatabase.getInstance().getReference("users")

    @Volatile private var INSTANCE: UserRepository? = null

    // if instance is null then it will only return a new instance
    fun getInstance(): UserRepository{
        return INSTANCE ?: synchronized(this){
            val instance = UserRepository()
            INSTANCE = instance
            instance
        }
    }

    fun loadUsers(userList: MutableLiveData<List<User>>){
        databaseReference.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                try{
                    val _userList : List<User> = snapshot.children.map {dataSnapshot ->  
                        dataSnapshot.getValue(User::class.java)!!
                    }
                    userList.postValue(_userList)
                }catch (e: Exception){
                    Log.d("Data Change","${e.message}")
                }
            }

            override fun onCancelled(error: DatabaseError) {

            }

        })
    }
}