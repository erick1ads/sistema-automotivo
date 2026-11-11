package br.com.sistemaautomotivo;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ModeloRepository extends JpaRepository<Modelo, Long> {
    List<Modelo> findByMarcaId(Long marcaId);
    boolean existsByNomeIgnoreCaseAndMarcaId(String nome, Long marcaId);
}
