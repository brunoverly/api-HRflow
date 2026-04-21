package br.com.HRFlow.colaborador;

import org.springframework.context.ApplicationEvent;

public class ColaboradorCriadoEvent extends ApplicationEvent {
    private final Colaborador colaborador;

    public ColaboradorCriadoEvent(Object source, Colaborador colaborador) {
        super(source);
        this.colaborador = colaborador;
    }

    public Colaborador getColaborador() {
        return colaborador;
    }
}
