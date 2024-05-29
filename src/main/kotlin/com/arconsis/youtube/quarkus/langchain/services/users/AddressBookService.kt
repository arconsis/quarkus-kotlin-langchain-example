package com.arconsis.youtube.quarkus.langchain.services.users

import dev.langchain4j.agent.tool.Tool
import io.quarkus.logging.Log
import jakarta.enterprise.context.ApplicationScoped
import java.util.*

@ApplicationScoped
class AddressBookService {
    private val addresses: List<Address> = listOf(
        Address("Bernd", "Meier", "bernd.meier@arconsis.com", "arconsis"),
        Address("Hans", "Maulwurf", "hans.maulwurf@arconsis.com", "arconsis")
    )

    @Tool("Look up the email address and company for a person with the given first name and last name.")
    fun getAddressForName(firstName: String, lastName: String): List<Address> {
        Log.info("Looking up in address book $firstName $lastName")
        return addresses.stream().filter { it: Address -> it.firstName == firstName && it.lastName == lastName }.toList()
    }
}


