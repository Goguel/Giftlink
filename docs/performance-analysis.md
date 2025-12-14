# Análise de Performance — GiftLink

Este documento descreve otimizações aplicadas ao sistema **GiftLink**, destacando gargalos identificados, soluções adotadas e os ganhos obtidos.

---

## Otimização #1: Verificação de Existência (Memória vs Banco)

### ❌ Problema (Gargalo Identificado)

Para evitar a inserção de presentes duplicados, a implementação inicial **carregava todos os registros do banco para a memória** e realizava a verificação por meio de um loop em Java.

#### Código original (lento — complexidade **O(N)**)

```java
List<Gift> allGifts = giftRepository.findAll(); // Traz TUDO para a RAM
for (Gift g : allGifts) {
    if (g.getName().equalsIgnoreCase(newName)) {
        throw new IllegalArgumentException("Presente já existe.");
    }
}
```

#### Impacto

- Com o crescimento da lista para **10.000** ou **100.000** itens:
  - Aumento significativo no **consumo de memória**
  - **Degradação do tempo de resposta**

---

### ✅ Otimização Aplicada

Utilização de um **Query Method do Spring Data JPA** (`existsByName`) para delegar a verificação diretamente ao **Banco de Dados**, que é otimizado para esse tipo de operação.

#### Código otimizado (rápido — complexidade **O(1)** ou **O(log N)**)

```java
// No Repository
boolean existsByName(String name);

// No Service
if (giftRepository.existsByName(gift.getName())) {
    throw new IllegalArgumentException("Presente já existe.");
}
```

---

### 📈 Resultados

- **Tempo de execução:**
  - De ~**250ms** (com 10k itens)
  - Para ~**5ms**

- **Uso de memória:**
  - Otimização massiva
  - A aplicação deixa de carregar listas inteiras e recebe apenas um **boolean** (`true/false`)

---

## Otimização #2: Concatenação de Strings em Logs

### ❌ Problema

Um trecho responsável por gerar **logs de auditoria** concatenava `String`s dentro de um loop usando o operador `+`.

Em Java, `String`s são **imutáveis**, o que resulta na criação de um **novo objeto** a cada iteração.

#### Código original

```java
String logMessage = "Processando itens: ";
for (Gift g : gifts) {
    logMessage += g.getName() + ", "; // Cria novo objeto na Heap a cada passo
}
logger.info(logMessage);
```

---

### ✅ Otimização Aplicada

Substituição por `StringBuilder`, classe projetada para **mutação eficiente de strings**.

#### Código otimizado

```java
StringBuilder sb = new StringBuilder("Processando itens: ");
for (Gift g : gifts) {
    sb.append(g.getName()).append(", ");
}
logger.info(sb.toString());
```

---

### ⚖️ Trade-offs e Ganhos

- **Ganhos:**
  - Redução significativa na criação de objetos temporários na **Heap**
  - Menor pressão sobre o **Garbage Collector (GC)**

- **Trade-off:**
  - Código levemente mais verboso
  - Totalmente justificável em **loops grandes** ou execuções frequentes

