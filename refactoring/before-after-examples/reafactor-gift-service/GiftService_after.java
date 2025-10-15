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

    public Gift createGift(Gift gift) {
        validateGiftData(gift);
        return giftRepository.save(gift);
    }

    private void validateGiftData(Gift gift) {
        if (!StringUtils.hasText(gift.getName())) {
            throw new IllegalArgumentException("O nome do presente não pode ser vazio.");
        }
        if (gift.getValue() == null || gift.getValue().compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("O valor do presente não pode ser nulo ou negativo.");
        }
    }
}