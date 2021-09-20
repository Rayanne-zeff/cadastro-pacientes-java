# Cadastro de Pacientes

Foi desenvolvida uma REST API de um sistema para o cadastro de pacientes para uma clínica, onde conterá o cadastro de pacientes, médicos e enfermeiros.


## Tecnologias Utilizadas:

<table>
  <tr>
   <td>Projeto</td>
   <td>Linguagem</td>
   <td>Spring Boot</td>
   <td>IDE</td>
   <td>Banco de Dados</td>
   <td>Tomcat Port</td>
  </tr>
   <tr>
   <td>Spring Boot</td>
   <td>Java 8</td>
   <td>2.5.4</td>
   <td>IntelliJ IDEA</td>
   <td>Postgres</td>
   <td>8090</td>
  </tr>
</table>

  <h3> Outras </h3>

1) Maven
2) Spring Framework
3) Spring JPA
4) Spring Security
5) Spring Security Oauth2
6) Lombok
7) Heroku
8) Para realizar os testes de get, post, pull e delete, foi utilizado o Postman para fazer as chamadas.
9) Nome da base de dados: cadastro_pacientes



## Instalação
```
mvn install
```

## Base de dados
#### Para importar a base de dados com dados basta utilizar o arquivo abaixo do diretório:
```
src/main/resources/database/cadastro_pacientes.sql
```

## Executando
```
spring-boot:run
```


## Urls utilizadas para testar a API:

#### Link da api disponibilizada no Heroku: https://cadastro-pacientes-java.herokuapp.com

### Autenticação
1) TOKEN:
    <table>
      <tr>
       <td>Método</td>
       <td>Url</td>
       <td>Authorization</td>
      </tr>
       <tr>
       <td>POST</td>
       <td>localhost:8090/oauth/token</td>
       <td>Basic MWE4OGQ5OWQtNDNlZi00NTUwLTljYjctMDMzZWY0NzBhM2IxOjNmMDMwZTcwLWRiOGQtNDkyZC1hMmRmLWViODUwZjlmYTcxNQ==</td>
      </tr>
    </table>

    <h4>Body</h4>
      <table>
      <tr>
       <td>grant_type</td>
       <td>username</td>
       <td>password</td>
       <td>scope</td>
      </tr>
       <tr>
       <td>password</td>
       <td>freddie</td>
       <td>123</td>
       <td>password</td>
      </tr>
    </table>
<br>

### Autenticação basic


      username: 1a88d99d-43ef-4550-9cb7-033ef470a3b1

      password: 3f030e70-db8d-492d-a2df-eb850f9fa715

### Pessoa
1) LISTAR:
    <table>
      <tr>
       <td>Método</td>
       <td>Url</td>
       <td>Authorization</td>
      </tr>
       <tr>
       <td>POST</td>
       <td>localhost:8090/api/pessoa</td>
       <td>Bearer d5b447d3-1129-4397-9892-a4e41380adac</td>
      </tr>
    </table>

2) INCLUIR:
    <table>
      <tr>
       <td>Método</td>
       <td>Url</td>
       <td>Authorization</td>
      </tr>
       <tr>
       <td>POST</td>
       <td>localhost:8090/api/pessoa</td>
       <td>Bearer d5b447d3-1129-4397-9892-a4e41380adac</td>
      </tr>
    </table>
   
    <h4>JSON</h4>

   ```
   {
      "pessoaCpf": "629.020.880-27",
      "pessoaName": "Ozzy Osbourne",
      "pessoaTipo": "Medico"
   }
   ```

3) ALTERAR:
   <table>
     <tr>
      <td>Método</td>
      <td>Url</td>
       <td>Authorization</td>
     </tr>
      <tr>
      <td>PUT</td>
      <td>localhost:8090/api/pessoa/2</td>
       <td>Bearer d5b447d3-1129-4397-9892-a4e41380adac</td>
     </tr>
   </table>

   <h4>JSON</h4>

   ```
   {
      "pessoaId": "2",
      "pessoaName": "Ozzy Osbourne",
      "pessoaTipo": "Enfermeiro"
   }
   ```

