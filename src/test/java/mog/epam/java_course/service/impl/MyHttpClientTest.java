package mog.epam.java_course.service.impl;

import mog.epam.java_course.service.ClientRequestException;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import java.io.*;
import org.mockito.runners.MockitoJUnitRunner;
import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class MyHttpClientTest {
    @Mock
    private HttpEntity entityMock;

    @Mock
    private CloseableHttpClient closeableHttpClient;

    @Mock
    CloseableHttpResponse closeableHttpResponse;

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

        when(entityMock.getContent()).thenReturn(new ByteArrayInputStream(request.getBytes()));
        when(closeableHttpClient.execute(any(HttpGet.class))).thenReturn(closeableHttpResponse);
        when(closeableHttpResponse.getEntity()).thenReturn(entityMock);
        String actual = new MyHttpClient(closeableHttpClient).doGet("92");
        assertEquals(actual, request);
    }

    @Test(expected = ClientRequestException.class)
    public void testDoGetRequestException() throws IOException {
        String request = "{" +
                "\"userId\" \"10\"," +
                "\"id\": \"92\"," +
                "\"title\": \"random title\"," +
                "\"body\": \"body\"" +
                "}";

        when(entityMock.getContent()).thenReturn(new ByteArrayInputStream(request.getBytes()));
        when(closeableHttpClient.execute(any(HttpGet.class))).thenThrow(new IOException());
        when(closeableHttpResponse.getEntity()).thenReturn(entityMock);
        String actual = new MyHttpClient(closeableHttpClient).doGet("92");
    }

    @Test
    public void testDoPost() throws IOException {
        String request = "{" +
                "\"title\": \"random title\"," +
                "\"body\": \"body\"" +
                "\"userId\" \"10\"," +
                "}";
        String expected = "{" +
                "\"userId\" \"10\"," +
                "\"id\": \"101\"," +
                "\"title\": \"random title\"," +
                "\"body\": \"body\"" +
                "}";

        when(entityMock.getContent()).thenReturn(new ByteArrayInputStream(expected.getBytes()));
        when(closeableHttpClient.execute(any(HttpPost.class))).thenReturn(closeableHttpResponse);
        when(closeableHttpResponse.getEntity()).thenReturn(entityMock);
        String actual = new MyHttpClient(closeableHttpClient).doPost("92", request);
        assertEquals(actual, expected);
    }


    @Test(expected = ClientRequestException.class)
    public void testDoPostRequestException() throws IOException {
        String request = "{" +
                "\"title\": \"random title\"," +
                "\"body\": \"body\"" +
                "\"userId\" \"10\"," +
                "}";
        String expected = "{" +
                "\"userId\" \"10\"," +
                "\"id\": \"101\"," +
                "\"title\": \"random title\"," +
                "\"body\": \"body\"" +
                "}";

        when(entityMock.getContent()).thenReturn(new ByteArrayInputStream(expected.getBytes()));
        when(closeableHttpClient.execute(any(HttpPost.class))).thenThrow(new IOException());
        String actual = new MyHttpClient(closeableHttpClient).doPost("92", request);
    }
}