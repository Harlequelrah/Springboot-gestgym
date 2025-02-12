package gestgym.com.gestgym.services.suscription;

import gestgym.com.gestgym.exceptions.RessourceNotFoundException;
import gestgym.com.gestgym.exceptions.RessourceUpdateException;
import gestgym.com.gestgym.exceptions.RessourceDeletionException;
import gestgym.com.gestgym.models.Customer;
import gestgym.com.gestgym.models.Pack;
import gestgym.com.gestgym.models.Suscription;
import gestgym.com.gestgym.repositories.SuscriptionRepository;
import gestgym.com.gestgym.services.customer.CustomerService;
import gestgym.com.gestgym.services.pack.PackService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SuscriptionService implements ISuscriptionService {

    @Autowired
    private SuscriptionRepository suscriptionRepository;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private PackService packService;

    @Override
    public List<Suscription> readAllSuscriptions() {
        return suscriptionRepository.findAll();
    }

    @Override
    public Suscription readOneSuscription(Long suscription_id) throws RessourceNotFoundException {
        return suscriptionRepository.findById(suscription_id)
                .orElseThrow(
                        () -> new RessourceNotFoundException("Suscription with id " + suscription_id + " not found"));
    }

    @Override
    public Suscription saveSuscription(Suscription suscription) throws RessourceNotFoundException {
        Customer customer = customerService.readOneCustomer(suscription.getCustomer_id());
        Pack pack = packService.readOnePack(suscription.getPack_id());
        suscription.setCustomer(customer);
        suscription.setPack(pack);
        return suscriptionRepository.save(suscription);
    }

    @Override
    public Suscription updateSuscription(Long suscription_id, Suscription suscriptionDetails)
            throws RessourceNotFoundException, RessourceUpdateException {
        Suscription suscription = suscriptionRepository.findById(suscription_id)
                .orElseThrow(
                        () -> new RessourceNotFoundException("Suscription with id " + suscription_id + " not found"));
        try {
            suscription.setStart_date(suscriptionDetails.getStart_date());
            return suscriptionRepository.save(suscription);
        } catch (Exception e) {
            throw new RessourceUpdateException("Failed to update suscription with id " + suscription_id, e);
        }
    }

    @Override
    public void deleteSuscription(Long suscription_id) throws RessourceNotFoundException, RessourceDeletionException {
        Suscription suscription = suscriptionRepository.findById(suscription_id)
                .orElseThrow(
                        () -> new RessourceNotFoundException("Suscription with id " + suscription_id + " not found"));

        try {
            suscriptionRepository.delete(suscription);
        } catch (Exception e) {
            throw new RessourceDeletionException("Failed to delete suscription with id " + suscription_id, e);
        }
    }

    @Override
    public List<Suscription> readAllSuscriptionsByCustomerId(Long customerId) throws RessourceNotFoundException {
        Customer customer = customerService.readOneCustomer(customerId);
        return suscriptionRepository.findAll().stream()
                .filter(suscription -> suscription.getCustomer().equals(customer))
                .collect(Collectors.toList());
    }

    @Override
    public List<Suscription> readAllSuscriptionsByPackId(Long PackId) throws RessourceNotFoundException {
        Pack pack = packService.readOnePack(PackId);
        return suscriptionRepository.findAll().stream()
                .filter(suscription -> suscription.getPack().equals(pack))
                .collect(Collectors.toList());
    }

    @Override
    public Suscription changeSuscriptionStatus(Long suscription_id, Suscription suscriptionDetails)
            throws RessourceNotFoundException, RessourceUpdateException {
        Suscription suscription = suscriptionRepository.findById(suscription_id)
                .orElseThrow(
                        () -> new RessourceNotFoundException("Suscription with id " + suscription_id + " not found"));
        try {
            Customer customer = suscription.getCustomer();
            if (!suscriptionDetails.isActive()) {
                if (customer.isActive_suscription()) {
                    throw new RessourceUpdateException("Failed to update suscription with id " + suscription_id
                            + "because customer has already an active suscription");
                }
            }
            suscription.setActive(!suscription.isActive());
            customer.setActive_suscription(!customer.isActive_suscription());
            customerService.updateCustomer(customer.getId(), customer);
            return suscriptionRepository.save(suscription);
        } catch (Exception e) {
            throw new RessourceUpdateException("Failed to update suscription with id " + suscription_id, e);
        }
    }

}
