package com.github.ebassani.electionmachine;

import java.io.IOException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(
        name = "HelloAppEngine",
        urlPatterns = {"/welcome", "/hello"}
)
public class HelloAppEngine extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

        response.setContentType("text/plain");
        response.setCharacterEncoding("UTF-8");

        int randomNumber = (int) (Math.random() * 10) + 1;

        response.getWriter().print("Hello Martin!\r\n");
        response.getWriter().print("Random number: " + randomNumber);
    }
}