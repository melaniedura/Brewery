package com.melaniedura.brewery.database

import androidx.room.*

@Dao
interface BeerDao {

    @Query("SELECT EXISTS (SELECT 1 FROM BeerEntity WHERE id = :id)")
    fun isInDB(id: String): Boolean

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(beer: BeerEntity)

    @Query("SELECT * FROM BeerEntity WHERE id = :beerId")
    fun get(beerId: String): BeerEntity

    @Update
    fun update(beer: BeerEntity)
}
