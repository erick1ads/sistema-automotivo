package br.com.sistemaautomotivo;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.math.BigDecimal;

@Entity
@Table(name = "veiculos")
public class Veiculo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "A cor é obrigatória.")
    @Column(nullable = false, length = 40)
    private String cor;

    @NotNull(message = "O ano de fabricação é obrigatório.")
    @Min(value = 1900, message = "O ano deve ser igual ou superior a 1900.")
    @Max(value = 2100, message = "O ano informado é inválido.")
    @Column(nullable = false)
    private Integer anoFabricacao;

    @NotNull(message = "O preço é obrigatório.")
    @DecimalMin(value = "0.0", inclusive = false, message = "O preço deve ser maior que zero.")
    @Column(nullable = false, precision = 12, scale = 2)
    private BigDecimal preco;

    @NotNull(message = "A quilometragem é obrigatória.")
    @Min(value = 0, message = "A quilometragem não pode ser negativa.")
    @Column(nullable = false)
    private Integer quilometragem;

    @NotNull(message = "O status é obrigatório.")
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private StatusVeiculo status = StatusVeiculo.DISPONIVEL;

    @NotNull(message = "O modelo é obrigatório.")
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "modelo_id", nullable = false)
    private Modelo modelo;

    public Veiculo() {}

    public Long getId() { return id; }
    public String getCor() { return cor; }
    public Integer getAnoFabricacao() { return anoFabricacao; }
    public BigDecimal getPreco() { return preco; }
    public Integer getQuilometragem() { return quilometragem; }
    public StatusVeiculo getStatus() { return status; }
    public Modelo getModelo() { return modelo; }

    public void setId(Long id) { this.id = id; }
    public void setCor(String cor) { this.cor = cor; }
    public void setAnoFabricacao(Integer anoFabricacao) { this.anoFabricacao = anoFabricacao; }
    public void setPreco(BigDecimal preco) { this.preco = preco; }
    public void setQuilometragem(Integer quilometragem) { this.quilometragem = quilometragem; }
    public void setStatus(StatusVeiculo status) { this.status = status; }
    public void setModelo(Modelo modelo) { this.modelo = modelo; }
}
