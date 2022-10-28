package com.example.mvi_example.di

import android.app.Application
import androidx.room.Room
import com.example.mvi_example.data.CounterDao
import com.example.mvi_example.data.CounterDatabase
import com.example.mvi_example.data.repository.CounterRepository
import com.example.mvi_example.domain.IncreaseDecreaseCounterUseCase
import com.example.mvi_example.domain.ObserveCounterUseCase
import com.example.mvi_example.domain.ResetCounterUseCase
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
            .build()
    }

    @Singleton
    @Provides
    fun provideCounterDao(counterDatabase: CounterDatabase): CounterDao {
        return counterDatabase.getCounterDao()
    }

    @Singleton
    @Provides
    fun provideCounterRepository(counterDao: CounterDao): CounterRepository =
        CounterRepository(counterDao)

    @Singleton
    @Provides
    fun provideObserveCounterUseCase(counterRepository: CounterRepository): ObserveCounterUseCase =
        ObserveCounterUseCase(counterRepository)

    @Singleton
    @Provides
    fun provideIncreaseDecreaseCounterUseCase(counterRepository: CounterRepository): IncreaseDecreaseCounterUseCase =
        IncreaseDecreaseCounterUseCase(counterRepository)

    @Singleton
    @Provides
    fun provideResetCounterUseCase(counterRepository: CounterRepository): ResetCounterUseCase =
        ResetCounterUseCase(counterRepository)
}