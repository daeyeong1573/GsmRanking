package org.gsm.software.gsmranking.model.repository

import com.apollographql.apollo3.ApolloCall
import com.apollographql.apollo3.ApolloClient
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import org.gsm.software.gsmranking.RankingResultQuery
import javax.inject.Inject

@Module
@InstallIn(SingletonComponent::class)
class MainRepository @Inject constructor (val api : ApolloClient) {
    fun getRanking(generation : Int) : ApolloCall<RankingResultQuery.Data>{
        return api.query(RankingResultQuery(criteria = "contributions", count = 10, page = 1,generation))
    }
}