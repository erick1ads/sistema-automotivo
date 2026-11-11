package br.com.sistemaautomotivo;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "marcas")
public class Marca {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "O nome da marca é obrigatório.")
    @Column(nullable = false, unique = true, length = 80)
    private String nome;

    public Marca() {}

    public Marca(String nome) { this.nome = nome; }

    public Long getId() { return id; }
    public String getNome() { return nome; }
    public void setId(Long id) { this.id = id; }
    public void setNome(String nome) { this.nome = nome; }
}
