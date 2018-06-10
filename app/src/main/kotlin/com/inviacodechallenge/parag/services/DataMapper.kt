package com.inviacodechallenge.parag.services

interface DataMapper<T, U> {
    fun transform(input: T) : U
}