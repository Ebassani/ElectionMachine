package com.github.ebassani.electionmachine;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@WebServlet(
        name = "Conversion",
        urlPatterns = {"/conversion"}
)
public class Conversion extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<p>");

        try {
            double centimeters = Double.parseDouble(request.getParameter("centimeters"));
            out.println(centimeters + " cm = " + (centimeters / 2.54) + " in<br/>");
        } catch (Exception ignored) {}

        try {
            double inches = Double.parseDouble(request.getParameter("inches"));
            out.println(inches + " in = " + (inches * 2.54) + " cm<br/>");
        } catch (Exception ignored) {}

        try {
            double meters = Double.parseDouble(request.getParameter("meters"));
            out.println(meters + " m = " + (meters * 1.0936133) + " yd<br/>");
        } catch (Exception ignored) {}

        try {
            double yards = Double.parseDouble(request.getParameter("yards"));
            out.println(yards + " yd = " + (yards / 1.0936133) + " m<br/>");
        } catch (Exception ignored) {}

        out.println("</p>");
        out.close();
    }
}
