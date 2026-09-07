package med.voll.api.controller;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.domain.entities.Paciente;
import med.voll.api.domain.services.PacienteService;
import med.voll.api.domain.valueobjects.paciente.DadosAtualizacaoPaciente;
import med.voll.api.domain.valueobjects.paciente.DadosCadastroPaciente;
import med.voll.api.domain.valueobjects.paciente.DadosDetalhamentoPaciente;
import med.voll.api.domain.valueobjects.paciente.DadosListagemPacientes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RequestMapping("pacientes")
@RestController
public class PacienteController {

    @Autowired
    PacienteService service;

    @PostMapping
    public ResponseEntity Cadastro(@RequestBody @Valid DadosCadastroPaciente data, UriComponentsBuilder uriBuilder) {
        var paciente = new Paciente(data);
        service.create(paciente);
        var uri = uriBuilder.path("pacientes/{id}").buildAndExpand(paciente.getId()).toUri();
        return ResponseEntity.created(uri).body(new DadosDetalhamentoPaciente(paciente));
    }

    @GetMapping
    public ResponseEntity<Page<DadosListagemPacientes>> listar(Pageable paginacao) {
        var page = service.getAll(paginacao);
        return ResponseEntity.ok(page);
    }

    @PutMapping
    @Transactional
    public ResponseEntity atualizar(@RequestBody @Valid DadosAtualizacaoPaciente dados){
        var paciente = service.update(dados);
        return ResponseEntity.ok(new DadosDetalhamentoPaciente(paciente));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity excluir(@PathVariable Long id) {
        service.disable(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity detalhar(@PathVariable Long id) {
        var paciente = service.getById(id);
        return ResponseEntity.ok(new DadosDetalhamentoPaciente(paciente));
    }
}
