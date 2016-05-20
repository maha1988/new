package com.kliuchnik.project.webapp.page.sklad.panel;

import java.io.Serializable;
import java.util.Iterator;

import javax.inject.Inject;
import javax.persistence.metamodel.SingularAttribute;

import org.apache.wicket.datetime.markup.html.basic.DateLabel;
import org.apache.wicket.extensions.markup.html.repeater.data.sort.OrderByBorder;
import org.apache.wicket.extensions.markup.html.repeater.data.sort.SortOrder;
import org.apache.wicket.extensions.markup.html.repeater.util.SortableDataProvider;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.CheckBox;
import org.apache.wicket.markup.html.navigation.paging.PagingNavigator;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.markup.repeater.data.DataView;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

import com.kliuchnik.project.dataaccess.filters.ProductFilter;
import com.kliuchnik.project.dataaccess.filters.SkladFilter;
import com.kliuchnik.project.datamodel.Product;
import com.kliuchnik.project.datamodel.Product_;
import com.kliuchnik.project.datamodel.Sklad;
import com.kliuchnik.project.datamodel.Sklad_;
import com.kliuchnik.project.service.ProductService;
import com.kliuchnik.project.service.SkladService;


public class SkladListPanel extends Panel {

    @Inject
    private SkladService skladService;

    public SkladListPanel(String id) {
        super(id);

        SkladDataProvider skladDataProvider = new SkladDataProvider();
        DataView<Sklad> dataView = new DataView<Sklad>("skladlist", skladDataProvider, 5) {
            @Override
            protected void populateItem(Item<Sklad> item) {
                Sklad sklad = item.getModelObject();

                item.add(new Label("id", sklad.getId()));
                item.add(new Label("name", sklad.getName()));
            //    item.add(new Label("products", sklad.getProducts()));
                                      
                               
              //  item.add(DateLabel.forDatePattern("created", Model.of(product.getCreated()), "dd-MM-yyyy"));

   //             CheckBox checkbox = new CheckBox("active", Model.of(product.getActive()));
     //           checkbox.setEnabled(false);
         //       item.add(checkbox);
            }
        };
        add(dataView);
        add(new PagingNavigator("paging", dataView));

        add(new OrderByBorder("sort-id", Sklad_.id, skladDataProvider));
        add(new OrderByBorder("sort-name", Sklad_.name, skladDataProvider));
       // add(new OrderByBorder("sort-products", Sklad_.products, skladDataProvider));
       
       
    }

    private class SkladDataProvider extends SortableDataProvider<Sklad, Serializable> {

        private SkladFilter skladFilter;

        public SkladDataProvider() {
            super();
            skladFilter = new SkladFilter();
            setSort((Serializable) Sklad_.name, SortOrder.ASCENDING);
        }

        @Override
        public Iterator<Sklad> iterator(long first, long count) {
            Serializable property = getSort().getProperty();
            SortOrder propertySortOrder = getSortState().getPropertySortOrder(property);

            skladFilter.setSortProperty((SingularAttribute) property);
            skladFilter.setSortOrder(propertySortOrder.equals(SortOrder.ASCENDING) ? true : false);

            skladFilter.setLimit((int) count);
            skladFilter.setOffset((int) first);
            return skladService.find(skladFilter).iterator();
        }

        @Override
        public long size() {
            return skladService.count(skladFilter);
        }

        @Override
        public IModel<Sklad> model(Sklad object) {
            return new Model( object);
        }

    }
    }
    
