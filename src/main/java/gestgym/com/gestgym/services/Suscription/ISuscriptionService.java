package gestgym.com.gestgym.services.suscription;

import java.util.List;

import gestgym.com.gestgym.exceptions.RessourceDeletionException;
import gestgym.com.gestgym.exceptions.RessourceNotFoundException;
import gestgym.com.gestgym.exceptions.RessourceUpdateException;
import gestgym.com.gestgym.models.Suscription;

public interface ISuscriptionService {
    public List<Suscription> readAllSuscriptions();

    public List<Suscription> readAllSuscriptionsByCustomerId(Long customer_id) throws RessourceNotFoundException;

    public List<Suscription> readAllSuscriptionsByPackId(Long PackId) throws RessourceNotFoundException;

    public Suscription readOneSuscription(Long suscription_id) throws RessourceNotFoundException;

    public Suscription saveSuscription(Suscription suscription) throws RessourceNotFoundException;

    public Suscription changeSuscriptionStatus(Long suscription_id, Suscription suscription)
            throws RessourceNotFoundException,
            RessourceUpdateException;

    public Suscription updateSuscription(Long suscription_id, Suscription suscription)
            throws RessourceNotFoundException, RessourceUpdateException;

    public void deleteSuscription(Long suscription_id) throws RessourceNotFoundException, RessourceDeletionException;



}
