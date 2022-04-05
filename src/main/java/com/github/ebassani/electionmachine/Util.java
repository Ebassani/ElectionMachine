package com.github.ebassani.electionmachine;

import javax.servlet.http.HttpServletRequest;

public class Util {

    public static int isLoggedIn(HttpServletRequest req) {
        return (int) req.getSession().getAttribute("user_id");
    }

}
