package com.github.ebassani.electionmachine;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

@WebServlet(
        name = "Cookies",
        urlPatterns = {"/cookies"}
)
public class Cookies extends HttpServlet {

    public static final String COOKIE_NAME = "best-cookie";

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");

        // check if cookie already exists
        Cookie[] cookies = request.getCookies();

        // go through the cookies and try to find the one with name 'best-cookie'
        for (Cookie cookie : cookies) {
            if (Objects.equals(cookie.getName(), COOKIE_NAME)) {
                response.getWriter().println("<h2>Cookie name: " + cookie.getName() + "</h2>");
                response.getWriter().println("<h2>Cookie value: " + cookie.getValue() + "</h2>");
                response.getWriter().close();
                return;
            }
        }

        // if cookie wasn't found in the request
        response.addCookie(
                new Cookie(COOKIE_NAME, "hello!")
        );
        response.getWriter().println("<h1>Cookie not found! Refresh!</h1>");
    }
}
