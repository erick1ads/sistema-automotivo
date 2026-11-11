# Roteiro do vídeo pitch — até 4 minutos

> Grave somente depois de executar e testar a aplicação. Ajuste o roteiro ao que realmente funcionar no seu computador.

## 0:00–0:25 — Introdução
“Olá! Este é o Sistema Automotivo — Gestão de Estoque de Veículos. O objetivo é centralizar as informações de marcas, modelos e veículos, permitindo cadastrar, consultar, filtrar, atualizar e excluir registros.”

## 0:25–1:00 — Arquitetura e tecnologias
“O projeto foi desenvolvido em Java com Spring Boot para disponibilizar uma API REST. As entidades representam marcas, modelos e veículos. O Spring Data JPA faz a persistência e o banco configurado pode ser H2 para testes locais ou MySQL para a configuração final. Os controladores recebem as requisições HTTP.”

## 1:00–1:35 — Cadastro
“Primeiro, cadastro uma marca. Depois, cadastro um modelo associado a essa marca. Por fim, cadastro um veículo informando o modelo, cor, ano, preço, quilometragem e status.”

Demonstre POST para `/api/marcas`, depois POST para `/api/modelos` e `/api/veiculos`.

## 1:35–2:20 — Consulta e filtros
“Agora listo os veículos e mostro que a API permite filtrar por status, marca, modelo, ano e faixa de preço.”

Demonstre GET `/api/veiculos` e pelo menos um filtro, por exemplo `/api/veiculos?status=DISPONIVEL`.

## 2:20–3:00 — Atualização e exclusão
“Com o PUT posso atualizar os dados de um veículo, como preço ou quilometragem. O DELETE permite remover um registro quando necessário.”

Demonstre PUT e DELETE com IDs de registros de teste.

## 3:00–3:35 — POO e validação
“As classes organizam os dados em objetos. O encapsulamento é aplicado por atributos privados e métodos de acesso. Os relacionamentos conectam marcas, modelos e veículos. As validações ajudam a impedir dados inválidos.”

## 3:35–4:00 — Encerramento
“Com isso, o sistema oferece uma base organizada para o controle de estoque de uma concessionária e aplica conceitos de orientação a objetos, API REST e banco de dados. Obrigado!”

## Checklist antes de gravar
- [ ] A aplicação iniciou sem erros.
- [ ] Os endpoints mostrados foram testados.
- [ ] Os IDs usados no vídeo existem.
- [ ] Nenhuma senha aparece na tela.
- [ ] O vídeo tem até 4 minutos.
- [ ] O link do vídeo está acessível para o professor.
