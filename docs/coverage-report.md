# Relatório de Cobertura de Código — GiftLink

Este documento apresenta a **evolução da cobertura de código** do projeto **GiftLink**, destacando as métricas alcançadas, decisões técnicas e justificativas para exclusões de cobertura.

---

## Evolução da Cobertura

### 📌 Análise Inicial (Sem Testes)

Na entrega anterior (**Unidade 1**), o projeto **não possuía testes automatizados**, dependendo exclusivamente de testes manuais.

- **Cobertura de código:** tecnicamente **0%**
- **Risco:** alta probabilidade de regressões e falhas silenciosas

---

### 📈 Análise Final (Unidade 3)

Com a implementação da **suíte de testes automatizados** utilizando **JUnit 5** e **JaCoCo**, foi possível alcançar **cobertura total das regras de negócio**.

#### Métricas atingidas

- **Line Coverage (geral):** ~75% *(considerando exclusões intencionais)*
- **Branch Coverage (`Service`):** **100%**

---

## Justificativa para Código Não Coberto

Algumas partes do código não atingiram 100% de cobertura de forma **intencional**, seguindo boas práticas que priorizam testes **relevantes** e **manuteníveis**.

---

### Classe `GiftLinkApplication.java`

- **Razão:** Classe responsável apenas pelo *bootstrap* da aplicação Spring Boot
- **Justificativa:**
  - Testar o método `main` exigiria carregar todo o **contexto do Spring**
  - Isso caracterizaria um **teste de integração**, fora do escopo de testes unitários rápidos exigidos nesta unidade

---

### Interfaces de Repositório (`GiftRepository`)

- **Razão:** Interfaces que estendem `JpaRepository`
- **Justificativa:**
  - Não possuem lógica própria para ser testada
  - A implementação é fornecida dinamicamente pelo **Spring Data** em tempo de execução
  - Testar essas interfaces significaria **testar o framework**, e não o código da aplicação

---

## Evidência (JaCoCo)

📸 *Espaço reservado para a captura de tela do relatório HTML gerado pelo plugin **JaCoCo**, evidenciando as barras verdes na classe `GiftService`.*

---

📌 **Conclusão:** A cobertura alcançada reflete um equilíbrio entre qualidade, foco em regras de negócio e boas práticas de testes, garantindo confiabilidade sem introduzir complexidade desnecessária.