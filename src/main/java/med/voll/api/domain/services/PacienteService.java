package med.voll.api.domain.services;

import med.voll.api.domain.entities.Paciente;
import med.voll.api.domain.valueobjects.paciente.DadosAtualizacaoPaciente;
import med.voll.api.domain.valueobjects.paciente.DadosListagemPacientes;
import med.voll.api.infra.repository.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class PacienteService {

    @Autowired
    private PacienteRepository repository;

    public Page<DadosListagemPacientes> getAll(Pageable pagination) {
        return repository.findAll(pagination).map(DadosListagemPacientes::new);
    }

    public void create(Paciente patient) {
        repository.save(patient);
    }

    public Paciente getById(Long id) {
        return repository.getReferenceById(id);
    }

    public Paciente update(DadosAtualizacaoPaciente data) {

        var patient = repository.findById(data.id())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Paciente não encontrado."));

        if(data.nome() != null) {
            patient.setNome(data.nome());
        }

        if(data.telefone() != null) {
            patient.setTelefone(data.telefone());
        }

        if(data.endereco() != null) {
            patient.getEndereco().atualizarInformacoes(data.endereco());
        }

        return patient;
    }

    public void disable(Long id) {

        var patient = repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Paciente não encontrado."));

        patient.setAtivo(false);
    }
}
