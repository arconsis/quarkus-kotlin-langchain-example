package com.arconsis.youtube.quarkus.langchain.rest

import com.arconsis.youtube.quarkus.langchain.services.ai.SimplePoemAiService
import io.smallrye.mutiny.Multi
import jakarta.ws.rs.DefaultValue
import jakarta.ws.rs.GET
import jakarta.ws.rs.Path
import jakarta.ws.rs.Produces
import jakarta.ws.rs.core.MediaType
import org.jboss.resteasy.reactive.RestQuery
import org.jboss.resteasy.reactive.RestStreamElementType

@Path("/poem")
@Produces(MediaType.TEXT_PLAIN)
class PoemResource(private val myAiService: SimplePoemAiService) {

    @GET
    fun writePoem(@RestQuery topic: String, @DefaultValue("8") @RestQuery lines: Int): String {
        return myAiService.writeAPoem(topic, lines)
    }

    @GET
    @Path("/streaming")
    @RestStreamElementType(MediaType.TEXT_PLAIN)
    fun writePoemStreaming(@RestQuery topic: String, @DefaultValue("8") @RestQuery lines: Int): Multi<String> {
        return myAiService.writeAPoemStreaming(topic, lines)
    }
}

