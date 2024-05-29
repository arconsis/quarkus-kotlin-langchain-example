package com.arconsis.youtube.quarkus.langchain.services.ai

import dev.langchain4j.service.MemoryId
import dev.langchain4j.service.UserMessage
import io.quarkiverse.langchain4j.RegisterAiService
import jakarta.enterprise.context.ApplicationScoped


@RegisterAiService
@ApplicationScoped
interface SimpleChatBotService {

    fun sendPrompt(@MemoryId userId: String, @UserMessage query: String): String
}