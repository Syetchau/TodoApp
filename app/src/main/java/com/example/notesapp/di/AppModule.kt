package com.example.notesapp.di

import android.content.Context
import androidx.room.Room
import com.example.notesapp.db.TodoDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideTodoDatabase(@ApplicationContext context: Context) =
        Room.databaseBuilder(
            context,
            TodoDatabase::class.java,
            "todoDB"
        ).build()

    @Provides
    @Singleton
    fun provideTodoDao(db: TodoDatabase) = db.todoDao()
}