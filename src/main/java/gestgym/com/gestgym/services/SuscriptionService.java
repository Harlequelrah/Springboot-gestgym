package gestgym.com.gestgym.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import gestgym.com.gestgym.models.Suscription;
import gestgym.com.gestgym.repositories.SuscriptionRepository;

@Service
public class SuscriptionService implements ISuscriptionService {
    @Autowired
    private SuscriptionRepository suscriptionRepository;

    @Override
    public List<Suscription> readAllSuscription() {
        return suscriptionRepository.findAll();
    }

    @Override
    public Suscription readOneSuscription(Long suscription_id) {
        if (suscription_id != null && suscriptionRepository.existsById(suscription_id)) {
            return suscriptionRepository.findById(suscription_id).orElse(null);

        }
        else {
            throw new RuntimeException("Suscription with id " + suscription_id + " Not Found");
        }
    }

    @Override
    public Suscription saveSuscription(Suscription suscription) {
        return suscriptionRepository.save(suscription);
    }

    @Override
    public Suscription updateSuscription(Long suscription_id, Suscription suscription) {
        if (suscription_id != null && suscriptionRepository.existsById(suscription_id)
                && suscription_id == suscription.getId()) {
            return suscriptionRepository.save(suscription);
        } else {
            throw new RuntimeException("Error occurred while updating Suscription with id : " + suscription_id);
        }
    }

    @Override
    public void deleteSuscription(Long suscription_id) {
        if (suscription_id != null && suscriptionRepository.existsById(suscription_id)) {
            suscriptionRepository.deleteById(suscription_id);
        } else {
            throw new RuntimeException("Error occurred while deleting Suscription with id : " + suscription_id);
        }
    }
}
