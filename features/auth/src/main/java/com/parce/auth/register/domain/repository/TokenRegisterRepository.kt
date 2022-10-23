package com.parce.auth.register.domain.repository

import androidx.lifecycle.LiveData
import com.parce.auth.register.data.local.DaoTokenRegister
import com.parce.auth.register.data.local.entity.TokenRegisterEntity

data class TokenRegisterRepository(private val daoTokenRegisterDao: DaoTokenRegister) {

    val readAllData : LiveData<List<TokenRegisterEntity>> =  daoTokenRegisterDao.getAll()
    suspend fun addTodo(todoItem: TokenRegisterEntity) {
        daoTokenRegisterDao.insert(todoItem)
    }
    suspend fun updateTodo(todoItem: TokenRegisterEntity) {
        daoTokenRegisterDao.update(todoItem)
    }
    suspend fun deleteTodo(todoItem: TokenRegisterEntity) {
        daoTokenRegisterDao.delete(todoItem)
    }

}