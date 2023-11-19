CREATE SCHEMA atv_faculdade2;
USE atv_faculdade2;

CREATE TABLE aluno(
	ra INT PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(40) NOT NULL,
    email VARCHAR(40) NOT NULL,
    telefone INT,
    fk_curso INT
);

CREATE TABLE cursos(
	id_curso INT PRIMARY KEY AUTO_INCREMENT,
	nomeCurso VARCHAR(40) NOT NULL UNIQUE,
    acronimo_cur VARCHAR(5) NOT NULL UNIQUE,
    duracao VARCHAR(15) NOT NULL
);

ALTER TABLE aluno ADD CONSTRAINT fk_cursos FOREIGN KEY(fk_curso) REFERENCES cursos(id_curso);
SELECT * FROM cursos;
INSERT INTO cursos(nomeCurso, acronimo_cur, duracao) VALUES('Ciencias da Computação', 'CC', '8 Semestres');
INSERT INTO cursos(nomeCurso, acronimo_cur, duracao) VALUES('Sistemas da Informação', 'SI', '8 Semestres');
INSERT INTO cursos(nomeCurso, acronimo_cur, duracao) VALUES('Analise e Desenvolvimento de Sistemas', 'ADS', '4 Semestres');
