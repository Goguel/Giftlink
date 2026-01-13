package br.ufrn.miguelxavier.giftlink.controller;

import br.ufrn.miguelxavier.giftlink.model.Gift;
import br.ufrn.miguelxavier.giftlink.service.GiftService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/gifts")
@CrossOrigin(origins = "*")
public class GiftController {

    private final GiftService giftService;

    public GiftController(GiftService giftService) {
        this.giftService = giftService;
    }

    @GetMapping
    public List<Gift> listGifts() {
        return giftService.findAll();
    }

    @PostMapping
    public Gift addGift(@RequestBody Gift newGift) {
        return giftService.createGift(newGift);
    }

    @PatchMapping("/{id}/gift")
    public Gift markAsGifted(@PathVariable Long id) {
        return giftService.markAsGifted(id);
    }
}