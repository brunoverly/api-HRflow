INSERT INTO usuarios (nome, email, senha, role) VALUES
                                                    ('Alice Silva', 'alice.silva@email.com', '$2a$12$eImiTXuWVxfM37uY4JANjOijuKbgJzJ.rR.9f9u0YJvF8Oq18yU8i', 'ADMIN'),
                                                    ('Bruno Oliveira', 'bruno.o@email.com', '$2a$12$8K1p/992.3.Z.9.C2.3u.O6k1p/992.3.Z.9.C2.3u.O6', 'USER'),
                                                    ('Carla Souza', 'carla.souza@email.com', '$2a$12$7kP.B9Jz6yR5M8L2X3V4U5G6H7J8K9L0M1N2O3P4Q5R6', 'USER'),
                                                    ('Diego Santos', 'diego.santos@email.com', '$2a$12$vN1m2n3o4p5q6r7s8t9u0v1w2x3y4z5a6b7c8d9e0f1', 'USER'),
                                                    ('Eduarda Lima', 'eduarda.lima@email.com', '$2a$12$L9K8J7H6G5F4D3S2A1Q0W9E8R7T6Y5U4I3O2P1L0M9', 'MANAGER'),
                                                    ('Felipe Costa', 'felipe.c@email.com', '$2a$12$Z1X2C3V4B5N6M7L8K9J0H1G2F3D4S5A6Q7W8E9R0T1', 'USER'),
                                                    ('Gabriela Rocha', 'gabi.rocha@email.com', '$2a$12$M1N2B3V4C5X6Z7A8S9D0F1G2H3J4K5L6Q7W8E9R0T1', 'USER'),
                                                    ('Henrique Dias', 'h.dias@email.com', '$2a$12$Q1W2E3R4T5Y6U7I8O9P0A1S2D3F4G5H6J7K8L9M0N1', 'USER'),
                                                    ('Isabela Martins', 'isabela.m@email.com', '$2a$12$A1S2D3F4G5H6J7K8L9M0Q1W2E3R4T5Y6U7I8O9P0A1', 'USER'),
                                                    ('João Pedro', 'jp.dev@email.com', '$2a$12$Z0X9C8V7B6N5M4L3K2J1H0G9F8D7S6A5Q4W3E2R1T0', 'USER');

-- 1. Inserindo os Gestores primeiro (gestor_id como NULL)
INSERT INTO colaboradores (nome, email, cargo, departamento, data_admissao, gestor_id) VALUES
                                                                                           ('Ricardo Almeida', 'ricardo.almeida@empresa.com', 'Diretor de Operações', 'Executivo', '2015-03-10', NULL),
                                                                                           ('Mariana Costa', 'mariana.costa@empresa.com', 'Gerente de TI', 'Tecnologia', '2018-06-15', NULL),
                                                                                           ('Roberto Silva', 'roberto.silva@empresa.com', 'Gerente de RH', 'Recursos Humanos', '2019-01-20', NULL);

-- 2. Inserindo os Colaboradores vinculados aos gestores acima
-- Assumindo que os IDs gerados foram 1, 2 e 3 respectivamente
INSERT INTO colaboradores (nome, email, cargo, departamento, data_admissao, gestor_id) VALUES
                                                                                           ('Ana Clara', 'ana.clara@empresa.com', 'Desenvolvedora Sênior', 'Tecnologia', '2020-05-12', 2),
                                                                                           ('Lucas Pertile', 'lucas.p@empresa.com', 'Desenvolvedor Pleno', 'Tecnologia', '2021-08-22', 2),
                                                                                           ('Beatriz Nunes', 'beatriz.n@empresa.com', 'Analista de Sistemas', 'Tecnologia', '2022-11-30', 2),
                                                                                           ('Fernando Souza', 'fernando.s@empresa.com', 'Analista de Recrutamento', 'Recursos Humanos', '2021-03-05', 3),
                                                                                           ('Juliana Paiva', 'juliana.paiva@empresa.com', 'Coordenadora Administrativa', 'Operações', '2019-07-14', 1),
                                                                                           ('Thiago Mendes', 'thiago.mendes@empresa.com', 'Analista de Suporte', 'Tecnologia', '2023-02-10', 2),
                                                                                           ('Sérgio Moro', 'sergio.m@empresa.com', 'Assistente Administrativo', 'Operações', '2023-06-01', 1);