package br.com.HRFlow.notificacao;

import br.com.HRFlow.colaborador.Colaborador;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class NotificacaoService {
    @Autowired
    private NotificacaoRepository repository;

   public void criarNotificacaoEmail(Colaborador colaborador, String assunto,TipoNotificacao tipoNotificacao, StatusNotificacao status) {
       Notificacao notificacao = Notificacao.builder()
           .destinatario(colaborador.getEmail())
           .assunto(assunto)
           .tipo(tipoNotificacao)
           .dataEnvio(LocalDateTime.now())
           .colaborador(colaborador)
           .status(status)
           .build();

       repository.save(notificacao);
   }
}
