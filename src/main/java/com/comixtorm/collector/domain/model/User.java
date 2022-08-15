package com.comixtorm.collector.domain.model;

import com.comixtorm.collector.util.Constants;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.Date;
import java.util.List;

@Document
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class User {
    @Id
    private String id;

    @NotNull
    @NotBlank(message = "username shouldn't be blank")
    @Pattern(regexp = Constants.USERNAME_REGEX,
            message = Constants.USERNAME_EXCEPTION_MESSAGE)
    private String username;

    @NotNull
    @NotBlank(message = "Password shouldn't be blank")
    @Pattern(regexp = Constants.PASSWORD_REGEX,
            message = Constants.PASSWORD_EXCEPTION_MESSAGE)
    private String password;

    @NotBlank(message = "Email shouldn't be blank")
    @Email
    private String email;
    private String avatar;
    private String cover;
    private String aboutYou;
    private Date signUpDate;
    private String status;
    private String profile;
    private List<Like> likes;
    @JsonIgnore
    private List<Item> items;
}