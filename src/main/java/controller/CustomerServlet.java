package controller;

import model.Customer;
import service.CustomerService;
import service.impl.CustomerImpl;

import java.io.*;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet(name = "customerServlet", value = "/customer")
public class CustomerServlet extends HttpServlet {

    private CustomerService customerService = new CustomerImpl();


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        String action = req.getParameter("action");
        if (action == null)
        {
            action = "";
        }
        switch (action) {
            case "add" ->
            {
                add(req, resp);
            }
            case "edit" -> {
                edit(req, resp);
            }
        }

    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html");
        String action = request.getParameter("action");
        if (action == null)
        {
            action = "";
        }
        switch (action)
        {
            case "add" -> {
                request.setAttribute("action",action);
                request.getRequestDispatcher("update_insert.jsp").forward(request, response);
            }
            case "edit" ->{
                long id = Long.parseLong(request.getParameter("id"));
                Customer customer = customerService.getById(id);
                request.setAttribute("id", id);
                request.setAttribute("customerCode",customer.getCustomerCode() );
                request.setAttribute("customerName", customer.getCustomerName());
                request.setAttribute("customerPhone", customer.getPhone());
                request.setAttribute("customerAddress", customer.getAddress());
                request.setAttribute("action",action);
                request.getRequestDispatcher("update_insert.jsp").forward(request, response);
            }
            case "remove" -> {
                remove(request,response);
            }
            case "search" -> {
            }
            default -> {
                request.setAttribute("customers", customerService.findAll());
                request.getRequestDispatcher("index.jsp").forward(request, response);
            }
        }
    }

    private void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        String customerCode = request.getParameter("customerCode");
        String customerName = request.getParameter("customerName");
        String customerPhone = request.getParameter("customerPhone");
        String customerAddress = request.getParameter("customerAddress");
        List<Customer> customerList = customerService.findAll();
        Customer customer = new Customer(customerList.get(customerList.size()-1).getId()+1, customerCode,
                                          customerName,customerAddress, customerPhone);
        boolean customerAdd = customerService.add(customer);

        if (customerAdd)
        {
            response.sendRedirect("http://localhost:8080/customer");
        }
        else
        {
            request.setAttribute("customerCode",customerCode );
            request.setAttribute("customerName", customerName);
            request.setAttribute("customerPhone", customerPhone);
            request.setAttribute("customerAddress", customerAddress);
            request.setAttribute("action","add");
            request.getRequestDispatcher("update_insert.jsp").forward(request,response);
        }
    }
    private void edit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        long id = Long.parseLong(request.getParameter("id"));
        String customerCode = request.getParameter("customerCode");
        String customerName = request.getParameter("customerName");
        String customerPhone = request.getParameter("customerPhone");
        String customerAddress = request.getParameter("customerAddress");
        Customer customer = customerService.getById(id);
        boolean update = customerService.phoneNumberExitsCheck(id, customerPhone);
        if (update)
        {
            request.setAttribute("id",id);
            request.setAttribute("customerCode",customerCode );
            request.setAttribute("customerName", customerName);
            request.setAttribute("customerPhone", customerPhone);
            request.setAttribute("customerAddress", customerAddress);
            request.setAttribute("action","edit");
            request.getRequestDispatcher("update_insert.jsp").forward(request, response);
        }
        else
        {
            customer.setId(id);
            customer.setCustomerCode(customerCode);
            customer.setCustomerName(customerName);
            customer.setAddress(customerAddress);
            customer.setPhone(customerPhone);
            customerService.update(customer);
            response.sendRedirect("http://localhost:8080/customer");
        }

    }
    private void remove(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        long id = Long.parseLong(request.getParameter("id"));
        Customer customer = customerService.getById(id);
        customerService.remove(customer);
        response.sendRedirect("http://localhost:8080/customer");
    }

}