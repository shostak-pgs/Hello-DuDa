package mog.epam.java_course.bean;

import java.util.Objects;

/**
 * Сlass models a response from the site "https://jsonplaceholder.typicode.com" and has fields
 * to hold four parameters describing an existing publication
 */
public class Response {
    private String userId;
    private String id;
    private String title;
    private String body;

    /**
     * An empty constructor need for work Jackson ObjectMapper
     */
    public Response(){};

    public Response(String userId, String id, String title, String body) {
        this.userId = userId;
        this.id = id;
        this.title = title;
        this.body = body;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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
        if (!(o instanceof Response)) return false;
        Response response = (Response) o;
        return userId.equals(response.userId) &&
                id.equals(response.id) &&
                title.equals(response.title) &&
                body.equals(response.body);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, id, title, body);
    }

    @Override
    public String toString() {
        return "Response{" +
                "userId='" + userId + '\'' +
                ", id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", body='" + body + '\'' +
                '}';
    }
}
