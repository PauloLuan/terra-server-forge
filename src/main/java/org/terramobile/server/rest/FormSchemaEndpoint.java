package org.terramobile.server.rest;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriBuilder;
import br.terracore.server.form.FormSchema;

/**
 * 
 */
@Stateless
@Path("/formschemas")
public class FormSchemaEndpoint
{
   @PersistenceContext(unitName="terramobile-server-persistence-unit")
   private EntityManager em;

   @POST
   @Consumes("application/xml")
   public Response create(FormSchema entity)
   {
      em.persist(entity);
      return Response.created(UriBuilder.fromResource(FormSchemaEndpoint.class).path(String.valueOf(entity.getId())).build()).build();
   }

   @DELETE
   @Path("/{id:[0-9][0-9]*}")
   public Response deleteById(@PathParam("id") Long id)
   {
      FormSchema entity = em.find(FormSchema.class, id);
      if (entity == null) {
        return Response.status(Status.NOT_FOUND).build();
      }
      em.remove(entity);
      return Response.noContent().build();
   }

   @GET
   @Path("/{id:[0-9][0-9]*}")
   @Produces("application/xml")
   public Response findById(@PathParam("id") Long id)
   {
      TypedQuery<FormSchema> findByIdQuery = em.createQuery("SELECT DISTINCT f FROM FormSchema f WHERE f.id = :entityId ORDER BY f.id", FormSchema.class);
      findByIdQuery.setParameter("entityId", id);
      FormSchema entity;
      try {
         entity = findByIdQuery.getSingleResult();
      } catch (NoResultException nre) {
         entity = null;
      }
      if (entity == null) {
        return Response.status(Status.NOT_FOUND).build();
      }
      return Response.ok(entity).build();
   }

   @GET
   @Produces("application/xml")
   public List<FormSchema> listAll(@QueryParam("start") Integer startPosition, @QueryParam("max") Integer maxResult)
   {
      TypedQuery<FormSchema> findAllQuery = em.createQuery("SELECT DISTINCT f FROM FormSchema f ORDER BY f.id", FormSchema.class);
      if (startPosition != null)
      {
         findAllQuery.setFirstResult(startPosition);
      }
      if (maxResult != null)
      {
         findAllQuery.setMaxResults(maxResult);
      }
      final List<FormSchema> results = findAllQuery.getResultList();
      return results;
   }

   @PUT
   @Path("/{id:[0-9][0-9]*}")
   @Consumes("application/xml")
   public Response update(FormSchema entity)
   {
      entity = em.merge(entity);
      return Response.noContent().build();
   }
}