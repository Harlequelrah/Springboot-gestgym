package gestgym.com.gestgym.services;

import java.util.List;

import gestgym.com.gestgym.models.Pack;

public interface IPackService {
    public List<Pack> readAllPack();

    public Pack readOnePack(Long pack_id);

    public Pack savePack(Pack pack);

    public Pack updatePack(Long pack_id, Pack pack);

    public void deletePack(Long pack_id);
}
