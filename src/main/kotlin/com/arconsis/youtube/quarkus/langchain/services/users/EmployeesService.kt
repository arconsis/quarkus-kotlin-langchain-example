package com.arconsis.youtube.quarkus.langchain.services.users

import dev.langchain4j.agent.tool.Tool
import io.quarkus.logging.Log
import jakarta.enterprise.context.ApplicationScoped
import java.util.*
import java.util.stream.Collectors

@ApplicationScoped
class EmployeesService {
    private val employees: Map<UUID, Employees> = listOf(
        Employees(UUID.randomUUID(), "Hans", "Frankfurt", "arconsis"),
        Employees(UUID.randomUUID(), "Felix", "Karlsruhe", "arconsis"),
        Employees(UUID.randomUUID(), "Andreas", "Karlsruhe", "arconsis"),
        Employees(UUID.randomUUID(), "Beate", "Stuttgart", "arconsis"),
        Employees(UUID.randomUUID(), "Gustav", "Karlsruhe", "andrena"),
        Employees(UUID.randomUUID(), "Hans", "Karlsruhe", "andrena")
    ).associateBy { it.id }


    @Tool("retrieves the employees of a given company with their employee id, name and location and company")
    fun getEmployeesForCompany(company: String): Set<Employees> {
        Log.info("getEmployeesForCompany($company)")
        return employees.values.stream().filter { e: Employees -> e.company == company }.collect(Collectors.toSet())
    }

    @Tool("retrieves all employees of a given company located at the given locations (comma separated list) with their employee id, name and location and company")
    fun getEmployeesAtLocations(company: String, locations: String): List<Employees> {
        Log.info("getEmployeesAtLocations($company, \"$locations\"")

        val locationsSet = Arrays.stream(locations.split(",".toRegex()).dropLastWhile { it.isEmpty() }
            .toTypedArray()).map { obj: String -> obj.trim { it <= ' ' } }.collect(Collectors.toSet())
        Log.info("locationsSet:$locationsSet }")

        return employees.values.stream()
            .filter { e: Employees -> e.company == company }
            .filter { e: Employees -> locationsSet.contains(e.location) }
            .toList()
    }
}
