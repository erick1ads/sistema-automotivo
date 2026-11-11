package br.com.sistemaautomotivo;

import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;
import jakarta.validation.Valid;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/veiculos")
public class VeiculoController {
    private final VeiculoRepository veiculoRepository;
    private final ModeloRepository modeloRepository;

    public VeiculoController(VeiculoRepository veiculoRepository, ModeloRepository modeloRepository) {
        this.veiculoRepository = veiculoRepository;
        this.modeloRepository = modeloRepository;
    }

    // Os filtros são opcionais: o usuário pode combinar mais de um na mesma consulta.
    @GetMapping
    public List<Veiculo> listar(
            @RequestParam(required = false) Long marcaId,
            @RequestParam(required = false) Long modeloId,
            @RequestParam(required = false) BigDecimal precoMin,
            @RequestParam(required = false) BigDecimal precoMax,
            @RequestParam(required = false) Integer ano,
            @RequestParam(required = false) StatusVeiculo status) {

        // Começa sem restrições e acrescenta somente os filtros que foram informados.
        Specification<Veiculo> spec = (root, query, cb) -> cb.conjunction();

        if (marcaId != null) {
            spec = spec.and((root, query, cb) -> {
                Join<Veiculo, Modelo> modelo = root.join("modelo", JoinType.INNER);
                return cb.equal(modelo.get("marca").get("id"), marcaId);
            });
        }
        if (modeloId != null) {
            spec = spec.and((root, query, cb) -> cb.equal(root.get("modelo").get("id"), modeloId));
        }
        if (precoMin != null) {
            spec = spec.and((root, query, cb) -> cb.greaterThanOrEqualTo(root.get("preco"), precoMin));
        }
        if (precoMax != null) {
            spec = spec.and((root, query, cb) -> cb.lessThanOrEqualTo(root.get("preco"), precoMax));
        }
        if (ano != null) {
            spec = spec.and((root, query, cb) -> cb.equal(root.get("anoFabricacao"), ano));
        }
        if (status != null) {
            spec = spec.and((root, query, cb) -> cb.equal(root.get("status"), status));
        }
        return veiculoRepository.findAll(spec);
    }

    @GetMapping("/{id}")
    public Veiculo buscar(@PathVariable Long id) {
        return veiculoRepository.findById(id)
            .orElseThrow(() -> new RecursoNaoEncontradoException("Veículo não encontrado: " + id));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Veiculo criar(@Valid @RequestBody VeiculoEntrada entrada) {
        Modelo modelo = modeloRepository.findById(entrada.modeloId())
            .orElseThrow(() -> new RecursoNaoEncontradoException("Modelo não encontrado: " + entrada.modeloId()));
        Veiculo veiculo = new Veiculo();
        preencher(veiculo, entrada, modelo);
        return veiculoRepository.save(veiculo);
    }

    @PutMapping("/{id}")
    public Veiculo atualizar(@PathVariable Long id, @Valid @RequestBody VeiculoEntrada entrada) {
        Veiculo veiculo = buscar(id);
        Modelo modelo = modeloRepository.findById(entrada.modeloId())
            .orElseThrow(() -> new RecursoNaoEncontradoException("Modelo não encontrado: " + entrada.modeloId()));
        preencher(veiculo, entrada, modelo);
        return veiculoRepository.save(veiculo);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void excluir(@PathVariable Long id) {
        veiculoRepository.delete(buscar(id));
    }

    // Mantém a criação e a atualização usando a mesma lógica de preenchimento.
    private void preencher(Veiculo veiculo, VeiculoEntrada entrada, Modelo modelo) {
        veiculo.setModelo(modelo);
        veiculo.setCor(entrada.cor());
        veiculo.setAnoFabricacao(entrada.anoFabricacao());
        veiculo.setPreco(entrada.preco());
        veiculo.setQuilometragem(entrada.quilometragem());
        veiculo.setStatus(entrada.status());
    }

    public record VeiculoEntrada(
        @jakarta.validation.constraints.NotNull Long modeloId,
        @jakarta.validation.constraints.NotBlank String cor,
        @jakarta.validation.constraints.NotNull @jakarta.validation.constraints.Min(1900) @jakarta.validation.constraints.Max(2100) Integer anoFabricacao,
        @jakarta.validation.constraints.NotNull @jakarta.validation.constraints.DecimalMin("0.01") BigDecimal preco,
        @jakarta.validation.constraints.NotNull @jakarta.validation.constraints.Min(0) Integer quilometragem,
        @jakarta.validation.constraints.NotNull StatusVeiculo status
    ) {}
}
