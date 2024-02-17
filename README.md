# Microserviço responsável por envio de notificações ao cliente

Quando cadastrado uma conta de cliente no banco pelo microserviço bank-conta, este serviço irá publicar uma mensagem na fila do RabbitMQ, onde o 
serviço de notificação vai se inscrever nessa fila como listener e será responsável por dar as boas vindas por email ao cliente.
A mensagem é enviada para fila no RabbitMQ, o serivço bank-notificacao se inscreve nessa fila, recebendo a mensagem e disparando o email
enviado do payload.
