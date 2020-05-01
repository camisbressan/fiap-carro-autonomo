DROP TABLE IF EXISTS TB_CARRO;
 
CREATE TABLE TB_CARRO (
  id INT AUTO_INCREMENT  PRIMARY KEY,
  marca VARCHAR(100) NOT NULL,
  modelo VARCHAR(100) NOT NULL,
  ano INT NOT NULL,
  placa VARCHAR(7) NOT NULL,
  status VARCHAR(20) NOT NULL,
  data_criacao timestamp DEFAULT NULL,
  data_atualizacao timestamp DEFAULT NULL
);

DROP TABLE IF EXISTS TB_VIAGEM;

CREATE TABLE TB_VIAGEM (
  id INT AUTO_INCREMENT  PRIMARY KEY,
  id_carro INT NOT NULL,
  latitude_origem_usuario DECIMAL(10,8) NOT NULL,
  longitude_origem_usuario DECIMAL(11,8) NOT NULL,
  latitude_destino_usuario DECIMAL(10,8) NOT NULL,
  longitude_destino_usuario DECIMAL(11,8) NOT NULL,
  status VARCHAR(20) NOT NULL,
  distancia_percorrida DECIMAL(5,2) DEFAULT NULL,
  valor DECIMAL(5,2) DEFAULT NULL,
  data_criacao timestamp DEFAULT NULL,
  data_atualizacao timestamp DEFAULT NULL
);
