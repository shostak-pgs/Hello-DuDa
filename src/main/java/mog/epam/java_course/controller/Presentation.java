package mog.epam.java_course.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import mog.epam.java_course.bean.Response;

import java.io.IOException;

/**
 * Displays the result of the program execution
 */
public class Presentation {

    /**
     * Displays the passed answer in the specified format. Uses for GET response
     * @param response response to be displayed
     */
    public static void showGetResponse(Response response) {
        System.out.printf("Article [%s]: User [%s] Title [\"%s\"] Message [\"%s\"]",
                response.getId(), response.getUserId(), response.getTitle(), response.getBody());
    }

    /**
     * Displays the passed answer in the specified format. Uses for POST response
     * @param response response to be displayed
     */
    public static void showPostResponse(Response response) {
        System.out.printf("Article [%s] has been created: User [%s] Title [\"%s\"] Message [\"%s\"]",
                response.getId(), response.getUserId(), response.getTitle(), response.getBody());
    }
}
