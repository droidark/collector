package net.comicorp.collector.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import static net.comicorp.collector.constant.Constants.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserDTO {

    @NotNull
    @NotBlank(message = USERNAME_BLANK)
    @Pattern(regexp = USERNAME_REGEX,
            message = USERNAME_EXCEPTION_MESSAGE)
    private String username;

    @NotNull
    @NotBlank(message = USERNAME_BLANK)
    @Pattern(regexp = PASSWORD_REGEX,
            message = PASSWORD_EXCEPTION_MESSAGE)
    private String password;

    @NotNull
    @Email
    private String email;
    private String avatar;
    private String cover;
    private String aboutYou;
}
