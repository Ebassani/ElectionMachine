package com.github.ebassani.electionmachine;


import com.github.ebassani.electionmachine.data.QuestionDao;
import com.github.ebassani.electionmachine.data.model.Question;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/questionhandler")
public class QuestionHandler {

    @POST
    @Path("/add")
    @Produces(MediaType.TEXT_HTML)
    public String addQuestion(@FormParam("question") String question){
        Question ques=new Question(question);
        EntityManagerFactory emf= Persistence.createEntityManagerFactory("questions");
        EntityManager em=emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(ques);
        em.getTransaction().commit();

        return "ques";
    }


}
