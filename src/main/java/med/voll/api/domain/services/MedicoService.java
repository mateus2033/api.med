package med.voll.api.domain.services;

import med.voll.api.domain.entities.Medico;
import med.voll.api.domain.valueobjects.medico.DadosAtualizacaoMedico;
import med.voll.api.domain.valueobjects.medico.DadosListagemMedicos;
import med.voll.api.infra.repository.MedicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class MedicoService {

    @Autowired
    private MedicoRepository repository;

    public Page<DadosListagemMedicos> getAll(Pageable pagination) {
        return repository.findAll(pagination).map(DadosListagemMedicos::new);
    }

    public void create(Medico doctor) {
        repository.save(doctor);
    }

    public Medico getById(Long id) {
        return repository.getReferenceById(id);
    }

    public Medico update(DadosAtualizacaoMedico data) {

        var doctor = repository.findById(data.id())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Medico não encontrado."));

        if(data.nome() != null) {
            doctor.setNome(data.nome());
        }

        if(data.telefone() != null) {
            doctor.setTelefone(data.telefone());
        }

        if(data.endereco() != null) {
            doctor.getEndereco().atualizarInformacoes(data.endereco());
        }

        return doctor;
    }

    public void disable(Long id) {

        var medico = repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Médico não encontrado."));

        medico.setAtivo(false);
    }
}
