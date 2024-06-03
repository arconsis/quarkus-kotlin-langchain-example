package com.arconsis.youtube.quarkus.langchain.services.users

import dev.langchain4j.agent.tool.Tool
import io.quarkus.logging.Log
import jakarta.enterprise.context.ApplicationScoped

@ApplicationScoped
class AddressBookService {
    private val addresses: List<Address> = listOf(
        Address(firstName = "Bernd", lastName = "Meier", email = "bernd.meier@arconsis.com", company = "arconsis"),
        Address(firstName = "Hans", lastName = "Maulwurf", email = "hans.maulwurf@moleman.com", company = "moleman"),
        Address(firstName = "Hans", lastName = "Maulwurf", email = "hans.maulwurf@arconsis.com", company = "arconsis"),
    )

    @Tool("Look up the email address and company for a person with the given first name and last name.")
    fun getAddressForName(firstName: String, lastName: String): List<Address> {
        Log.info("Looking up in address book $firstName $lastName")
        return addresses.filter { it.firstName == firstName && it.lastName == lastName }
    }
}
