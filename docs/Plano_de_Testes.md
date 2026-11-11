# Plano de testes da API

Execute a aplicação e use Postman ou outra ferramenta HTTP em `http://localhost:8080`.

## 1. Criar marca
POST `/api/marcas`
```json
{"nome":"Toyota"}
```
Esperado: HTTP 201 e JSON com o ID gerado.

## 2. Listar marcas
GET `/api/marcas`
Esperado: HTTP 200 e lista contendo Toyota.

## 3. Criar modelo
POST `/api/modelos`
```json
{"nome":"Corolla","marcaId":1}
```
Use o ID real da marca criada. Esperado: HTTP 201.

## 4. Criar veículo
POST `/api/veiculos`
```json
{
  "modeloId": 1,
  "cor": "Prata",
  "anoFabricacao": 2022,
  "preco": 112900.00,
  "quilometragem": 35000,
  "status": "DISPONIVEL"
}
```
Use o ID real do modelo criado. Esperado: HTTP 201.

## 5. Consultar veículos
GET `/api/veiculos`
Esperado: HTTP 200 e lista contendo o veículo.

## 6. Filtrar por status
GET `/api/veiculos?status=DISPONIVEL`
Esperado: somente veículos disponíveis.

## 7. Filtrar por faixa de preço
GET `/api/veiculos?precoMin=50000&precoMax=150000`
Esperado: veículos dentro do intervalo.

## 8. Atualizar veículo
PUT `/api/veiculos/1`
```json
{
  "modeloId": 1,
  "cor": "Prata",
  "anoFabricacao": 2022,
  "preco": 109900.00,
  "quilometragem": 36000,
  "status": "RESERVADO"
}
```
Use o ID real. Esperado: HTTP 200 e dados atualizados.

## 9. Consultar veículo inexistente
GET `/api/veiculos/99999`
Esperado: HTTP 404 e mensagem explicativa.

## 10. Excluir veículo
DELETE `/api/veiculos/1`
Use o ID real do veículo de teste. Esperado: HTTP 204.

## Evidências para o PDF
Registre capturas de tela reais de:
1. Aplicação iniciada sem erros no terminal.
2. Marca e modelo cadastrados.
3. Veículo cadastrado.
4. Lista de veículos.
5. Um filtro aplicado.
6. Atualização de veículo.
7. Exclusão de veículo.
8. Banco/tabelas no MySQL Workbench, se a configuração MySQL for usada.

Marque cada teste como aprovado somente após executá-lo no seu ambiente.
