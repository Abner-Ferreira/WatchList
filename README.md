# WatchList

Uma API para o sistema de guardar seus filmes e séries que deseja assistir

## Endponits
- Series
    - [Cadastrar](#cadastrar-series)
    - [Listar todas](#listar-series)
    - [Apagar](#apagar-serie)
    - [Mostrar os detalhes](#detalhes-serie)
- Filmes
    - [Cadastrar](#cadastrar-filmes)
    - [Listar todas](#listar-filmes)
    - [Apagar](#apagar-filme)
    - [Mostrar os detalhes](#detalhes-filme)
- Usuario
    - Cadastrar
    - Listar todas
    - Apagar
    - Alterar 
    - Mostrar os detalhes

---
### Cadastrar Series
`POST` /watchlist/api/serie

| campo | tipo | obrigatório | descrição 
|-------|------|-------------|-----------
| nome | String | sim | É o nome da série
| categoria_id | int | sim | é o id de uma categoria
| usuario_id| int | sim | é o id do usuário
| nome_diretor | String | sim | É o nome do diretor
| duracao | String | não | Aqui se coloca a quantidade de temporadas

**Exemplo de corpo do request**

```js

{
    "nome" : "Friends",
    "categoria_id" : 1,
    "usuario_id" : 1,
    "nome_diretor" : "Ben Winston" ,
    "duracao" : "10 temporadas"
}

```

**Códigos de Repsosta**

| codigo | descrição
|--------|-----------
| 201 | série cadastrada com sucesso
| 400 | erro na validaçao dos dados da requisição

---

### Detalhes Serie
`GET` /watchlist/api/serie/{id}

**Exemplo de corpo para resposta**

```js

{
    "nome" : "Friends",
    "categoria" : {
        "categoria_id" : 1,
        "nome" : "Comédia"
    },
    "usuario" : {
        "usuario_id" : 1,
        "nome" : "Kleber"
    },
    "nome_diretor" : "Ben Winston" ,
    "duracao" : "10 temporadas"
}

```

**Códigos de Resposta**

| código | descrição 
|--------|-----------
| 200 | dados retornados no corpo da resposta
| 404 | não foi encontrada a série com o id informado

---

### Apagar Serie
`DELETE` /watchlist/api/serie/{id}

**Códigos de Resposta**

| código | descrição 
|--------|-----------
| 202 | série apagada com sucesso
| 404 | série não encontrada

---

### Listar Series
`GET` /watchlist/api/serie/

**Exemplo de corpo para resposta**

```js

{
    {
        "nome" : "Friends",
        "categoria" : {
            "categoria_id" : 1,
            "nome" : "Comédia"
        },
        "usuario" : {
            "usuario_id" : 1,
            "nome" : "Kleber"
        },
        "nome_diretor" : "Ben Winston" ,
        "duracao" : "10 temporadas"
    },
    {
        "nome" : "The Sopranos",
        "categoria" : {
            "categoria_id" : 2,
            "nome" : "Drama"
        },
        "usuario" : {
            "usuario_id" : 2,
            "nome" : "Jorge"
        },
        "nome_diretor" : "David Chase" ,
        "duracao" : "6 temporadas"
    },
    .
    .
    .

}

```

**Códigos de Resposta**

| código | descrição 
|--------|-----------
| 200 | Dados retornados no corpo da resposta
| 404 | Dados não encontrado

---
### Cadastrar Filmes
`POST` /watchlist/api/filme

| campo | tipo | obrigatório | descrição 
|-------|------|-------------|-----------
| nome | String | sim | É o nome do filme
| categoria_id | int | sim | é o id de uma categoria
| usuario_id| int | sim | é o id do usuário
| nome_diretor | String | sim | É o nome do diretor
| duracao | String | não | Aqui se coloca a duração do filme

**Exemplo de corpo do request**

```js

{
    "nome" : "The Godfather",
    "categoria_id" : 2,
    "usuario_id" : 1,
    "nome_diretor" : "Francis Ford Coppola" ,
    "duracao" : "173 min"
}

```

**Códigos de Repsosta**

| codigo | descrição
|--------|-----------
| 201 | Filme cadastrada com sucesso
| 400 | Erro na validaçao dos dados da requisição

---

### Detalhes Filme
`GET` /watchlist/api/filme/{id}

**Exemplo de corpo para resposta**

```js

{
    "nome" : "The Godfather",
    "categoria" : {
        "categoria_id" : 2,
        "nome" : "Drama"
    },
    "usuario" : {
        "usuario_id" : 1,
        "nome" : "Kleber"
    },
    "nome_diretor" : "Francis Ford Coppola" ,
    "duracao" : "173 min"
}

```

**Códigos de Resposta**

| código | descrição 
|--------|-----------
| 200 | Dados retornados no corpo da resposta
| 404 | Não foi encontrado o filme com o id informado

---

### Apagar Filme
`DELETE` /watchlist/api/filme/{id}

**Códigos de Resposta**

| código | descrição 
|--------|-----------
| 202 | Filme apagado com sucesso
| 404 | Filme não encontrado

---

### Listar Filmes
`GET` /watchlist/api/filme

**Exemplo de corpo para resposta**

```js

{
    {
        "nome" : "The Godfather",
        "categoria" : {
            "categoria_id" : 2,
            "nome" : "Drama"
        },
        "usuario" : {
            "usuario_id" : 1,
            "nome" : "Kleber"
        },
        "nome_diretor" : "Francis Ford Coppola" ,
        "duracao" : "173 min"
    },
    {
        "nome" : "Back to the Future",
        "categoria" : {
            "categoria_id" : 3,
            "nome" : "Sci-Fi"
        },
        "usuario" : {
            "usuario_id" : 2,
            "nome" : "Jorge"
        },
        "nome_diretor" : "Robert Zemeckis" ,
        "duracao" : "116 min"
    },
    .
    .
    .

}

```

**Códigos de Resposta**

| código | descrição 
|--------|-----------
| 200 | Dados retornados no corpo da resposta
| 404 | Dados não encontrado

---