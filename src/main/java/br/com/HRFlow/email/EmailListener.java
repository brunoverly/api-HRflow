package br.com.HRFlow.email;

import br.com.HRFlow.colaborador.Colaborador;
import br.com.HRFlow.colaborador.ColaboradorAtualizadoEvent;
import br.com.HRFlow.colaborador.ColaboradorCriadoEvent;
import br.com.HRFlow.colaborador.ColaboradorDesativadoEvent;
import br.com.HRFlow.notificacao.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
public class EmailListener {
    @Autowired
    private EmailService service;

    @Autowired
    private NotificacaoService notificacaoService;

    @Async
    @EventListener
    public void handleColaboradorCriado(ColaboradorCriadoEvent event) {
        Colaborador colaborador = event.getColaborador();

        try {
            service.enviarEmailBoasVindas(colaborador);
            notificacaoService.criarNotificacaoEmail(
                    colaborador,
                    "Email de conta criada enviado com sucesso para "+ colaborador.getNome(),
                    TipoNotificacao.CRIADO,
                    StatusNotificacao.ENVIADO);
        }catch (Exception e) {
            e.printStackTrace();
            System.out.println("entrou aqui");
            notificacaoService.criarNotificacaoEmail(
                    colaborador,
                    "Falhou ao enviar o email de conta criada para "+ colaborador.getNome(),
                    TipoNotificacao.CRIADO,
                    StatusNotificacao.FALHOU);
        }
    }

    @Async
    @EventListener
    public void handleColaboradorDesativado(ColaboradorDesativadoEvent event) {
        Colaborador colaborador = event.getColaborador();
        Colaborador gestor = event.getGestor();

        try {
            service.enviarEmailDesativado(colaborador, gestor);
            notificacaoService.criarNotificacaoEmail(
                    gestor,
                    "Email de conta desativa enviado com sucesso para o gestor de "+ colaborador.getNome(),
                    TipoNotificacao.DESATIVADO,
                    StatusNotificacao.ENVIADO);
        }catch (Exception e) {
            e.printStackTrace();
            System.out.println("entrou aqui");
            notificacaoService.criarNotificacaoEmail(
                    gestor,
                    "Falhou ao enviar o email de conta desativada para o gestor de "+ colaborador.getNome(),
                    TipoNotificacao.DESATIVADO,
                    StatusNotificacao.FALHOU);
        }

    }

    @Async
    @EventListener
    public void handleColaboradorAtualizado(ColaboradorAtualizadoEvent event) {
        Colaborador colaborador = event.getColaborador();

        try {
            service.enviarEmailAtualizado(colaborador);
            notificacaoService.criarNotificacaoEmail(
                    colaborador,
                    "Email de conta atualizada enviado com sucesso para "+ colaborador.getNome(),
                    TipoNotificacao.ATUALIZADO,
                    StatusNotificacao.ENVIADO);
        }catch (Exception e) {
            e.printStackTrace();
            System.out.println("entrou aqui");
            notificacaoService.criarNotificacaoEmail(
                    colaborador,
                    "Falhou ao enviar o email de conta atualizada para "+ colaborador.getNome(),
                    TipoNotificacao.ATUALIZADO,
                    StatusNotificacao.FALHOU);
        }

    }
}
