package br.com.sistemaautomotivo;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/marcas")
public class MarcaController {
    private final MarcaRepository repository;

    public MarcaController(MarcaRepository repository) { this.repository = repository; }

    @GetMapping
    public List<Marca> listar() { return repository.findAll(); }

    @GetMapping("/{id}")
    public Marca buscar(@PathVariable Long id) {
        return repository.findById(id).orElseThrow(() -> new RecursoNaoEncontradoException("Marca não encontrada: " + id));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Marca criar(@Valid @RequestBody Marca marca) {
        if (repository.existsByNomeIgnoreCase(marca.getNome())) {
            throw new RegraNegocioException("Já existe uma marca com esse nome.");
        }
        marca.setId(null);
        return repository.save(marca);
    }

    @PutMapping("/{id}")
    public Marca atualizar(@PathVariable Long id, @Valid @RequestBody Marca dados) {
        Marca atual = buscar(id);
        if (!atual.getNome().equalsIgnoreCase(dados.getNome()) && repository.existsByNomeIgnoreCase(dados.getNome())) {
            throw new RegraNegocioException("Já existe uma marca com esse nome.");
        }
        atual.setNome(dados.getNome());
        return repository.save(atual);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void excluir(@PathVariable Long id) {
        Marca marca = buscar(id);
        if (!marca.getId().equals(id)) throw new RegraNegocioException("Marca inválida.");
        repository.delete(marca);
    }
}
