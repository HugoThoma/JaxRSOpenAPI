package fr.istic.taa.jaxrs;



import fr.istic.taa.jaxrs.rest.PersonneRessource;
import fr.istic.taa.jaxrs.rest.SwaggerResource;
import io.swagger.v3.jaxrs2.integration.resources.OpenApiResource;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.util.HashSet;
import java.util.Set;

@ApplicationPath("/")
public class RestApplication extends Application {


    @Override
    public Set<Class<?>> getClasses() {
        final Set<Class<?>> resources = new HashSet<>();


        // SWAGGER endpoints
        resources.add(OpenApiResource.class);
        resources.add(PersonneRessource.class);
        //NEW LINE TO ADD
        resources.add(SwaggerResource.class);

        return resources;
    }
}
