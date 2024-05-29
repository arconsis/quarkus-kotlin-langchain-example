package com.arconsis.youtube.quarkus.langchain.services.rag

import dev.langchain4j.data.document.Document
import dev.langchain4j.data.document.loader.FileSystemDocumentLoader
import dev.langchain4j.data.document.parser.apache.pdfbox.ApachePdfBoxDocumentParser
import dev.langchain4j.data.document.splitter.DocumentSplitters.recursive
import dev.langchain4j.model.embedding.EmbeddingModel
import dev.langchain4j.store.embedding.EmbeddingStoreIngestor
import io.quarkiverse.langchain4j.redis.RedisEmbeddingStore
import io.quarkus.runtime.StartupEvent
import jakarta.enterprise.context.ApplicationScoped
import jakarta.enterprise.event.Observes
import org.eclipse.microprofile.config.inject.ConfigProperty


@ApplicationScoped
class PdfDocumentsIngestor(
    private val embeddingStore: RedisEmbeddingStore,
    private val embeddingModel: EmbeddingModel,
    @ConfigProperty(name = "rag.input-directory") private val inputDirectory: String,
) {

    fun onStart(@Observes startupEvent: StartupEvent) {
        embeddingStore.deleteAll()

        val documents = createDocuments()
        ingest(documents)
    }

    private fun ingest(documents: List<Document>) {
        val ingestor = EmbeddingStoreIngestor.builder()
            .embeddingStore(embeddingStore)
            .embeddingModel(embeddingModel)
            .documentSplitter(recursive(500, 0))
            .build()

        ingestor.ingest(documents)
    }

    private fun createDocuments(): List<Document> {
        return FileSystemDocumentLoader.loadDocuments(inputDirectory, ApachePdfBoxDocumentParser())
    }
}