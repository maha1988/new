package com.kliuchnik.project.webapp.page.sklad;


import com.kliuchnik.project.webapp.page.AbstractPage;

import com.kliuchnik.project.webapp.page.sklad.panel.SkladListPanel;

public class SkladPage extends AbstractPage {
	

    public SkladPage() {
        super();
    	add(new SkladListPanel("panel"));

       
    }
}
