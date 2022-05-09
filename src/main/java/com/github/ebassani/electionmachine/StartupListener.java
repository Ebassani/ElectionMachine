package com.github.ebassani.electionmachine;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class StartupListener implements ServletContextListener {

    /**
     * Runs on the beginning to load all env variables from the secrets.txt file
     */
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("Context is being initialized.");
        try {
            Constants.load();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

    }
}
