package com.kliuchnik.project.webapp.page.common;

import org.apache.wicket.markup.html.form.ChoiceRenderer;

import com.kliuchnik.project.datamodel.Unit;

public class UnitChoiceRender 	 extends ChoiceRenderer<Unit> {

	    public static final UnitChoiceRender INSTANCE = new UnitChoiceRender();

	    private UnitChoiceRender() {
	        super();
	    }

	    @Override
	    public Object getDisplayValue(Unit object) {
	        return object.ШT;
	    }

	    @Override
	    public String getIdValue(Unit object, int index) {
	        return String.valueOf(object.ordinal());
	    }
	}

