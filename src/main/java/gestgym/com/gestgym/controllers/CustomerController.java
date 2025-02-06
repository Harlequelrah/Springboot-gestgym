package gestgym.com.gestgym.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import gestgym.com.gestgym.exceptions.RessourceDeletionException;
import gestgym.com.gestgym.exceptions.RessourceNotFoundException;
import gestgym.com.gestgym.exceptions.RessourceUpdateException;
import gestgym.com.gestgym.models.Customer;
import gestgym.com.gestgym.services.Customer.CustomerService;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @GetMapping("/read-all-customer")
    public ResponseEntity<List<Customer>> readAllCustomer() {
        List<Customer> customers = customerService.readAllCustomer();
        return ResponseEntity.ok(customers);
    }

    @GetMapping("/read-one-customer/{customer_id}")
    public ResponseEntity<Customer> readOneCustomer(@PathVariable Long customer_id) throws RessourceNotFoundException {
        Customer customer = customerService.readOneCustomer(customer_id);
        return ResponseEntity.ok(customer);
    }

    @PostMapping("/save-customer")
    public ResponseEntity<Customer> saveCustomer(@RequestBody Customer customer) {
        Customer saveCustomer = customerService.saveCustomer(customer);
        return ResponseEntity.ok(saveCustomer);
    }

    @PutMapping("/update-customer/{customer_id}")
    public ResponseEntity<Customer> updateCustomer(@PathVariable Long customer_id, @RequestBody Customer customer) throws RessourceNotFoundException, RessourceUpdateException {
        Customer saveCustomer = customerService.updateCustomer(customer_id, customer);
        return ResponseEntity.ok(saveCustomer);
    }

    @DeleteMapping("/delete-customer/{customer_id}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable Long customer_id) throws RessourceNotFoundException, RessourceDeletionException {
        customerService.deleteCustomer(customer_id);
        return ResponseEntity.noContent().build();
    }
}
