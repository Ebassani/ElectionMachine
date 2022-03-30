package com.github.ebassani.electionmachine;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(
        name = "ResponseSendRedirect",
        urlPatterns = {"/rsredirect"}
)
public class ResponseSendRedirect extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.sendRedirect("/welcome");
    }
}
