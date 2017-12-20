package config;

import com.google.gson.Gson;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.matcher.Matchers;
import com.google.inject.servlet.GuiceServletContextListener;
import com.google.inject.servlet.ServletModule;
import servlets.ActivityServlet;
import servlets.AuthorityServlet;
import servlets.EchoServlet;
import servlets.UserServlet;

public class GuiceServletConfig extends GuiceServletContextListener {
    @Override
    protected Injector getInjector() {
        return Guice.createInjector(new ServletModule() {
            @Override
            protected void configureServlets() {
                serve("/echo/get").with(EchoServlet.class);
                serve("/echo/post").with(EchoServlet.class);
                serve("/ajax/user").with(UserServlet.class);
                serve("/ajax/activity").with(ActivityServlet.class);
                serve("/ajax/authority").with(AuthorityServlet.class);
                bindListener(Matchers.any(), new Log4JTypeListener());
                bind(Gson.class).toProvider(SerializeProvider.class);
            }
        });
    }
}