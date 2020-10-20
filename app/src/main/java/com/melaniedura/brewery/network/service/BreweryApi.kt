package com.melaniedura.brewery.network.service

import com.melaniedura.brewery.network.response.GetBeerDetailsResponse
import com.melaniedura.brewery.network.response.GetBeersResponse
import com.melaniedura.brewery.network.response.GetStylesResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface BreweryApi {
    @GET("styles")
    suspend fun getStyles(): GetStylesResponse

    @GET("beers")
    suspend fun getBeers(
        @Query("styleId") styleId: Int
    ): GetBeersResponse

    @GET("beers")
    suspend fun getBeer(
        @Query("beerId") beerId: String
    ): GetBeerDetailsResponse
}
