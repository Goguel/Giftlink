package br.ufrn.miguelxavier.giftlink.service;

import br.ufrn.miguelxavier.giftlink.model.Gift;
import br.ufrn.miguelxavier.giftlink.repository.GiftRepository;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.util.List;

@Service
public class GiftService {

    private final GiftRepository giftRepository;

    public GiftService(GiftRepository giftRepository) {
        this.giftRepository = giftRepository;
    }

    public List<Gift> findAll() {
        return giftRepository.findAll();
    }

    /**
     * Método refatorado
     *
     * Cria e salva um novo presente, agora recebendo um objeto Gift.
     * Organiza a validação e a persistência.
     *
     * Refatorações Aplicadas:
     * 1. Introduce Parameter Object: A assinatura agora é createGift(Gift gift),
     * resolvendo o 'Long Parameter List' e 'Primitive Obsession'.
     * 2. Extract Method: A lógica de validação foi movida para o método validateGiftData,
     * resolvendo o 'Long Method'.
     *
     * @param gift O objeto Gift a ser validado e salvo.
     * @return o Gift salvo.
     */
    public Gift createGift(Gift gift) {
        validateGiftData(gift);

        // A responsabilidade de salvar é clara e única.
        return giftRepository.save(gift);
    }

    /**
     * Método privado extraído para lidar apenas com a validação.
     *
     * @param gift O objeto a ser validado.
     */
    private void validateGiftData(Gift gift) {
        // Usando StringUtils para uma verificação mais robusta (boa prática).
        if (!StringUtils.hasText(gift.getName())) {
            throw new IllegalArgumentException("O nome do presente não pode ser vazio.");
        }
        if (gift.getValue() == null || gift.getValue().compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("O valor do presente não pode ser nulo ou negativo.");
        }
    }
}