package com.rickardosatrioabout.asesment2_mobpro1.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.rickardosatrioabout.asesment2_mobpro1.model.Ukm
import kotlinx.coroutines.flow.Flow

@Dao
interface UkmDao {
    @Insert
    suspend fun insert(ukm: Ukm)

    @Update
    suspend fun update(ukm: Ukm)

    @Query("SELECT * FROM ukm ORDER BY namaukm ASC")
    fun getUkm(): Flow<List<Ukm>>

    @Query("SELECT * FROM ukm WHERE id = :id")
    suspend fun getUkmById(id: Long): Ukm?
}