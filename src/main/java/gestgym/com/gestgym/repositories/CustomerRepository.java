package gestgym.com.gestgym.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import gestgym.com.gestgym.models.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {


}
