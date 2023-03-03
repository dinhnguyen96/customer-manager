package service.impl;

import model.Customer;
import service.CustomerService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CustomerImpl implements CustomerService
{
    private static Map<Long,Customer> customerMap;


    static {
        customerMap = new HashMap<>();
        customerMap.put(1L, new Customer(1, "15DH110321","Nguyễn Đình Nguyên","Hà Nội","0941904792"));
        customerMap.put(2L,new Customer(2, "15DH110355","Trần Kim Ngọc","Nghệ An","0912260674"));
        customerMap.put(3L,new Customer(3, "15DH110305","Nguyễn Thị Linh","Thanh Hóa","0912260674"));
        customerMap.put(4L,new Customer(4, "15DH110308","Nguyễn Văn Hùng","Ninh Bình","0912260674"));
    }
    @Override
    public List<Customer> findAll()
    {
        return new ArrayList<>(customerMap.values());
    }

    @Override
    public Customer getById(long id)
    {
        return customerMap.get(id);
    }

    @Override
    public boolean phoneNumberExitsCheck(long id, String phone)
    {
        for (Customer customer: findAll())
        {
            if (customer.getId() != id && customer.getPhone().equals(phone))
            {
                return true;
            }
        }
        return false;

    }

    @Override
    public boolean add(Customer customer)
    {
        boolean phoneExist = phoneNumberExitsCheck(customer.getId(), customer.getPhone());
        if (!phoneExist)
        {
            customerMap.put(customer.getId(), customer);
            return true;
        }
        return false;
    }

    @Override
    public void update(Customer customer)
    {
        customerMap.put(customer.getId(),customer);
    }

    @Override
    public void remove(Customer customer)
    {
        customerMap.remove(customer.getId());
    }
}
