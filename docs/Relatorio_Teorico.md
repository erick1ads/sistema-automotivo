# Sistema Automotivo: Gestão de Estoque de Veículos

**Relatório do projeto**

**Aluno(a):** [preencha seu nome]  
**Curso:** [preencha seu curso]  
**Disciplina:** [preencha a disciplina]  
**Professor(a):** [preencha o nome]  
**Instituição:** [preencha a instituição]  
**Data:** [preencha a data]

## 1. Introdução

Uma concessionária precisa saber quais veículos estão no estoque, quanto custam e se estão disponíveis, reservados ou vendidos. Quando essas informações ficam espalhadas em planilhas ou anotações, pode ficar mais difícil encontrar os dados certos e manter tudo atualizado.

A proposta deste trabalho é desenvolver um sistema para organizar essas informações. Nele, será possível cadastrar marcas, modelos e veículos, consultar os registros, aplicar filtros e alterar ou excluir dados quando necessário. A aplicação foi planejada em Java, usando Spring Boot para criar uma API REST e um banco de dados relacional para guardar as informações.

## 2. Problema e justificativa

O problema escolhido é a dificuldade de controlar o estoque de veículos de forma organizada. Por exemplo, um funcionário pode consultar um preço antigo ou demorar para encontrar um veículo com determinadas características. Um sistema centralizado ajuda a manter os dados em um só lugar e facilita as consultas.

A aplicação não resolve sozinha todos os problemas de uma concessionária, mas pode ajudar a equipe a consultar e atualizar o estoque com mais facilidade.

## 3. Levantamento de requisitos

Como não foi realizada uma entrevista real com uma concessionária, as perguntas e respostas a seguir são **simuladas**. Elas representam necessidades possíveis para esse tipo de sistema e podem ser ajustadas caso sejam coletadas informações reais.

1. **Quais dados devem ser registrados sobre cada veículo?**  
   **Resposta simulada:** Marca, modelo, ano de fabricação, cor, preço, quilometragem e status.

2. **Quem usaria o sistema?**  
   **Resposta simulada:** Funcionários responsáveis pelo estoque, vendedores e gestores que precisem consultar os veículos.

3. **Por que cadastrar marcas e modelos separadamente?**  
   **Resposta simulada:** Para não precisar repetir o nome da marca em cada cadastro e para manter os modelos ligados à marca correta.

4. **Quais status serão usados?**  
   **Resposta simulada:** Disponível, reservado, vendido e descontinuado.

5. **Como os veículos devem ser pesquisados?**  
   **Resposta simulada:** Por marca, modelo, ano, status e faixa de preço.

6. **Quais dados podem ser atualizados?**  
   **Resposta simulada:** Preço, quilometragem, cor, ano, modelo e status, conforme a necessidade.

7. **O sistema precisa permitir exclusões?**  
   **Resposta simulada:** Sim. A exclusão deve ser feita com cuidado quando o registro estiver ligado a outros dados.

8. **O que fazer quando um veículo for vendido?**  
   **Resposta simulada:** Alterar seu status para “vendido”, para que ele continue registrado no sistema.

9. **Como tratar dados incorretos?**  
   **Resposta simulada:** O sistema deve avisar sobre o erro e impedir campos obrigatórios vazios, preços inválidos ou quilometragem negativa.

10. **O sistema precisa ser rápido?**  
    **Resposta simulada:** As consultas mais comuns devem funcionar em tempo adequado para o tamanho do estoque da empresa.

11. **Os dados devem continuar salvos depois de fechar o sistema?**  
    **Resposta simulada:** Sim. As informações precisam ficar em um banco de dados, e não apenas na memória enquanto o programa estiver aberto.

12. **Que melhorias poderiam ser feitas depois?**  
    **Resposta simulada:** Relatórios de veículos disponíveis e vendidos, além de consultas por marca e faixa de preço.

## 4. Entidades do sistema

### Marca
Guarda o nome da fabricante, como Toyota ou Honda. Cada marca tem um identificador próprio, e o nome é obrigatório.

### Modelo
Guarda o nome do modelo e a marca à qual ele pertence. Uma marca pode ter vários modelos cadastrados.

### Veículo
Representa uma unidade do estoque. Os dados principais são modelo, ano de fabricação, cor, preço, quilometragem e status.

### StatusVeiculo
É uma enumeração usada para limitar o status a quatro opções: `DISPONIVEL`, `RESERVADO`, `VENDIDO` e `DESCONTINUADO`.

### Relacionamentos
Uma marca pode ter vários modelos, e um modelo pode estar ligado a vários veículos. Assim, a marca não precisa ser digitada de novo em cada veículo: o sistema chega até ela por meio do modelo.

## 5. Programação Orientada a Objetos

- **Classes:** `Marca`, `Modelo` e `Veiculo` representam os principais elementos do sistema.
- **Atributos:** guardam informações, como nome, cor, preço e quilometragem.
- **Métodos:** getters e setters permitem consultar e alterar atributos. Os controladores também possuem métodos para cadastrar, listar, atualizar e excluir registros.
- **Encapsulamento:** os atributos das entidades são privados e são acessados por métodos. Isso ajuda a organizar o acesso aos dados dos objetos.
- **Associação:** `Modelo` tem uma referência a `Marca`, e `Veiculo` tem uma referência a `Modelo`.
- **Herança e interfaces:** os repositórios estendem interfaces do Spring Data JPA, reaproveitando operações de banco de dados fornecidas pelo framework. O domínio não tem uma classe-base de veículos porque o escopo atual não exige tipos diferentes de veículo com comportamentos próprios.
- **Enumeração:** `StatusVeiculo` limita os estados aceitos pelo sistema.

