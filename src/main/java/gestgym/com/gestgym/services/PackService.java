package gestgym.com.gestgym.services;

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
        if (pack_id != null && packRepository.existsById(pack_id)) {
            return packRepository.findById(pack_id).orElse(null);
        } else {
            throw new RuntimeException("Pack with id " + pack_id + " Not Found");
        }
    }

    @Override
    public Pack savePack(Pack pack) {
        return packRepository.save(pack);
    }

    @Override
    public Pack updatePack(Long pack_id, Pack pack) {
        if (pack_id != null && packRepository.existsById(pack_id) && pack_id == pack.getId()) {
            return packRepository.save(pack);
        } else {
            throw new RuntimeException("Error occurred while updating Pack with id : " + pack_id);
        }
    }

    @Override
    public void deletePack(Long pack_id) {
        if (packRepository.existsById(pack_id)) {
            packRepository.deleteById(pack_id);
        } else {
            throw new RuntimeException("Error occurred while deleting Pack with id : " + pack_id);
        }
    }

}
