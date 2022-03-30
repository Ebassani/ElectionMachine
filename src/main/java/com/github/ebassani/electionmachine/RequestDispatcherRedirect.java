package com.github.ebassani.electionmachine;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(
        name = "RequestDispatcherRedirect",
        urlPatterns = {"/rdredirect"}
)
public class RequestDispatcherRedirect extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        RequestDispatcher rd = request.getRequestDispatcher("/welcome");
        rd.forward(request, response);
    }
}
