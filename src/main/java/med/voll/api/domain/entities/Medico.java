package med.voll.api.domain.entities;

import jakarta.persistence.*;
import lombok.*;
import med.voll.api.domain.valueobjects.medico.DadosAtualizacaoMedico;
import med.voll.api.domain.valueobjects.medico.DadosCadastroMedico;
import med.voll.api.domain.enums.Especialidade;

@Table(name = "medicos")
@Entity(name = "Medico")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Medico {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String email;
    private String telefone;
    private String crm;
    private Boolean ativo;

    @Enumerated(EnumType.STRING)
    private Especialidade especialidade;

    @Embedded
    private Endereco endereco;

    public Medico(DadosCadastroMedico dados) {
        this.nome = dados.nome();
        this.email = dados.email();
        this.telefone = dados.telefone();
        this.crm = dados.crm();
        this.especialidade = dados.especialidade();
        this.ativo = true;
        this.endereco = new Endereco(dados.endereco());
    }
}
