import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonToken;
import org.apache.http.HttpHeaders;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.List;

public class SuapClient {

    private final String baseUrl;
    private String token;

    public SuapClient(String apiURL) {
        this.baseUrl = apiURL;
    }

    public RequestHandler<Void> login(String username, String password) {

        return new RequestHandler<>() {

            @Override
            public Void execute() {

            try (CloseableHttpClient client = HttpClients.createDefault()) {

                HttpPost post = new HttpPost(baseUrl + "autenticacao/token/");

                post.setEntity(new UrlEncodedFormEntity(Arrays.asList(
                        new BasicNameValuePair("username", username),
                        new BasicNameValuePair("password", password)
                )));

                HttpResponse httpResponse = client.execute(post);
                String response = EntityUtils.toString(httpResponse.getEntity());
                int responseStatusCode = httpResponse.getStatusLine().getStatusCode();
                JsonObject jsonObject = JsonParser.parseString(response).getAsJsonObject();

                if (responseStatusCode == HttpStatus.SC_OK) {
                    if (jsonObject.has("token")) {
                        token = jsonObject.get("token").getAsString();
                    }
                } else {
                    if (jsonObject.has("detail")) {
                        throw new Exception(jsonObject.get("detail").getAsString());
                    }
                    throw new Exception("Erro não identificado");
                }

            } catch (Exception e) {
                handleError(e);
            }

            return null;

            }


        };

    }

    public RequestHandler<Aluno> getMeusDados() {

        return new RequestHandler<>() {
            @Override
            public Aluno execute() {

            try (CloseableHttpClient client = HttpClients.createDefault()) {

                HttpGet get = new HttpGet(baseUrl+"/minhas-informacoes/meus-dados/");
                get.setHeader(HttpHeaders.AUTHORIZATION, "Jwt " + token);

                HttpResponse httpResponse = client.execute(get);
                String response = EntityUtils.toString(httpResponse.getEntity());
                int responseStatusCode = httpResponse.getStatusLine().getStatusCode();

                if (responseStatusCode == HttpStatus.SC_OK) {
                    return new Gson().fromJson(response, Aluno.class);
                } else {
                    JsonObject jsonObject = JsonParser.parseString(response).getAsJsonObject();
                    if (jsonObject.has("detail")) {
                        throw new Exception(jsonObject.get("detail").getAsString());
                    }
                    throw new Exception("Erro não identificado");
                }

            } catch (Exception e) {
                handleError(e);
            }

            return null;

            }
        };

    }

    public RequestHandler<List<DisciplinaBoletim>> getBoletim(Integer ano, Integer periodo) {

        return new RequestHandler<>() {
            @Override
            public List<DisciplinaBoletim> execute() {

                try (CloseableHttpClient client = HttpClients.createDefault()) {

                    HttpGet get = new HttpGet(baseUrl + "/minhas-informacoes/boletim/"+ ano +"/"+ periodo +"/");
                    get.setHeader(HttpHeaders.AUTHORIZATION, "Jwt " + token);

                    HttpResponse httpResponse = client.execute(get);
                    String response = EntityUtils.toString(httpResponse.getEntity());
                    int responseStatusCode = httpResponse.getStatusLine().getStatusCode();

                    Type type = new TypeToken<List<DisciplinaBoletim>>() {}.getType();

                    if (responseStatusCode == HttpStatus.SC_OK) {
                        return new Gson().fromJson(response, type);
                    } else {
                        JsonObject jsonObject = JsonParser.parseString(response).getAsJsonObject();
                        if (jsonObject.has("detail")) {
                            throw new Exception(jsonObject.get("detail").getAsString());
                        }
                        throw new Exception("Erro não identificado");
                    }

                } catch (Exception e) {
                    handleError(e);
                }

                return null;

            }
        };

    }

    public boolean isAuthorized() {
        return token != null;
    }

}
