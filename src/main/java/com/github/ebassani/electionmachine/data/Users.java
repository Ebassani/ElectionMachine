package com.github.ebassani.electionmachine.data;

import com.github.ebassani.electionmachine.data.model.Question;
import com.github.ebassani.electionmachine.data.model.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/users")
public class Users {

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("election-machine");

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<User> readUsers() {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        List list = em.createQuery("SELECT a FROM Users a").getResultList();
        em.getTransaction().commit();
        return list;
    }

    @POST
    public void addUser(@FormParam("question") String question) {
        Question ques = new Question(question);
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(ques);
        em.getTransaction().commit();
    }

    @PUT
    public void updateUser(User newUser) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        User f = em.find(User.class, newUser.getId());
        if (f != null) {
            em.merge(newUser);
        }
        em.getTransaction().commit();
    }

    @DELETE
    @Path("/{id}")
    public void deleteUser(@PathParam("id") int id) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        User f = em.find(User.class, id);
        if (f != null) {
            em.remove(f);
        }
        em.getTransaction().commit();
    }

}
