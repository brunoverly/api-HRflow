CREATE TABLE colaboradores (
                               id BIGSERIAL PRIMARY KEY,
                               nome VARCHAR(250) NOT NULL,
                               email VARCHAR(200) NOT NULL,
                               cargo VARCHAR(200),
                               departamento VARCHAR(200),
                               data_admissao DATE,
                               ativo BOOLEAN DEFAULT TRUE,
                               gestor_id BIGINT,
                               CONSTRAINT fk_gestor_id
                                   FOREIGN KEY (gestor_id)
                                       REFERENCES colaboradores(id)
);