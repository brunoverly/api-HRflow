package br.com.HRFlow.colaborador;

import org.springframework.context.ApplicationEvent;

public class ColaboradorAtualizadoEvent extends ApplicationEvent {
    private final Colaborador colaborador;

    public ColaboradorAtualizadoEvent(Object source, Colaborador colaborador) {
        super(source);
        this.colaborador = colaborador;
    }

    public Colaborador getColaborador() {
        return colaborador;
    }
}
