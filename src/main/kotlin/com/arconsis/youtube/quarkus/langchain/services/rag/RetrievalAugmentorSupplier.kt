@file:Suppress("CdiInjectionPointsInspection")

package com.arconsis.youtube.quarkus.langchain.services.rag

import dev.langchain4j.model.embedding.EmbeddingModel
import dev.langchain4j.rag.DefaultRetrievalAugmentor
import dev.langchain4j.rag.RetrievalAugmentor
import dev.langchain4j.rag.content.retriever.EmbeddingStoreContentRetriever
import io.quarkiverse.langchain4j.redis.RedisEmbeddingStore
import jakarta.inject.Singleton
import java.util.function.Supplier

@Singleton
class RetrievalAugmentorSupplier(store: RedisEmbeddingStore, model: EmbeddingModel) : Supplier<RetrievalAugmentor> {
    private val augmentor: RetrievalAugmentor

    init {
        val contentRetriever: EmbeddingStoreContentRetriever = EmbeddingStoreContentRetriever.builder()
            .embeddingModel(model)
            .embeddingStore(store)
            .maxResults(3)
            .build()
        augmentor = DefaultRetrievalAugmentor
            .builder()
            .contentRetriever(contentRetriever)
            .build()
    }

    override fun get(): RetrievalAugmentor {
        return augmentor
    }
}