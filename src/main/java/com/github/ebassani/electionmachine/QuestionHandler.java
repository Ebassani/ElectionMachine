package com.github.ebassani.electionmachine;

import com.github.ebassani.electionmachine.data.model.Question;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/questionhandler")
public class QuestionHandler {

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("election-machine");

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

    @POST
    @Path("/add")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public List<Question> addQuestion(@FormParam("question") String question) {
        Question ques = new Question(question);
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(ques);
        em.getTransaction().commit();
        List<Question> list = readQuestion();
        return list;
    }

    @PUT
    @Path("/update")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public List<Question> updateQuestion(Question ques) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Question f = em.find(Question.class, ques.getId());
        if (f != null) {
            em.merge(ques);
        }
        em.getTransaction().commit();
        List<Question> list = readQuestion();
        return list;
    }

    @DELETE
    @Path("/delete/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public List<Question> deleteQuestion(@PathParam("id") int id) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Question f = em.find(Question.class, id);
        if (f != null) {
            em.remove(f);
        }
        em.getTransaction().commit();
        List<Question> list = readQuestion();
        return list;
    }

}
