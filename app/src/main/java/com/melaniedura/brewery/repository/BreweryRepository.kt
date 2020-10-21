package com.melaniedura.brewery.repository

import com.melaniedura.brewery.database.BeerDao
import com.melaniedura.brewery.database.toDomainModel
import com.melaniedura.brewery.model.BeerDomainModel
import com.melaniedura.brewery.model.StyleDomainModel
import com.melaniedura.brewery.model.toBeerEntity
import com.melaniedura.brewery.network.ResponseHandler
import com.melaniedura.brewery.network.model.toDomainModel
import com.melaniedura.brewery.network.service.BreweryApi
import com.melaniedura.brewery.repository.util.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class BreweryRepository @Inject constructor(private val breweryApi: BreweryApi, private val beerDao: BeerDao, private val responseHandler: ResponseHandler) {

    suspend fun getBeerStyles(): Resource<List<StyleDomainModel>> {
        return try {
            val response = breweryApi.getStyles().data.map { it.toDomainModel() }
            responseHandler.handleSuccess(response)
        } catch (e: Exception) {
            responseHandler.handleException(e)
        }
    }

    suspend fun getBeers(styleId: Int): Resource<List<BeerDomainModel>> {
        return try {
            val response = breweryApi.getBeers(styleId).beers?.map { it.toDomainModel() }
            responseHandler.handleSuccess(response)
        } catch (e: Exception) {
            responseHandler.handleException(e)
        }
    }

    suspend fun getBeer(id: String): Resource<BeerDomainModel> {
        return try {
            withContext(Dispatchers.IO) {
                val isInDB = beerDao.isInDB(id)
                if (!isInDB) {
                    val response = breweryApi.getBeer(id).beer.toDomainModel()
                    beerDao.insert(response.toBeerEntity())
                    responseHandler.handleSuccess(response)
                } else {
                    val beer = beerDao.get(id).toDomainModel()
                    responseHandler.handleSuccess(beer)
                }
            }
        } catch (e: Exception) {
            responseHandler.handleException(e)
        }
    }

    suspend fun updateBeer(beer: BeerDomainModel): Resource<BeerDomainModel> {
        withContext(Dispatchers.IO) {
            responseHandler.handleSuccess(beerDao.update(beer.toBeerEntity()))
        }
        return responseHandler.handleSuccess(beer)
    }
}