package com.github.ebassani.electionmachine;

import freemarker.template.Configuration;
import freemarker.template.TemplateExceptionHandler;

import java.io.File;
import java.io.IOException;

/**
 * Config utility class for FreeMarker.
 */
public class FMConfiguration {

    private static Configuration fmConfigInstance = null;

    /**
     * Returns an instance of FreeMarker Configuration.
     */
    public static Configuration getInstance() throws IOException {
        if (fmConfigInstance == null) {
            fmConfigInstance = new Configuration(Configuration.VERSION_2_3_21);
            fmConfigInstance.setDirectoryForTemplateLoading(new File("templates/"));
            fmConfigInstance.setDefaultEncoding("UTF-8");
            fmConfigInstance.setTemplateExceptionHandler(TemplateExceptionHandler.HTML_DEBUG_HANDLER);
            fmConfigInstance.setLogTemplateExceptions(false);
            fmConfigInstance.setWrapUncheckedExceptions(true);
            fmConfigInstance.setFallbackOnNullLoopVariable(false);
        }

        return fmConfigInstance;
    }

}
