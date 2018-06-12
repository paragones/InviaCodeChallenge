package com.inviacodechallenge.parag

import com.inviacodechallenge.parag.models.RepositoryViewHandler
import com.inviacodechallenge.parag.models.Pagination

/**
 * AffinitasPersonalityTest
 *
 * Created by Paul Aragones on 9/2/2017.
 */
object TestUtil {

    fun pojoResults(): RepositoryViewHandler {
        return RepositoryViewHandler(Pagination(), emptyList())
    }
}