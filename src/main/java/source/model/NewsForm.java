package source.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class NewsForm {

    private int id;

    @Size(min=4, max = 32, message = "Title must be 4-32 symbols")
    @Pattern(regexp = "[A-Za-z0-9, .!?;:]*"
            ,message = "Title must only consist of regular symbols (without '<','/' for e.g)")
    private String title;

    @Size(max = 500, message = "Title must be 1-500 symbols")
    @NotBlank(message = "Body cannot be empty")
    @Pattern(regexp = "[^</]*"
            ,message = "Body must only consist of regular symbols (without \"<\",\"/\" for e.g)")
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