4) DELETAR:
      <table>
        <tr>
         <td>Método</td>
         <td>Url</td>
       <td>Authorization</td>
        </tr>
        <tr>
         <td>DELETE</td>
         <td>localhost:8090/api/pessoa/3</td>
       <td>Bearer d5b447d3-1129-4397-9892-a4e41380adac</td>
        </tr>
      </table>

<br>

### Usuário
1) LISTAR:
    <table>
      <tr>
       <td>Método</td>
       <td>Url</td>
       <td>Authorization</td>
      </tr>
       <tr>
       <td>POST</td>
       <td>localhost:8090/api/usuario</td>
       <td>Bearer d5b447d3-1129-4397-9892-a4e41380adac</td>
      </tr>
    </table>

2) INCLUIR:
    <table>
      <tr>
       <td>Método</td>
       <td>Url</td>
       <td>Authorization</td>
      </tr>
       <tr>
       <td>POST</td>
       <td>localhost:8090/api/usuario</td>
       <td>Bearer d5b447d3-1129-4397-9892-a4e41380adac</td>
      </tr>
    </table>
   
    <h4>JSON</h4>

   ```
   {
       "usuarioLogin": "62568673001",
       "usuarioSenha": "123456789",
       "pessoa": {
           "pessoaId": "1"
       }
   }
   ```

3) ALTERAR:
   <table>
     <tr>
      <td>Método</td>
      <td>Url</td>
       <td>Authorization</td>
     </tr>
      <tr>
      <td>PUT</td>
      <td>localhost:8090/api/usuario/2</td>
       <td>Bearer d5b447d3-1129-4397-9892-a4e41380adac</td>
     </tr>
   </table>

   <h4>JSON</h4>

   ```
   {
       "usuarioId": "2"
       "usuarioSenha": "31561",
   }
   ```

4) DELETAR:
      <table>
        <tr>
         <td>Método</td>
         <td>Url</td>
       <td>Authorization</td>
        </tr>
        <tr>
         <td>DELETE</td>
         <td>localhost:8090/api/usuario/2</td>
       <td>Bearer d5b447d3-1129-4397-9892-a4e41380adac</td>
        </tr>
      </table>

<br>

### Médico
1) LISTAR:
    <table>
      <tr>
       <td>Método</td>
       <td>Url</td>
       <td>Authorization</td>
      </tr>
       <tr>
       <td>POST</td>
       <td>localhost:8090/api/medico</td>
       <td>Bearer d5b447d3-1129-4397-9892-a4e41380adac</td>
      </tr>
    </table>

2) INCLUIR:
    <table>
      <tr>
       <td>Método</td>
       <td>Url</td>
       <td>Authorization</td>
      </tr>
       <tr>
       <td>POST</td>
       <td>localhost:8090/api/medico</td>
       <td>Bearer d5b447d3-1129-4397-9892-a4e41380adac</td>
      </tr>
    </table>
   
    <h4>JSON</h4>

   ```
   {
        "pessoaId": "6",
        "medicoCrm": "351652"
   }
   ```

3) ALTERAR:
   <table>
     <tr>
      <td>Método</td>
      <td>Url</td>
       <td>Authorization</td>
     </tr>
      <tr>
      <td>PUT</td>
      <td>localhost:8090/api/medico/6</td>
       <td>Bearer d5b447d3-1129-4397-9892-a4e41380adac</td>
     </tr>
   </table>

   <h4>JSON</h4>

   ```
   {
        "pessoaId": "6",
        "medicoCrm": "851516"
   }
   ```

4) DELETAR:
      <table>
        <tr>
         <td>Método</td>
         <td>Url</td>
       <td>Authorization</td>
        </tr>
        <tr>
         <td>DELETE</td>
         <td>localhost:8090/api/medico/6</td>
       <td>Bearer d5b447d3-1129-4397-9892-a4e41380adac</td>
        </tr>
      </table>

