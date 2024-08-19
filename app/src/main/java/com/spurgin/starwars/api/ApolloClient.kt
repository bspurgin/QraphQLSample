package com.spurgin.starwars.api
import com.apollographql.apollo3.ApolloClient

object ApolloClientInstance {
    val client: ApolloClient by lazy {
        ApolloClient.Builder()
            .serverUrl("https://swapi-graphql.netlify.app/.netlify/functions/index")
            .build()
    }
}