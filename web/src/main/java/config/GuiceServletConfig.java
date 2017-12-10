package config;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.matcher.Matchers;
import com.google.inject.servlet.GuiceServletContextListener;
import com.google.inject.servlet.ServletModule;
import servlets.EchoServlet;
import servlets.UserServlet;

public class GuiceServletConfig extends GuiceServletContextListener {

    @Override
    protected Injector getInjector() {
        return Guice.createInjector(new ServletModule() {
            @Override
            protected void configureServlets () {
                serve("/echo/get").with(EchoServlet.class);
                serve("/echo/post").with(EchoServlet.class);
                serve("/ajax/user").with(UserServlet.class);
                serve("/ajax/authority").with(UserServlet.class);
                serve("/ajax/activity").with(UserServlet.class);
                bindListener(Matchers.any(), new Log4JTypeListener());
            }
        });
    }
}