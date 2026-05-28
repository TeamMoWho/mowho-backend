package team.mowho.backend.domain.member.presentation.command;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import team.mowho.backend.domain.member.application.command.MemberCommandService;
import team.mowho.backend.domain.member.application.dto.response.MemberRegisterResponse;
import team.mowho.backend.domain.member.presentation.request.MemberRegisterRequest;

@RequiredArgsConstructor
@RequestMapping("/api/members")
@RestController
public class MemberCommandController {

    private final MemberCommandService memberCommandService;

    @PostMapping
    public ResponseEntity<MemberRegisterResponse> register(
            @Valid @RequestBody MemberRegisterRequest request
    ) {
        MemberRegisterResponse response = memberCommandService.register(request.toServiceRequest());

        return ResponseEntity.status(201).body(response);
    }

}
