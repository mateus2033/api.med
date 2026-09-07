package med.voll.api.domain.valueobjects.medico;

import med.voll.api.domain.entities.Medico;
import med.voll.api.domain.enums.Especialidade;

public record DadosListagemMedicos(
        Long id,
        String nome,
        String email,
        String crm,
        Especialidade especialidade) {

        public DadosListagemMedicos(Medico medico) {
            this(
                    medico.getId(),
                    medico.getNome(),
                    medico.getEmail(),
                    medico.getCrm(),
                    medico.getEspecialidade()
            );
        }
}
