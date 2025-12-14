package br.ufrn.miguelxavier.giftlink.controller;

import br.ufrn.miguelxavier.giftlink.model.Gift; 
import br.ufrn.miguelxavier.giftlink.service.GiftService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute; 
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/gifts")
public class GiftController {

    private final GiftService giftService;

    public GiftController(GiftService giftService) {
        this.giftService = giftService;
    }

    @GetMapping
    public String listGifts(Model model) {
        model.addAttribute("gifts", giftService.findAll());
        // Adicionando um objeto Gift vazio para o formulário usar (boa prática)
        model.addAttribute("newGift", new Gift());
        return "gift-list";
    }

    /**
     * Método ajustado a refatoração do service
     *
     * Agora, em vez de receber vários @RequestParams, ele recebe um único objeto Gift.
     * O Spring Boot preenche o objeto 'newGift' com os dados do formulário
     * automaticamente se os nomes dos campos no HTML (name, description, value)
     * corresponderem aos nomes dos atributos da classe Gift.
     *
     * @param newGift O objeto Gift preenchido com os dados do formulário.
     * @return Redireciona o usuário de volta para a página da lista.
     */
    @PostMapping("/add")
    public String addGift(@ModelAttribute Gift newGift) {
        // A chamada ao serviço agora é muito mais limpa e expressiva.
        giftService.createGift(newGift);

        return "redirect:/gifts";
    }
}