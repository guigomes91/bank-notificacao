# Microserviço responsável por envio de notificações ao cliente do microserviço bank-conta

Quando cadastrado uma conta pelo serviço bank-conta, o mesmo notifica o este serviço, que é responsável por dar as boas vindas por email ao cliente.
A mensagem é enviada para fila no RabbitMQ, o serivço bank-notificacao se inscreve nessa fila, recebendo a mensagem e disparando o email
enviado do payload.
