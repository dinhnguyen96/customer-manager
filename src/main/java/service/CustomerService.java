package service;

import model.Customer;

import java.util.List;
import java.util.Map;

public interface CustomerService {

    List<Customer> findAll();

    Customer getById(long id);

    boolean phoneNumberExitsCheck(long id, String phone);

    boolean add(Customer customer);

    void update(Customer customer);

    void remove(Customer customer);
}
