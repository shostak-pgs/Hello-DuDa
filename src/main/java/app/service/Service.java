package app.service;

import java.sql.SQLException;

public interface Service {

    /**
     * Returns the object by the transferred name
     * @param name name of object need to return
     * @return the requestedObject
     * @throws SQLException an exception that provides information on a database access
     * error or other errors.
     */
    Object get(String name);
}
