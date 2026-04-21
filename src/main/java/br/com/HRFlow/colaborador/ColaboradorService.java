package br.com.HRFlow.colaborador;

import br.com.HRFlow.colaborador.dto.ColaboradoresRequestDto;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ColaboradorService {
    @Autowired
    private ColaboradorRepository repository;

    @Autowired
    private ColaboradorMapper mapper;


    public Object create(@Valid ColaboradoresRequestDto dto) {
        Colaborador gestor = null;
        if(dto.gestorId() != null){
            gestor = repository.findById(dto.gestorId())
                    .orElseThrow(() -> new EntityNotFoundException("Gestor com id {" + dto.gestorId() + "} não localizado no sistema"));
        }

        Colaborador colaborador = mapper.toEntity(dto);
        colaborador.setGestor(gestor);

        repository.save(colaborador);

        return mapper.toResponse(colaborador);
    }
}
