package gestgym.com.gestgym.services;

import java.util.List;

import gestgym.com.gestgym.models.Customer;

public interface ICustomerService {
    public List<Customer> readAllCustomer();

    public Customer readOneCustomer(Long customer_id);

    public Customer saveCustomer(Customer customer);

    public Customer updateCustomer(Long customer_id, Customer customer);

    public void deleteCustomer(Long customer_id);


}
