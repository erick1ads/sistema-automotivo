package br.com.sistemaautomotivo;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "modelos", uniqueConstraints = @UniqueConstraint(columnNames = {"nome", "marca_id"}))
public class Modelo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "O nome do modelo é obrigatório.")
    @Column(nullable = false, length = 100)
    private String nome;

    @NotNull(message = "A marca é obrigatória.")
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "marca_id", nullable = false)
    private Marca marca;

    public Modelo() {}

    public Modelo(String nome, Marca marca) {
        this.nome = nome;
        this.marca = marca;
    }

    public Long getId() { return id; }
    public String getNome() { return nome; }
    public Marca getMarca() { return marca; }
    public void setId(Long id) { this.id = id; }
    public void setNome(String nome) { this.nome = nome; }
    public void setMarca(Marca marca) { this.marca = marca; }
}
