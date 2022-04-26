package com.github.ebassani.electionmachine;


import com.github.ebassani.electionmachine.data.QuestionDao;

import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

@Path("/questionhandler")
public class QuestionHandler {

    @POST
    @Path("/add")
    public void addQuestion(@FormParam("question") String question){
        QuestionDao.createQuestion(question);
    }


}
