package gestgym.com.gestgym.services.pack;

import gestgym.com.gestgym.exceptions.RessourceNotFoundException;
import gestgym.com.gestgym.exceptions.RessourceUpdateException;
import gestgym.com.gestgym.exceptions.RessourceDeletionException;
import gestgym.com.gestgym.models.Pack;
import gestgym.com.gestgym.repositories.PackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PackService implements IPackService{

    @Autowired
    private PackRepository packRepository;


    @Override
    public List<Pack> readAllPack() {
        return packRepository.findAll();
    }

    @Override
    public Pack readOnePack(Long pack_id) throws RessourceNotFoundException {
        return packRepository.findById(pack_id)
                .orElseThrow(() -> new RessourceNotFoundException("Pack with id " + pack_id + " not found"));
    }

    @Override
    public Pack savePack(Pack pack) {
        return packRepository.save(pack);
    }

    @Override
    public Pack updatePack(Long pack_id, Pack packDetails) throws RessourceNotFoundException, RessourceUpdateException {
        Pack pack = packRepository.findById(pack_id)
                .orElseThrow(() -> new RessourceNotFoundException("Pack with id " + pack_id + " not found"));

        try {
            pack.setOffer_name(packDetails.getOffer_name());
            pack.setMonthly_price(packDetails.getMonthly_price());
            pack.setDuration_months(packDetails.getDuration_months());

            return packRepository.save(pack);
        } catch (Exception e) {
            throw new RessourceUpdateException("Failed to update pack with id " + pack_id, e);
        }
    }

    @Override
    public void deletePack(Long pack_id) throws RessourceNotFoundException, RessourceDeletionException {
        Pack pack = packRepository.findById(pack_id)
                .orElseThrow(() -> new RessourceNotFoundException("Pack with id " + pack_id + " not found"));

        try {
            packRepository.delete(pack);
        } catch (Exception e) {
            throw new RessourceDeletionException("Failed to delete pack with id " + pack_id, e);
        }
    }

}
