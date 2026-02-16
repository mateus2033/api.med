package med.voll.api.domain.paciente;

public record DadosListagemPacientes(
        String nome,
        String email,
        String telefone,
        String cpf) {

    public DadosListagemPacientes(Paciente paciente) {
        this(
                paciente.getNome(),
                paciente.getEmail(),
                paciente.getTelefone(),
                paciente.getCpf()
        );
    }
}
