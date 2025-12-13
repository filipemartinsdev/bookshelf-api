# 📚 Bookshelf API
> ⚙️ Em construção ⚙️

## 📌 Sobre

API RESTful para gerenciamento de informações sobre livros. Este projeto foi desenvolvido com o objetivo de estudar Java com Spring e aplicar uma arquitetura de software concisa e escalável.

## ⚙️ Tecnologias

[![My Skills](https://skillicons.dev/icons?i=java,spring,postgresql,supabase,hibernate,maven)](https://skillicons.dev)


## 📍 Endpoints

- `GET  /books`

  Busca todos os livros.

- `GET  /books?query={query}`

  Busca pelo título.

- `POST  /books`

  Cria novo livro.

  ```
  {
    "title": <string>,
    "subtitle": <string>,
    "author": <string>,
    "pages": <int>,
    "status": <[0|1|2]>,
    "rating": <[0-10]>,
    "bookCover": <img_URL>
  }
  ```
## 📃 To do list
- [x] CRUD com Postgresql.
- [x] Imagens em nuvem no Imgur.
- [ ] Deploy no Railways.
- [ ] Deploy no Supabase.
- [ ] Migrations com flyway.
- [ ] Refatorar operações para melhor desempenho.
