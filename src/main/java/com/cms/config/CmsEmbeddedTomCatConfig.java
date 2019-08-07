package com.cms.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.stereotype.Component;

import java.lang.invoke.MethodHandles;

@Component
public class CmsEmbeddedTomCatConfig implements WebServerFactoryCustomizer<TomcatServletWebServerFactory> {

    private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    @Override
    public void customize(TomcatServletWebServerFactory factory) {
        logger.info("**************************** Starting CMS Tomcat ***********************************");
        factory.setPort(8402);
    }
}
