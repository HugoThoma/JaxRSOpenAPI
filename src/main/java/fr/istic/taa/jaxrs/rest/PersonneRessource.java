package fr.istic.taa.jaxrs.rest;


import fr.istic.taa.jaxrs.dao.EntityManagerHelper;
import fr.istic.taa.jaxrs.dao.PersonneDAO;
import fr.istic.taa.jaxrs.domain.Personne;
import fr.istic.taa.jaxrs.domain.SupportMember;
import fr.istic.taa.jaxrs.domain.User;
import io.swagger.v3.oas.annotations.Parameter;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/personne")
@Produces({"application/json"})
public class PersonneRessource {

    @GET
    @Path("/all")
    public Response getAllPersonnes() {
        PersonneDAO personneDAO = new PersonneDAO();
        List<Personne> personnes = personneDAO.getAllPersonnes();
        return Response
                .ok(personnes)
                .header("Access-Control-Allow-Origin", "*")
                .header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT")
                .header("Access-Control-Allow-Headers", "Content-Type")
                .build();
    }

    @GET
    @Path("/{personneId}")
    public Response getPersonneById(@PathParam("personneId") Long personneId) {
        PersonneDAO personneDAO = new PersonneDAO();
        Personne personne = personneDAO.getPersonneByID(personneId);
        String type = personne instanceof User ? "User" : "SupportMember";
        // return pet
        if(type == "User"){
            User user = new User();
            user.setId_member(personne.getId_member());
            user.setName(personne.getName());
            return Response
                    .ok(user)
                    .header("Access-Control-Allow-Origin", "*")
                    .header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT")
                    .header("Access-Control-Allow-Headers", "Content-Type")
                    .build();
        } else{
            SupportMember support = new SupportMember();
            support.setId_member(personne.getId_member());
            support.setName(personne.getName());
            return Response
                    .ok(support)
                    .header("Access-Control-Allow-Origin", "*")
                    .header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT")
                    .header("Access-Control-Allow-Headers", "Content-Type")
                    .build();
        }

    }

    //curl -X POST -H 'Content-type:application/json' -H 'Accept:application/json' -d '{"name":"peter"}' http://localhost:8080/personne
    /*
    @POST
    @Consumes("application/json")
    public Response addPersonne(
            @Parameter(description = "Personne object that needs to be added to the list", required = true) Personne personne) {
        // add pet

        return Response.ok().entity("SUCCESS").build();
    }*/
    @POST
    @Path("/addPersonne/{nom}")
    @Consumes("application/json")
    public Response addPersonne(
            @PathParam("nom") String nom) {
        // add Personne
        PersonneDAO personneDAO = new PersonneDAO();
        EntityManager manager = EntityManagerHelper.getEntityManager();
        EntityTransaction tx = manager.getTransaction();
        tx.begin();
        User user = new User(nom);
        personneDAO.save(user);
        tx.commit();
        manager.close();

        return Response.ok().entity("ADD User SUCCESS : " + user).build();
    }
}