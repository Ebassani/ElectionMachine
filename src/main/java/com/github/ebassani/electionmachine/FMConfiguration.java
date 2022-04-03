package com.github.ebassani.electionmachine;

import freemarker.template.Configuration;
import freemarker.template.TemplateExceptionHandler;

import java.io.File;
import java.io.IOException;

public class FMConfiguration {

    private static Configuration fmConfigInstance = null;

    private FMConfiguration() throws IOException {
        fmConfigInstance.setDirectoryForTemplateLoading(new File("templates/"));
        fmConfigInstance.setDefaultEncoding("UTF-8");
        fmConfigInstance.setTemplateExceptionHandler(TemplateExceptionHandler.HTML_DEBUG_HANDLER);
        fmConfigInstance.setLogTemplateExceptions(false);
        fmConfigInstance.setWrapUncheckedExceptions(true);
        fmConfigInstance.setFallbackOnNullLoopVariable(false);
    }

    public static Configuration getInstance() {
        if (fmConfigInstance == null) fmConfigInstance = new Configuration(Configuration.VERSION_2_3_21);
        return fmConfigInstance;
    }

}
