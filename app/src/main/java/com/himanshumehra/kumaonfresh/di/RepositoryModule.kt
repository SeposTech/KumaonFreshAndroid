package com.himanshumehra.kumaonfresh.di

import com.himanshumehra.kumaonfresh.data.repository.CategoryRepositoryImp
import com.himanshumehra.kumaonfresh.data.repository.CreateUserRepositoryImp
import com.himanshumehra.kumaonfresh.data.repository.ItemRepositoryImp
import com.himanshumehra.kumaonfresh.data.repository.LoginRepositoryImp
import com.himanshumehra.kumaonfresh.domain.repository.CategoryRepository
import com.himanshumehra.kumaonfresh.domain.repository.CreateUserRepository
import com.himanshumehra.kumaonfresh.domain.repository.ItemResponse
import com.himanshumehra.kumaonfresh.domain.repository.LoginRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindLoginRepository(loginRepositoryImp: LoginRepositoryImp): LoginRepository

    @Binds
    @Singleton
    abstract fun bindCreateUserRepository(createUserRepositoryImp: CreateUserRepositoryImp): CreateUserRepository

    @Binds
    @Singleton
    abstract fun bindCategoryRepository(categoryRepositoryImp: CategoryRepositoryImp): CategoryRepository

    @Binds
    @Singleton
    abstract fun bindItemResponse(itemRepositoryImp: ItemRepositoryImp): ItemResponse

}



