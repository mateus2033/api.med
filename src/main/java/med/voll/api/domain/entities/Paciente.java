package med.voll.api.domain.entities;

import lombok.*;
import jakarta.persistence.*;
import med.voll.api.domain.valueobjects.paciente.DadosCadastroPaciente;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@Entity(name = "Paciente")
@Table(name = "pacientes")
public class Paciente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Embedded
    private Endereco endereco;

    private Boolean ativo;
    private String nome;
    private String email;
    private String cpf;
    private String telefone;

    public Paciente(DadosCadastroPaciente data) {
        this.ativo = true;
        this.nome = data.nome();
        this.email = data.email();
        this.telefone = data.telefone();
        this.cpf = data.cpf();
        this.endereco = new Endereco(data.endereco());
    }
}
