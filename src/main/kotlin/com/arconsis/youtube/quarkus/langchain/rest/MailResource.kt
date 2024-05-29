package com.arconsis.youtube.quarkus.langchain.rest

import com.arconsis.youtube.quarkus.langchain.services.ai.MailAiService
import io.quarkiverse.langchain4j.ChatMemoryRemover
import jakarta.ws.rs.DELETE
import jakarta.ws.rs.GET
import jakarta.ws.rs.Path
import jakarta.ws.rs.Produces
import jakarta.ws.rs.core.MediaType
import org.jboss.resteasy.reactive.RestQuery

@Path("/mails")
class MailResource(private val aiService: MailAiService) {

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    fun sendAnEmail(@RestQuery userId: String, @RestQuery query: String): String {
        return aiService.chatWithMailBot(userId, query)
    }

    @DELETE
    @Path("/conversations")
    fun deleteConversation(@RestQuery userId: String) {
        ChatMemoryRemover.remove(aiService, userId)
    }
}
