package med.voll.api.domain.valueobjects.medico;

import med.voll.api.domain.entities.Endereco;
import med.voll.api.domain.entities.Medico;
import med.voll.api.domain.enums.Especialidade;

public record DadosDetalhamentoMedico(
        Long id,
        String nome,
        String email,
        String crm,
        String telefone,
        Especialidade especialidadem,
        Endereco endereco) {


        public DadosDetalhamentoMedico(Medico medico) {
            this(
                    medico.getId(),
                    medico.getNome(),
                    medico.getEmail(),
                    medico.getCrm(),
                    medico.getTelefone(),
                    medico.getEspecialidade(),
                    medico.getEndereco()
            );
        }
}
