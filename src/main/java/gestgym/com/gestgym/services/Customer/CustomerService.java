package gestgym.com.gestgym.services.Customer;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import gestgym.com.gestgym.models.Customer;
import gestgym.com.gestgym.repositories.CustomerRepository;

@Service
public class CustomerService implements ICustomerService {
    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public List<Customer> readAllCustomer() {
        return customerRepository.findAll();
    }

    @Override
    public Customer readOneCustomer(Long customer_id) {
        if ( isValidId(customer_id)) {
            return customerRepository.findById(customer_id).orElse(null);
        } else {
            throw new UnsupportedOperationException("Error occured while reading customer " + customer_id + " not found");
        }

    }

    @Override

    public Customer saveCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    @Override

    public Customer updateCustomer(Long customer_id, Customer customer) {
        if (isValidId(customer_id) && customer_id == customer.getId()) {
            return customerRepository.save(customer);
        } else {
            throw new UnsupportedOperationException("Error occurred while updating Customer with id : " + customer_id);
        }

    }

    public void deleteCustomer(Long customer_id) {
        if (isValidId(customer_id)) {
            customerRepository.deleteById(customer_id);
        } else {
            throw new UnsupportedOperationException("Error occured while deleting customer with " + customer_id);
        }

    }

    @Override
    public boolean isValidId(Long customer_id) {
        if (customer_id != null && customerRepository.existsById(customer_id)) {
            return true;
        } else {
            throw new UnsupportedOperationException("Customer with id " + customer_id + " not found");
        }
    }

}
