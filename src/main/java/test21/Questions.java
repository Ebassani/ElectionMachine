package test21;

import javax.ws.rs.POST;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.core.MediaType;

@Path("/questions")
public class Questions {
	
	EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa247");


	@GET
    @Path("/read")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public List<Question> readQuestion() {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        List list = em.createQuery("select a from Question a").getResultList();
        em.getTransaction().commit();
        return list;
    }

}
