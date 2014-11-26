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

import br.terracore.server.photo.Photo;

/**
 * 
 */
@Stateless
@Path("/photos")
public class PhotoEndpoint {
        @PersistenceContext(unitName = "terramobile-server-persistence-unit")
        private EntityManager em;
        
        @POST
        @Consumes("application/json")
        public Response create(Photo entity) {
                em.persist(entity);
                return Response.created(UriBuilder.fromResource(PhotoEndpoint.class).path(String.valueOf(entity.getId())).build()).build();
        }
        
        @DELETE
        @Path("/{id:[0-9][0-9]*}")
        public Response deleteById(@PathParam("id") Long id) {
                Photo entity = em.find(Photo.class, id);
                if (entity == null) { return Response.status(Status.NOT_FOUND).build(); }
                em.remove(entity);
                return Response.noContent().build();
        }
        
        @GET
        @Path("/{id:[0-9][0-9]*}")
        @Produces("application/json")
        public Response findById(@PathParam("id") Long id) {
                TypedQuery<Photo> findByIdQuery = em.createQuery("SELECT DISTINCT p FROM Photo p LEFT JOIN FETCH p.form WHERE p.id = :entityId ORDER BY p.id", Photo.class);
                findByIdQuery.setParameter("entityId", id);
                Photo entity;
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
        public List<Photo> listAll(
                                   @QueryParam("start") Integer startPosition,
                                   @QueryParam("max") Integer maxResult) {
                TypedQuery<Photo> findAllQuery = em.createQuery("SELECT DISTINCT p FROM Photo p LEFT JOIN FETCH p.form ORDER BY p.id", Photo.class);
                if (startPosition != null) {
                        findAllQuery.setFirstResult(startPosition);
                }
                if (maxResult != null) {
                        findAllQuery.setMaxResults(maxResult);
                }
                final List<Photo> results = findAllQuery.getResultList();
                return results;
        }
        
        @PUT
        @Path("/{id:[0-9][0-9]*}")
        @Consumes("application/json")
        public Response update(Photo entity) {
                entity = em.merge(entity);
                return Response.noContent().build();
        }
}