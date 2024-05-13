# 🚀 FIAP : Challenge Pós-Tech Software Architecture
## 🍔 Projeto Fast Food | Aplicação para Pedido de Comida

Projeto realizado para a Fase 3 da Pós-Graduação de Arquitetura de Sistemas da FIAP. O sistema deste projeto foi construído utilizando componentes AWS, Kubernetes e Arquitetura Limpa como ensinado no curso.

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

Solução arquitetônica realizada:

![](misc/fiap-fastfood-architecture-kubernetes-kubernetes.drawio.svg)

Solução arquitetônica realizada (Cloud AWS) apenas com EKS:

![](misc/fiap-fastfood-architecture-kubernetes-eks.drawio.svg)

Solução arquitetônica realizada (Cloud AWS) completa:
![](misc/sol_fase_3.drawio.svg)


### 💻 Tecnologias

Tecnologias utilizadas:

* Java 17
* Spring Framework
* Gradle
* MongoDB
* Docker
* Swagger
* Cloud AWS
* Kubernetes

### 👓 Serviços Utilizados

* Github
* Postman
* Docker Desktop 
* MongoDB Compass
* k9s
* Minikube
* AWS CLI


## 🎬 Como executar este projeto?

### 1) FASE 1 - Rodando com Docker

### 💿 Getting started - Rodando com docker-compose

Faça o download ou clone este projeto. É preciso ter:

    - Docker instalado na máquina

🚨 Passo-a-passo:

1. Abra o projeto no seu explorador de arquivos 
2. no arquivo application.properties, comente a linha 'spring.data.mongodb.uri'
3. Migre para a pasta infra-docker e, no terminal, execute o comando: ```docker-compose up --build```
4. Um container com a aplicação e um banco de dados MongoDB serão inicializados nas portas 8080 e 27017 respectivamente
   1. Se possuir Docker Desktop, veja os containers rodando nele.
5. Para chamar os endpoints, você pode ver as rotas no link ```http://localhost:8080/swagger-ui/index.html```


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
5. Edite as configurçòes para rodar o projeto, adicionando a variável "SPRING_PROFILES_ACTIVE=local" para usar o application-properties.local
6. No arquivo application.properties, comente a linha 'spring.data.mongodb.uri'
7. Um container com um banco de dados MongoDB será inicializado na porta 27017
8. Abra a classe FastFoodApplication e execute a aplicação
9. Para chamar os endpoints, você pode ver as rotas no link ```http://localhost:8080/swagger-ui/index.html```

###
### 2) FASE 2 - Rodando com Kubernetes

### 💿 Getting started - Rodando em cluster kubernetes local

Faça o download ou clone este projeto e abra em uma IDE (preferencialmente IntelliJ).
É preciso ter:

    - Docker instalado na máquina
    - Kubectl 
    - Minikube
    - (opcional) K9s

🚨 Passo-a-passo:

1. Abra o Powershell
2. Inicie o minikube com o comando ```minikube start```
3. Com os três componentes acima instalados, configure o kubectl para usar o minikube com o comando ``` alias kubectl="minikube kubectl --"```
4. Você pode visualizar os pods rodando no minikube com o comando ```kubectl get pods```
5. Em outro terminal, navegue para a pasta infra-kubernetes deste projeto
6. Crie um namespace para conter os serviços do projeto, por exemplo : ```kubectl create namespace fiap-pos-tech```
7. Altere o  "path" na linha 13 do arquivo "mongo-All.yaml" para um path da sua máquina, se necessário
8. Execute primeiramente o comando ```kubectl apply -f mongo-All.yaml``` para subir os componentes do pod do Mongo
9. Execute o comando ```kubectl apply -f manifest.yaml``` para subir os componentes do pod da aplicação
10. (opcional) Verifique os pods sendo executados através do comando ```k9s``` no PowerShell
11. No PowerShell, execute o comando ```minikube tunnel``` para expor externamente a service criada para a aplicação
12. Para chamar o swagger da aplicação e ver os endpoints disponíveis, acesse ```http://localhost:80/swagger-ui/index.html```
13. Caso queira remover os serviços em execução, execute os seguintes comandos:
    ```
    kubectl delete service fastfood-fiap-deployment -n fiap-pos-tech
    kubectl delete service fastfood-fiap-service -n fiap-pos-tech
    kubectl delete deployment  fastfood-fiap-deployment -n fiap-pos-tech
    kubectl delete service mongodb-service -n fiap-pos-tech
    kubectl delete statefulset mongo-sfs -n fiap-pos-tech
    kubectl delete configmap mongodb-configmap -n fiap-pos-tech
    kubectl delete secret mongodb-secret -n fiap-pos-tech
    kubectl delete pvc mongodb-pvc -n fiap-pos-tech
    kubectl delete pv mongodb-pv
    ```


