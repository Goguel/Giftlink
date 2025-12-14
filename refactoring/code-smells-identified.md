# Code Smells Identificados - Projeto GiftLink

## 1. Long Method (Método Longo)

- **Arquivo**: `src/main/java/br/ufrn/miguelxavier/giftlink/service/GiftService.java`
- **Método**: `createGift(String name, String description, BigDecimal value)`
- **Descrição**: O método `createGift` possui múltiplas responsabilidades. Ele é responsável por (1) validar os dados de entrada, (2) criar a instância do objeto `Gift`, e (3) solicitar a persistência dos dados no banco. Isso viola o Princípio da Responsabilidade Única, tornando o método mais difícil de testar e entender.
- **Severidade**: Média
- **Ferramenta de Detecção**: Análise manual (identificado pela violação de princípios de design).
- **Status**: Pendente de refatoração.

## 2. Long Parameter List (Lista Longa de Parâmetros)

- **Arquivo**: `src/main/java/br/ufrn/miguelxavier/giftlink/service/GiftService.java`
- **Método**: `createGift(String name, String description, BigDecimal value)`
- **Descrição**: O método recebe três parâmetros individuais para criar um `Gift`. Se a entidade `Gift` ganhar mais atributos no futuro (ex: categoria, URL da imagem), esta lista de parâmetros tenderá a crescer, tornando a chamada do método complexa e propensa a erros.
- **Severidade**: Baixa
- **Ferramenta de Detecção**: Análise manual.
- **Status**: Pendente de refatoração.

## 3. Primitive Obsession (Obsessão por Tipos Primitivos)

- **Arquivo**: `src/main/java/br/ufrn/miguelxavier/giftlink/service/GiftService.java`
- **Método**: `createGift(String name, String description, BigDecimal value)`
- **Descrição**: Este code smell está diretamente relacionado ao `Long Parameter List`. O método depende de uma coleção de tipos simples (String, BigDecimal) em vez de receber um objeto único que agrupe esses dados de forma coesa (o próprio objeto `Gift` ou um DTO - Data Transfer Object). O uso de um objeto tornaria o código mais expressivo e organizado.
- **Severidade**: Baixa
- **Ferramenta de Detecção**: Análise manual.
- **Status**: Pendente de refatoração.