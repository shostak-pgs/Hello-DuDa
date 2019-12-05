package mog.epam.java_course.service;

public class ClientRequestException extends Exception {
    public ClientRequestException(String str, Exception e) {
        super(str, e);
    }
}
