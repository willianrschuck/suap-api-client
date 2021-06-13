import java.util.List;

public class Main {

    public static void main(String[] args) {

        SuapClient suapClient = new SuapClient("https://suap.ifsul.edu.br/api/v2/");
        suapClient
            .login("", "")
            .onError(e -> System.out.println("Erro ao realizar login: " + e.getDetail()))
            .execute();

        Aluno aluno = suapClient.getMeusDados()
                .onError(e -> System.out.println(e.getDetail()))
                .execute();
        System.out.println(aluno);

        List<DisciplinaBoletim> boletim = suapClient.getBoletim(2021, 1).execute();
        System.out.println(boletim);

        if (suapClient.isAuthorized()) {
            System.out.println("Login realizado com sucesso!");
        }

    }

}
