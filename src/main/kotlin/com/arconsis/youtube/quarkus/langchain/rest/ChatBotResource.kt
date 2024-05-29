package com.arconsis.youtube.quarkus.langchain.rest

import com.arconsis.youtube.quarkus.langchain.services.ai.ChatWithMemoryAiService
import io.quarkiverse.langchain4j.ChatMemoryRemover
import jakarta.ws.rs.DELETE
import jakarta.ws.rs.GET
import jakarta.ws.rs.Path
import jakarta.ws.rs.Produces
import jakarta.ws.rs.core.MediaType
import org.jboss.resteasy.reactive.RestQuery

@Path("/chat")
class ChatBotResource(private val aiService: ChatWithMemoryAiService) {

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    fun writePoem(@RestQuery userId: String, @RestQuery prompt: String): String {
        return aiService.sendPrompt(userId, prompt)
    }

    @DELETE
    fun deleteConversation(@RestQuery userId: String) {
        ChatMemoryRemover.remove(aiService, userId)
    }
}

