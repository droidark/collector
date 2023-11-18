package xyz.krakenkat.collector.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import xyz.krakenkat.collector.util.Constants;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserDTO {
    @NotNull
    @NotBlank(message = Constants.USERNAME_BLANK)
    @Pattern(regexp = Constants.USERNAME_REGEX,
            message = Constants.USERNAME_EXCEPTION_MESSAGE)
    private String username;

    @NotNull
    @NotBlank(message = Constants.USERNAME_BLANK)
    @Pattern(regexp = Constants.PASSWORD_REGEX,
            message = Constants.PASSWORD_EXCEPTION_MESSAGE)
    private String password;

    @NotNull
    @Email
    private String email;
    private String avatar;
    private String cover;
    private String aboutYou;
    private List<LikeDTO> likes;
    private List<ItemDTO> items;
}
