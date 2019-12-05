package mog.epam.java_course.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import mog.epam.java_course.bean.Request;
import mog.epam.java_course.bean.Response;
import mog.epam.java_course.service.JSONMapperException;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * Class designed for creating response and request from the parameters passed in JSON String
 * format or String format(for request)
 */
public class RequestResponseBuilder {

    /**
     * Creates a response object by mapping passed JSON String. Throws a JSONMapperException
     * if the String have incorrect format
     * @param str String in JSON format for parsing
     * @return created response object
     * @throws JSONMapperException it thrown if the String have incorrect format
     */
    public Response buildResponse(String str) throws JSONMapperException {
        Response response;
        ObjectMapper mapper = new ObjectMapper();
        try {
            response = mapper.readValue(str, Response.class);
        } catch (IOException e) {
            throw new JSONMapperException("Wrong response from jsonplaceholder.typicode.com", e);
        }
        return response;
    }

    /**
     * Creates a request in JSON String format by creating request object and mapping it to JSON String.
     * Throws a JSONMapperException if the String have incorrect format
     * @param title title for request object
     * @param message message for request object
     * @param userId userId for request object
     * @return created response in JSON String format
     * @throws JSONMapperException it thrown if the request object can't be converted to JSON String
     */
    public String buildRequest(String title, String message, String userId) throws JSONMapperException {
        OutputStream output = new ByteArrayOutputStream();
        ObjectMapper objectMapper = new ObjectMapper();
        Request request = new Request(title, message, userId);
        try {
            objectMapper.writeValue(output, request);
        } catch (IOException e) {
            throw new JSONMapperException("Wrong request to jsonplaceholder.typicode.com", e);
        }
        return output.toString();
    }


}

