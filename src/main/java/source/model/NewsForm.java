package source.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class NewsForm {

    private int id;

    @Size(min=4, max = 32, message = "Title must be 4-32 symbols")
    private String title;

    @Size(max = 500, message = "Title must be 1-500 symbols")
    @NotBlank(message = "Body cannot be empty")
    private String body;

    public NewsForm(){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
