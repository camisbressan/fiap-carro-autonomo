INSERT INTO 
	TB_CARRO (marca, modelo, ano, placa, status, data_criacao, data_atualizacao) 
VALUES
  	('Ford', 'Ka', 2020, 'ABC1234', 'Disponível','2020-04-20T17:18:29.001Z','2020-04-20T17:18:29.001Z'),
  	('Fiat', 'Palio', 2019, 'XYX9876', 'Ocupado','2020-04-20T17:18:29.001Z','2020-04-20T17:18:29.001Z');
  	
  	
INSERT INTO TB_VIAGEM (id_carro, latitude_origem_usuario, longitude_origem_usuario, latitude_destino_usuario, longitude_destino_usuario,
	status, distancia_percorrida, valor, data_criacao, data_atualizacao) 
VALUES
  	(1, 32.9697, -96.80322, 29.46786, -98.53506, 'Solicitada', 0,0, '2020-04-20T17:18:00.001Z','2020-04-20T17:18:00.001Z'),
  	(1, 32.9697, -96.80322, 29.46786, -98.53506, 'Cancelada', 0, 0, '2020-04-20T17:18:00.001Z','2020-04-20T17:18:00.001Z'),
  	(2, 32.9697, -96.80322, 29.46786, -98.53506, 'Iniciada',0 , 0, '2020-04-20T17:18:00.001Z','2020-04-20T17:18:20.001Z'),
	(2, 32.9697, -96.80322, 29.46786, -98.53506, 'Finalizada', 10.3, 30.50, '2020-04-20T17:18:00.001Z','2020-04-20T17:18:29.001Z');