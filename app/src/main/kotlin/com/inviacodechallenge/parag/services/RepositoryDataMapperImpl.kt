package com.inviacodechallenge.parag.services

import com.inviacodechallenge.parag.models.Owner
import com.inviacodechallenge.parag.models.Repository
import com.inviacodechallenge.parag.models.RepositoryResults

class RepositoryDataMapperImpl: DataMapper<List<RepositoryResults>, List<Repository>> {
    override fun transform(input: List<RepositoryResults>): List<Repository> {
        return input.map {
            Repository(it.repositoryName,
                    it.repositoryFullName,
                    Owner(it.repositoryOwner.ownerName,
                            it.repositoryOwner.ownerAvatarUrl),
                    it.repositoryDescription,
                    it.repositoryLanguage,
                    it.repositoryForks,
                    it.update)
        }
    }
}