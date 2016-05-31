package com.kliuchnik.project.webapp.page.sklad;

import javax.inject.Inject;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.ajax.markup.html.form.AjaxSubmitLink;
import org.apache.wicket.extensions.ajax.markup.html.modal.ModalWindow;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.CompoundPropertyModel;

import com.kliuchnik.project.datamodel.Sklad;
import com.kliuchnik.project.service.SkladService;

public class SkladEditPanel extends Panel {

	@Inject
	private SkladService skladService;

	private Sklad sklad;

	private ModalWindow modalWindow;

	public SkladEditPanel(ModalWindow modalWindow, Sklad sklad) {
        super(modalWindow.getContentId());
        this.sklad = sklad;
        this.modalWindow = modalWindow;
    }

    @Override
    protected void onInitialize() {
        super.onInitialize();
        Form<Sklad> form = new Form<>("form", new CompoundPropertyModel<>(sklad));
        add(form);
       
        form.add(new TextField<>("name"));
      

        form.add(new AjaxSubmitLink("save") {
            @Override
            protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
                super.onSubmit(target, form);
                skladService.saveOrUpdate(sklad);
                modalWindow.close(target);
            }
        });

        form.add(new AjaxLink("cancel") {
            @Override
            public void onClick(AjaxRequestTarget target) {
                modalWindow.close(target);
            }
        });
        
    }
    
}

		
	

