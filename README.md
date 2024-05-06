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

Solução arquitetônica realizada:

![](misc/fiap-fastfood-architecture-kubernetes-kubernetes.drawio.svg)

Solução arquitetônica realizada (Cloud AWS):

![](misc/fiap-fastfood-architecture-kubernetes-eks.drawio.svg)


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

### 1) Rodando com Docker

### 💿 Getting started - Rodando com docker-compose

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

###
### 2) Rodando com Kubernetes

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

### 3) Rodando com CICD e infra descentralizada

### 💿 Getting started - Rodando em cluster kubernetes + Load balancer + Api Gateway na AWS

TBD

## Versioning

1.0.0.0
