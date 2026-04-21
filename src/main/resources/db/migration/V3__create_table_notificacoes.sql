CREATE TABLE notificacoes (
                              id BIGSERIAL PRIMARY KEY,
                              destinatario VARCHAR(250) NOT NULL,
                              assunto VARCHAR(1000),
                              tipo VARCHAR(50),
                              status VARCHAR(50),
                              data_envio TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                              colaborador_id BIGINT,

                              CONSTRAINT fk_colaborador_id
                                  FOREIGN KEY (colaborador_id)
                                      REFERENCES colaboradores(id)
);