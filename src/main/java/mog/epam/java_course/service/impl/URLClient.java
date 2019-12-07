package mog.epam.java_course.service.impl;

import mog.epam.java_course.service.Client;
import mog.epam.java_course.service.ClientRequestException;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Designed to perform GET and POST requests with java.net.URL and java.net.HttpURLConnection classes
 */
public class URLClient implements Client {
    public static final String URL_STRING = "https://jsonplaceholder.typicode.com/posts";

    /**
     * Designed to receive publications from the site by the specified publication id
     * @param articleId of the required publication
     * @return String representation of response in JSON format
     * @throws ClientRequestException thrown if it is unable to connect to the server or such publication does not exist
     */
    @Override
    public String doGet(String articleId) throws ClientRequestException {
        PrintStream consoleStream = System.out;
        OutputStream response = new ByteArrayOutputStream();
        try {
            URL url = new URL(URL_STRING + "/" + articleId);
            HttpURLConnection urlCon = (HttpURLConnection)url.openConnection();
            try(BufferedReader reader = new BufferedReader(new InputStreamReader(urlCon.getInputStream()))) {
                System.setOut(new PrintStream(response));
                String str;
                while ((str = reader.readLine()) != null) {
                    System.out.println(str);
                }
            }
        } catch (MalformedURLException e) {
            throw new ClientRequestException("URL Exception!", e);
        } catch (IOException e) {
            throw new ClientRequestException("File Not Found!", e);
        }
        System.setOut(consoleStream);
        return response.toString();
    }

    /**
     * Designed to store information about a new publication by the specified publication id. If the request
     * is successful, returns the response in JSON format
     * @param articleId id under which you want to post an article
     * @param params settings for saving information about a new publication in JSON format
     * @return String representation of response in JSON format
     * @throws ClientRequestException thrown if it is unable to connect to the server or unable to store publication
     */
    @Override
    public String doPost(String articleId, String params) throws ClientRequestException {
        StringBuilder response = new StringBuilder();
        try {
            URL url = new URL(URL_STRING);
            HttpURLConnection urlCon = (HttpURLConnection)url.openConnection();
            urlCon.setRequestMethod("POST");
            urlCon.setRequestProperty("Content-Type", "application/json");
            urlCon.setRequestProperty("Accept", "application/json");
            urlCon.setDoOutput(true);
            urlCon.setDoInput(true);
            OutputStream out = urlCon.getOutputStream();
            out.write(params.getBytes("UTF-8"));
            out.close();
            if (urlCon.getResponseCode() == 201) {
                try (BufferedReader br = new BufferedReader(new InputStreamReader(urlCon.getInputStream()))) {
                    String str;
                    while ((str = br.readLine()) != null) {
                        response.append(str.trim());
                    }
                }
            }
        } catch (MalformedURLException e) {
            throw new ClientRequestException("URL Exception!", e);
        } catch (IOException e) {
            throw new ClientRequestException("File Not Found!", e);
        }
        return substituteID(response.toString(), articleId);
    }

    /**
     * The method is intended to substitute publication id
     * @param articleId id under which you want to post an article
     * @return
     */
    private String substituteID(String str, String articleId) {
        String lineWithRightId;
        Integer currentId = Integer.parseInt(articleId);
        if (currentId < 100) {
            currentId = 101;
        }
        lineWithRightId = "\"id\": \"" + currentId + "\"";
        String result = str.replace("\"id\": 101", lineWithRightId);
        return result;
    }
}