## 6. Requisitos funcionais

- **RF01 — Cadastrar veículos:** guardar os dados principais de cada veículo e relacioná-lo a um modelo.
- **RF02 — Gerenciar marcas:** cadastrar, consultar, alterar e excluir marcas, respeitando os relacionamentos existentes.
- **RF03 — Gerenciar modelos:** cadastrar e consultar modelos vinculados a uma marca.
- **RF04 — Consultar veículos:** listar os veículos e buscar um registro pelo identificador.
- **RF05 — Filtrar resultados:** pesquisar por marca, modelo, preço mínimo e máximo, ano e status.
- **RF06 — Atualizar veículos:** alterar os dados de um veículo já cadastrado.
- **RF07 — Excluir veículos:** remover um registro quando necessário.
- **RF08 — Validar informações:** impedir o envio de dados obrigatórios ausentes ou valores fora dos limites definidos.

## 7. Requisitos não funcionais

- **RNF01 — Facilidade de uso:** os endereços da API e as mensagens de erro devem ser compreensíveis.
- **RNF02 — Desempenho:** as consultas devem responder em tempo adequado ao volume de dados previsto.
- **RNF03 — Persistência:** os dados devem continuar armazenados após o encerramento da aplicação.
- **RNF04 — Organização:** o código deve ficar dividido em classes com responsabilidades diferentes, como entidades, repositórios e controladores.
- **RNF05 — Integridade:** o sistema deve evitar referências a marcas ou modelos inexistentes e validar os campos recebidos.
- **RNF06 — Segurança:** senhas de banco de dados não devem ser colocadas diretamente no código publicado.

## 8. Tecnologias utilizadas

### Java 21
É a linguagem escolhida para implementar o sistema. Ela oferece recursos de orientação a objetos, que são parte importante do conteúdo da disciplina.

### Spring Boot
Ajuda a configurar a aplicação Java e a criar os endereços HTTP da API sem precisar montar toda a infraestrutura manualmente.

### Spring Data JPA
Facilita o acesso ao banco de dados. Com os repositórios, operações comuns como salvar, consultar e excluir registros exigem menos código repetido.

### MySQL
É o banco relacional previsto para guardar os dados de forma persistente. O projeto inclui um arquivo SQL e uma configuração alternativa para utilizá-lo.

### H2
Também foi incluído como opção local para facilitar os primeiros testes sem precisar configurar um servidor MySQL logo no início. Para demonstrar o uso do MySQL, essa configuração deve ser feita e testada separadamente.

### Maven e Postman
O Maven gerencia as bibliotecas e ajuda a executar a aplicação. O Postman pode ser usado para enviar requisições à API e verificar as respostas.

## 9. Organização da aplicação

O projeto foi dividido em partes para facilitar a leitura e a manutenção:

1. **Controllers:** recebem as requisições HTTP e definem as operações disponíveis.
2. **Entidades:** representam marcas, modelos e veículos.
3. **Repositories:** fazem a comunicação com o banco de dados por meio do Spring Data JPA.
4. **Banco de dados:** armazena os registros.

A API segue as operações HTTP mais comuns: `GET` para consultar, `POST` para cadastrar, `PUT` para atualizar e `DELETE` para excluir.

## 10. Dificuldades previstas

Uma das dificuldades é configurar corretamente o ambiente Java e o banco de dados. Outra é entender como as entidades se relacionam e como os controladores usam os repositórios para consultar e salvar informações.

Para lidar com isso, o ideal é executar o projeto por etapas, conferir as mensagens do terminal e testar cada operação separadamente. A validação dos campos também ajuda a evitar cadastros incompletos.

**Depois de executar o sistema, acrescente aqui as dificuldades que você realmente encontrou e como resolveu cada uma.**

## 11. O que pode acontecer sem um controle organizado

Se os dados continuarem espalhados em planilhas ou anotações, podem ocorrer informações duplicadas, preços desatualizados e demora para localizar veículos. Isso pode atrapalhar o atendimento e dificultar a visão geral do estoque. Por esse motivo, manter os dados organizados é importante para o trabalho diário da equipe.

## 12. Conclusão

O projeto propõe uma forma de organizar os dados de uma concessionária em um único sistema. Com o cadastro de marcas, modelos e veículos, além das consultas e filtros, fica mais fácil localizar e atualizar as informações do estoque.

O desenvolvimento também permite praticar conceitos de orientação a objetos, API REST e banco de dados relacional. Antes da entrega, é necessário executar o programa, testar as operações e incluir capturas de tela reais. As conclusões finais devem refletir os resultados desses testes.

## 13. Referências

- DEITEL, Paul; DEITEL, Harvey. *Java Como Programar*. Referência indicada no enunciado da atividade.
- CASA DO CÓDIGO. *Desbravando Java e Orientação a Objetos: um guia para o iniciante da linguagem*. Referência indicada no enunciado.
- SPRING. *Spring Boot Documentation*. Disponível em: https://docs.spring.io/spring-boot/
- SPRING. *Spring Data JPA Reference*. Disponível em: https://docs.spring.io/spring-data/jpa/reference/
- MYSQL. *MySQL Documentation*. Disponível em: https://dev.mysql.com/doc/

---

**Antes de entregar:** preencha os dados de identificação, revise as respostas simuladas, inclua as dificuldades que realmente teve, execute os testes e acrescente evidências reais. Não descreva como concluída uma etapa que ainda não foi realizada.
