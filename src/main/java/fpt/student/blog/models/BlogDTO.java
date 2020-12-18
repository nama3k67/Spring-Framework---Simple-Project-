package fpt.student.blog.models;

import lombok.Data;
import javax.validation.constraints.NotBlank;

@Data
public class BlogDTO {
    @NotBlank(message = "Title is required!")
    private String title;

    @NotBlank(message = "Content is required!")
    private String content;

    private String picture;

    private Integer cateId;
}
