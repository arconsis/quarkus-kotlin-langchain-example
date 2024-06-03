@file:Suppress("CdiManagedBeanInconsistencyInspection")

package com.arconsis.youtube.quarkus.langchain.services.ai

import com.arconsis.youtube.quarkus.langchain.services.rag.RetrievalAugmentorSupplier
import dev.langchain4j.service.MemoryId
import dev.langchain4j.service.SystemMessage
import dev.langchain4j.service.UserMessage
import io.quarkiverse.langchain4j.RegisterAiService
import jakarta.enterprise.context.ApplicationScoped

@ApplicationScoped
@RegisterAiService(retrievalAugmentor = RetrievalAugmentorSupplier::class)
interface QuarkusDocumentationAiService {

    @SystemMessage(
        "You are an assistant AI to support Quarkus LangChain users by providing insights into the Quarkus LangChain documentation."
    )
    fun chatWithQuarkusDocsBot(@MemoryId userId: String, @UserMessage prompt: String): String
}