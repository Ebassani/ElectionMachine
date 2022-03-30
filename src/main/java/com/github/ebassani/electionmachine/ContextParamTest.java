package com.github.ebassani.electionmachine;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(
        name = "ContextParamTest",
        urlPatterns = {"/contextparam"}
)
public class ContextParamTest extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");

        PrintWriter writer = response.getWriter();
        writer.println("<h1>Email: " + getServletContext().getInitParameter("email") + "</h1>");
        writer.close();
    }
}
