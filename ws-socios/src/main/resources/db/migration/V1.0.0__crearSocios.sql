CREATE TABLE socios
(
    codigo VARCHAR(9),
    nombre VARCHAR(100),
    email  VARCHAR(30),
    saldo  NUMERIC(6, 2),

    CONSTRAINT "PK_SOCIOS.CODIGO" PRIMARY KEY (codigo),
    CONSTRAINT "UK_SOCIOS.EMAIL" UNIQUE (email),
    CONSTRAINT "NN_SOCIOS.CODIGO" CHECK ( codigo IS NOT NULL ),
    CONSTRAINT "NN_SOCIOS.EMAIL" CHECK ( email IS NOT NULL ),
    CONSTRAINT "NN_SOCIOS.NOMBRE" CHECK ( nombre IS NOT NULL ),
    CONSTRAINT "CH_SOCIOS.SALDO" CHECK ( saldo >= 0)

);

CREATE SEQUENCE SEQ_SOCIOS MINVALUE 1 MAXVALUE 999   START WITH 1 INCREMENT BY 1;
INSERT INTO socios(codigo,nombre,email,saldo) VALUES('XA','ENRIQUE', 'enrqiue@asdf-com', 321);
INSERT INTO socios(codigo,nombre,email,saldo) VALUES('XB','ANA', 'ana@asdf-com', 23);
INSERT INTO socios(codigo,nombre,email,saldo) VALUES('XC','PEPITO', 'pepe@asdf-com', 8);

COMMIT;