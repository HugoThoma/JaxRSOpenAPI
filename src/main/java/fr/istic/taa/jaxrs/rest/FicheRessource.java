package fr.istic.taa.jaxrs.rest;

import fr.istic.taa.jaxrs.dao.EntityManagerHelper;
import fr.istic.taa.jaxrs.dao.FicheDAO;
import fr.istic.taa.jaxrs.dao.PersonneDAO;
import io.swagger.v3.oas.annotations.Parameter;
import fr.istic.taa.jaxrs.domain.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/fiche")
//@Produces({"application/json","application/xml"})
@Produces(MediaType.APPLICATION_JSON)
public class FicheRessource {

    @GET
    @Path("/{ficheId}")
    public Response getFicheById(@PathParam("ficheId") Long ficheId) {
        FicheDAO ficheDAO = new FicheDAO();
        Fiche fiche = ficheDAO.getFicheByID(ficheId);
        String type = fiche instanceof BugFiche ? "Bug" : "Feature";
        // return pet
        if (type == "Bug") {
            BugFiche bug = new BugFiche(fiche.getTitle(),fiche.getUser());
            bug.setDescription(fiche.getDescription());
            bug.setDateEmission(fiche.getDateEmission());
            bug.setId(fiche.getId());
            bug.setSupport(fiche.getSupport());
            bug.setDatePriseenCharge(fiche.getDatePriseenCharge());
            bug.setDateCloture(fiche.getDateCloture());
            bug.setTags(fiche.getTags());
            return Response
                    .ok(bug)
                    .header("Access-Control-Allow-Origin", "*")
                    .header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT")
                    .header("Access-Control-Allow-Headers", "Content-Type")
                    .build();
        } else {

            FeatureRequestFiche feature = new FeatureRequestFiche(fiche.getTitle(),fiche.getUser());
            feature.setDescription(fiche.getDescription());
            feature.setDateEmission(fiche.getDateEmission());
            feature.setId(fiche.getId());
            feature.setSupport(fiche.getSupport());
            feature.setDatePriseenCharge(fiche.getDatePriseenCharge());
            feature.setDateCloture(fiche.getDateCloture());
            feature.setTags(fiche.getTags());
            return Response
                    .ok(feature)
                    .header("Access-Control-Allow-Origin", "*")
                    .header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT")
                    .header("Access-Control-Allow-Headers", "Content-Type")
                    .build();
        }

    }
    @GET
    @Path("/all")
    public Response getAllFiches() {
        FicheDAO ficheDAO = new FicheDAO();
        List<Fiche> fiches = ficheDAO.getAllFiches();
        return Response
                .ok(fiches)
                .header("Access-Control-Allow-Origin", "*")
                .header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT")
                .header("Access-Control-Allow-Headers", "Content-Type")
                .build();

    }

    /*
    @POST
    @Consumes("application/json")
    public Response addFiche(
            @Parameter(description = "Fiche object that needs to be added to the list", required = true) Fiche fiche) {
        // add pet

        return Response.ok().entity("SUCCESS").build();
    }*/
    @POST
    @Path("/addFiche") // Utilisation : http://localhost:8080/fiche/addFiche?type=bug&title=MonTitre&description=MaDescription&userID=1
    @Consumes("application/json")
    public Response addFiche(
            @QueryParam("type") String type,
            @QueryParam("title") String title,
            @QueryParam("description") String description,
            @QueryParam("userID") Long userID) {
        // add Fiche
        FicheDAO ficheDAO = new FicheDAO();
        Fiche fiche;
        if(type.equalsIgnoreCase("bug")){
            fiche = new BugFiche(title);
        }else{
            fiche = new FeatureRequestFiche(title);
        }

        fiche.setDescription(description);

        PersonneDAO personneDAO = new PersonneDAO();
        Personne user = personneDAO.getPersonneByID(userID);

        fiche.setUser((User) user);

        EntityManager manager = EntityManagerHelper.getEntityManager();
        EntityTransaction tx = manager.getTransaction();
        tx.begin();
        ficheDAO.save(fiche);
        tx.commit();
        manager.close();

        return Response.ok().entity("ADD Fiche SUCCESS : " + fiche).build();
    }



}
