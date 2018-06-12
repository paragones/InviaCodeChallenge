package com.inviacodechallenge.parag.services

import com.inviacodechallenge.parag.models.*

class DataMapperImpl: DataMapper {
    override fun transformRepository(input: List<RepositoryResults>): List<Repository> {
        return input.map {
            Repository(it.repositoryName,
                    it.repositoryFullName,
                    Owner(it.ownerResults?.ownerName,
                            it.ownerResults?.ownerAvatarUrl),
                    it.repositoryDescription,
                    it.repositoryLanguage,
                    it.repositoryForks,
                    it.update)
        }
    }

    override fun tranformSubscribers(input: List<OwnerResults>): List<Subscriber> {
        return input.map {
            Subscriber(it.ownerName, it.ownerAvatarUrl)
        }
    }
}