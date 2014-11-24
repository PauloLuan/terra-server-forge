Terra Server
====================


Executando
===================

* Ter Maven 3.x instalado e Java 7.x (ou mais novo)
* Baixar o `JBoss AS 7.1.1.Final` na seguinte URL: http://jbossas.jboss.org/downloads
* Descompartar o JBoss AS 7.1.1 localmente
    * `unzip jboss-as-7.1.1.Final.zip -d /opt/jboss/AS`
* Subir o JBoss AS 7.1.1 em uma aba(tenha certeza que não há nada rodando na porta 8080 antes):
    * `cd /opt/jboss/AS/jboss-as-7.1.1.Final`
    * `./bin/standalone.sh`
* Em uma linha de comando separada ir para o diretório do projeto `terra-server-forge`
* Executar o comando maven para realizar o build e o deploy do projeto:
    * `mvn clean package jboss-as:deploy`    
* Nos logs do JBoss você deverá ver a aplicação se instalando, confirmada pela seguinte parte do log:
~~~
12:18:29,667 INFO  [org.jboss.web] (MSC service thread 1-3) JBAS018210: Registering web context: /terramobile-server-1.0.0-SNAPSHOT
12:18:29,834 INFO  [org.jboss.as.server] (management-handler-thread - 5) JBAS018559: Deployed "terramobile-server-1.0.0-SNAPSHOT.war"
~~~
* Usando um navegador, acesse: `http://localhost:8080/terramobile-server`

*Aviso: O banco de dados será sempre apagado a cada novo deploy. *

Trocando banco de dados
===================
Para usar outro banco de dados, como o Postgres, siga esse tutorial: https://github.com/jboss-developer/jboss-developer-shared-resources/blob/master/guides/CONFIGURE_POSTGRESQL.md#configure-the-postgresql-database-for-use-with-the-quickstarts
