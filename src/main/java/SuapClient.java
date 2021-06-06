import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.util.Arrays;

public class SuapClient {

    CloseableHttpClient client = HttpClients.createDefault();
    private final String apiURL;
    private String token;

    public SuapClient(String apiURL) {
        this.apiURL = apiURL;
    }

    public void login(String username, String password) throws Exception {

        HttpPost post = new HttpPost(apiURL + "autenticacao/token/");

        post.setEntity(new UrlEncodedFormEntity(Arrays.asList(
                new BasicNameValuePair("username", username),
                new BasicNameValuePair("password", password)
        )));

        CloseableHttpResponse response = client.execute(post);

        System.out.println(EntityUtils.toString(response.getEntity()));

    }

}
