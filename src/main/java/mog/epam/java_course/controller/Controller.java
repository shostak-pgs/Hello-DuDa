package mog.epam.java_course.controller;

import mog.epam.java_course.bean.Response;
import mog.epam.java_course.service.JSONMapperException;
import mog.epam.java_course.service.ClientRequestException;
import mog.epam.java_course.service.impl.MyHttpClient;
import mog.epam.java_course.service.impl.RequestResponseBuilder;
import mog.epam.java_course.service.impl.URLClient;

/**
 * The program takes three command-line argument and, if the first argument valid,
 * selects the method GET or POST and sends a request to the website "https://jsonplaceholder.typicode.com"
 * with HttpClient or URLClient depends of third argument
 * Displays the result of the query
 */
public class Controller {
    /**
     * Contains main program logic
     * @param args array contains arguments. The first - a method type, could be GET or POST.
     * The second - id of publication, must be > 0.
     * The third - type of a client, HTTP or URL
     * @throws ClientRequestException if it is unable to communicate with the resource
     * @throws JSONMapperException is thrown if an error of parsing occurs
     * @throws ContollerException thrown if the passed parameters do not correct
     */
    public static void main(String[] args) throws ClientRequestException, JSONMapperException, ContollerException {
       if (Validator.validate(args) == false) {
           throw new ContollerException("Wrong arguments");
       }
        final String METHOD = args[0];
        final String ID = args[1];
        final String CLASS = args[2];
        String stringResponse = null;

        switch (METHOD) {
            case ("GET"):
                if (CLASS.equals("URL")) {
                    stringResponse = new URLClient().doGet(ID);
                } else if (CLASS.equals("HTTP")) {
                    stringResponse = new MyHttpClient().doGet(ID);
                }
                Response response = new RequestResponseBuilder().buildResponse(stringResponse);
                Presentation.showGetResponse(response);
                break;
            case ("POST"):
                String request = new RequestResponseBuilder().buildRequest("ops", "mother clean the window", "2");
                if (CLASS.equals("URL")) {
                    stringResponse = new URLClient().doPost(args[1], request);
                } else if (CLASS.equals("HTTP")) {
                    stringResponse = new MyHttpClient().doPost(args[1], request);
                }
                Response response2 = new RequestResponseBuilder().buildResponse(stringResponse);
                Presentation.showPostResponse(response2);
                break;
        }
    }
}
