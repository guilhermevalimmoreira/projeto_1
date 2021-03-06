DROP DATABASE IF EXISTS RPG;
CREATE DATABASE RPG;
USE RPG;


CREATE TABLE IF NOT EXISTS Arma (
  tipo VARCHAR(30) NOT NULL,
  PRIMARY KEY (tipo))
ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS Personagem (
  nome VARCHAR(30) NOT NULL,
  nivel VARCHAR(20) NOT NULL,
  familia VARCHAR(30) NOT NULL,
  classe VARCHAR(45) NOT NULL,
  Arma_tipo VARCHAR(30) NOT NULL,
  PRIMARY KEY (nome),
  CONSTRAINT Personagem_Arma_fk
    FOREIGN KEY (Arma_tipo)
    REFERENCES Arma (tipo)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS Ferreiro (
  nome VARCHAR(30) NOT NULL,
  familia VARCHAR(45) NOT NULL,
  PRIMARY KEY (nome))
ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS Ferreiro_has_Arma (
  Id INT NOT NULL,
  Ferreiro_nome VARCHAR(30) NOT NULL,
  Arma_tipo VARCHAR(30) NOT NULL,
  PRIMARY KEY (Id),
  CONSTRAINT fk_Ferreiro_has_Arma_Ferreiro1
    FOREIGN KEY (Ferreiro_nome)
    REFERENCES Ferreiro (nome)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT fk_Ferreiro_has_Arma_Arma1
    FOREIGN KEY (Arma_tipo)
    REFERENCES Arma (tipo)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS Artesao (
  nome VARCHAR(30) NOT NULL,
  ferreiro_nome VARCHAR(30) NOT NULL,
  PRIMARY KEY (nome),
  CONSTRAINT fk_Artesao_Ferreiro1
    FOREIGN KEY (Ferreiro_nome)
    REFERENCES Ferreiro (nome)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;