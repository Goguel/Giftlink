package br.ufrn.miguelxavier.giftlink.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.math.BigDecimal;

/**
 * Representa um item de presente na lista.
 * Esta é uma entidade JPA, o que significa que será mapeada para uma tabela no banco de dados.
 */
@Entity
public class Gift {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String description;
    
    // BigDecimal para valores monetários para evitar problemas de precisão com float/double.
    @Column(name = "gift_value")
    private BigDecimal value;

    private boolean isGifted = false;

    // Construtor padrão exigido pelo JPA
    public Gift() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }

    public boolean isGifted() {
        return isGifted;
    }

    public void setGifted(boolean gifted) {
        isGifted = gifted;
    }
}