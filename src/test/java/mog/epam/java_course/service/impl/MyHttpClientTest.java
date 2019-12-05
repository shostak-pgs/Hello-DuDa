package mog.epam.java_course.service.impl;

import mog.epam.java_course.service.ClientRequestException;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.util.EntityUtils;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import static org.junit.Assert.*;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.when;

public class MyHttpClientTest {
    @Mock
    HttpEntity entityMock;

    @InjectMocks
    MyHttpClient client;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void doGet() throws IOException, ClientRequestException {
        String request =  "{" +
                "\"userId\" \"10\"," +
                "\"id\": \"92\"," +
                "\"title\": \"random title\"," +
                "\"body\": \"body\"" +
                "}";
        when(entityMock.getContent()).thenReturn(new ByteArrayInputStream(request.getBytes()));
        String actual = new MyHttpClient().doGet("92");
        System.out.println(actual);
        assertEquals(actual, request);
    }

    @Test
    public void doPost() {
    }
}