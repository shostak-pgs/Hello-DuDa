package mog.epam.java_course.controller;

import mog.epam.java_course.bean.Request;
import mog.epam.java_course.bean.Response;
import mog.epam.java_course.service.JSONMapperException;
import mog.epam.java_course.service.ClientRequestException;
import mog.epam.java_course.service.impl.MyHttpClient;
import mog.epam.java_course.service.impl.URLClient;
import org.apache.http.impl.client.HttpClientBuilder;

import java.io.IOException;

/**
 * The program takes three command-line argument and, if the first argument valid,
 * selects the method GET or POST and sends a request to the website "https://jsonplaceholder.typicode.com"
 * with HttpClient or URLClient depends of third argument
 * Displays the result of the query
 */
public class Controller {
    private static final String GET = "GET";
    private static final String POST = "POST";
    private static final String HTTP = "HTTP";
    private static final String URL = "URL";

    /**
     * Contains main program logic
     * @param args array contains arguments. The first - a method type, could be GET or POST.
     * The second - id of publication, must be > 0.
     * The third - type of a client, HTTP or URL
     * @throws ClientRequestException if it is unable to communicate with the resource
     * @throws JSONMapperException is thrown if an error of parsing occurs
     * @throws ValidatorException thrown if the passed parameters do not correct
     */
    public static void main(String[] args) throws IOException, JSONMapperException, ValidatorException {
        ParameterValidator.validate(args);
        final String METHOD = args[0];
        final String ID = args[1];
        final String CLIENT_TYPE = args[2];

        switch (METHOD) {
            case (GET):
                sendGetRequest(ID, CLIENT_TYPE);
                break;
            case (POST):
                sendPostRequest(ID, CLIENT_TYPE);
                break;
        }
    }

    private static void sendGetRequest(String id, String ClientType) throws IOException, JSONMapperException {
        String stringResponse = null;
        if (ClientType.equals(URL)) {
            stringResponse = new URLClient().doGet(id);
        } else {
            stringResponse = new MyHttpClient(HttpClientBuilder.create().build()).doGet(id);
        }
        Response response = Response.ResponseBuilder.buildResponse(stringResponse);
        Presentation.showGetResponse(response);
    }

    private static void sendPostRequest(String articleId, String ClientType) throws IOException, JSONMapperException {
        String requestParam = Request.RequestBuilder.buildRequest("ops", "mother clean the window", "2");
        String stringResponse = null;
        if (ClientType.equals("URL")) {
            stringResponse = new URLClient().doPost(articleId, requestParam);
        } else if (ClientType.equals("HTTP")) {
            stringResponse =  new MyHttpClient(HttpClientBuilder.create().build()).doPost(articleId, requestParam);
        }
        Response response2 = Response.ResponseBuilder.buildResponse(stringResponse);
        Presentation.showPostResponse(response2);
    }
}
