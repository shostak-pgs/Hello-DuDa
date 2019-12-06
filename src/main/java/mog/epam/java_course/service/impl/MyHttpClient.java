package mog.epam.java_course.service.impl;

import mog.epam.java_course.service.Client;
import mog.epam.java_course.service.ClientRequestException;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

/**
 * Designed to perform GET and POST requests with apache HttpClient class
 */
public class MyHttpClient implements Client {
    private static final String URL_STRING = "https://jsonplaceholder.typicode.com/posts";
    private CloseableHttpClient client;

    public MyHttpClient(CloseableHttpClient client){
        this.client = client;
    }

    /**
     * Designed to receive publications from the site by the specified publication id
     * @param articleId id of the required publication
     * @return String representation of response in JSON format
     * @throws ClientRequestException thrown if it is unable to connect to the server or such publication does not exist
     */
    @Override
    public String doGet(String articleId) throws ClientRequestException {
        String result;
        HttpGet get = new HttpGet(URL_STRING + "/" + articleId);
        CloseableHttpResponse response = null;
        try {
            response = client.execute(get);
            HttpEntity entity = response.getEntity();
            result = EntityUtils.toString(entity);
            response.close();
        } catch (IOException e) {
            throw new ClientRequestException("Server connection Exception!", e);
        }
        return result;
    }

    /**
     * Designed to store information about a new publication by the specified publication id
     * @param articleId id under which you want to post an article
     * @param params for saving information about a new publication
     * @return String representation of response in JSON format
     * @throws ClientRequestException thrown if it is unable to connect to the server or unable to store publication
     */
    @Override
    public String doPost(String articleId, String params) throws ClientRequestException {
        StringBuilder result = new StringBuilder();
        try {
            HttpPost post = new HttpPost(URL_STRING);
            post.addHeader("content-type", "application/json");
            post.setEntity(new StringEntity(params));
            CloseableHttpResponse response = null;
            response = client.execute(post);
            BufferedReader reader = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
            String str;
            while ((str = reader.readLine()) != null) {
                if (str.contains("id")) {
                    str = "\"id\": \"" + articleId +"\"";
                }
                result.append(str.trim());
            }
            response.close();
        } catch (UnsupportedEncodingException e) {
            throw new ClientRequestException("Wrong encoding!", e);
        } catch (IOException e) {
            throw new ClientRequestException("Server connection Exception!", e);
        }
        return result.toString();
    }
}

