import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class Aluno {

    @SerializedName("id")
    private Integer id;

    @SerializedName("matricula")
    private String matricula;

    @SerializedName("nome_usual")
    private String nome;

    @SerializedName("email")
    private String email;

    @SerializedName("cpf")
    private String cpf;

    @SerializedName("vinculo")
    private Vinculo vinculo;

    @SerializedName("data_nascimento")
    private Date dataNascimento;

    public Integer getId() {
        return id;
    }

    public String getMatricula() {
        return matricula;
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public String getCpf() {
        return cpf;
    }

    public Vinculo getVinculo() {
        return vinculo;
    }

    public Date getDataNascimento() {
        return dataNascimento;
    }

    @Override
    public String toString() {
        return "Aluno{" +
                "id=" + id +
                ", matricula='" + matricula + '\'' +
                ", nome='" + nome + '\'' +
                ", email='" + email + '\'' +
                ", cpf='" + cpf + '\'' +
                ", vinculo=" + vinculo +
                ", dataNascimento=" + dataNascimento +
                '}';
    }

}
