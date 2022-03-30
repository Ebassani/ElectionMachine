package com.github.ebassani.electionmachine;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;

/*
 * The name of the servlet is AddGame
 * and the servlet's URI (url-pattern) is 'addgame'
 */
@WebServlet(
        name = "AddGame",
        urlPatterns = {"/addgame"}
)
public class AddGame extends HttpServlet {

    Dao dao = new Dao();

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        doGet(request, response);
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        RequestDispatcher rd = request.getRequestDispatcher("htmlstart.html");
        rd.include(request, response);

        String breed = request.getParameter("breed");
        String weight = request.getParameter("weight");

        if (breed != null && weight != null) {
            dao.createCat(breed, weight);
        }

        try {
            ResultSet rs = dao.selectAllCats();
            out.println("<ol>");
            while (rs.next()) {
                out.println("<li>" + rs.getString("breed") + " (" + rs.getString("weight") + ")</li>");
            }
        } catch (Exception ignored) {
        }

        out.println("<a href='/form.html'>Back to form</a>");

        rd = request.getRequestDispatcher("htmlend.html");
        rd.include(request, response);
    }
}