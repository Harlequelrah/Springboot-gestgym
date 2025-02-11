package gestgym.com.gestgym.services.pack;

import java.util.List;

import gestgym.com.gestgym.exceptions.RessourceDeletionException;
import gestgym.com.gestgym.exceptions.RessourceNotFoundException;
import gestgym.com.gestgym.exceptions.RessourceUpdateException;
import gestgym.com.gestgym.models.Pack;

public interface IPackService {
    public List<Pack> readAllPack();

    public Pack readOnePack(Long pack_id) throws RessourceNotFoundException;

    public Pack savePack(Pack pack);

    public Pack updatePack(Long pack_id, Pack pack) throws RessourceNotFoundException, RessourceUpdateException;

    public void deletePack(Long pack_id) throws RessourceNotFoundException, RessourceDeletionException;

}
