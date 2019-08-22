package source.model;

import javax.validation.constraints.Size;

public class UserForm {
    private int id;

    @Size(min=4, max=32,message = "Username must be 4-32 symbols")
    private String username;
    @Size(min=4, max=255, message = "Password must be 4-255 symbols")
    private String password;
    private String checkPassword;

    public UserForm(){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCheckPassword() {
        return checkPassword;
    }

    public void setCheckPassword(String checkPassword) {
        this.checkPassword = checkPassword;
    }
}
