package com.github.ebassani.electionmachine.service;

import com.github.ebassani.electionmachine.Util;
import com.github.ebassani.electionmachine.data.UserDao;
import com.github.ebassani.electionmachine.data.entity.UsersEntity;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Path("/users")
public class UsersService {

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa247");

    @GET
    @Path("/read")
    @Produces(MediaType.APPLICATION_JSON)
    public List<UsersEntity> readUsers() {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        List list = em.createQuery("SELECT a FROM UsersEntity a").getResultList();
        em.getTransaction().commit();
        return list;
    }

    // email, password_hash, is_admin, is_candidate, names, surnames, region, age
    @POST
    @Path("/add")
    public void addUser(
            @FormParam("email") String email,
            @FormParam("password") String password,
            @FormParam("names") String names,
            @FormParam("surnames") String surnames,
            @FormParam("region") String region,
            @FormParam("age") int age,
            @FormParam("admin") String admin) {
        UsersEntity user = new UsersEntity();

        System.out.println(email);

        user.setEmail(email);
        user.setPasswordHash(Util.hashPassword(password));
        user.setNames(names);
        user.setSurnames(surnames);
        user.setIsAdmin(Objects.equals(admin, "true"));
        user.setIsCandidate(!Objects.equals(admin, "true"));
        user.setAge(age);
        user.setRegion(region);

        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(user);
        em.getTransaction().commit();
    }

    @POST
    @Path("/update")
    public void updateUser(
            @Context HttpServletRequest request,
            @Context HttpServletResponse response,
            @FormParam("id") int id,
            @FormParam("names") String names,
            @FormParam("surnames") String surnames,
            @FormParam("region") String region,
            @FormParam("age") int age,
            @FormParam("admin") String admin
    ) {
        System.out.println(Arrays.toString(request.getCookies()));
        System.out.println(response.getHeaderNames());

        UsersEntity user = new UsersEntity();
        user.setId(id);
        user.setNames(names);
        user.setSurnames(surnames);
        user.setRegion(region);
        user.setAge(age);
        user.setIsAdmin(Objects.equals(admin, "true"));
        user.setIsCandidate(!Objects.equals(admin, "true"));

        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        UsersEntity f = em.find(UsersEntity.class, user.getId());
        if (f != null) {
            user.setEmail(f.getEmail());
            user.setPasswordHash(f.getPasswordHash());
            em.merge(user);
        }
        em.getTransaction().commit();
    }

    @POST
    @Path("/delete")
    public void deleteUser(@FormParam("id") int id) {
        try {
            UserDao.removeUser(id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
