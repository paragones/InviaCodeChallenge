package com.inviacodechallenge.parag.services

import com.inviacodechallenge.parag.models.OwnerResults
import com.inviacodechallenge.parag.models.Repository
import com.inviacodechallenge.parag.models.RepositoryResults
import com.inviacodechallenge.parag.models.Subscriber

interface DataMapper {
    fun transformRepository(input: List<RepositoryResults>) : List<Repository>
    fun tranformSubscribers(input: List<OwnerResults>) : List<Subscriber>
}