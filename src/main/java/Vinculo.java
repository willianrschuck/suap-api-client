import com.google.gson.annotations.SerializedName;

public class Vinculo {

    @SerializedName("curso")
    private String curso;

    @SerializedName("campus")
    private String campus;

    @SerializedName("situacao")
    private String situacao;

    public String getCurso() {
        return curso;
    }

    public String getCampus() {
        return campus;
    }

    public String getSituacao() {
        return situacao;
    }

    @Override
    public String toString() {
        return "Vinculo{" +
                "curso='" + curso + '\'' +
                ", campus='" + campus + '\'' +
                ", situaçao='" + situacao + '\'' +
                '}';
    }

}
