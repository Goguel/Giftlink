# Registro de Depuração (Debugging Log) — GiftLink

Este documento registra bugs identificados durante o desenvolvimento do **GiftLink**, detalhando o processo de investigação, a causa raiz e as correções aplicadas.

---

## Bug #1: Erro de Comparação com `BigDecimal`

### 🆔 Identificação

- **Data:** 01/12/2025
- **Reportado por:** Teste unitário (`createGift_ValueZero_Success`)
- **Severidade:** Média
- **Módulo:** `GiftService.java`

---

### ❌ Descrição

O sistema rejeitava presentes com valor **0.00** (gratuitos), lançando incorretamente uma exceção de **"valor negativo"**. Isso impedia o cadastro de itens simbólicos ou gratuitos.

---

### 🔍 Investigação

- **Técnica utilizada:** Análise de *stack trace* e teste isolado
- Foi criado um teste unitário específico para valores iguais a zero
- O código original utilizava uma lógica de comparação ambígua com `BigDecimal`, sensível à forma como o retorno do método `compareTo` era interpretado

#### Código problemático

```java
// O operador <= -1 pode ser confuso ou incorreto dependendo do contexto
if (gift.getValue().compareTo(BigDecimal.ZERO) <= -1) {
    ...
}
```

---

### ✅ Correção Aplicada

A lógica foi simplificada para verificar explicitamente se o valor é **menor que zero**.

```java
// Correção clara e objetiva
if (gift.getValue().compareTo(BigDecimal.ZERO) < 0) {
    throw new IllegalArgumentException("O valor do presente não pode ser nulo ou negativo.");
}
```

---

## Bug #2: `NullPointerException` na Validação de Nome

### 🆔 Identificação

- **Reportado por:** Teste manual (requisição via Postman com JSON sem o campo `name`)
- **Severidade:** Alta
- **Impacto:** Retorno de erro **500** no servidor em vez de **400 (Bad Request)**

---

### ❌ Descrição

Quando o *payload* JSON não continha o campo `name`, o método de validação falhava com um erro de sistema (`NullPointerException`) antes mesmo da validação de regra de negócio.

---

### 🔍 Investigação

- **Técnica utilizada:** *Logging* estratégico
- Foi adicionado `System.out.println` antes da validação
- Identificado que `gift.getName()` retornava `null`
- A chamada de `.isBlank()` em um objeto nulo causava a exceção

#### Código problemático

```java
if (gift.getName().isBlank()) { // Lança NullPointerException se getName() for null
    throw new IllegalArgumentException(...);
}
```

---

### ✅ Correção Aplicada

Utilização da classe utilitária `StringUtils` do Spring, que trata corretamente valores **nulos**, **vazios** e compostos apenas por **espaços em branco**.

```java
if (!StringUtils.hasText(gift.getName())) {
    throw new IllegalArgumentException("O nome do presente não pode ser vazio.");
}
```

---

## Bug #3: Descrição não Persistida no Banco de Dados

### 🆔 Identificação

- **Reportado por:** Inspeção visual no **H2 Console** após cadastro via formulário web
- **Severidade:** Baixa
- **Impacto:** Perda de dados não crítica

---

### ❌ Descrição

Ao criar um presente preenchendo o campo **Descrição** no front-end, o valor não era persistido no banco de dados (coluna permanecia `NULL`).

---

### 🔍 Investigação

- **Técnica utilizada:** Debugger da IDE
- Foi colocado um *breakpoint* no `GiftController`
- O objeto `gift` recebido possuía o campo `description` como `null`
- Ao inspecionar o HTML, foi identificado que o atributo `name` do `input` não correspondia ao atributo da classe Java

#### Código problemático (HTML)

```html
<input type="text" name="desc" ... > <!-- O Spring espera "description" -->
```

---

### ✅ Correção Aplicada

Padronização do atributo `name` do input para corresponder exatamente ao atributo da entidade `Gift`.

```html
<input type="text" name="description" ... >
```

---

📌 **Observação:** Esses registros auxiliam na rastreabilidade de decisões técnicas, melhoria contínua do código e na prevenção de regressões futuras.

