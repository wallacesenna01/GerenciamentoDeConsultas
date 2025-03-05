


![gerenciamento de consulta](https://github.com/user-attachments/assets/d4f46420-0159-4439-aadd-167331486711)





Gerenciamento de Consultas
O Gerenciamento de Consultas é um sistema de agendamento de consultas médicas que permite gerenciar consultas, médicos e pacientes de maneira eficiente. Ele foi desenvolvido utilizando Spring Boot e tem uma arquitetura que inclui um banco de dados MySQL, gerenciamento de status de consultas, notificações por fila RabbitMQ e funcionalidades RESTful.

Funcionalidades
Agendamento de Consultas: Permite agendar novas consultas, associando um paciente a um médico e uma data e hora específica.
Cancelamento de Consultas: O status das consultas pode ser alterado para "CANCELADA", permitindo o cancelamento de agendamentos.
Atualização de Consultas: É possível atualizar as consultas já agendadas, alterando a data, hora ou médico responsável.
Busca por Consultas: Permite a busca de consultas por ID ou listar todas as consultas agendadas.
Status da Consulta: Gerencia o status das consultas, como "AGENDADA", "REALIZADA" e "CANCELADA".
Notificação por Email: Envia notificações por email para os pacientes quando uma consulta é agendada.
Tecnologias Utilizadas
Spring Boot: Framework principal para construção do back-end.
MySQL: Banco de dados relacional utilizado para armazenar os dados.
RabbitMQ: Fila de mensagens para o envio de notificações de consultas.
JPA/Hibernate: Para o gerenciamento de entidades e persistência no banco de dados.
REST API: Implementação de APIs RESTful para comunicação com o sistema.
Como Rodar o Projeto
Pré-requisitos
Antes de rodar o projeto, certifique-se de ter as seguintes dependências instaladas:

Java 11 ou superior (para rodar a aplicação Spring Boot)
MySQL (ou outro banco de dados configurado)
RabbitMQ (para a fila de notificações)
Passos para Configuração
Clone o repositório

Clone o repositório para a sua máquina local:

bash
Copiar
Editar
git clone https://github.com/seu-usuario/gerenciamento-consultas.git
Configuração do Banco de Dados

O projeto usa MySQL como banco de dados. Certifique-se de que o MySQL esteja instalado e rodando, e crie um banco de dados chamado gerenciamento_consultas (ou altere o nome no arquivo application.properties).

No arquivo src/main/resources/application.properties, configure a URL, usuário e senha do seu banco de dados:

properties
Copiar
Editar
spring.datasource.url=jdbc:mysql://localhost:3306/gerenciamento_consultas
spring.datasource.username=seu-usuario
spring.datasource.password=sua-senha
spring.jpa.hibernate.ddl-auto=update
Configuração do RabbitMQ

Caso queira usar o RabbitMQ para as notificações, instale o RabbitMQ localmente ou use o serviço na AWS. Para configurar a conexão, altere as variáveis no application.properties:

properties
Copiar
Editar
spring.rabbitmq.host=localhost
spring.rabbitmq.port=5672
spring.rabbitmq.username=guest
spring.rabbitmq.password=guest
Rodando a Aplicação

Para rodar o projeto, navegue até o diretório do projeto e execute o comando:

bash
Copiar
Editar
./mvnw spring-boot:run
Ou, caso esteja utilizando Windows, utilize:

bash
Copiar
Editar
mvnw spring-boot:run
A aplicação será iniciada e estará disponível no http://localhost:8080.

Testando a API

Você pode testar a API utilizando ferramentas como Postman ou Insomnia, ou diretamente do terminal com curl.

Endpoints Disponíveis:

POST /consultas - Agendar uma nova consulta.
PUT /consultas/{id} - Atualizar uma consulta existente.
GET /consultas/{id} - Buscar uma consulta pelo ID.
GET /consultas - Listar todas as consultas.
DELETE /consultas/{id} - Cancelar uma consulta.
Exemplo de Requisição para Agendamento de Consulta
POST /consultas
json
Copiar
Editar
{
  "datahora": "2025-03-10T10:30:00",
  "medicoId": 1,
  "pacienteId": 1
}
Resposta
json
Copiar
Editar
{
  "id": 1,
  "datahora": "2025-03-10T10:30:00",
  "status": "AGENDADA",
  "paciente": {
    "id": 1,
    "nome": "João Silva",
    "email": "joao.silva@example.com"
  },
  "medico": {
    "id": 1,
    "nome": "Dr. Paulo"
  }
}
Contribuindo
Se você deseja contribuir para o projeto, siga estas etapas:

Fork o repositório.
Crie uma branch para suas modificações (git checkout -b feature/novas-funcionalidades).
Faça commit das suas mudanças (git commit -am 'Adicionando novas funcionalidades').
Push para a branch (git push origin feature/novas-funcionalidades).
Crie um Pull Request.
Licença
Este projeto está licenciado sob a licença MIT - veja o arquivo LICENSE para mais detalhes.

Contato
Se você tiver dúvidas, sugestões ou problemas, sinta-se à vontade para me contatar através do e-mail wallaceartur@gmail.com ou abrir uma issue neste repositório.

