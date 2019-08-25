package source.entity;


import org.springframework.data.annotation.TypeAlias;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name="users", uniqueConstraints = {@UniqueConstraint(name="USER_NAME_UQ",
        columnNames = "user_name")})
public class User{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="user_id")
    private int id;

    @Column(name = "user_name",length = 32, nullable = false)
    private String name;

    @Column(name = "user_password",nullable = false)
    private String password;

    //fetch type should be fixed to LAZY:
    //if it switches on LAZY got exception in UserServiceImpl
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name="user_role",
            joinColumns = @JoinColumn(name = "user_fk"),
            inverseJoinColumns = @JoinColumn(name="role_fk"),
            uniqueConstraints = @UniqueConstraint(name="USER_ROLE_UQ",
                    columnNames = {"user_fk","role_fk"}))
    private Set<Role> roles=new HashSet<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Comment> comments;

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof User)
            return Objects.equals(getName(),((User) obj).getName());
        return false;
    }

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
