package com.example.mvi_example.di

import android.app.Application
import android.content.ContentValues
import androidx.room.OnConflictStrategy
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.mvi_example.data.CounterDao
import com.example.mvi_example.data.CounterDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Singleton
    @Provides
    fun provideCounterDatabase(app: Application): CounterDatabase {
        return Room
            .databaseBuilder(app, CounterDatabase::class.java, "counter_db")
            // insert init value
            .addCallback(object : RoomDatabase.Callback() {
                override fun onCreate(db: SupportSQLiteDatabase) {
                    super.onCreate(db)
                    val contentValues = ContentValues()
                    contentValues.put("id", 0)
                    contentValues.put("count", 0)
                    db.insert("counter_table", OnConflictStrategy.IGNORE, contentValues)
                }
            })
            .build()
    }

    @Singleton
    @Provides
    fun provideCounterDao(counterDatabase: CounterDatabase): CounterDao {
        return counterDatabase.getCounterDao()
    }

}