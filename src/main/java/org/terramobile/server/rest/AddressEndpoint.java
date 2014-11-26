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
import br.terracore.server.address.Address;

/**
 * 
 */
@Stateless
@Path("/addresses")
public class AddressEndpoint
{
   @PersistenceContext(unitName="terramobile-server-persistence-unit")
   private EntityManager em;

   @POST
   @Consumes("application/xml")
   public Response create(Address entity)
   {
      em.persist(entity);
      return Response.created(UriBuilder.fromResource(AddressEndpoint.class).path(String.valueOf(entity.getId())).build()).build();
   }

   @DELETE
   @Path("/{id:[0-9][0-9]*}")
   public Response deleteById(@PathParam("id") Long id)
   {
      Address entity = em.find(Address.class, id);
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
      TypedQuery<Address> findByIdQuery = em.createQuery("SELECT DISTINCT a FROM Address a WHERE a.id = :entityId ORDER BY a.id", Address.class);
      findByIdQuery.setParameter("entityId", id);
      Address entity;
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
   public List<Address> listAll(@QueryParam("start") Integer startPosition, @QueryParam("max") Integer maxResult)
   {
      TypedQuery<Address> findAllQuery = em.createQuery("SELECT DISTINCT a FROM Address a ORDER BY a.id", Address.class);
      if (startPosition != null)
      {
         findAllQuery.setFirstResult(startPosition);
      }
      if (maxResult != null)
      {
         findAllQuery.setMaxResults(maxResult);
      }
      final List<Address> results = findAllQuery.getResultList();
      return results;
   }

   @PUT
   @Path("/{id:[0-9][0-9]*}")
   @Consumes("application/xml")
   public Response update(Address entity)
   {
      entity = em.merge(entity);
      return Response.noContent().build();
   }
}