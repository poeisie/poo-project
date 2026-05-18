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
| 🎥 Screencast | [Assistir no YouTube →](youtu.be/-SNA-cdIQr8) |
| 🐞 Bug tracker (VPA-1 a VPA-19) | [Ver planilha →](https://docs.google.com/spreadsheets/d/1F9LqVv9y9CrThgpUzaNE2ApuoM_dKlN1JgAWgZU_HiA/edit?usp=sharing) |

<br>

---

### `03` · Jornada do Usuário Completa

> **Histórias entregues:** Visualização do Evento · Compra do Ingresso · Meus Ingressos

| Recurso | |
|---|---|
| 🎥 Screencast | [Assistir no YouTube →](youtu.be/LDaSQDC5Ia4) |
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
| 🎥 Screencast | [Assistir no YouTube →](https://youtu.be/LINK_ENTREGA_4) |
| 🐞 Bug tracker | [Issues do GitHub →](https://github.com/poeisie/poo-project/issues) |

**Print do bug tracker — Entrega 4:**

📸 Issues abertas
![Issues abertas – Entrega 4](https://placehold.co/900x400?text=Issues+Abertas+%E2%80%93+Entrega+4)

📸 Issues concluídas
![Issues concluídas – Entrega 4](https://placehold.co/900x400?text=Issues+Conclu%C3%ADdas+%E2%80%93+Entrega+4)

<br>

---

## 🚀 Como executar

<br>

### Pré-requisitos

- **Git**
- **Docker Desktop** com WSL 2 ativado (Windows) ou suporte nativo (macOS/Linux)

<br>

### Configuração

```bash
# 1. Clone o repositório
git clone https://github.com/poeisie/poo-project.git
cd poo-project

# 2. Crie o arquivo de variáveis de ambiente
echo "DB_USER=postgres"            > .env
echo "DB_PASSWORD=sua_senha_aqui" >> .env
echo "DB_NAME=arenapernambuco"    >> .env

# 3. Suba os containers
docker compose up -d --build
```

<br>

### Páginas da aplicação

| URL | Descrição |
|---|---|
| `http://localhost:8080` | 🏠 Página inicial |
| `http://localhost:8080/login` | 🔐 Login |
| `http://localhost:8080/cadastro` | 📝 Cadastro |
| `http://localhost:8080/eventos/listar` | 📅 Lista de eventos |

> [!TIP]
> **Primeiro acesso:** o sistema cria automaticamente um administrador.
> Use `admin@arena.com` / `admin123` para entrar.

<br>

### Parar a aplicação

```bash
docker compose down
```

<br>

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
