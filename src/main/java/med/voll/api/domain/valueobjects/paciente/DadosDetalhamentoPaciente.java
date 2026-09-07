package med.voll.api.domain.valueobjects.paciente;

import med.voll.api.domain.entities.Endereco;
import med.voll.api.domain.entities.Paciente;

public record DadosDetalhamentoPaciente(
        Long id,
        String nome,
        String email,
        String telefone,
        Endereco endereco) {

        public DadosDetalhamentoPaciente(Paciente paciente) {
            this(
                    paciente.getId(),
                    paciente.getNome(),
                    paciente.getEmail(),
                    paciente.getTelefone(),
                    paciente.getEndereco()
            );
        }
}
