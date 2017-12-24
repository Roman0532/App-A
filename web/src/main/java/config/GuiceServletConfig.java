package config;

import com.google.gson.Gson;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Singleton;
import com.google.inject.matcher.Matchers;
import com.google.inject.servlet.GuiceServletContextListener;
import com.google.inject.servlet.ServletModule;
import com.google.inject.throwingproviders.ThrowingProviderBinder;
import provider.ConnectionProvider;
import provider.DaoProvider;
import service.ConnectionService;
import servlets.ActivityServlet;
import servlets.AuthorityServlet;
import servlets.EchoServlet;
import servlets.UserServlet;

import java.sql.Connection;

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
                bind(Gson.class).toProvider(SerializeProvider.class).in(Singleton.class);
                install(ThrowingProviderBinder.forModule(this));
                ThrowingProviderBinder.create(binder())
                        .bind(ConnectionProvider.class, Connection.class).annotatedWith(DaoProvider.class)
                        .to(ConnectionService.class).in(Singleton.class);
            }
        });
    }
}
