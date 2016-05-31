package com.kliuchnik.project.webapp.app;

import javax.inject.Inject;

import org.apache.wicket.Session;
import org.apache.wicket.authroles.authentication.AuthenticatedWebSession;
import org.apache.wicket.authroles.authorization.strategies.role.Roles;
import org.apache.wicket.injection.Injector;
import org.apache.wicket.request.Request;

import com.kliuchnik.project.datamodel.User;
import com.kliuchnik.project.service.UserService;



public class AuthorizedSession extends AuthenticatedWebSession {
    @Inject
    private UserService userService;

    private User loggedUser;

    private Roles roles;

    public AuthorizedSession(Request request) {
        super(request);
        Injector.get().inject(this);

    }

    public static AuthorizedSession get() {
        return (AuthorizedSession) Session.get();
    }

    @Override
    public boolean authenticate(final String userName, final String password) {
        loggedUser = userService.getByNameAndPassword(userName, password);
        return loggedUser != null;
    }

    @Override
    public Roles getRoles() {
        if (isSignedIn() && (roles != null)) {
            roles = new Roles();
            roles.addAll(userService.resolveRoles(loggedUser.getId()));
        }
        if (!isSignedIn() && (roles == null)) {
            roles = new Roles();
            roles.add("Unknown");
        }
        return roles;
        
    }

    @Override
    public void signOut() {
        super.signOut();
        loggedUser = null;
        roles = null;
    }

    public User getLoggedUser() {
        return loggedUser;
    }

}
