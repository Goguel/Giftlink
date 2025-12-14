package br.ufrn.miguelxavier.giftlink.service;

import br.ufrn.miguelxavier.giftlink.model.Gift;
import br.ufrn.miguelxavier.giftlink.repository.GiftRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class GiftServiceTest {

    @Mock
    private GiftRepository giftRepository;

    @InjectMocks
    private GiftService giftService;

    // --- TESTES DE SUCESSO ---

    @Test
    @DisplayName("Deve criar um presente com sucesso quando dados são válidos")
    void createGift_Success() {
        // Arrange
        Gift inputGift = new Gift();
        inputGift.setName("Playstation 5");
        inputGift.setValue(new BigDecimal("3500.00"));
        
        when(giftRepository.save(any(Gift.class))).thenReturn(inputGift);

        // Act
        Gift created = giftService.createGift(inputGift);

        // Assert
        assertNotNull(created);
        assertEquals("Playstation 5", created.getName());
        verify(giftRepository, times(1)).save(inputGift);
    }

    @Test
    @DisplayName("Deve listar todos os presentes")
    void findAll_Success() {
        // Arrange
        when(giftRepository.findAll()).thenReturn(List.of(new Gift(), new Gift()));

        // Act
        List<Gift> gifts = giftService.findAll();

        // Assert
        assertEquals(2, gifts.size());
        verify(giftRepository, times(1)).findAll();
    }

    // --- TESTES DE VALIDAÇÃO (CASOS DE FALHA) ---

    @Test
    @DisplayName("Deve lançar erro quando nome é nulo")
    void createGift_NameNull_ThrowsException() {
        Gift gift = new Gift();
        gift.setName(null);
        gift.setValue(BigDecimal.TEN);

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            giftService.createGift(gift);
        });

        assertEquals("O nome do presente não pode ser vazio.", exception.getMessage());
    }

    @Test
    @DisplayName("Deve lançar erro quando nome é vazio")
    void createGift_NameEmpty_ThrowsException() {
        Gift gift = new Gift();
        gift.setName("");
        gift.setValue(BigDecimal.TEN);

        assertThrows(IllegalArgumentException.class, () -> giftService.createGift(gift));
    }

    @Test
    @DisplayName("Deve lançar erro quando nome é apenas espaços em branco")
    void createGift_NameBlank_ThrowsException() {
        Gift gift = new Gift();
        gift.setName("   ");
        gift.setValue(BigDecimal.TEN);

        assertThrows(IllegalArgumentException.class, () -> giftService.createGift(gift));
    }

    @Test
    @DisplayName("Deve lançar erro quando valor é nulo")
    void createGift_ValueNull_ThrowsException() {
        Gift gift = new Gift();
        gift.setName("Gift");
        gift.setValue(null);

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            giftService.createGift(gift);
        });

        assertEquals("O valor do presente não pode ser nulo ou negativo.", exception.getMessage());
    }

    @Test
    @DisplayName("Deve lançar erro quando valor é negativo")
    void createGift_ValueNegative_ThrowsException() {
        Gift gift = new Gift();
        gift.setName("Gift");
        gift.setValue(new BigDecimal("-1.00"));

        assertThrows(IllegalArgumentException.class, () -> giftService.createGift(gift));
    }

    // --- TESTES DE EDGE CASES (CASOS LIMITE) ---

    @Test
    @DisplayName("Deve aceitar valor zero (presente gratuito)")
    void createGift_ValueZero_Success() {
        Gift gift = new Gift();
        gift.setName("Free Hug");
        gift.setValue(BigDecimal.ZERO);
        
        when(giftRepository.save(any())).thenReturn(gift);

        assertDoesNotThrow(() -> giftService.createGift(gift));
    }

    // --- TESTES DA ENTIDADE GIFT (Para aumentar cobertura) ---
    
    @Test
    @DisplayName("Teste de Getters e Setters da Entidade Gift")
    void testGiftEntity() {
        Gift gift = new Gift();
        gift.setId(1L);
        gift.setDescription("Desc");
        gift.setGifted(true);

        assertEquals(1L, gift.getId());
        assertEquals("Desc", gift.getDescription());
        assertTrue(gift.isGifted());
    }
    
    @Test
    @DisplayName("Deve salvar descrição corretamente")
    void createGift_Description_Success() {
        // Arrange
        Gift gift = new Gift();
        gift.setName("Livro");
        gift.setValue(BigDecimal.TEN);
        gift.setDescription("Uma descrição válida");
        
        when(giftRepository.save(any(Gift.class))).thenReturn(gift);

        // Act
        Gift created = giftService.createGift(gift);

        // Assert
        assertEquals("Uma descrição válida", created.getDescription());
    }
    
}