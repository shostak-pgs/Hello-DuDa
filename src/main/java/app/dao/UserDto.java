package app.dao;

import app.entity.Order;
import javax.validation.constraints.NotBlank;
import java.util.Objects;

public class UserDto {
    private final Long id;
    private final String login;
    private final String password;
    private final String role;
    private Order order;

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }



    public UserDto(final long id, @NotBlank final String login, @NotBlank final String password, final String role, Order order) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.role = role;
        this.order = order;
    }

    public UserDto() {
        this.id = 0L;
        this.login = "";
        this.password = "";
        this.role = "USER";
        this.order = new Order();
    }

    public UserDto(@NotBlank final String login, @NotBlank final String password, String role) {
        this.id = 0L;
        this.login = login;
        this.password = password;
        this.role = role;
        this.order = new Order();
    }

    public Long getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public String getRole() {
        return role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserDto)) return false;
        UserDto userDto = (UserDto) o;
        return Objects.equals(id, userDto.id) &&
                login.equals(userDto.login) &&
                password.equals(userDto.password) &&
                role.equals(userDto.role);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, login, password, role);
    }

    @Override
    public String toString() {
        return "UserDto{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", password= \\" +
                ", role=" + role +
                '}';
    }
}
