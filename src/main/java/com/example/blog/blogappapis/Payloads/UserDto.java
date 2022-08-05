package com.example.blog.blogappapis.Payloads;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.UniqueElements;

import javax.validation.constraints.*;

@NoArgsConstructor
@Setter
@Getter
public class UserDto {

    private Long id;

    @NotNull(message = "Please enter your name")
    @Size(min = 3, message = "Name must have 3 characters")
    private String name;

    @Email(message = "Email should be a valid email")
    private String email;

    @NotNull
    @Size(min = 8, max = 15, message = "Password must contain characters between 8 - 15")
    private String password;

    @NotNull
    @Size(min = 4, message = "About must have characters more that 4")
    private String about;
}
