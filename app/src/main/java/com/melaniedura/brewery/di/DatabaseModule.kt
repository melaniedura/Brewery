package com.melaniedura.brewery.di

import android.content.Context
import androidx.room.Room
import com.melaniedura.brewery.database.BeerDao
import com.melaniedura.brewery.database.BeerDatabase
import com.melaniedura.brewery.util.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object DatabaseModule {
    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context): BeerDatabase {
        return Room.databaseBuilder(
            context,
            BeerDatabase::class.java,
            Constants.DATABASE_NAME
        )
            .fallbackToDestructiveMigration()
            .build()
    }

    @Singleton
    @Provides
    fun provideBeerDao(db: BeerDatabase): BeerDao {
        return db.beerDao()
    }
}
