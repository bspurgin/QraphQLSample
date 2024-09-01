package com.spurgin.starwars.di

import com.spurgin.starwars.api.ApolloClientInstance
import com.spurgin.starwars.repository.StarWarsRepository
import dagger.Module
import dagger.Provides

@Module
object AppModule {
    @Provides
    fun provideRepository(): StarWarsRepository {
        return StarWarsRepository(ApolloClientInstance.client)
    }
}