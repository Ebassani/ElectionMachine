package com.github.ebassani.electionmachine.service;

import com.github.ebassani.electionmachine.data.AnswerDao;
import com.github.ebassani.electionmachine.data.QuestionDao;
import com.github.ebassani.electionmachine.data.entity.AnswersEntity;
import com.github.ebassani.electionmachine.data.entity.QuestionsEntity;
import com.github.ebassani.electionmachine.data.entity.UsersEntity;
import com.github.ebassani.electionmachine.data.model.Answer;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/questions")
public class QuestionsService {

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa247");

    @GET
    @Path("/read")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public List<QuestionsEntity> readQuestion() {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        List list = em.createQuery("select a from QuestionsEntity a").getResultList();
        em.getTransaction().commit();
        return list;
    }

    @POST
    @Path("/add")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public void addQuestion(@FormParam("question") String question) {
        QuestionsEntity ques = new QuestionsEntity();
        ques.setText(question);
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(ques);
        em.getTransaction().commit();

        em.getTransaction().begin();
        List allUsers = em.createQuery("select a from UsersEntity a").getResultList();
        em.getTransaction().commit();

        em.getTransaction().begin();
        for (Object user : allUsers) {
            AnswersEntity answer = new AnswersEntity();
            answer.setValue(4);
            answer.setQuestionId(ques.getId());
            answer.setUserId(((UsersEntity) user).getId());
            em.persist(answer);
        }
        em.getTransaction().commit();
    }

    @POST
    @Path("/update")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public void updateQuestions(
            @FormParam("id") int id,
            @FormParam("question") String questionText
    ) {
        QuestionsEntity question = new QuestionsEntity();
        question.setId(id);
        question.setText(questionText);

        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        QuestionsEntity f = em.find(QuestionsEntity.class, question.getId());
        if (f != null) {
            em.merge(question);
        }
        em.getTransaction().commit();
    }

    @POST
    @Path("/delete")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public void deleteQuestion(@FormParam("id") int id) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        QuestionsEntity f = em.find(QuestionsEntity.class, id);
        if (f != null) {
            em.remove(f);
        }
        em.getTransaction().commit();
    }

}