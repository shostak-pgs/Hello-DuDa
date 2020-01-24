package app.utils.validator;

import app.dao.UserDto;
import app.role.Role;

public class UserValidatorImpl {
    private static final String NAME_IS_NOT_VALID = "Name and login can't be empty";
    private static final String ROLE_IS_NOT_VALID = "Role is not valid";

    public void validate(UserDto user) {
        System.out.println(Role.toRole(user.getRole()));
        if (user.getLogin().equals("") || user.getPassword().equals("")) {
            throw new IllegalArgumentException(NAME_IS_NOT_VALID);
        } else if(Role.toRole(user.getRole()).equals(Role.DEFAULT)) {
            throw new IllegalArgumentException(ROLE_IS_NOT_VALID);
        }
    }
}
