package source.entity;


import org.springframework.data.annotation.TypeAlias;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="users", uniqueConstraints = {@UniqueConstraint(name="USER_NAME_UQ",
        columnNames = "user_name")})
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="user_id")
    private int id;

    @Column(name = "user_name",length = 32, nullable = false)
    private String name;

    @Column(name = "user_password",nullable = false)
    private String password;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name="user_role",
            joinColumns = @JoinColumn(name = "user_fk"),
            inverseJoinColumns = @JoinColumn(name="role_fk"),
            uniqueConstraints = @UniqueConstraint(name="USER_ROLE_UQ",
                    columnNames = {"user_fk","role_fk"}))
    private Set<Role> roles=new HashSet<>();

    public User(){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }
}