<br>

### Enfermeiro
1) LISTAR:
    <table>
      <tr>
       <td>Método</td>
       <td>Url</td>
       <td>Authorization</td>
      </tr>
       <tr>
       <td>POST</td>
       <td>localhost:8090/api/enfermeiro</td>
       <td>Bearer d5b447d3-1129-4397-9892-a4e41380adac</td>
      </tr>
    </table>

2) INCLUIR:
    <table>
      <tr>
       <td>Método</td>
       <td>Url</td>
       <td>Authorization</td>
      </tr>
       <tr>
       <td>POST</td>
       <td>localhost:8090/api/enfermeiro</td>
       <td>Bearer d5b447d3-1129-4397-9892-a4e41380adac</td>
      </tr>
    </table>
   
    <h4>JSON</h4>

   ```
   {
        "pessoaId": "10",
        "enfermeiroCre": "351652"
   }
   ```

3) ALTERAR:
   <table>
     <tr>
      <td>Método</td>
      <td>Url</td>
       <td>Authorization</td>
     </tr>
      <tr>
      <td>PUT</td>
      <td>localhost:8090/api/enfermeiro/6</td>
       <td>Bearer d5b447d3-1129-4397-9892-a4e41380adac</td>
     </tr>
   </table>

   <h4>JSON</h4>

   ```
   {
        "pessoaId": "10",
        "enfermeiroCre": "123"
   }
   ```

4) DELETAR:
      <table>
        <tr>
         <td>Método</td>
         <td>Url</td>
       <td>Authorization</td>
        </tr>
        <tr>
         <td>DELETE</td>
         <td>localhost:8090/api/enfermeiro/6</td>
       <td>Bearer d5b447d3-1129-4397-9892-a4e41380adac</td>
        </tr>
      </table>

<br>

### Paciente
1) LISTAR:
    <table>
      <tr>
       <td>Método</td>
       <td>Url</td>
       <td>Authorization</td>
      </tr>
       <tr>
       <td>POST</td>
       <td>localhost:8090/api/paciente</td>
       <td>Bearer d5b447d3-1129-4397-9892-a4e41380adac</td>
      </tr>
    </table>

2) INCLUIR:
    <table>
      <tr>
       <td>Método</td>
       <td>Url</td>
       <td>Authorization</td>
      </tr>
       <tr>
       <td>POST</td>
       <td>localhost:8090/api/paciente</td>
       <td>Bearer d5b447d3-1129-4397-9892-a4e41380adac</td>
      </tr>
    </table>
   
    <h4>JSON</h4>

   ```
   {
      "pacienteAltura": "1.68",
      "pacientePeso": "75",
      "pacienteUf": "MG"
   }
   ```

3) ALTERAR:
   <table>
     <tr>
      <td>Método</td>
      <td>Url</td>
       <td>Authorization</td>
     </tr>
      <tr>
      <td>PUT</td>
      <td>localhost:8090/api/paciente/20</td>
       <td>Bearer d5b447d3-1129-4397-9892-a4e41380adac</td>
     </tr>
   </table>

   <h4>JSON</h4>

   ```
   {
      "pessoaId": "20",
      "pacienteAltura": "1.68",
      "pacientePeso": "75",
      "pacienteUf": "SP"
   }
   ```

4) DELETAR:
      <table>
        <tr>
         <td>Método</td>
         <td>Url</td>
       <td>Authorization</td>
        </tr>
        <tr>
         <td>DELETE</td>
         <td>localhost:8090/api/paciente/20</td>
       <td>Bearer d5b447d3-1129-4397-9892-a4e41380adac</td>
        </tr>
      </table>




## Contato em caso de dúvidas:

<text> Em caso de dúvida para utilizar ou configurar a API pode estar entrando em contato por e-mail, que assim que possível eu esclareço as dúvidas.
</text>

  ```
     E-mail: gloria_zeferino@hotmail.com
  ```