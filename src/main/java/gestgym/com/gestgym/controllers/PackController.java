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

import gestgym.com.gestgym.models.Pack;
import gestgym.com.gestgym.services.PackService;

@RestController
@RequestMapping("/packs")
public class PackController {
    @Autowired
    private PackService packService;

    @GetMapping("/read-all-pack")
    public ResponseEntity<List<Pack>> readAllPack() {
        List<Pack> packs = packService.readAllPack();
        return ResponseEntity.ok(packs);
    }

    @GetMapping("/read-one-pack/{pack_id}")
    public ResponseEntity<Pack> readOnePack(@PathVariable Long pack_id) {
        Pack pack = packService.readOnePack(pack_id);
        return ResponseEntity.ok(pack);
    }

    @PostMapping("/save-pack")
    public ResponseEntity<Pack> savePack(@RequestBody Pack pack) {
        Pack savePack = packService.savePack(pack);
        return ResponseEntity.ok(savePack);
    }
    @PutMapping("/update-pack/{pack_id}")
    public ResponseEntity<Pack> updatePack(@PathVariable Long pack_id , @RequestBody Pack pack)
    {
        Pack savePack = packService.updatePack(pack_id, pack);
        return ResponseEntity.ok(savePack);
    }
    @DeleteMapping("/delete-pack/{pack_id}")
    public ResponseEntity<Void> deletePack(@PathVariable Long pack_id)
    {
        packService.deletePack(pack_id);
        return ResponseEntity.noContent().build();
    }

}
