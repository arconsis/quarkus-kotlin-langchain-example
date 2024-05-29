package com.arconsis.youtube.quarkus.langchain.rest

import com.arconsis.youtube.quarkus.langchain.services.ai.QuarkusDocumentationAiService
import jakarta.ws.rs.GET
import jakarta.ws.rs.Path
import jakarta.ws.rs.Produces
import jakarta.ws.rs.core.MediaType
import org.jboss.resteasy.reactive.RestQuery

@Path("/docs/quarkus-langchain")
class QuarkusDocumentationBotResource(private val quarkusDocumentationAiService: QuarkusDocumentationAiService) {

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    fun promptQuarkusDocsBot(@RestQuery userId: String, @RestQuery prompt: String): String {
        return quarkusDocumentationAiService.chatWithQuarkusDocsBot(userId, prompt)
    }
}

