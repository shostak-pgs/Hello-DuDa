package app.entity;

import javax.persistence.*;
import java.util.Objects;

/**
 * This class encapsulate data for user representation
 */
@Entity
@Table(name = "USERS")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "userName")
    private String login;

    @Column(name = "password")
    private String password;

    @Column(name = "role")
   // @Enumerated(EnumType.STRING)
    private String role;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "id", referencedColumnName = "userId")
    private Order order;

    public User(){}

    public User(final long id, final String login, final String password, final String role, Order order) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.role = role;
        this.order = order;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id &&
                login.equals(user.login) &&
                password.equals(user.password) &&
                order.equals(user.order) &&
                role.equals(user.role);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, login, password, role, order);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", password=*****'"  + '\'' +
                ", role=" + role +
                '}';
    }
}
