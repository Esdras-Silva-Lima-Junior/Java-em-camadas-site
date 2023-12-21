CREATE SCHEMA atv_faculdade2;
USE atv_faculdade2;

CREATE TABLE funcionario(
	matricula INT PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(40) NOT NULL,
    email VARCHAR(40) NOT NULL,
    cpf varchar(11),
    telefone INT,
    cargo varchar(25)
);

SELECT * FROM funcionario;