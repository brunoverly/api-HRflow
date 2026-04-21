package br.com.HRFlow.colaborador;

import br.com.HRFlow.colaborador.dto.ColaboradoresRequestDto;
import br.com.HRFlow.colaborador.dto.ColaboradoresResponseDto;
import br.com.HRFlow.exception.DadosDuplicadosException;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

@Service
public class ColaboradorService {
    @Autowired
    private ColaboradorRepository repository;

    @Autowired
    private ColaboradorMapper mapper;

    @Autowired
    private ApplicationEventPublisher publisher;


    public ColaboradoresResponseDto create(@Valid ColaboradoresRequestDto dto) {
        Colaborador gestor = null;
        if(dto.gestorId() != null){
            gestor = repository.findByIdAndAtivoTrue(dto.gestorId())
                    .orElseThrow(() -> new EntityNotFoundException("Gestor com id {" + dto.gestorId() + "} não localizado no sistema"));
        }
        if(repository.existsByEmail(dto.email())){
            throw new DadosDuplicadosException("Email informado não disponível");
        }

        Colaborador colaborador = mapper.toEntity(dto);
        colaborador.setGestor(gestor);
        colaborador.setEmail(colaborador.getEmail().toLowerCase());
        repository.save(colaborador);

        publisher.publishEvent(new ColaboradorCriadoEvent(this, colaborador));

        return mapper.toResponse(colaborador);
    }

    public void delete(Long id) {
        Colaborador colaborador = repository.findByIdAndAtivoTrue(id)
                .orElseThrow(()-> new EntityNotFoundException("Colaborador com id {" + id + "} não localizado no sistema"));
        colaborador.setAtivo(false);

        repository.save(colaborador);

        if(colaborador.getGestor() != null){
            Colaborador gestor = repository.findById(colaborador.getGestor().getId()).get();

            publisher.publishEvent(new ColaboradorDesativadoEvent(
                    this,
                    colaborador,
                    gestor));
        }
    }

    public ColaboradoresResponseDto update(@Valid ColaboradoresRequestDto dto, Long id) {
        Colaborador colaborador = repository.findByIdAndAtivoTrue(id)
                .orElseThrow(() -> new EntityNotFoundException("Colaborador com id {" + id + "} não localizado no sistema"));

        if(dto.nome() != null) colaborador.setNome(dto.nome());
        if(dto.email() != null) colaborador.setEmail(dto.email());
        if(dto.cargo() != null) colaborador.setCargo(dto.cargo());
        if(dto.departamento() != null) colaborador.setDepartamento(dto.departamento());
        if(dto.dataAdimissao() != null) colaborador.setDataAdimissao(dto.dataAdimissao());
        if(dto.gestorId() != null){
            Colaborador gestor = repository.findByIdAndAtivoTrue(dto.gestorId())
                    .orElseThrow(() -> new EntityNotFoundException("Gestor com id {" + dto.gestorId() + "} não localizado no sistema."));
            colaborador.setGestor(gestor);
        }
        repository.save(colaborador);

        publisher.publishEvent(new ColaboradorAtualizadoEvent(this, colaborador));
        return mapper.toResponse(colaborador);
    }

}
