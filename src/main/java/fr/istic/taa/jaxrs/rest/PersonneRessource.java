package fr.istic.taa.jaxrs.rest;


import fr.istic.taa.jaxrs.dao.PersonneDAO;
import fr.istic.taa.jaxrs.domain.Personne;
import fr.istic.taa.jaxrs.domain.SupportMember;
import fr.istic.taa.jaxrs.domain.User;
import io.swagger.v3.oas.annotations.Parameter;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;

@Path("/personne")
@Produces({"application/json"})
public class PersonneRessource {

    @GET
    @Path("/{personneId}")
    public Personne getPersonneById(@PathParam("personneId") Long personneId) {
        PersonneDAO personneDAO = new PersonneDAO();
        Personne personne = personneDAO.getPersonneByID(personneId);
        String type = personne instanceof User ? "User" : "SupportMember";
        // return pet
        if(type == "User"){
            User user = new User();
            user.setId_member(personne.getId_member());
            user.setName(personne.getName());
            return user;
        } else{
            SupportMember support = new SupportMember();
            support.setId_member(personne.getId_member());
            support.setName(personne.getName());
            return support;
        }

    }

    //curl -X POST -H 'Content-type:application/json' -H 'Accept:application/json' -d '{"name":"peter"}' http://localhost:8080/personne
    @POST
    @Consumes("application/json")
    public Response addPersonne(
            @Parameter(description = "Personne object that needs to be added to the list", required = true) Personne personne) {
        // add pet

        return Response.ok().entity("SUCCESS").build();
    }
}