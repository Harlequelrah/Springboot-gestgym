package gestgym.com.gestgym.services.customer;

import java.util.List;

import gestgym.com.gestgym.exceptions.RessourceDeletionException;
import gestgym.com.gestgym.exceptions.RessourceNotFoundException;
import gestgym.com.gestgym.exceptions.RessourceUpdateException;
import gestgym.com.gestgym.models.Customer;

public interface ICustomerService {
    public List<Customer> readAllCustomer();

    public Customer readOneCustomer(Long customer_id) throws RessourceNotFoundException;

    public Customer readCustomerByName(String full_name) throws RessourceNotFoundException;

    public Customer saveCustomer(Customer customer);

    public Customer updateCustomer(Long customer_id, Customer customer) throws RessourceNotFoundException, RessourceUpdateException;

    public void deleteCustomer(Long customer_id) throws RessourceNotFoundException, RessourceDeletionException;



}
