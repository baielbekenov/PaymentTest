package kg.angryelizar.paymenttest.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Schema(description = "Request for register")
public class SignUpDto {
    @Schema(description = "Login - phone number", example = "+996708801562")
    @Size(min = 13, max = 13, message = "Phone number need to have 13 symbols")
    @NotBlank(message = "Phone number cannot be empty")
    @Pattern(regexp = "\\+996\\d{9}", message = "Enter the number in the format +996XXXXXXXXX")
    private String phoneNumber;

    @Schema(description = "Username", example = "angryelizar")
    @Size(min = 8, max = 30, message = "Username needs to have more than 8 symbols and less than 30 symbols")
    @NotBlank(message = "Username number cannot be empty")
    private String username;

    @Schema(description = "Password", example = "qwerty")
    @Size(min = 5, max = 30, message = "Password needs to have more than 5 symbols and less than 30 symbols")
    @NotBlank(message = "Password cannot be empty")
    private String password;
}
