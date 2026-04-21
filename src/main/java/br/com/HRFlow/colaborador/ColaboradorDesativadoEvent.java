package br.com.HRFlow.colaborador;

import org.springframework.context.ApplicationEvent;

public class ColaboradorDesativadoEvent extends ApplicationEvent {
    private final Colaborador colaborador;
    private final Colaborador gestor;

    public ColaboradorDesativadoEvent(Object source, Colaborador colaborador, Colaborador gestor) {
        super(source);
        this.colaborador = colaborador;
        this.gestor = gestor;
    }

    public Colaborador getColaborador() {
        return colaborador;
    }

    public Colaborador getGestor() {
        return gestor;
    }
}
