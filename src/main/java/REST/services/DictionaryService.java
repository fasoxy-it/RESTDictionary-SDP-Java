package REST.services;

import REST.beans.Word;
import REST.beans.Words;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.Dictionary;

@Path("dictionary")
public class DictionaryService {
    @GET
    @Produces({"application/json", "application/xml"})
    public Response getDictionary() {
        return Response.ok(Words.getInstance()).build();
    }

    @Path("add")
    @POST
    @Consumes({"application/json", "application/xml"})
    public Response addWord(Word w) {
        Words.getInstance().add(w);
        return Response.ok().build();
    }

    @Path("change")
    @POST
    @Consumes({"application/json", "application/xml"})
    public Response changeDefinition(Word w) {
        Words.getInstance().change(w);
        return Response.ok().build();
    }

    @Path("get/{word}")
    @GET
    @Produces({"application/json", "application/xml"})
    public Response getDefinition(@PathParam("word") String word) {
        Word w = Words.getInstance().getDefinition(word);
        if (w != null) {
            return Response.ok(w).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @Path("delete/{word}")
    @GET
    @Consumes({"application/json", "application/xml"})
    public Response deleteWord(@PathParam("word") String w) {
        Words.getInstance().delete(w);
        return Response.ok().build();
    }
}
