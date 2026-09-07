package med.voll.api.domain.valueobjects.medico;

import jakarta.validation.constraints.NotNull;
import med.voll.api.domain.valueobjects.endereco.DadosEndereco;

public record DadosAtualizacaoMedico(
        @NotNull
        Long id,
        String nome,
        String telefone,
        DadosEndereco endereco) {
}
