package br.com.HRFlow.colaborador;

import br.com.HRFlow.colaborador.dto.ColaboradoresRequestDto;
import br.com.HRFlow.colaborador.dto.ColaboradoresResponseDto;
import br.com.HRFlow.exception.DadosDuplicadosException;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestTemplate;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.context.ApplicationEventPublisher;
import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ColaboradorServiceTest {
    @Mock
    private ColaboradorRepository repository;

    @Mock
    private ColaboradorMapper mapper;

    @Mock
    private ApplicationEventPublisher publisher;

    @InjectMocks
    private ColaboradorService service;


    private Colaborador createColaborador() {
        return Colaborador.builder()
                .id(1L)
                .nome("Carlos Brito")
                .email("carlos@email.com")
                .cargo("dev")
                .departamento("TI")
                .dataAdimissao(LocalDate.of(2025,04,04))
                .ativo(true)
                .gestor(null)
                .build();
    };

    private ColaboradoresRequestDto createColaboradorRequestDto() {
        return new ColaboradoresRequestDto(
                "Carlos Brito",
                "carlos@email.com",
                "dev",
                "TI",
                LocalDate.of(2025,04,04),
                null
        );
    };
    ColaboradoresResponseDto createColaboradorResponseDto() {
        return new ColaboradoresResponseDto(
                1L,
                "Carlos Brito",
                "carlos@email.com",
                "dev",
                "TI",
                LocalDate.of(2025, 04, 04),
                null
        );
    }
    @Test
    void deveCriarColaboradorComSucesso(){
        //ARRANGE
        Colaborador colaborador = createColaborador();
        ColaboradoresRequestDto dtoRequest = createColaboradorRequestDto();
        ColaboradoresResponseDto dtoResponse = createColaboradorResponseDto();


        when(repository.existsByEmail(dtoRequest.email())).thenReturn(false);
        when(mapper.toEntity(dtoRequest)).thenReturn(colaborador);
        when(repository.save(colaborador)).thenReturn(colaborador);
        when(mapper.toResponse(colaborador)).thenReturn(dtoResponse);

        //ACT
        ColaboradoresResponseDto response = service.create(dtoRequest);

        //ASSERT
        assertEquals(dtoResponse, response);
        verify(repository).existsByEmail(dtoRequest.email());
        verify(mapper).toEntity(dtoRequest);
        verify(repository).save(any(Colaborador.class));
        verify(mapper).toResponse(colaborador);
        verify(publisher).publishEvent(any(ColaboradorCriadoEvent.class));
    }

    @Test
    void deveCriarColaboradorComErroDeIdGestor(){
        ColaboradoresRequestDto dtoRequest = new ColaboradoresRequestDto(
                "Carlos Brito",
                "carlos@email.com",
                "dev",
                "TI",
                LocalDate.of(2025,04,04),
                33L
        );

        when(repository.findById(dtoRequest.gestorId())).thenReturn(Optional.empty());

        Exception e = assertThrows(EntityNotFoundException.class, () -> service.create(dtoRequest));
        assertEquals(e.getMessage(),"Gestor com id {" + dtoRequest.gestorId() + "} não localizado no sistema");
        verify(repository).findById(dtoRequest.gestorId());
        verify(repository, never()).save(any());
    }

    @Test
    void deveCriarColaboradorComErroDeEmailDuplicado(){
        ColaboradoresRequestDto dtoRequest = createColaboradorRequestDto();

        when(repository.existsByEmail(dtoRequest.email())).thenReturn(true);

        Exception e = assertThrows(DadosDuplicadosException.class, () -> service.create(dtoRequest));
        assertEquals(e.getMessage(),"Email informado não disponível");
        verify(repository).existsByEmail(dtoRequest.email());
        verify(repository, never()).save(any());
    }

    @Test
    void deveDeletarColaboradorComSucesso(){
        //ARRANGE
        Long id = 1L;
        Colaborador colaborador = createColaborador();
        Colaborador gestor = createColaborador();
        gestor.setId(2L);
        colaborador.setGestor(gestor);

        when(repository.findByIdAndAtivoTrue(id)).thenReturn(Optional.of(colaborador));
        when(repository.findById(colaborador.getGestor().getId())).thenReturn(Optional.of(gestor));
        //ACT

        service.delete(id);
        //ASSERT

        assertFalse(colaborador.isAtivo());
        verify(repository).findByIdAndAtivoTrue(id);
        verify(repository).save(colaborador);
        verify(publisher).publishEvent(any(ColaboradorDesativadoEvent.class));
    }

    @Test
    void deveDeletarColaboradorComErroDeIdNaoEncontrado(){
        //ARRANGE
        Long id = 1L;

        when(repository.findByIdAndAtivoTrue(id)).thenReturn(Optional.empty());

        //ACT+ASSERT
        Exception e =  assertThrows(EntityNotFoundException.class, () -> service.delete(id));
        assertEquals(e.getMessage(), "Colaborador com id {1} não localizado no sistema");
        verify(repository).findByIdAndAtivoTrue(id);
        verify(repository, never()).save(any());
    }

    @Test
    void deveAtualizarColaboradorComSucesso(){
        //ARRANGE
        Long id = 1L;
        Colaborador colaborador = createColaborador();
        ColaboradoresRequestDto gestor = new ColaboradoresRequestDto(
                "teste3",
                "teste333@email.com",
                "cargoTeste",
                "departamentoTeste",
                LocalDate.of(2025,04,04),
                1L
        );

        when(repository.findByIdAndAtivoTrue(id)).thenReturn(Optional.of(colaborador));

        //ACT+ASSERT
        Exception e =  assertThrows(EntityNotFoundException.class, () -> service.delete(id));
        assertEquals(e.getMessage(), "Colaborador com id {1} não localizado no sistema");
        verify(repository).findByIdAndAtivoTrue(id);
        verify(repository, never()).save(any());
    }

    @Test
    void deveAtualizarColaboradorComErroDeIdNaoEncontrado(){
        //ARRANGE
        Long id = 1L;

        when(repository.findByIdAndAtivoTrue(id)).thenReturn(Optional.empty());

        //ACT+ASSERT
        Exception e =  assertThrows(EntityNotFoundException.class, () -> service.delete(id));
        assertEquals(e.getMessage(), "Colaborador com id {1} não localizado no sistema");
        verify(repository).findByIdAndAtivoTrue(id);
        verify(repository, never()).save(any());
    }


}
