package com.kliuchnik.project.webapp.page.user ;

import org.apache.wicket.authroles.authorization.strategies.role.Roles;
import org.apache.wicket.authroles.authorization.strategies.role.annotations.AuthorizeInstantiation;
import org.apache.wicket.markup.html.link.Link;


import com.kliuchnik.project.datamodel.User;
import com.kliuchnik.project.webapp.app.AuthorizedSession;
import com.kliuchnik.project.webapp.page.AbstractPage;

import com.kliuchnik.project.webapp.page.user.panel.UsersListPanel;

@AuthorizeInstantiation(value = {"ADMIN"})
public class UsersPage extends AbstractPage {

    public UsersPage() {
        super();

        User loggedUser = AuthorizedSession.get().getLoggedUser();
        Roles roles = AuthorizedSession.get().getRoles();

        add(new UsersListPanel("list-panel"));
        add(new Link("create") {
            @Override
            public void onClick() {
                setResponsePage(new UsersEditPage(new User()));
            }
        });
    }

}
