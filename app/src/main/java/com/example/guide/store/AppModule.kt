package com.example.guide.store

import android.app.Application
import androidx.room.Room
import com.example.guide.data.db.AppDatabase
import com.example.guide.data.db.dao.AttractionDao
import com.example.guide.data.db.dao.UserNoteDao
import com.example.guide.data.db.dao.VisitedDao
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    single {
        Room.databaseBuilder(
            get<Application>(),
            AppDatabase::class.java,
            "guide.db"
        ).fallbackToDestructiveMigration().build()
    }


    single<AttractionDao> { get<AppDatabase>().attractionDao() }
    single<UserNoteDao> { get<AppDatabase>().userNoteDao() }
    single<VisitedDao> { get<AppDatabase>().visitedDao() }

    viewModel { AttractionsViewModel(get()) }
    viewModel { NotesViewModel(get()) }
    viewModel { VisitedViewModel(get()) }
}
