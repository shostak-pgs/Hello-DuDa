package mog.epam.java_course.service.impl;

import mog.epam.java_course.bean.Request;
import mog.epam.java_course.bean.Response;
import mog.epam.java_course.service.JSONMapperException;
import org.junit.Test;

import static org.junit.Assert.*;

public class RequestResponseBuilderTest {

    @Test
    public void buildResponse() throws JSONMapperException {
        String params =  "{" +
                "\"userId\": \"10\"," +
                "\"id\": \"92\"," +
                "\"title\": \"random title\"," +
                "\"body\": \"body\"" +
                 "}";
        Response expected = new Response("10", "92", "random title", "body");
        Response actual = new RequestResponseBuilder().buildResponse(params);
        assertEquals(expected, actual);
    }

    @Test(expected = JSONMapperException.class)
    public void buildResponseNegative() throws JSONMapperException {
        String params =  "{" +
                "\"userId\" \"10\"," +
                "\"id\": \"92\"," +
                "\"title\": \"random title\"," +
                "\"body\": \"body\"" +
                "}";
        Response actual = new RequestResponseBuilder().buildResponse(params);
    }

    @Test
    public void buildRequest() throws JSONMapperException {
            String expected =  "{" +
                    "\"title\":\"title\"," +
                    "\"body\":\"mother clear the window again\"," +
                    "\"userId\":\"101\"" +
                    "}";
            String actual = new RequestResponseBuilder().buildRequest("title", "mother clear the window again", "101");
            assertEquals(expected, actual);
    }
}