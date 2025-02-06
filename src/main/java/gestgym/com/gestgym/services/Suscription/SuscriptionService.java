package gestgym.com.gestgym.services.Suscription;

import gestgym.com.gestgym.exceptions.RessourceNotFoundException;
import gestgym.com.gestgym.exceptions.RessourceUpdateException;
import gestgym.com.gestgym.exceptions.RessourceDeletionException;
import gestgym.com.gestgym.models.Customer;
import gestgym.com.gestgym.models.Pack;
import gestgym.com.gestgym.models.Suscription;
import gestgym.com.gestgym.repositories.SuscriptionRepository;
import gestgym.com.gestgym.services.Customer.CustomerService;
import gestgym.com.gestgym.services.Pack.PackService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SuscriptionService implements ISuscriptionService {

    @Autowired
    private SuscriptionRepository suscriptionRepository;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private PackService  packService;

    @Override
    public List<Suscription> readAllSuscription() {
        return suscriptionRepository.findAll();
    }

    @Override
    public Suscription readOneSuscription(Long suscription_id) throws RessourceNotFoundException {
        return suscriptionRepository.findById(suscription_id)
                .orElseThrow(() -> new RessourceNotFoundException("Suscription with id " + suscription_id + " not found"));
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
    public Suscription updateSuscription(Long suscription_id, Suscription suscriptionDetails) throws RessourceNotFoundException, RessourceUpdateException {
        Suscription suscription = suscriptionRepository.findById(suscription_id)
                .orElseThrow(() -> new RessourceNotFoundException("Suscription with id " + suscription_id + " not found"));

        try {
            suscription.setStart_date(suscriptionDetails.getStart_date());
            return this.saveSuscription(suscription);
        } catch (Exception e) {
            throw new RessourceUpdateException("Failed to update suscription with id " + suscription_id, e);
        }
    }

    @Override
    public void deleteSuscription(Long suscription_id) throws RessourceNotFoundException, RessourceDeletionException {
        Suscription suscription = suscriptionRepository.findById(suscription_id)
                .orElseThrow(() -> new RessourceNotFoundException("Suscription with id " + suscription_id + " not found"));

        try {
            suscriptionRepository.delete(suscription);
        } catch (Exception e) {
            throw new RessourceDeletionException("Failed to delete suscription with id " + suscription_id, e);
        }
    }

}
