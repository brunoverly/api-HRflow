<div align="center">

# 🏢 HRFlow Gestão de Pessoas

<!-- Badges Core & Security -->
![Java](https://img.shields.io/badge/Java_17-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)
![Spring Boot 3](https://img.shields.io/badge/Spring_Boot_3-6DB33F?style=for-the-badge&logo=spring&logoColor=white)
![Spring Security](https://img.shields.io/badge/Spring_Security-6DB33F?style=for-the-badge&logo=spring-security&logoColor=white)
![JWT](https://img.shields.io/badge/JWT-000000?style=for-the-badge&logo=json-web-tokens&logoColor=white)

<!-- Badges Data & Email Services -->
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-316192?style=for-the-badge&logo=postgresql&logoColor=white)
![Flyway](https://img.shields.io/badge/Flyway-CC0200?style=for-the-badge&logo=flyway&logoColor=white)
![Thymeleaf](https://img.shields.io/badge/Thymeleaf-005F0F?style=for-the-badge&logo=thymeleaf&logoColor=white)
![Spring Events & Async](https://img.shields.io/badge/Events_&_Async-6DB33F?style=for-the-badge&logo=spring&logoColor=white)

---

O **HRFlow Gestão de Pessoas** é uma API RESTful desenvolvida para o gerenciamento eficiente do ciclo de vida de colaboradores dentro de uma organização. O sistema permite o cadastro, atualização e inativação (soft delete) de funcionários, além de estruturar a hierarquia corporativa através de um auto-relacionamento com gestores, garantindo a segurança de todos os endpoints com autenticação JWT.

O grande diferencial técnico desta aplicação é a sua arquitetura orientada a eventos (*Spring Events*) aliada ao processamento assíncrono (`@Async`). Ações importantes — como boas-vindas na admissão, atualizações cadastrais ou notificações de desligamento aos gestores — disparam o envio automático de e-mails em background, utilizando templates HTML dinâmicos renderizados com *Thymeleaf*. Essa abordagem desacopla a regra de negócio e garante que a latência de servidores SMTP não trave o tempo de resposta da API.

<br>
> [!WARNING]
> 🚧 **Projeto ainda em construção** 🚧

</div>