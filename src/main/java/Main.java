public class Main {

    public static void main(String[] args) {

        SuapClient suapClient = new SuapClient("https://suap.ifsul.edu.br/api/v2/");
        suapClient
            .login("", "")
            .onError(e -> System.out.println("Erro ao realizar login: " + e.getDetail()))
            .execute();

        if (suapClient.isAuthorized()) {
            System.out.println("Login realizado com sucesso!");
        }

    }

}
