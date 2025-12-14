import br.ufrn.miguelxavier.giftlink.model.Gift;
import br.ufrn.miguelxavier.giftlink.repository.GiftRepository;
import org.springframework.stereotype.Service;

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

    public Gift createGift(String name, String description, BigDecimal value) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("O nome do presente não pode ser vazio.");
        }
        if (value == null || value.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("O valor do presente não pode ser negativo.");
        }

        Gift newGift = new Gift();
        newGift.setName(name.trim());
        newGift.setDescription(description);
        newGift.setValue(value);
        newGift.setGifted(false);

        return giftRepository.save(newGift);
    }
}