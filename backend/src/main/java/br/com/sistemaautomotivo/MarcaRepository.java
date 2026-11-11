package br.com.sistemaautomotivo;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MarcaRepository extends JpaRepository<Marca, Long> {
    boolean existsByNomeIgnoreCase(String nome);
}
