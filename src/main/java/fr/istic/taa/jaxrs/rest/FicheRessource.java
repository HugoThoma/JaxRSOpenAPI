package fr.istic.taa.jaxrs.rest;

import fr.istic.taa.jaxrs.dao.FicheDAO;
import io.swagger.v3.oas.annotations.Parameter;
import fr.istic.taa.jaxrs.domain.*;

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
    public Fiche getFicheById(@PathParam("ficheId") Long ficheId) {
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
            return bug;
        } else {

            FeatureRequestFiche feature = new FeatureRequestFiche(fiche.getTitle(),fiche.getUser());
            feature.setDescription(fiche.getDescription());
            feature.setDateEmission(fiche.getDateEmission());
            feature.setId(fiche.getId());
            feature.setSupport(fiche.getSupport());
            feature.setDatePriseenCharge(fiche.getDatePriseenCharge());
            feature.setDateCloture(fiche.getDateCloture());
            feature.setTags(fiche.getTags());
            return feature;
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

    @POST
    @Consumes("application/json")
    public Response addFiche(
            @Parameter(description = "Fiche object that needs to be added to the list", required = true) Fiche fiche) {
        // add pet

        return Response.ok().entity("SUCCESS").build();
    }

}
