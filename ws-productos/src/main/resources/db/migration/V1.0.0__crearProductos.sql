CREATE TABLE productos
(
    codigo      VARCHAR(9),
    nombre      VARCHAR(100),
    descripcion VARCHAR(200),
    stock       NUMERIC(4),
    precio      NUMERIC(6, 2),

    CONSTRAINT "PK_PRODUCTOS.CODIGO" PRIMARY KEY (codigo),
    CONSTRAINT "UK_PRODUCTOS.NOMBRE" UNIQUE (nombre),
    CONSTRAINT "NN_PRODUCTOS.NOMBRE" CHECK ( nombre IS NOT NULL ),
    CONSTRAINT "NN_PRODUCTOS.CODIGO" CHECK ( codigo IS NOT NULL ),
    CONSTRAINT "NN_PRODUCTOS.DESCRIPCION" CHECK ( descripcion IS NOT NULL ),
    CONSTRAINT "NN_PRODUCTOS.PRECIO" CHECK ( precio IS NOT NULL ),
    CONSTRAINT "CH_PRODUCTOS.STOCK" CHECK ( stock >= 0 ),
    CONSTRAINT "CH_PRODUCTOS.PRECIO" CHECK ( precio > 0 )

);

CREATE SEQUENCE SEQ_PRODUCTOS    MINVALUE 1 MAXVALUE 999 START WITH 1 INCREMENT BY 1;
INSERT INTO productos(codigo, nombre, descripcion, stock, precio) VALUES ('YA', 'Comida', 'esto es comida', 455, 43.3);
INSERT INTO productos(codigo, nombre, descripcion, stock, precio) VALUES ('YB', 'Bebida', 'se trata de bebida', 234, 54);
INSERT INTO productos(codigo, nombre, descripcion, stock, precio) VALUES ('YC', 'Otros', 'no se me ocurre', 543, 21);