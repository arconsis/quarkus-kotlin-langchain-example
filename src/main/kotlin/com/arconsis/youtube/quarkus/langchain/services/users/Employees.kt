package com.arconsis.youtube.quarkus.langchain.services.users

import java.util.*

data class Employees(@JvmField val id: UUID, val name: String, @JvmField val location: String, @JvmField val company: String)
