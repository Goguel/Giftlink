# Relatório de Testes e Qualidade — GiftLink

Este documento descreve a **estratégia de testes**, as **métricas de cobertura** e a **avaliação de qualidade** do projeto **GiftLink**.

---

## 1. Suíte de Testes

### 1.1 Visão Geral

A suíte de testes foi desenvolvida com foco na **integridade da lógica de negócios**, concentrando-se principalmente na classe `GiftService`, responsável pelas regras de validação e pelo fluxo de dados da aplicação.

- **Total de testes:** 12 testes unitários
- **Frameworks utilizados:**
  - JUnit 5 (Jupiter)
  - Mockito
- **Status da suíte:** ✅ *Todos os testes passando (Success)*

---

### 1.2 Estrutura e Organização

Os testes estão localizados em:

```
src/test/java/br/ufrn/miguelxavier/giftlink/service/
```

Foi adotado o padrão **AAA (Arrange, Act, Assert)** para garantir clareza, legibilidade e manutenção dos testes.

- **Arrange:** Preparação dos *mocks* (`GiftRepository`) e dos dados de entrada
- **Act:** Execução do método do serviço a ser testado
- **Assert:** Verificação do retorno e validação das interações com o repositório

---

## 2. Cobertura de Código

### 2.1 Métricas Gerais

O foco da cobertura de testes foi direcionado às **classes de Domínio e Serviço**, que concentram a lógica crítica da aplicação.

- **Cobertura de Linhas (`GiftService`):** 100%
- **Cobertura de Branches (`GiftService`):** 100%

---

### 2.2 Detalhamento por Módulo

| Classe                  | Cobertura de Linhas | Cobertura de Branches | Status                          |
|-------------------------|---------------------|------------------------|---------------------------------|
| `GiftService`           | 100%                | 100%                   | 🟢 Excelente                    |
| `Gift` (Model)          | 100%                | N/A                    | 🟢 Excelente                    |
| `GiftController`        | N/A                 | N/A                    | ⚪ Testes de Integração Futuros  |
| `GiftLinkApplication`   | 0%                  | N/A                    | 🔴 Excluído (Configuração)      |

> **Nota:** Classes de configuração e a classe principal (`main`) foram excluídas da meta de cobertura, pois não contêm lógica de negócio suscetível a falhas lógicas.

---

## 3. Conclusão

O projeto **atingiu plenamente os requisitos de qualidade da Unidade 3**. A refatoração realizada anteriormente facilitou significativamente a criação dos testes, uma vez que os métodos passaram a ser **menores**, **mais coesos** e com **responsabilidade única**.

Como resultado:

- O sistema está protegido contra **regressões** nas regras de validação de **preço** e **nome**
- A base de código apresenta **alta confiabilidade** e **facilidade de manutenção**

---

📌 **Resumo:** O GiftLink demonstra maturidade em práticas de testes e qualidade, estando bem preparado para evolução futura e inclusão de testes de integração.

