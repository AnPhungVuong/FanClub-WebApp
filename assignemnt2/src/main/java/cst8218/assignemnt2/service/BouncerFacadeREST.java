/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cst8218.assignemnt2.service;

import cst8218.assignemnt2.entity.Bouncer;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author anvp0
 */
@Stateless
@Path("cst8218.assignemnt2.entity.Bouncer")
public class BouncerFacadeREST extends AbstractFacade<Bouncer> {

    @PersistenceContext(unitName = "my_persistence_unit")
    private EntityManager em;

    public BouncerFacadeREST() {
        super(Bouncer.class);
    }

/**
 * This class POST to root, will handle the creating of bouncer and return bad request if needed.
 * 
 */

    @POST
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @Produces(MediaType.APPLICATION_JSON)
    public Response creates(Bouncer entity) {
        if (entity.getId() == null) {
            // Initialize non-id null values to reasonable defaults
            // ...
            entity.setX(0);
            entity.setY(0);
            entity.setYSpeed(0);

            // Create the new Bouncer
            super.create(entity);

            // Return the newly created Bouncer in the response body
            return Response.status(Response.Status.CREATED).entity(entity).build();
        } else {
            // If the id is not null, it is a bad request
            return Response.status(Response.Status.BAD_REQUEST).entity("").build();
        }

    }
    
 /**
 * This class POST to id, will handle the id existing and give out the possible status
 * 
 */
    @POST
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateBouncer(@PathParam("id") Long id, Bouncer entity) {
        
        if (super.find(id) == null) {
            return Response.status(Response.Status.NOT_FOUND).entity("ID not found in database").build();
        }
        
        if (entity.getId() != null && !entity.getId().equals(id)) {
            return Response.status(Response.Status.BAD_REQUEST).entity("Bouncer has a non-null non-matching ID").build();
        }
        Bouncer existingBouncer = super.find(id);
        existingBouncer.updates(entity);
        super.edit(existingBouncer);
        return Response.status(Response.Status.OK).entity(existingBouncer).build();
    }
 /**
 * This class PUT to id, will handle the id existing and give out the possible status
 * 
 */   
    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @Produces(MediaType.APPLICATION_JSON)
    public Response edit(@PathParam("id") Long id, Bouncer entity) {
        if (super.find(entity.getId()) == null) {
            return Response.status(Response.Status.NOT_FOUND).entity("ID not found in database").build();
        }

        if (entity.getId() != null && !entity.getId().equals(id)) {
            return Response.status(Response.Status.BAD_REQUEST).entity(entity.getId()).build();
        }
        Bouncer existingBouncer = super.find(id);
//        if(entity.getId() != null){
//            existingBouncer.setId(entity.getId());
//        }

        if(entity.getX() != null){
            existingBouncer.setX(entity.getX());
        }else{
            existingBouncer.setX(0);
        }
        
        if(entity.getY() != null){
            existingBouncer.setY(entity.getY());
        }else{
            existingBouncer.setY(0);
        }
        if(entity.getYSpeed() != null){
            existingBouncer.setYSpeed(entity.getYSpeed());
        }else{
            existingBouncer.setYSpeed(0);
        }
        super.edit(existingBouncer);
        
        return Response.status(Response.Status.OK).entity(existingBouncer).build();
    }
    
 /**
 * This class PUT to root, will give out the error not allow
 * 
 */
    @PUT
    public Response notAllow(){
        return Response.status(Response.Status.METHOD_NOT_ALLOWED).entity("root resource (bouncer table) is not allowed").build();
    }
    
    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") Long id) {
        super.remove(super.find(id));
    }

    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Bouncer find(@PathParam("id") Long id) {
        return super.find(id);
    }

    @GET
    @Override
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Bouncer> findAll() {
        return super.findAll();
    }

    @GET
    @Path("{from}/{to}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Bouncer> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
        return super.findRange(new int[]{from, to});
    }
/**
 * This class GET to count, will count the bouncer
 * 
 */

    @GET
    @Path("count")
    @Produces(MediaType.APPLICATION_JSON)
    public Response bouncerCount() {
        int count = super.count();
        return Response.status(Response.Status.ACCEPTED).entity("Hello World123").build();
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

}
