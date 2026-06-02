package team.mowho.backend.domain.auth.presentation.command;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import team.mowho.backend.domain.auth.application.auth.command.AuthCommandService;
import team.mowho.backend.domain.auth.presentation.request.LoginRequest;

@RequiredArgsConstructor
@RequestMapping("/api/auth")
@RestController
public class AuthCommandController {

    private final AuthCommandService authCommandService;

    @PostMapping("/login")
    public ResponseEntity<Void> login(
            @Valid @RequestBody LoginRequest request,
            HttpServletResponse response
    ) {
        authCommandService.login(request.toServiceRequest(), response);

        return ResponseEntity.ok().build();
    }

}