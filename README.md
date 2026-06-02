# **Projeto HELPDESK**

## Qual seu objetivo?
O objetivo do projeto é criar um sistema de gerenciamento de chamados capaz de realizar atualização, criação, busca e exclusão de acordo com as roles configuradas para cada usuário do sistema.

## Qual arquitetura utilizada?
Utilizada a arquitetura em camada no padrão mvc.

No backend está sendo utilizado Springboot 4, spring boot security 4, jwt, java 21 e base de dados postgres. 

## Principais problemas resolvidos durante o desenvolvimento
**1 - Configuração com banco de dados**

O projeto original utilizou o H2 e o mysql como base de dados com os perfis de teste e de dev, respectivamente, no application.properties. 
Porém, utilizei o postgres ao invés do mysql e isso causou algum tipo de incompatibilidade com o H2, onde somente foi resolvido quando deixei apenas o perfil de dev e o postgres configurado o qual era minha intenção desde o inicio do projeto.

**2 - Incompatibilidade de versões**

Projeto original desenvolvido em versões de Springboot 2 e java 11. Para desenvolver o projeto nas versões mais atuais, para a maioria dos casos, foi necessário fazer algumas modificações simples. 
Porém, para a parte de autorização e autenticação com jwt foi necessário mudar a estrutura devido a algmas anotações e metodos utilizados no original estarem deprecados (após assistir muitos videos, ler varios tutoriais e fóruns).

## O que falta desenvolver?
- Front-end
- Geração de relatório
- Gráficos
- Incluir ativado/desativado na base para os usuários
- Acesso indivdualizado aos dados (usuário somente acessar seus dados)
- Admin atribuir um chamado para outro tecnico

## Como rodar a aplicação?
1 - Execute o arquivo "src/main/resources/scripts/script.sql" para criar as tabelas na sua base de dados

2 - Na sua IDE de preferência, IntelliJ ou Spring Tools for Eclipse, crie um novo projeto spring boot e o importe desse repositorio.

3 - Dê um "Update Maven Project" e um "build" no projeto.

4 - Dê um click com o botão esquerdo do mouse no arquivo "HelpdeskApplication.java" e mande executar como "Spring Boot App"

5 - Importe no postman a collection "src/main/resources/postman/Helpdesk.postman_collection.json" e utilize a requisição de "Login"

6 - A aplicação irá retornar como resposta "200 OK" e no "Headers" o token de authorização (ex: Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhZG1AbWFpbC5jb20iLCJleHAiOjE3ODA0MjMxOTZ9.Cy1GYVR1zDHJODdGOCXwww5PngrJz_4nhwUKk6INrNY)

7 - Coloque o valor do token na variável de ambiente "token" do postman

8 - Utilize as demais requisições do postman para testar a aplicação


