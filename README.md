# 🚀 Sobre o Projeto  

### O objetivo deste projeto é o desenvolvimento de uma **API RESTful** utilizando **Java** e o **framework Spring Boot**.  

A aplicação foi criada para **enviar e receber requisições HTTP** de um cliente genérico, desenvolvido apenas para fins de demonstração.  

Esse cliente representa o **site de uma academia fictícia**, responsável por consumir os serviços fornecidos pela API.  

O projeto tem como foco principal demonstrar o **funcionamento básico de uma API** e sua **comunicação com um frontend**, abordando conceitos essenciais de **requisições, respostas e integração entre sistemas**.  

Além disso, o projeto também foi desenvolvido com o objetivo de **aprimorar o aprendizado em Java, Spring Boot e arquitetura REST**.  

---

## 💻 Visão do Site  

<img width="1914" height="908" alt="Captura de tela 2025-11-06 214426" src="https://github.com/user-attachments/assets/332ac5a0-728d-4cea-a117-a07615d8c6f4" />  

A tela acima exibe a **listagem de alunos cadastrados**, mostrando **ID, nome, idade e plano**.  

Essa é a **página inicial do frontend**, que exibe os clientes obtidos via requisição `GET` para a API.  

Ao listar os alunos, o **frontend envia uma requisição GET**, e o método `findAll()` do repositório aciona o **service**, responsável por analisar o status das faturas de cada cliente.  

---

## ⚙️ Funcionamento da Regra de Negócio  

Durante a listagem, a API executa uma verificação no status das faturas de cada aluno da seguinte forma:

- Se a **última fatura** foi **paga**, é gerada automaticamente **uma nova fatura** com status `IN_PROGRESS`.  
- Caso falte **uma semana para o vencimento**, o status muda para `WAITING_PAYMENT`.  
- Se a fatura for **paga**, o ciclo recomeça.  
- Caso a data de vencimento seja ultrapassada sem pagamento, o status é alterado para `OVERDUE`.  

Essa lógica garante que o sistema mantenha o **controle de pagamentos atualizado** de forma automática.  

---

## 🧩 Trecho do Código  

```java
public List<Goer> findAll() {
    List<Goer> list = new ArrayList<>();

    for (Goer goer : goerRepository.findAll()) {
        currentInvoiceStatus(goer.getId());
        list.add(goer);
    }

    return list;
}

private void currentInvoiceStatus(Long id) {
    int lastInvoice = goerRepository.findById(id).get().getInvoices().size() - 1;

    if (goerRepository.getReferenceById(id).getInvoices().get(lastInvoice).getStatusPayment().equals(StatusPayment.IN_PROGRESS)) {

        if (LocalDate.now().getDayOfYear() > goerRepository.getReferenceById(id).getInvoices().get(lastInvoice).getDueDay().minusDays(7).getDayOfYear()
                && LocalDate.now().getDayOfYear() < goerRepository.getReferenceById(id).getInvoices().get(lastInvoice).getDueDay().getDayOfYear()) {

            goerRepository.getReferenceById(id).getInvoices().get(lastInvoice).setStatusPayment(StatusPayment.WAITING_PAYMENT);
            invoiceRepository.save(goerRepository.getReferenceById(id).getInvoices().get(lastInvoice));
        }

    } else if (goerRepository.getReferenceById(id).getInvoices().get(lastInvoice).getStatusPayment().equals(StatusPayment.WAITING_PAYMENT)) {

        if (LocalDate.now().getDayOfYear() > goerRepository.getReferenceById(id).getInvoices().get(lastInvoice).getDueDay().getDayOfYear()) {

            goerRepository.getReferenceById(id).getInvoices().get(lastInvoice).setStatusPayment(StatusPayment.OVERDUE);
            invoiceRepository.save(goerRepository.getReferenceById(id).getInvoices().get(lastInvoice));
        }

    } else if (goerRepository.getReferenceById(id).getInvoices().get(lastInvoice).getStatusPayment().equals(StatusPayment.PAID)) {

        Invoice invoice = new Invoice(null, StatusPayment.IN_PROGRESS);
        invoice.setGoer(goerRepository.getReferenceById(id));

        invoice.setPrice();
        invoice.finallySetDueDay(goerRepository.getReferenceById(id).getPlanCategory());
        invoice.setReferenceMonth(invoice.getDueDay().getMonth());

        goerRepository.getReferenceById(id).getInvoices().add(invoice);
        invoiceRepository.save(invoice);
    }
}
```

