package com.arconsis.youtube.quarkus.langchain.services

import dev.langchain4j.agent.tool.Tool
import io.quarkus.logging.Log
import jakarta.enterprise.context.ApplicationScoped

@ApplicationScoped
class EmailService {

    @Tool("send an email to a single recipient email with the given subject and content")
    fun sendAnEmailWithRecipientSubjectAndContent(recipientEmail: String, subject: String, content: String) {
        Log.info("Sending an email to: $recipientEmail with subject $subject and content: $content")
    }

}