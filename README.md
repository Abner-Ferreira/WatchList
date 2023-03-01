# WatchList

Uma API para o sistema de guardar seus filmes e séries que deseja assistir

## Endponits
-Series
    -[Cadastrar](#cadastrar-series)
    -Listar todas
    -[Apagar](#apagar-serie)
    -[Mostrar os detalhes](#detalhes-serie)
-Filmes
    -Cadastrar
    -Listar todas
    -Apagar
    -Mostrar os detalhes
-Usuario
    -Cadastrar
    -Listar todas
    -Apagar
    -Alterar 
    -Mostrar os detalhes

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

| codigo | descricao
|--------|-----------
| 201 | série cadastrada com sucesso
| 400 | erro na validaçao dos dadios da requisição

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
| 200 | dados retornados no corpo da resposta
| 404 | Dados não encontrado

---