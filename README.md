# API-Restaurante
No decorrer deste projeto pude desenvolver um Sistema Web completo. Uma API, cujo back-end foi construído em Java usando o spring Boot.
O projeto de um restaurante fictício atende todas as necessidades e funcionalidades de um serviço de mundo real. 
O site conta com cadastramento de usuário, agendamento de reserva, opções de menu, inscrição por e-mail, responsividade para todos os dispositivos, salvamento de imagens para o usuário e menu,  e muito mais.. 
No que se refere a segurança foi utilizado o Spring Security, com validação e geração de Token com o JWT. 




## Modo de Uso

Para usar este projeto, siga estas etapas:

1. Clone este repositório para o seu computador:

```bash
git clone https://github.com/JamesCode-Ts/API-Restaurante-
```

2. Configuração do Ambiente Java com SDKMAN!

```bash
curl -s "https://get.sdkman.io" | bash
sdk install java 8.0.402-tem  
sdk use java 8.0.402-tem  
```
3. Instalação do maven
```bash
sdk install maven 
```

4. Instale e configure o postgres de acordo com seu sistema Operacional.
   * Neste caso darei instruções pra instalar o postgres no linux(Ubuntu).
   
    I. Instalar a chave pública para o repositório (se ainda não foi feito anteriormente):

```bash
curl -fsS https://www.pgadmin.org/static/packages_pgadmin_org.pub | sudo gpg --dearmor -o /usr/share/keyrings/packages-pgadmin-org.gpg
```

II. Criar o arquivo de configuração do repositório:

```bash
sudo sh -c 'echo "deb [signed-by=/usr/share/keyrings/packages-pgadmin-org.gpg] https://ftp.postgresql.org/pub/pgadmin/pgadmin4/apt/$(lsb_release -cs) pgadmin4 main" > /etc/apt/sources.list.d/pgadmin4.list && apt update'
```


 ### 5. Instalar pgAdmin


 I. Instalar para ambos os modos desktop e web:

```bash
sudo apt install pgadmin4
```

II. Instalar apenas para o modo desktop:

```bash
sudo apt install pgadmin4-desktop
```

III. Instalar apenas para o modo web: 

```bash
sudo apt install pgadmin4-web 
```

IV. Configurar o servidor web, se você instalou pgadmin4-web:

```bash
sudo /usr/pgadmin4/bin/setup-web.sh
```
6. Crie um banco de dados de acordo com o que esta espeficado no application-dev.properties.

7. Execute a aplicação.



### - Tecnologias Utilizadas:

* Java

* SpringBoot

* Spring Security

* PostgreSQL

* Devtools

* JWT

* MAVEN

