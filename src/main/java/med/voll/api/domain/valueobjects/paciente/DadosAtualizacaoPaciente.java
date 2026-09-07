package med.voll.api.domain.valueobjects.paciente;

import jakarta.validation.constraints.NotNull;
import med.voll.api.domain.valueobjects.endereco.DadosEndereco;

public record DadosAtualizacaoPaciente(
        @NotNull
        Long id,
        String nome,
        String telefone,
        DadosEndereco endereco) {
}
