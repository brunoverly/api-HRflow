package br.com.HRFlow.auth;

import br.com.HRFlow.auth.dto.CreateLoginDto;
import br.com.HRFlow.auth.dto.LoginRequestDto;
import br.com.HRFlow.auth.dto.LoginResponseDto;
import jakarta.validation.Valid;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("v1/auth")
public class AuthController {
    @Autowired
    private AuthService service;

    @PostMapping
    public ResponseEntity<LoginResponseDto> create(@Valid @RequestBody CreateLoginDto dto) throws BadRequestException {
        return ResponseEntity.ok().body(service.create(dto));
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> login (@Valid @RequestBody LoginRequestDto dto) throws BadRequestException {
        return ResponseEntity.ok().body(service.login(dto));
    }
}
