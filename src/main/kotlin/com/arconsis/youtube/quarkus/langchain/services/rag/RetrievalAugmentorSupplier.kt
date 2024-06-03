@file:Suppress("CdiInjectionPointsInspection")

package com.arconsis.youtube.quarkus.langchain.services.rag

import dev.langchain4j.data.segment.TextSegment
import dev.langchain4j.model.embedding.EmbeddingModel
import dev.langchain4j.rag.DefaultRetrievalAugmentor
import dev.langchain4j.rag.RetrievalAugmentor
import dev.langchain4j.rag.content.retriever.EmbeddingStoreContentRetriever
import dev.langchain4j.store.embedding.EmbeddingStore
import jakarta.inject.Singleton
import java.util.function.Supplier

@Singleton
class RetrievalAugmentorSupplier(store: EmbeddingStore<TextSegment>, model: EmbeddingModel) : Supplier<RetrievalAugmentor> {
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