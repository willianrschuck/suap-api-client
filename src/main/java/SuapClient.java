import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.util.Arrays;

public class SuapClient {

    private final String apiURL;
    private String token;

    public SuapClient(String apiURL) {
        this.apiURL = apiURL;
    }

    public RequestHandler login(String username, String password) {

        return new RequestHandler() {

            @Override
            public void execute() {
                try (CloseableHttpClient client = HttpClients.createDefault()) {

                    HttpPost post = new HttpPost(apiURL + "autenticacao/token/");

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
            }

        };

    }

    public boolean isAuthorized() {
        return token != null;
    }

}