## 🧾 Cadastro de Alunos

### Tela de cadastro de alunos

<img width="1902" height="900" alt="Tela de cadastro de alunos" src="https://github.com/user-attachments/assets/1063a165-8258-4443-a735-52dc18d7b6da" />

Esta é a tela utilizada para o cadastro de novos clientes, onde é possível adicionar:

- Nome  
- Data de nascimento  
- CPF  
- Telefone  
- Endereço  
- Plano desejado (MENSAL — 1 mês, TRIMESTRAL — 3 meses, SEMESTRAL — 6 meses ou ANUAL — 12 meses)  

Após o preenchimento, os dados são enviados para a API para serem salvos.

<img width="1894" height="902" alt="Confirmação de envio" src="https://github.com/user-attachments/assets/a3795544-7ed2-4a9d-a9bf-0aef896f00fb" />

Após o envio, é exibida uma **mensagem de confirmação de cadastro**, mostrando que o cliente foi salvo com sucesso, juntamente com o seu ID.

### Tabela no banco de dados

<img width="1238" height="456" alt="Tabela no banco de dados" src="https://github.com/user-attachments/assets/31cc6d8c-ab41-4863-8eba-8311083c725a" />

---

## 🧠 Lógica de Inserção de Aluno

### Controller

O controller chama o service e constrói a nova entidade, retornando uma resposta HTTP `201 Created`:

```java
@PostMapping
public ResponseEntity<Goer> insert(@RequestBody Goer goer) {
    URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(goer.getId()).toUri();

    return ResponseEntity.created(uri).body(goerService.insert(goer));
}

service:

public Goer insert(Goer goer){

        goer.setAge();
        goerRepository.save(goer);

        goer.getInvoices().get(0).setGoer(goer);
        goer.getInvoices().get(0).setPrice();
        goer.getInvoices().get(0).setDueDay();

        invoiceRepository.save(goer.getInvoices().get(0));

        return goer;

    }
```

### Lógica do Service para Inserção de Aluno

No service, antes de salvar o novo aluno:

- A **idade é pré-calculada** com base na data de nascimento fornecida no corpo da requisição.  
- Em seguida, são definidos os **atributos da primeira fatura**, que é gerada automaticamente com base nos dados do aluno:  
  - A fatura é vinculada ao aluno cadastrado.  
  - O preço da fatura é definido de acordo com o plano escolhido pelo cliente.  
  - A data de vencimento é ajustada conforme o plano:  
    - Mensal → 1 mês de validade  
    - Trimestral → 3 meses  
    - Semestral → 6 meses  
    - Anual → 12 meses  
- Por fim, a fatura é salva no **repositório de faturas** (`invoiceRepository`).


## 💳 Consulta de Faturas

### Tela de consulta de faturas

<img width="1903" height="773" alt="Consulta de faturas" src="https://github.com/user-attachments/assets/e10d48d9-41d8-4a0c-bbdc-cef6e4cf1bb2" />

### Buscando aluno

<img width="1873" height="847" alt="Busca de aluno" src="https://github.com/user-attachments/assets/ba1a75f8-43e4-44d3-8099-8cb94c696795" />

### Detalhes da fatura do aluno

<img width="1850" height="908" alt="Detalhes da fatura" src="https://github.com/user-attachments/assets/5c2fdd9e-5e20-4646-8275-ad581e8443e7" />

Ao clicar sobre um aluno, são exibidos os **detalhes completos da fatura**, incluindo:

- Informações pessoais do aluno  
- Situação atual da última fatura  
- Histórico de todas as faturas  
- Botão para pagar a última fatura, caso o status esteja **WAITING_PAYMENT** ou **OVERDUE**

Essa funcionalidade permite acompanhar o **status financeiro atualizado** de cada cliente e visualizar seu histórico de pagamentos.





 