### 💿 Getting started - Rodando em cluster EKS na Cloud AWS

Faça o download ou clone este projeto e abra em uma IDE (preferencialmente IntelliJ).
É preciso ter:

    - Uma conta cadastrada na Cloud AWS
    - AWS CLI
    - Kubectl
    - (opcional) K9s

🚨 Passo-a-passo:

1. Faça login na sua conta AWS 
2. Insira suas credenciais no AWS CLI através do comando ```aws configure``` no terminal ou alterando no arquivo ```credentials``` na pasta C:/%userprofile%/.aws
3. Crie um cluster EKS com um Grupo de Nós associado a uma VPC
   1. NO CONSOLE: É possível criar a VPC, o Cluster e Grupo de Nós manualmente
   2. PROGRAMATICAMENTE: utilizando o arquivo aws-subnet-eks.yaml na pasta infra-kubernetes através do comando ```eksctl create cluster -f aws-subnet-eks.yaml``` no terminal
      * ```Observação: o tamanho mínimo das máquinas deve ser t3.medium```
4. Utilize o comando ```aws eks --region {nome-da-regiao}  update-kubeconfig --name {nome-do-cluster}``` para adicionar o cluster criado aos cluster autenticados
5. (opcional) Verifique a conexão com o cluster executando o comando ```k9s``` no terminal. O context e cluster estarão com o arn do cluster criado na AWS ("Context: arn:aws:eks:...")
6. (opcional) Caso deseje conferir informações sobre a rede do cluster, execute o comando ```aws eks describe-cluster --name cluster-teste-1 --region us-east-1 --query cluster.resourcesVpcConfig``` no terminal
7. Execute primeiramente o comando ```kubectl apply -f namespace.yaml``` para criar o namespace
8. Execute em seguida o comando ```kubectl apply -f mongo-All.yaml``` para subir os componentes do pod do Mongo
9. Execute o comando ```kubectl apply -f manifest.yaml``` para subir os componentes do pod da aplicação
10. (opcional) Verifique os pods sendo executados através do comando ```k9s``` no PowerShell
11. Será criado um loadbalancer para a Service da aplicação. Obtenha o DNS dele para realizar chamadas para a API. Ele possui o formato ````{sequencia-numerica}.{regiao}.elb.amazonaws.com````
12. Para chamar o swagger da aplicação e ver os endpoints disponíveis, acesse ```http://{DNS-Load-Balancer}/swagger-ui/index.html```
13. Caso queira remover os serviços em execução, delete os serviços na nuvem

### 3) FASE 3 - Rodando com CICD e infra descentralizada

Compõe esta entrega:
* Repositório da Lambda de Autenticação - https://github.com/GHBAlbuquerque/fiap-postech-lambda-auth-fastfood
* Repositório da Infra - https://github.com/GHBAlbuquerque/fiap-postech-infra-fastfood
* Repositório da Database - https://github.com/GHBAlbuquerque/fiap-postech-infra-database
* Repositório da App - https://github.com/GHBAlbuquerque/fiap-postech-fastfood

Faça o download ou clone este projeto e abra em uma IDE (preferencialmente IntelliJ).
É preciso ter:

    - Uma conta cadastrada na Cloud AWS
    - Uma conta cadastrada na nuvem Atlas

### 💿 Getting started - Rodando em cluster kubernetes + Load balancer + Api Gateway na AWS

