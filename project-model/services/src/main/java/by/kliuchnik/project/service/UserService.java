package by.kliuchnik.project.service;

import java.util.List;

import javax.transaction.Transactional;

import by.kliuchnik.project.dataaccess.filters.UserFilter;
import by.kliuchnik.project.datamodel.Customer;
import by.kliuchnik.project.datamodel.User;

public interface UserService {


    @Transactional
    void register(User user, Customer customer);

    User getUser(Long id);

    Customer getCustomer(Long id);

    @Transactional
    void update(User user);

    @Transactional
    void delete(Long id);
    List<Customer> find(UserFilter filter);

    List<Customer> getAll();
}
