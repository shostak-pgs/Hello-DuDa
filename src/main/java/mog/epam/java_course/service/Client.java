package mog.epam.java_course.service;

import java.io.IOException;

/**
 * Designed to perform GET and POST requests
 */
public interface Client {

    /**
     * Designed to receive publications from the site by the specified publication id
     * @param id id of the required publication
     * @return String representation of response in JSON format
     * @throws ClientRequestException thrown if it is unable to connect to the server or such publication does not exist
     */
    public String doGet(String id) throws IOException;

    /**
     * Designed to store information about a new publication by the specified publication id
     * @param articleId id under which you want to post an article
     * @param params for saving information about a new publication
     * @return String representation of response in JSON format
     * @throws ClientRequestException thrown if it is unable to connect to the server or unable to store publication
     */
    public String doPost(String articleId, String params) throws IOException;
}
