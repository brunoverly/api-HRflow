package br.com.HRFlow.colaborador;

import br.com.HRFlow.colaborador.dto.ColaboradoresRequestDto;
import br.com.HRFlow.colaborador.dto.ColaboradoresResponseDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ColaboradorMapper {

    Colaborador toEntity(ColaboradoresRequestDto dto);
    ColaboradoresResponseDto toResponse(Colaborador colaborador);
}
