package br.com.sistemaautomotivo;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/modelos")
public class ModeloController {
    private final ModeloRepository modeloRepository;
    private final MarcaRepository marcaRepository;

    public ModeloController(ModeloRepository modeloRepository, MarcaRepository marcaRepository) {
        this.modeloRepository = modeloRepository;
        this.marcaRepository = marcaRepository;
    }

    @GetMapping
    public List<Modelo> listar(@RequestParam(required = false) Long marcaId) {
        return marcaId == null ? modeloRepository.findAll() : modeloRepository.findByMarcaId(marcaId);
    }

    @GetMapping("/{id}")
    public Modelo buscar(@PathVariable Long id) {
        return modeloRepository.findById(id).orElseThrow(() -> new RecursoNaoEncontradoException("Modelo não encontrado: " + id));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Modelo criar(@Valid @RequestBody ModeloEntrada entrada) {
        Marca marca = marcaRepository.findById(entrada.marcaId())
            .orElseThrow(() -> new RecursoNaoEncontradoException("Marca não encontrada: " + entrada.marcaId()));
        if (modeloRepository.existsByNomeIgnoreCaseAndMarcaId(entrada.nome(), entrada.marcaId())) {
            throw new RegraNegocioException("Esse modelo já está cadastrado para essa marca.");
        }
        return modeloRepository.save(new Modelo(entrada.nome(), marca));
    }

    @PutMapping("/{id}")
    public Modelo atualizar(@PathVariable Long id, @Valid @RequestBody ModeloEntrada entrada) {
        Modelo atual = buscar(id);
        Marca marca = marcaRepository.findById(entrada.marcaId())
            .orElseThrow(() -> new RecursoNaoEncontradoException("Marca não encontrada: " + entrada.marcaId()));
        atual.setNome(entrada.nome());
        atual.setMarca(marca);
        return modeloRepository.save(atual);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void excluir(@PathVariable Long id) {
        modeloRepository.delete(buscar(id));
    }

    public record ModeloEntrada(@jakarta.validation.constraints.NotBlank String nome,
                                @jakarta.validation.constraints.NotNull Long marcaId) {}
}
