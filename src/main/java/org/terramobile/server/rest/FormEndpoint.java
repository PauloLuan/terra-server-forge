package org.terramobile.server.rest;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriBuilder;

import br.terracore.server.form.Form;

/**
 * 
 */
@Stateless
@Path("/forms")
public class FormEndpoint {
        @PersistenceContext(unitName = "terramobile-server-persistence-unit")
        private EntityManager em;
        
        @POST
        @Consumes("application/json")
        public Response create(Form entity) {
                em.persist(entity);
                return Response.created(UriBuilder.fromResource(FormEndpoint.class).path(String.valueOf(entity.getId())).build()).build();
        }
        
        @DELETE
        @Path("/{id:[0-9][0-9]*}")
        public Response deleteById(@PathParam("id") Long id) {
                Form entity = em.find(Form.class, id);
                if (entity == null) { return Response.status(Status.NOT_FOUND).build(); }
                em.remove(entity);
                return Response.noContent().build();
        }
        
        @GET
        @Path("/{id:[0-9][0-9]*}")
        @Produces("application/json")
        public Response findById(@PathParam("id") Long id) {
                TypedQuery<Form> findByIdQuery = em.createQuery("SELECT DISTINCT f FROM Form f LEFT JOIN FETCH f.schema WHERE f.id = :entityId ORDER BY f.id", Form.class);
                findByIdQuery.setParameter("entityId", id);
                Form entity;
                try {
                        entity = findByIdQuery.getSingleResult();
                }
                catch (NoResultException nre) {
                        entity = null;
                }
                if (entity == null) { return Response.status(Status.NOT_FOUND).build(); }
                return Response.ok(entity).build();
        }
        
        @GET
        @Produces("application/json")
        public List<Form> listAll(
                                  @QueryParam("start") Integer startPosition,
                                  @QueryParam("max") Integer maxResult) {
                TypedQuery<Form> findAllQuery = em.createQuery("SELECT DISTINCT f FROM Form f LEFT JOIN FETCH f.schema ORDER BY f.id", Form.class);
                if (startPosition != null) {
                        findAllQuery.setFirstResult(startPosition);
                }
                if (maxResult != null) {
                        findAllQuery.setMaxResults(maxResult);
                }
                final List<Form> results = findAllQuery.getResultList();
                return results;
        }
        
        @PUT
        @Path("/{id:[0-9][0-9]*}")
        @Consumes("application/json")
        public Response update(Form entity) {
                entity = em.merge(entity);
                return Response.noContent().build();
        }
}