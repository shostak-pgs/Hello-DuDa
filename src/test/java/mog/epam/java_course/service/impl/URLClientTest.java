package mog.epam.java_course.service.impl;

import mog.epam.java_course.service.ClientRequestException;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

public class URLClientTest {
    @Mock
    private HttpURLConnection urlConMock;

   // @Mock
  //4  private URL urlMock;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testDoGet() throws IOException {
        String request = "{" +
                "\"userId\" \"10\"," +
                "\"id\": \"92\"," +
                "\"title\": \"random title\"," +
                "\"body\": \"body\"" +
                "}";

        when(any(URL.class).openConnection()).thenReturn(urlConMock);
        when(urlConMock.getInputStream()).thenReturn(new ByteArrayInputStream(request.getBytes()));
        String actual = new URLClient().doGet("92");
        assertEquals(actual, request);
    }

    @Test
    public void testDoPost() {
    }
}