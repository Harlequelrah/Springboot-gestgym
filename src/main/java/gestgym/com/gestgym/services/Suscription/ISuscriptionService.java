package gestgym.com.gestgym.services.Suscription;

import java.util.List;

import gestgym.com.gestgym.models.Suscription;

public interface ISuscriptionService {
    public List<Suscription> readAllSuscription();

    public Suscription readOneSuscription(Long suscription_id);

    public Suscription saveSuscription(Suscription suscription);

    public Suscription updateSuscription(Long suscription_id, Suscription suscription);

    public void deleteSuscription(Long suscription_id);

    public boolean isValidId(Long suscription_id);

}