Antes de iniciar:
1. Criar manualmente bucket s3 na conta com para guardar os states do terraform (utilizei o nome ‘terraform-state-backend-postech-new’)
2. Criar manualmente repositório ECR na conta com o nome ‘fiap-pos-tech-fastfood’
3. Caso não esteja usando AWS Academy, é necessário criar também Policies e Roles para os serviços. Esta etapa não foi feita na entrega da Pós e foram usadas as Roles padrão do laboratório.

Passo-a-passo:
1. Obtenha credenciais de aws_access_key_id, aws_secret_access_key e aws_session_token da AWS Lab na AWS Academy ou na sua conta AWS.
2. Altere credenciais nos secrets para actions dos repositórios
3. Altere credenciais no arquivo .credentials na pasta .aws no seu computador

> Subindo o Banco de Dados na Atlas
1. Para criar o banco de dados na nuvem atlas, utilie o **Repositório da Database**
2. Ajuste segredos de Actions para CI/CD no repositório
3. Ajuste os valores de variáveis da AWS e da nuvem Atlas no arquivo terraform.tfvars
4. Suba o banco na nuvem Atlas via CI/CD do repositório
5. Crie as collections de acordo com o script em /script/mongo-init.js
6. Obtenha a string de conexão do banco de dados na nuvem Atlas e altere na aplicação, no **Repositório da App**, no arquivo /infra-kubernetes/manifest.yaml - env DB_HOST

> Subindo a Lambda de Autenticação
1. Ajuste variáveis  e segredos de Actions para CI/CD no **Repositório da Lambda de Autenticação**
   1. Lambda Role
   2. Bucket armazenador dos states terraform -> arquivo main.tf
2. Suba a lambda via CICD do repositório

> Subindo a Infraestrutura do projeto
1. Ajuste variáveis e segredos de Actions para CI/CD no **Repositório da Infra**
   1. AccountId
   2. Nome da Lambda
   3. Arn da Lambda criada para autenticação
   4. Role Arn
   5. VPC Id
   6. VPC CIDR
   7. subnets
   8. Bucket armazenador dos states terraform -> arquivo main.tf
2. Suba infraestrutura via CICD do repositório (Api Gateway, LoadBalancer, Secuirty Group, EKS Cluster)
3. Ajuste Security Group gerado automaticamente pelo cluster para liberar tráfego da VPC (ver CIDR) e do Security Group usado no ALB (id). Liberar ‘Todo o Tráfego’.
4. Ajuste bug do autorizador do API Gateway que monstra erro 500 e mensagem ‘null’:
   1. Ir em ‘Autorizadores’
   2. Selecionar ‘lambda_authorizer_cpf’ e editar
   3. Escolher a função lambda da lista
   4. Salvar alterações
   5. Realizar deploy da API no estágio
5. Teste conexão chamando o DNS do loadbalancer na url: ``{DNS Load Balancer}/actuator/health``
6. Obtenha endereço do stage do API Gateway no console para realizar chamadas
   1. Vá em API Gateway > api_gateway_fiap_postech > estágios > pegar o valor Invoke Url

> Subindo a App
1. Abra o **Repositório da App**
2. Ajuste segredos de Actions para CI/CD no repositório
3. Ajuste URI do repositório remoto ECR AWS (accountid e region) no repositório da aplicação, arquivo infra-kubernetes/manifest.yaml
4. Suba a aplicação via CI/CD do repositório
5. Verifique componentes em execução na AWS
6. Obtenha url do estágio no API Gateway para realizar chamadas -> API Gateway / APIs / api_gateway_fiap_postech (xxxxx) / Estágios : Invocar URL
7. Para chamar o swagger da aplicação e ver os endpoints disponíveis, acesse: {{gateway_url}}/swagger-ui/index
8. Para realizar chamadas aos endpoints http do gateway, utilize os seguintes headers:
   1. cpf_cliente -> valor cadastrado previamente: 93678719023
   2. senha_cliente -> valor cadastrado previamente: FIAPauth123_

Ex. de chamada:
![](misc/chamada_gateway_exemplo.png)

## Versioning

1.0.0.0
