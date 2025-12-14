package med.voll.api.controller;

import jakarta.validation.Valid;
import med.voll.api.paciente.DadosCadastroPaciente;
import med.voll.api.paciente.DadosListagemPacientes;
import med.voll.api.paciente.Paciente;
import med.voll.api.paciente.PacienteRepository;
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
}
