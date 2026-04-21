package br.com.HRFlow.colaborador;

import br.com.HRFlow.colaborador.dto.ColaboradoresRequestDto;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/colaboradores")
public class ColaboradorController {

    @Autowired
    private ColaboradorService service;

    @PostMapping
    public ResponseEntity create(@Valid @RequestBody ColaboradoresRequestDto dto) {
        return ResponseEntity.ok().body(service.create(dto));
    }

}
