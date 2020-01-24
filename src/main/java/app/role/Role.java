package app.role;


public enum Role {
    SURFER("surfer"),
    USER("user"),
    ADMIN("admin"),
    DEFAULT("default");

    private String role;

    Role(String role){
        this.role = role;
    }

    /**
     * Returns Role enum from string representation
     * @param str string for Role search
     * @return the Role
     */
    public static Role toRole(String str){
        Role role;
        switch(str.toUpperCase()){
            case "ADMIN":
                role = ADMIN;
                break;
            case "USER":
                role = USER;
            break;
            case "SURFER":
                role = SURFER;
            break;
            default:
                role = DEFAULT;
                break;
        }
        return role;
    }
}

