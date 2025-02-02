package gestgym.com.gestgym.services.Pack;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import gestgym.com.gestgym.models.Pack;
import gestgym.com.gestgym.repositories.PackRepository;

@Service
public class PackService implements IPackService {
    @Autowired
    private PackRepository packRepository;

    @Override
    public List<Pack> readAllPack() {
        return packRepository.findAll();
    }

    @Override
    public Pack readOnePack(Long pack_id) {
        if (isValidId(pack_id)) {
            return packRepository.findById(pack_id).orElse(null);
        } else {
            throw new UnsupportedOperationException("Error occured while reading pack with id " + pack_id);
        }
    }

    @Override
    public Pack savePack(Pack pack) {
        return packRepository.save(pack);
    }

    @Override
    public Pack updatePack(Long pack_id, Pack pack) {
        if (isValidId((pack_id)) && pack_id == pack.getId()) {
            return packRepository.save(pack);
        } else {
            throw new UnsupportedOperationException("Error occurred while updating Pack with id : " + pack_id);
        }
    }

    @Override
    public void deletePack(Long pack_id) {
        if (isValidId(pack_id)) {
            packRepository.deleteById(pack_id);
        } else {
            throw new UnsupportedOperationException("Error occurred while deleting Pack with id : " + pack_id);
        }
    }

    @Override
    public boolean isValidId(Long pack_id) {
        if (pack_id != null && packRepository.existsById(pack_id)) {
            return true;
        } else {
            throw new UnsupportedOperationException("Pack with id " + pack_id + " not found");
        }
    }

}
