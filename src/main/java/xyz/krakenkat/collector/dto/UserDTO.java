package xyz.krakenkat.collector.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import xyz.krakenkat.collector.util.Constants;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserDTO {
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

    @NotNull
    @Email
    private String email;
    private String avatar;
    private String cover;
    private String aboutYou;
    private List<LikeDTO> likes;
    private List<ItemDTO> items;
}
