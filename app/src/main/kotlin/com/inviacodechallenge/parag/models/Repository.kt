package com.inviacodechallenge.parag.models

import java.util.*

data class Repository(val name: String?,
                      val fullName: String?,
                      val owner: Owner?,
                      val description: String?,
                      val language: String?,
                      val forks: Int?,
                      val update: Date?)