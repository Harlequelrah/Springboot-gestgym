package gestgym.com.gestgym.services.Suscription;

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
        if (isValidId(suscription_id)) {
            return suscriptionRepository.findById(suscription_id).orElse(null);

        }
        else {
            throw new UnsupportedOperationException("Error occured while reading suscription with id " + suscription_id);
        }
    }

    @Override
    public Suscription saveSuscription(Suscription suscription) {
        return suscriptionRepository.save(suscription);
    }

    @Override
    public Suscription updateSuscription(Long suscription_id, Suscription suscription) {
        if (isValidId(suscription_id)
                && suscription_id == suscription.getId()) {
            return suscriptionRepository.save(suscription);
        } else {
            throw new UnsupportedOperationException("Error occurred while updating Suscription with id : " + suscription_id);
        }
    }

    @Override
    public void deleteSuscription(Long suscription_id) {
        if (isValidId((suscription_id))) {
            suscriptionRepository.deleteById(suscription_id);
        } else {
            throw new UnsupportedOperationException(
                    "Error occurred while deleting Suscription with id : " + suscription_id);
        }
    }

    @Override
    public boolean isValidId(Long suscription_id) {
        if (suscription_id != null && suscriptionRepository.existsById(suscription_id)) {
            return true;
        } else {
            throw new UnsupportedOperationException("Suscription with id " + suscription_id + " not found");
        }
    }
}
