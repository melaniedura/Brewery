package com.melaniedura.brewery

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.melaniedura.brewery.database.BeerDao
import com.melaniedura.brewery.database.BeerDatabase
import com.melaniedura.brewery.util.mockedBeerEntity
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class DBTest {
    private lateinit var beerDao: BeerDao
    private lateinit var db: BeerDatabase

    @Before
    fun createDb() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(
            context, BeerDatabase::class.java
        ).build()
        beerDao = db.beerDao()
    }

    @After
    fun closeDb() {
        db.close()
    }

    @Test
    fun insertAndGetBeer() = runBlocking {
        beerDao.insert(mockedBeerEntity)
        val beer = beerDao.get(mockedBeerEntity.id)
        assertEquals(beer, mockedBeerEntity)
    }

    @Test
    fun testBeerIsInDB() = runBlocking {
        beerDao.insert(mockedBeerEntity)
        val myBeer = beerDao.isInDB(mockedBeerEntity.id)
        assertTrue(myBeer)
    }

    @Test
    fun testBeerIsNotInDB() = runBlocking {
        beerDao.insert(mockedBeerEntity)
        val myBeer = beerDao.isInDB("d4e5f6")
        assertFalse(myBeer)
    }
}
