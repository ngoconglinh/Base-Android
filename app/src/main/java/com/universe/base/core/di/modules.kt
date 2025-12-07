package com.universe.base.core.di

import com.universe.base.data.repository.UserRepository
import com.universe.base.data.repository.UserRepositoryImpl
import com.universe.base.feature.home.viewmodel.HomeVM
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module


val repositoryModule = module {
    single<UserRepository> { UserRepositoryImpl() }
}

val viewmodelModule = module {
    viewModel { HomeVM(get()) }
}