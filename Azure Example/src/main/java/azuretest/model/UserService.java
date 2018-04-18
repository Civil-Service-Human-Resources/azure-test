package azuretest.model;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Date;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpRequest;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class UserService {

    private String accessToken;

    private Date tokenExpiryDate;

    private final String usersURL = "https://graph.microsoft.com/v1.0/gmackayme.onmicrosoft.com/users";

    private final String oauthURL = "https://login.microsoftonline.com/gmackayme.onmicrosoft.com/oauth2/token";

//    private final String clientID = "a4782088-76a7-4231-8800-b964548ffcac";
//
//    private final String clientSecret = "cUIG/Cg0z71DifOAH9sabFegHAzk1cyDtmbjE290B3A=";

    @Value("${azure.activedirectory.client-id}")
    private String clientID;

    @Value("${azure.activedirectory.client-secret")
    private String clientSecret;

    private final String resourceURL = "https://graph.microsoft.com";

    /**
     * Return newly created ID
     * @param userDetails
     * @return
     */
    public String createAzureUser(UserDetails userDetails) {

        try {            

            System.out.println(userDetails);

            AzureUser azureUser = AzureUser.builder()
                    .accountEnabled(Boolean.TRUE)
                    .displayName(userDetails.getUserName())
                    .mailNickname(userDetails.getUserName())
                    .userPrincipalName(userDetails.getUserName() + "@gmackayme.onmicrosoft.com")
                    .passwordProfile(new PasswordProfile(userDetails.getPassword(), false))
                    .build();

            ObjectMapper objectMapper = new ObjectMapper();
            String json = objectMapper.writeValueAsString(azureUser);

            RestTemplate restTemplate = new RestTemplate();

            HttpHeaders headers = new HttpHeaders();
            headers.add("Authorization", getAccessToken());
            headers.add("Content-Type", "application/json");
            HttpEntity<String> entity = new HttpEntity<>(json, headers);

            ResponseEntity<String> response = restTemplate.exchange(usersURL, HttpMethod.POST, entity, String.class);

            System.out.println("response=" + response.getBody());

            JSONObject jsonObject = new JSONObject(response.getBody());
            return jsonObject.getString("id");
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private String getAccessToken() {

        if (accessToken != null && tokenExpiryDate.after(new Date())) {
            return accessToken;
        }
        else {
            try {

                ClientHttpRequestFactory requestFactory = getClientHttpRequestFactory();

                
                String body = "grant_type=client_credentials";
                body += "&client_id=" + clientID;
                body += "&client_secret=" + clientSecret;
                body += "&resource=" + resourceURL;

                URI uri = new URI(oauthURL);

                ClientHttpRequest request = requestFactory.createRequest(uri, HttpMethod.POST);
                request.getBody().write(body.getBytes());
                request.getBody().flush();
                request.getBody().close();

                ClientHttpResponse response = request.execute();
                InputStream responseInputStream = response.getBody();

                BufferedReader streamReader = new BufferedReader(
                        new InputStreamReader(responseInputStream, "UTF-8"));
                StringBuilder responseStrBuilder = new StringBuilder();

                String inputStr;
                while ((inputStr = streamReader.readLine()) != null) {
                    responseStrBuilder.append(inputStr);
                }
                JSONObject json = new JSONObject(responseStrBuilder.toString());
                System.out.println("access_token=" + json);

                try {

                    tokenExpiryDate = new Date(json.getLong("expires_on"));
                }
                catch(JSONException je) {
                    je.printStackTrace();
//                    tokenExpiryDate = null;
                }

                return json.getString("access_token");
            }
            catch (URISyntaxException | IOException ex) {
                ex.printStackTrace();
                throw new RuntimeException(ex);
            }
        }
    }

    private ClientHttpRequestFactory getClientHttpRequestFactory() {

        int timeout = 5000;
        HttpComponentsClientHttpRequestFactory clientHttpRequestFactory
                = new HttpComponentsClientHttpRequestFactory();
        clientHttpRequestFactory.setConnectTimeout(timeout);
        return clientHttpRequestFactory;
    }
}
