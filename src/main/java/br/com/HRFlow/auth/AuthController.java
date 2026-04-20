package br.com.HRFlow.auth;

import br.com.HRFlow.auth.dto.CreateLoginDto;
import br.com.HRFlow.auth.dto.LoginResonseDto;
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
    public ResponseEntity<LoginResonseDto> create(@Valid @RequestBody CreateLoginDto dto) throws BadRequestException {
        return ResponseEntity.ok().body(service.create(dto));

    }


}
