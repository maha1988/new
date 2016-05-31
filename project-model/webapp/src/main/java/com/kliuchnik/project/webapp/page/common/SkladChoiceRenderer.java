package com.kliuchnik.project.webapp.page.common;

import org.apache.wicket.markup.html.form.ChoiceRenderer;

import com.kliuchnik.project.datamodel.Sklad;


public class SkladChoiceRenderer extends ChoiceRenderer<Sklad> {

    public static final SkladChoiceRenderer INSTANCE = new SkladChoiceRenderer();

    private SkladChoiceRenderer() {
        super();
    }

    @Override
    public Object getDisplayValue(Sklad object) {
        return object.getName();
    }

    @Override
    public String getIdValue(Sklad object, int index) {
        return String.valueOf(object.getId());
    }
}
