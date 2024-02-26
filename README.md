# 🚀 FIAP : Challenge Pós-Tech Software Architecture
## 🍔 Projeto Fast Food | Arquitetura Limpa

Projeto realizado para a Fase 2 da Pós-Graduação de Arquitetura de Sistemas da FIAP. O sistema deste projeto foi construído utilizando Arquitetura Limpa como ensinado no módulo, para estudar este padrão de uso difundido no mercado.

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

### 🎯 Clean Architecture

Projeto feito com base no repositório demonstrado em aula por Erick Muller.

**🔗 Referência: https://github.com/proferickmuller/fiap-cleanarch-na-pratica**


```
## Regras:

0. Faça **uma coisa de cada vez**.
1. Sempre comece pelo use case. Implemente o primeiro use case, implemente as entidades que ele usa, defina tudo o que ele precisa pra funcionar (via interfaces), e depois construa o controller para fazer o use case funcionar, e crie as interfaces necessárias. Depois disso, ajuste os Presenters e Gateways.
2. Não comece pela API ou pelo banco de dados. No máximo tenha um desenho de api para comunicar com o mundo exterior, mas que pode ajudar na definição dos casos de uso. *O uso define o contexto, e não o contrário*.
3. Esqueça os frameworks nesse momento. foque nas três camadas internas.
4. Use interfaces para todos os lugares onde é necessário a injeção de dependência.
5. Se quiser testar um caso de uso com gateway, use um "fornecedor de dados" falso (*mock*). Depois você pode implementar o acesso ao banco de dados.

## Heurísticas

Pequenos passos te levam longe. Ciclos curtos e bem definidos.

Entenda e defina -> Construa -> Teste -> Disponibilize.
```

### 📐 Desenho de Solução (Arquitetura)

TBD

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
2. Migre para a pasta infra-docker e, no terminal, execute o comando: ```docker-compose up --build```
3. Um container com a aplicação e um banco de dados MongoDB serão inicializados nas portas 8080 e 27017 respectivamente
   1. Se possuir Docker Desktop, veja os containers rodando nele.
4. Para chamar os endpoints, você pode ver as rotas no link ```http://localhost:8080/swagger-ui/index.html```


### 💿 Getting started - Rodando localmente com docker

Faça o download ou clone este projeto e abra em uma IDE (preferencialmente IntelliJ).
É preciso ter:

    - Java 17
    - Docker instalado na máquina
    - plugin do Lombok na IDE

🚨 Passo-a-passo:

1. Prepare sua IDE colocando o Java 17 nas configurações do projeto
2. Importe um projeto como um projeto Gradle (botão direito em ```src > build.gradle > import Gradle Project```)
3. Aguarde a instalação das dependências
4. Migre para a pasta infra-docker e, no terminal, execute o comando: ```docker-compose -f docker-compose-local.yaml up```
5. Um container com um banco de dados MongoDB será inicializado na porta 27017
6. Abra a classe FastFoodApplication e execute a aplicação
7. Para chamar os endpoints, você pode ver as rotas no link ```http://localhost:8080/swagger-ui/index.html```

### 💿 Getting started - Rodando em cluster kubernetes local

Faça o download ou clone este projeto e abra em uma IDE (preferencialmente IntelliJ).
É preciso ter:

    - Docker instalado na máquina
    - Kubectl 
    - Minikube
    - (opcional) K9s

🚨 Passo-a-passo:

1. Abra o Powershell
2. Com os três componentes acima instalados, configure o kubectl para usar o minikube com o comando ``` alias kubectl="minikube kubectl --"```
3. Você pode visualizar os pods rodando no minikube com o comando ```kubectl get pods```
4. Em outro terminal, navegue para a pasta infra-kubernetes deste projeto
5. Crie um namespace para conter os serviços do projeto, por exemplo : ```kubectl create namespace fiap-pos-tech```
6. Altere o  "path" na linha 13 do arquivo "mongo-All.yaml" para um path da sua máquina
7. Execute primeiramente o comando ```kubectl apply -f mongo-All.yaml``` para subir os componentes do pod do Mongo
8. Execute o comando ```kubectl apply -f manifest.yaml``` para subir os componentes do pod da aplicação
9. No PowerShell, execute o comando ```minikube tunnel``` para expor externamente a service criada para a aplicação
10. Para chamar o swagger da aplicação e ver os endpoints disponíveis, acesse ```http://localhost:80/swagger-ui/index.html```
11. Caso queira remover os serviços em execução, execute os seguintes comandos:
    ```
    kubectl delete service fastfood-fiap-deployment -n fiap-pos-tech
    kubectl delete service fastfood-fiap-service -n fiap-pos-tech
    kubectl delete deployment  fastfood-fiap-deployment -n fiap-pos-tech
    kubectl delete service mongodb-service -n fiap-pos-tech
    kubectl delete deployment  mongodb-deployment -n fiap-pos-tech
    kubectl delete statefulset  mongo-sfs -n fiap-pos-tech
    kubectl delete pvc mongodb-pvc -n fiap-pos-tech
    kubectl delete pv mongodb-pv
    ```

## Versioning

1.0.0.0
