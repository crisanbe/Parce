package com.gerotac.auth.register.data.local

import androidx.lifecycle.LiveData
import androidx.room.*
import com.gerotac.auth.register.data.local.entity.TokenRegisterEntity

@Dao
interface DaoTokenRegister {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(tokenRegisterEntity: TokenRegisterEntity)

    @Query("SELECT * FROM TokenRegisterEntity")
    fun getAll(): LiveData<List<TokenRegisterEntity>>

    @Query("SELECT * from TokenRegisterEntity where id = :id")
    fun getById(id: Int) : TokenRegisterEntity?

    @Update
    suspend fun update(item:TokenRegisterEntity)

    @Delete
    fun delete(tokenRegister: TokenRegisterEntity)

}