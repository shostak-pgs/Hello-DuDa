package mog.epam.java_course.bean;

import java.util.Objects;

/**
 * Сlass models the POST request to the site and has fields to hold three of the necessary
 * parameters to create a new publication
 */
public class Request {
    private String title;
    private String body;
    private String userId;

    /**
     * An empty constructor need for work Jackson ObjectMapper
     */
    public Request(){};

    public Request(String title, String body, String userId) {
        this.title = title;
        this.body = body;
        this.userId = userId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Request request = (Request) o;
        return Objects.equals(userId, request.userId) &&
                Objects.equals(title, request.title) &&
                Objects.equals(body, request.body);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, title, body);
    }

    @Override
    public String toString() {
        return "Request{" +
                "userId='" + userId + '\'' +
                ", title='" + title + '\'' +
                ", body='" + body + '\'' +
                '}';
    }
}

