package com.inviacodechallenge.parag

import android.net.Uri
import com.inviacodechallenge.parag.models.Owner
import com.inviacodechallenge.parag.models.OwnerResults
import com.inviacodechallenge.parag.models.Repository
import com.inviacodechallenge.parag.models.RepositoryResults
import com.inviacodechallenge.parag.services.DataMapper
import com.inviacodechallenge.parag.services.DataMapperImpl
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import java.util.*

class DataMapperTest {
    lateinit var mapper: DataMapper
    lateinit var repositoryResults: List<RepositoryResults>
    lateinit var actualResults: List<Repository>
    lateinit var expectedResults: List<Repository>

    @Before
    fun setup() {
        val date = Date()
        mapper = DataMapperImpl()
        repositoryResults = arrayListOf(RepositoryResults("name",
                "fullName",
                OwnerResults("owner", Uri.EMPTY),
                "description",
                "java",
                1,
                date))
        expectedResults = arrayListOf(Repository("name",
                "fullName",
                Owner("owner", Uri.EMPTY),
                "description",
                "java",
                1,
                date))
    }

    @Test
    fun shouldHaveMappedWrapperToPOJO() {
        actualResults = mapper.transformRepository(repositoryResults)
        assertEquals(actualResults[0].name, expectedResults[0].name)
        assertEquals(actualResults[0].fullName, expectedResults[0].fullName)
        assertEquals(actualResults[0].owner?.name, expectedResults[0].owner?.name)
        assertEquals(actualResults[0].description, expectedResults[0].description)
        assertEquals(actualResults[0].forks, expectedResults[0].forks)
        assertEquals(actualResults[0].update, expectedResults[0].update)
    }
}