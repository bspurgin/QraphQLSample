package com.spurgin.starwars.repository

import android.util.Log
import com.apollographql.apollo3.ApolloClient
import com.apollographql.apollo3.api.ApolloResponse
import com.spurgin.starwars.GetPersonQuery
import com.spurgin.starwars.GetPeopleQuery
import com.spurgin.starwars.api.ApolloClientInstance

class StarWarsRepository(private val apolloClient: ApolloClient) {

    suspend fun getPeople(): List<GetPeopleQuery.Person?> {
        val response: ApolloResponse<GetPeopleQuery.Data> = apolloClient.query(GetPeopleQuery()).execute()
        return response.data?.allPeople?.people.orEmpty()
    }

    suspend fun getPersonDetail(id: String): GetPersonQuery.Person? {
        Log.d("StarWarsRepository", "Fetching details for $id")
        val response: ApolloResponse<GetPersonQuery.Data> = apolloClient.query(GetPersonQuery(id = id)).execute()
        Log.d("StarWarsRepository", "Fetched person: ${response.data?.person}")
        return response.data?.person
    }
}

object StarWarsRepositoryProvider {
    val repository: StarWarsRepository by lazy {
        StarWarsRepository(ApolloClientInstance.client)
    }
}