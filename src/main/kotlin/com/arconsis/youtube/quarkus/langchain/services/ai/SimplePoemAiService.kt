package com.arconsis.youtube.quarkus.langchain.services.ai

import dev.langchain4j.service.SystemMessage
import dev.langchain4j.service.UserMessage
import io.quarkiverse.langchain4j.RegisterAiService
import io.smallrye.mutiny.Multi


@RegisterAiService
@SystemMessage("You are a professional poet of the 18th century")
interface SimplePoemAiService {

    @UserMessage("Write a poem about {topic}. The poem should be {lines} lines long.")
    fun writeAPoem(topic: String, lines: Int): String


    @UserMessage("Write a poem about {topic}. The poem should be {lines} lines long.")
    fun writeAPoemStreaming(topic: String, lines: Int): Multi<String>
}