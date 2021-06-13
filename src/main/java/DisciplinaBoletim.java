import com.google.gson.annotations.SerializedName;

public class DisciplinaBoletim {

    @SerializedName("codigo_diario")
    private String codigo;

    @SerializedName("disciplina")
    private String nome;

    @SerializedName("numero_faltas")
    private Integer numeroFaltas;

    @SerializedName("situacao")
    private String situacao;

    @SerializedName("carga_horaria")
    private Double cargaHoraria;

    @SerializedName("carga_horaria_cumprida")
    private Double cargaHorariaCumprida;

    @SerializedName("media_final_disciplina")
    private Double mediaFinal;

    @Override
    public String toString() {
        return "DisciplinaBoletim{" +
                "codigo='" + codigo + '\'' +
                ", nome='" + nome + '\'' +
                ", numeroFaltas=" + numeroFaltas +
                ", situacao='" + situacao + '\'' +
                ", cargaHoraria=" + cargaHoraria +
                ", cargaHorariaCumprida=" + cargaHorariaCumprida +
                ", mediaFinal=" + mediaFinal +
                '}';
    }

}
