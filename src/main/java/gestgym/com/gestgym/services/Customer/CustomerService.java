package gestgym.com.gestgym.services.Customer;

import gestgym.com.gestgym.exceptions.RessourceNotFoundException;
import gestgym.com.gestgym.exceptions.RessourceUpdateException;
import gestgym.com.gestgym.exceptions.RessourceDeletionException;
import gestgym.com.gestgym.models.Customer;
import gestgym.com.gestgym.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService implements ICustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public List<Customer> readAllCustomer() {
        return customerRepository.findAll();
    }

    @Override
    public Customer readOneCustomer(Long customer_id) throws RessourceNotFoundException {
        return customerRepository.findById(customer_id)
                .orElseThrow(() -> new RessourceNotFoundException("Customer with id " + customer_id + " not found"));
    }

    @Override
    public Customer saveCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    @Override
    public Customer updateCustomer(Long customer_id, Customer customerDetails)
            throws RessourceNotFoundException, RessourceUpdateException {
        Customer customer = customerRepository.findById(customer_id)
                .orElseThrow(() -> new RessourceNotFoundException("Customer with id " + customer_id + " not found"));

        try {
            customer.setFirst_name(customerDetails.getFirst_name());
            customer.setLast_name(customerDetails.getLast_name());
            // customer.setRegistration_date(customerDetails.getRegistration_date());
            customer.setPhone_number(customerDetails.getPhone_number());
            customer.setActive_suscription(customerDetails.isActive_suscription());

            return customerRepository.save(customer);
        } catch (Exception e) {
            throw new RessourceUpdateException("Failed to update customer with id " + customer_id, e);
        }
    }

    @Override
    public void deleteCustomer(Long customer_id) throws RessourceNotFoundException, RessourceDeletionException {
        Customer customer = customerRepository.findById(customer_id)
                .orElseThrow(() -> new RessourceNotFoundException("Customer with id " + customer_id + " not found"));

        try {
            customerRepository.delete(customer);
        } catch (Exception e) {
            throw new RessourceDeletionException("Failed to delete customer with id " + customer_id, e);
        }
    }

}
