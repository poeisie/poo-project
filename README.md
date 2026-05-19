<div align="center">
<br>

# 🏟️ Vem pra Arena

<br>

[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.4.4-6DB33F?style=flat-square&logo=springboot&logoColor=white)](https://spring.io/projects/spring-boot)
[![Java](https://img.shields.io/badge/Java-21-007396?style=flat-square&logo=openjdk&logoColor=white)](https://adoptium.net/)
[![PostgreSQL](https://img.shields.io/badge/PostgreSQL-18-4169E1?style=flat-square&logo=postgresql&logoColor=white)](https://www.postgresql.org/)
[![Docker](https://img.shields.io/badge/Docker-27.2.0-2496ED?style=flat-square&logo=docker&logoColor=white)](https://www.docker.com/)
[![Status](https://img.shields.io/badge/status-em%20desenvolvimento-f4a261?style=flat-square)](https://github.com/poeisie/poo-project)

<br>

**Plataforma digital integrada para a Arena Pernambuco.**  
Compra de ingressos, criação de eventos e painel de inteligência para gestores — tudo em um só lugar.

<br>

[Funcionalidades](#-funcionalidades) · [Entregas](#-entregas) · [Como executar](#-como-executar) · [Equipe](#-equipe)

<br>

</div>

---

## ✨ Funcionalidades

<br>

| | Recurso | Descrição |
|---|---|---|
| 🎟️ | **Exploração de eventos** | Feed personalizado com filtros por categoria, data e disponibilidade |
| 💳 | **Ticketing digital** | Compra via PIX ou cartão e carteira "Meus Eventos" |
| 📊 | **Painel administrativo** | Ocupação em tempo real, análise de público e cadastro de eventos |

<br>

---

## 📦 Entregas

<br>

### `01` · Protótipo e Histórias de Usuário

> Definição do escopo, jornadas em BDD e protótipo navegável lo-fi.

| Recurso | |
|---|---|
| 📄 Histórias de usuário (BDD) | [Abrir documento →](https://docs.google.com/document/d/1gqw9K4x-y1uYNg8-NPAM1b8q1wjoDYq8w0iQstYzfc0/edit?usp=sharing) |
| 🎨 Protótipo lo-fi | [Ver no Figma →](https://www.figma.com/proto/HFs8QUM9Nmq6a6zGCh67uC/Projeto-POO?node-id=0-1&t=OqRyrlnG93pjeCVs-1) |
| 🎥 Screencast | [Assistir no YouTube →](https://youtu.be/bCbvHcg8d_c) |

<br>

---

### `02` · Primeiras Histórias Implementadas

> **Histórias entregues:** Visualização e Filtros de Eventos · Cadastro de Novos Eventos (Admin)

| Recurso | |
|---|---|
| 🎥 Screencast | [Assistir no YouTube →](https://youtu.be/-SNA-cdIQr8) |
| 🐞 Bug tracker (VPA-1 a VPA-19) | [Ver planilha →](https://docs.google.com/spreadsheets/d/1F9LqVv9y9CrThgpUzaNE2ApuoM_dKlN1JgAWgZU_HiA/edit?usp=sharing) |

<br>

---

### `03` · Jornada do Usuário Completa

> **Histórias entregues:** Visualização do Evento · Compra do Ingresso · Meus Ingressos

| Recurso | |
|---|---|
| 🎥 Screencast | [Assistir no YouTube →](https://youtu.be/LDaSQDC5Ia4) |
| 🐞 Issues abertas | [Ver captura →](https://github.com/user-attachments/assets/acb53e06-bc84-45d7-a8f4-eef56549f5e6) |
| 🐞 Issues concluídas | [Ver captura →](https://github.com/user-attachments/assets/3926ef2c-f772-4aa1-98d0-079e946e5ad9) |

> [!NOTE]
> O rastreamento de bugs foi migrado para as [Issues do GitHub](https://github.com/poeisie/poo-project/issues). Os registros VPA-1 a VPA-19 foram atualizados com os novos status.

<br>

---

### `04` · Painel Administrativo e Segurança

> **Implementado:** dashboard com estatísticas, pré-cadastro de admin e ajustes de segurança em rotas.

| Recurso | |
|---|---|
| 🎥 Screencast | [Assistir no YouTube →](https://youtu.be/nya91LKVpAw) |
| 🐞 Bug tracker | [Issues do GitHub →](https://github.com/poeisie/poo-project/issues) |

**Print do bug tracker — Entrega 4:**

📸 Issues abertas
![Issues abertas – Entrega 4](https://github.com/user-attachments/assets/b3027114-bc29-4625-bc3b-8283c805323f)

📸 Issues concluídas
![Issues concluídas – Entrega 4](https://github.com/user-attachments/assets/6ebdf82d-5ca2-429f-b12c-645d77cc7158)

<br>


---

## 🚀🏟️ Rodando o projeto localmente

Este projeto utiliza **Docker** para orquestrar a aplicação **Spring Boot** e o banco de dados **PostgreSQL**.
Com isso, você não precisa instalar Java nem PostgreSQL localmente, pois o Docker sobe todos os serviços automaticamente e evita falhas ou erros de ambiente.

<br>

---

### 🛠️ Pré-requisitos

Antes de começar, certifique-se de possuir instalado:

- Docker Desktop
- Java e Maven *(opcionais para execução via Docker, mas úteis para testes e comandos locais)*

---

### 📄 Criando o arquivo `.env`

Faça uma cópia do arquivo `.env.example` e renomeie para `.env`.

#### Exemplo

```env
DB_USER=postgres
DB_PASSWORD=admin
DB_NAME=arenapernambuco
```

---

### ▶️ Subindo a aplicação

Com o Docker aberto, execute o comando abaixo na raiz do projeto:

```bash
docker compose up --build -d
```

#### O que esse comando faz?

- `up` → sobe os containers
- `--build` → recompila a imagem da aplicação com o código mais recente
- `-d` → executa os containers em segundo plano

---

### 🌐 Acessando os serviços

Após os containers iniciarem, a aplicação estará disponível nos seguintes endereços:

#### Aplicação Web

```txt
http://localhost:8080
```

---

### 🐘 Banco de Dados PostgreSQL

Você pode acessar o banco utilizando ferramentas como:

- DBeaver
- pgAdmin
- IntelliJ Database Tool

#### Configurações de conexão

| Configuração | Valor |
|---|---|
| Host | localhost |
| Porta | 5433 |
| Database | arenapernambuco |
| Usuário | valor definido no `.env` |
| Senha | valor definido no `.env` |

> A porta `5433` foi utilizada para evitar conflitos com instalações locais do PostgreSQL.

<br>

</div>

---

## 👥 Equipe

<br>

<div align="center">

| Nome | GitHub |
|---|---|
| Aguinaldo Neto | [@netokemon](https://github.com/netokemon) |
| Caliel Feijó | [@calielfeijo](https://github.com/calielfeijo) |
| Elis Tenório | [@elistenorio](https://github.com/elistenorio) |
| Eulália Albuquerque | [@eulaliaa](https://github.com/eulaliaa) |
| Giulia Ferreira | [@giuliaferreira](https://github.com/giuliaferreira) |
| Sarah Cyrne | [@sarahcyrne](https://github.com/sarahcyrne) |

<br>

---

*Desenvolvido na [Cesar School](https://www.cesar.school) · Disciplina de POO*

</div>
