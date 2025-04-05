package io.github.gitagliaudyte.estamate.rest;

import io.github.gitagliaudyte.estamate.entities.Property;
import io.github.gitagliaudyte.estamate.persistence.PropertiesDAO;
import io.github.gitagliaudyte.estamate.rest.contracts.OwnerPropertyDto;
import io.github.gitagliaudyte.estamate.rest.contracts.PropertyDto;
import lombok.Getter;
import lombok.Setter;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
@Path("/property-details")
public class PropertiesController {
    @Inject
    @Setter @Getter
    private PropertiesDAO propertiesDAO;

    @Path("/get-all")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAll() {
        List<Property> properties = propertiesDAO.loadAll();
        if(properties.isEmpty()) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        List<PropertyDto> propertyDtos = properties.stream()
                .map(PropertyDto::convertToPropertyDto)
                .collect(Collectors.toList());
        return Response.ok(propertyDtos).build();
    }

    @Path("/get{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getById(@PathParam("id") Integer id) {
        Property property = propertiesDAO.findById(id);
        if(property == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        PropertyDto propertyDto = PropertyDto.convertToPropertyDto(property);
        return Response.ok(propertyDto).build();
    }

    @Path("/post")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public Response create(OwnerPropertyDto ownerPropertyDto) {
        Property property = new Property();
        property.setAddress(ownerPropertyDto.getAddress());
        property.setPrice(ownerPropertyDto.getPrice());
        property.setOwner(ownerPropertyDto.getOwner());
        propertiesDAO.persist(property);
        return Response.status(Response.Status.CREATED).build();
    }
}
