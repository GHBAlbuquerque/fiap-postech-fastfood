# 🚀 FIAP : Challenge Pós-Tech Software Architecture
## 🍔 Projeto Fast Food | Arquitetura Hexagonal

Projeto realizado para a Fase 1 da Pós-Graduação de Arquitetura de Sistemas da FIAP. O sistema deste projeto foi construído utilizando Arquitetura Hexagonal (Ports and Adapters) como ensinado no módulo, para estudar este padrão de uso difundido no mercado.

### 👨‍🏫 Grupo

Integrantes:
- Diego S. Silveira (RM352891)
- Giovanna H. B. Albuquerque (RM352679)
- Kelvin Vieira (RM352728)
- Wellington Vieira (RM352970)

### 📍 DDD

Estudos de Domain Driven Design (DDD) como Domain StoryTelling, EventStorming, Linguagem Ubíqua foram feitos na ferramenta MIRO pelo grupo.
Os resultados destes estudos estão disponíveis no link abaixo:

**🔗 MIRO com DDD: https://miro.com/app/board/uXjVNMo8BCE=/?share_link_id=24975843522**

### 💻 Tecnologias

Tecnologias utilizadas:

* Java 17
* Spring Framework
* Gradle
* MongoDB
* Docker
* Swagger

### 👓 Serviços Utilizados

* Github
* Postman
* Docker Desktop 
* MongoDB Compass

### 🎬 Getting started - Rodando com docker-compose

Faça o download ou clone este projeto. É preciso ter:

    - Docker instalado na máquina

🚨 Passo-a-passo:

1. Abra o projeto no seu explorador de arquivos 
2. Migre para a pasta local-infra-fiap-fastfood e, no terminal, execute o comando: ```docker-compose up --build```
3. Um container com a aplicação e um banco de dados MongoDB serão inicializados nas portas 8080 e 27017 respectivamente
   1. Se possuir Docker Desktop, veja os containers rodando nele.
4. Para chamar os endpoints, você pode ver as rotas no link ```http://localhost:8080/swagger-ui/index.html```


### 💿 Getting started - Rodando localmente

Faça o download ou clone este projeto e abra em uma IDE (preferencialmente IntelliJ).
É preciso ter:

    - Java 17
    - Docker instalado na máquina
    - plugin do Lombok na IDE

🚨 Passo-a-passo:

1. Prepare sua IDE colocando o Java 17 nas configurações do projeto
2. Importe um projeto como um projeto Gradle (botão direito em ```src > build.gradle > import Gradle Project```)
3. Aguarde a instalação das dependências
4. Migre para a pasta local-infra-fiap-fastfood e, no terminal, execute o comando: ```docker-compose -f docker-compose-local.yaml up```
5. Um container com um banco de dados MongoDB será inicializado na porta 27017
6. Abra a classe FastFoodApplication e execute a aplicação
7. Para chamar os endpoints, você pode ver as rotas no link ```http://localhost:8080/swagger-ui/index.html```


## Versioning

1.0.0.0
