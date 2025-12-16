package med.voll.api.controller;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.paciente.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RequestMapping("pacientes")
@RestController
public class PacienteController {

    @Autowired
    PacienteRepository repository;

    @PostMapping
    public void Cadastro(@RequestBody @Valid DadosCadastroPaciente data) {
        repository.save(new Paciente(data));
    }

    @GetMapping
    public Page<DadosListagemPacientes> listar(Pageable paginacao) {
        return repository.findAll(paginacao).map(DadosListagemPacientes::new);
    }

    @PutMapping
    @Transactional
    public void atualizar(@RequestBody @Valid DadosAtualizacaoPaciente dados){
        var paciente = repository.getReferenceById(dados.id());
        paciente.atualizarInformacoes(dados);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public void excluir(@PathVariable Long id) {
        var paciente = repository.getReferenceById(id);
        paciente.excluir();
    }
}
