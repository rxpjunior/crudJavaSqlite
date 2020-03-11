--
-- File generated with SQLiteStudio v3.2.1 on sáb fev 29 13:17:08 2020
--
-- Text encoding used: System
--
PRAGMA foreign_keys = off;
BEGIN TRANSACTION;

-- Table: compensacao
DROP TABLE IF EXISTS compensacao;
CREATE TABLE compensacao (compensacaoId INTEGER PRIMARY KEY AUTOINCREMENT, data DATE NOT NULL, tempo DOUBLE NOT NULL, dia_compensarId INTEGER REFERENCES dia_compensar (dia_compensarId));

-- Table: dia_compensar
DROP TABLE IF EXISTS dia_compensar;
CREATE TABLE dia_compensar (dia_compensarId INTEGER PRIMARY KEY AUTOINCREMENT, data DATE NOT NULL UNIQUE, descricao VARCHAR NOT NULL, compensado BOOLEAN NOT NULL DEFAULT (false));

COMMIT TRANSACTION;
PRAGMA foreign_keys = on;
