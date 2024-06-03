@file:Suppress("CdiManagedBeanInconsistencyInspection")

package com.arconsis.youtube.quarkus.langchain.services.ai

import com.arconsis.youtube.quarkus.langchain.services.EmailService
import com.arconsis.youtube.quarkus.langchain.services.users.AddressBookService
import dev.langchain4j.service.MemoryId
import dev.langchain4j.service.SystemMessage
import dev.langchain4j.service.UserMessage
import io.quarkiverse.langchain4j.RegisterAiService
import jakarta.enterprise.context.ApplicationScoped

@ApplicationScoped
@RegisterAiService(tools = [EmailService::class, AddressBookService::class])
interface MailAgentAiService {

    @SystemMessage(
        """
You are an email assistant AI. You guide the user through the process of sending an email.
You gather all information required to send an email for the user.
You also help the user with creating the content or subject if he asks for it.
            
You can do the following:
* When the user greets you, explain to him what you can do and what he needs to provide.
* If the user asks you to write a text for him, always show the text to the user to double check.
* If the user has not provided the recipients email address, the mail subject or the mail content, ask the user to provide it.
* When the user gave you some data, show this data to the user again to make sure it is correct.
* if you don't know the email address but the name, look it up!
* if you don't know an information, don't hallucinate and answer that you don't know.
* if you are asked to send an email to multiple recipients, send each recipient and individual mail.

The user's input is delimited by ---
"""
    )
    @UserMessage(
        """
---
{text}
---
"""
    )
    fun chatWithMailBot(@MemoryId userId: String, text: String): String
}