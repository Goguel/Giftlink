# Log de Refatorações - Projeto GiftLink

## Refatoração #1: Extract Method (Extrair Método)

- **Code Smell Identificado**: Long Method (Método Longo) na classe `GiftService`.
- **Técnica Aplicada**: Extract Method.
- **Arquivos Afetados**: `src/main/java/br/ufrn/miguelxavier/giftlink/service/GiftService.java`
- **Justificativa**: O método `createGift` original violava o Princípio da Responsabilidade Única, realizando validação, criação de objeto e persistência em um único bloco de código. A extração de suas responsabilidades para métodos menores e mais focados melhora a legibilidade, a testabilidade e a manutenção do código.
- **Resultado Planejado**: O método original será decomposto em:
    - Um método privado para validar os dados de entrada (`validateGiftData`).
    - Um método privado para criar e configurar o objeto (`buildGiftFromData`).
    - O método público `createGift` se tornará um orquestrador, chamando os métodos extraídos em sequência.

---

## Refatoração #2: Introduce Parameter Object (Introduzir Objeto como Parâmetro)

- **Data**: 13/10/2025
- **Code Smells Identificados**: Long Parameter List (Lista Longa de Parâmetros) e Primitive Obsession (Obsessão por Tipos Primitivos) na classe `GiftService`.
- **Técnica Aplicada**: Introduce Parameter Object.
- **Arquivos Afetados**:
    - `src/main/java/br/ufrn/miguelxavier/giftlink/service/GiftService.java`
    - `src/main/java/br/ufrn/miguelxavier/giftlink/controller/GiftController.java`
- **Justificativa**: O método `createGift` recebia múltiplos parâmetros primitivos, o que o tornava menos legível e mais difícil de manter. Agrupar esses parâmetros em um objeto específico (neste caso, podemos usar a própria entidade `Gift`) torna a assinatura do método mais limpa e expressiva, além de reduzir o acoplamento entre o controller e os detalhes internos do serviço.
- **Resultado Planejado**: A assinatura do método `createGift` será alterada de `createGift(String name, String description, BigDecimal value)` para `createGift(Gift gift)`. O `GiftController` será ajustado para criar o objeto `Gift` antes de chamar o serviço.