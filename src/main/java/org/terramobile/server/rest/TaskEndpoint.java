package org.terramobile.server.rest;

import java.util.Date;
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

import br.terracore.server.address.Address;
import br.terracore.server.form.Form;
import br.terracore.server.form.FormSchema;
import br.terracore.server.task.Task;
import br.terracore.server.user.User;

/**
 * 
 */
@Stateless
@Path("/tasks")
public class TaskEndpoint {
        @PersistenceContext(unitName = "terramobile-server-persistence-unit")
        private EntityManager em;
        
        @POST
        @Consumes("application/json")
        public Response create(Task entity) {
                em.persist(entity);
                return Response.created(UriBuilder.fromResource(TaskEndpoint.class).path(String.valueOf(entity.getId())).build()).build();
        }
        
        @DELETE
        @Path("/{id:[0-9][0-9]*}")
        public Response deleteById(@PathParam("id") Long id) {
                Task entity = em.find(Task.class, id);
                if (entity == null) { return Response.status(Status.NOT_FOUND).build(); }
                em.remove(entity);
                return Response.noContent().build();
        }
        
        @GET
        @Path("/{id:[0-9][0-9]*}")
        @Produces("application/json")
        public Response findById(@PathParam("id") Long id) {
                TypedQuery<Task> findByIdQuery = em.createQuery("SELECT DISTINCT t FROM Task t LEFT JOIN FETCH t.address LEFT JOIN FETCH t.user LEFT JOIN FETCH t.form WHERE t.id = :entityId ORDER BY t.id", Task.class);
                findByIdQuery.setParameter("entityId", id);
                Task entity;
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
        public List<Task> listAll(
                                  @QueryParam("start") Integer startPosition,
                                  @QueryParam("max") Integer maxResult) {
                TypedQuery<Task> findAllQuery = em.createQuery("SELECT DISTINCT t FROM Task t LEFT JOIN FETCH t.address LEFT JOIN FETCH t.user LEFT JOIN FETCH t.form ORDER BY t.id", Task.class);
                if (startPosition != null) {
                        findAllQuery.setFirstResult(startPosition);
                }
                if (maxResult != null) {
                        findAllQuery.setMaxResults(maxResult);
                }
                final List<Task> results = findAllQuery.getResultList();
                return results;
        }
        
        @PUT
        @Path("/{id:[0-9][0-9]*}")
        @Consumes("application/json")
        public Response update(Task entity) {
                entity = em.merge(entity);
                return Response.noContent().build();
        }
        
        @GET
        @Path("/generate")
        @Produces("application/json")
        public Response generateTasks() {
                try {
                        for (long i = 0; i < 10; i++) {
                                Long objid = null;
                                
                                Address address = new Address(objid, "name" + i, "number" + i, "extra" + i, 0.0, 0.0, "postalCode" + i, "city" + i, "state" + i, "featureId" + i, "neighborhood" + i);
                                
                                String json = "[{'name':'Bauru Digital','type':'meta'},{'name':'Pesquisar Endereço','type':'AutoCompleteTextView','id':'search_country','default':'','priority':'0','options':{'db_url':['http://192.168.0.171:8000/'],'db_name':['tasks.db'],'inputColumns':['name','number','featureId','postalCode'],'outputColumn':['featureId'],'presentationColumns':['name','featureId'],'tableName':['address']}},{'name':'Desconformidade','type':'Spinner','id':'spinner_disconform','default':'0','priority':'1','options':{'0':'Não detectada','1':'Desconforme','2':'Vago','3':'Desmembrado','4':'Unificado'},'hideOnClick':{'0':['number_confirmation','aditional_numbers','primary_use','secondary_use','camera_button'],'2':['number_confirmation','aditional_numbers','primary_use','secondary_use','camera_button']}},{'name':'Confirmação do Número','type':'Spinner','id':'number_confirmation','default':'0','priority':'2','options':{'0':'Ok','1':'Inexistente','2':'Não confere'},'hideOnClick':{'0':['aditional_numbers'],'1':['aditional_numbers']}},{'name':'Números Adicionais','type':'IntegerTextView','id':'aditional_numbers','default':'','priority':'3'},{'name':'Uso Principal','type':'Spinner','id':'primary_use','default':'0','priority':'4','options':{'0':'','1':'Nenhum','2':'Residencial','3':'Comercial','4':'Serviços','5':'Industrial','6':'Religioso','7':'Em Construção','8':'Indefinido'}},{'name':'Uso Secundário','type':'Spinner','id':'secondary_use','default':'0','priority':'5','options':{'0':'','1':'Nenhum','2':'Residencial','3':'Comercial','4':'Serviços','5':'Industrial','6':'Religioso','7':'Em Construção','8':'Indefinido'}},{'name':'Infraestrutura','type':'Accordion','id':'lbl_title_infrastructure','priority':'1','children':['pavimentation','asphalt_guide','public_ilumination','energy','pluvial_gallery','observation']},{'name':'Obter Foto','type':'camera_button','id':'camera_button','default':'','priority':'7'},{'name':'Pavimentação','type':'Spinner','id':'pavimentation','default':'0','priority':'8','options':{'0':'','1':'Asfalto','2':'Paralelepípedo','3':'Lajota Intervalada','4':'Sem Pavimentação','5':'Parcial'}},{'name':'Guias','type':'Spinner','id':'asphalt_guide','default':'0','priority':'9','options':{'0':'','1':'Sim','2':'Não','3':'Parcial'}},{'name':'Iluminação Pública','type':'Spinner','id':'public_ilumination','default':'0','priority':'10','options':{'0':'','1':'Sim','2':'Não','3':'Parcial'}},{'name':'Energia elétrica','type':'Spinner','id':'energy','default':'0','priority':'11','options':{'0':'','1':'Sim','2':'Não','3':'Parcial'}},{'name':'Galeria Pluvial','type':'Spinner','id':'pluvial_gallery','default':'0','priority':'12','options':{'0':'','1':'Sim','2':'Não','3':'Parcial'}},{'name':'Observações','type':'StringTextView','id':'observation','default':'','priority':'13'}]";
                                FormSchema schema = new FormSchema(objid, "Nome" + i, "description" + i, "type" + i, "icon" + i, json);
                                
                                String answer = "{'asphalt_guide':'Sim','primary_use':'Religioso','observation':'','search_country':'','aditional_numbers':'','pavimentation':'Asfalto','public_ilumination':'Sim','number_confirmation':'Ok','pluvial_gallery':'Não','secondary_use':'Religioso','energy':'Parcial','spinner_disconform':'Desconforme'}";
                                Form form = new Form(objid, schema, new Date(), answer);
                                
                                User user = new User(objid, "name" + i, "login" + i, "password" + i, "hash" + i);
                                
                                Task task;
                                
                                if (i % 2 == 0) {
                                        task = new Task(objid, address, user, form, true);
                                }
                                else {
                                        task = new Task(objid, address, user, form, false);
                                }
                                
                                em.merge(task);
                        }
                }
                catch (Exception e) {
                        e.printStackTrace();
                        return Response.ok("Erro no servidor... " + e.getCause() + e.getMessage()).build();
                }
                
                return Response.ok("Criado!! :D").build();
        }
}