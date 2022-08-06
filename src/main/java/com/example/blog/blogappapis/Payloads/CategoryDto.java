package com.example.blog.blogappapis.Payloads;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@NoArgsConstructor
@Getter
@Setter
public class CategoryDto {

    private Long categoryId;

    @NotNull @NotBlank @Size(min = 4, max = 20, message = "Title must have characters between 4 to 20")
    private String categoryTitle;

    @NotNull @NotBlank @Size(min = 4,  message = "Descriptions must have at least 4 characters")
    private String categoryDescription;
}
