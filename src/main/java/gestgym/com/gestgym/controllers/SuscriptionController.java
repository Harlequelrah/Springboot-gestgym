package gestgym.com.gestgym.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import gestgym.com.gestgym.models.Customer;
import gestgym.com.gestgym.models.Pack;
import gestgym.com.gestgym.models.Suscription;
import gestgym.com.gestgym.services.Customer.CustomerService;
import gestgym.com.gestgym.services.Pack.PackService;
import gestgym.com.gestgym.services.Suscription.SuscriptionService;

@RestController
@RequestMapping("/suscriptions")
public class SuscriptionController {
    @Autowired
    private SuscriptionService suscriptionService;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private PackService packService;

    @GetMapping("/read-all-suscription")
    public ResponseEntity<List<Suscription>> readAllSuscription() {
        List<Suscription> suscriptions = suscriptionService.readAllSuscription();
        return ResponseEntity.ok(suscriptions);
    }

    @GetMapping("/read-one-suscription/{suscription_id}")
    public ResponseEntity<Suscription> readOneSuscription(@PathVariable Long suscription_id) {
        Suscription suscription = suscriptionService.readOneSuscription(suscription_id);
        return ResponseEntity.ok(suscription);
    }

    @PostMapping("/save-suscription")
    public ResponseEntity<Suscription> saveSuscription(@RequestBody Suscription suscription) {
        Customer customer = customerService.readOneCustomer(suscription.getCustomer_id());
        Pack pack = packService.readOnePack(suscription.getPack_id());
        suscription.setCustomer(customer);
        suscription.setPack(pack);
        Suscription saveSuscription = suscriptionService.saveSuscription(suscription);
        return ResponseEntity.ok(saveSuscription);
    }

    @PutMapping("/update-suscription/{suscription_id}")
    public ResponseEntity<Suscription> updateSuscription(@PathVariable Long suscription_id , @RequestBody Suscription suscription)
    {
        Suscription saveSuscription = suscriptionService.updateSuscription(suscription_id, suscription);
        return ResponseEntity.ok(saveSuscription);
    }

    @DeleteMapping("/delete-suscription/{suscription_id}")
    public ResponseEntity<Void> deleteSuscription(@PathVariable Long suscription_id)
    {
        suscriptionService.deleteSuscription(suscription_id);
        return ResponseEntity.noContent().build();
    }

}
