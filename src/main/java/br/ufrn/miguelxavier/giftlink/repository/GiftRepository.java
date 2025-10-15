package br.ufrn.miguelxavier.giftlink.repository;

import br.ufrn.miguelxavier.giftlink.model.Gift;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Interface Repository para a entidade Gift.
 * Herdar de JpaRepository fornece todos os métodos CRUD (Create, Read, Update, Delete)
 * prontos para uso, sem a necessidade de implementação.
 */
@Repository
public interface GiftRepository extends JpaRepository<Gift, Long> {
    // Métodos como save(), findById(), findAll(), deleteById() já estão disponíveis.
}