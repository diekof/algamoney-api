CREATE TABLE categoria
(
    codigo BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Insert rows into table 'categoria'
INSERT INTO categoria (nome) VALUES ( 'Lazer');
INSERT INTO categoria (nome) VALUES ( 'Alimientação');
INSERT INTO categoria (nome) VALUES ( 'Supermercado');
INSERT INTO categoria (nome) VALUES ( 'Farmácia');
INSERT INTO categoria (nome) VALUES ( 'Outros');