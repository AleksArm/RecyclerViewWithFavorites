package com.highestaim.recyclerviewwithfavorites.DI

import com.highestaim.recyclerviewwithfavorites.db.AppDatabase
import com.highestaim.recyclerviewwithfavorites.repository.FavoriteRepository
import com.highestaim.recyclerviewwithfavorites.repository.CommentsRepository
import com.highestaim.recyclerviewwithfavorites.service.InfoService
import com.highestaim.recyclerviewwithfavorites.viewModel.FavoriteViewModel
import com.highestaim.recyclerviewwithfavorites.viewModel.CommentsViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module

val appRepositories: Module = module {
    single { FavoriteRepository(get()) }
    single { CommentsRepository(get(),get()) }
}

val Services: Module = module {
    single { InfoService.create() }
    single { AppDatabase.getDatabase(get()) }
}

val appViewModels: Module = module {
    viewModel { FavoriteViewModel(get()) }
    viewModel { CommentsViewModel(get()) }
}