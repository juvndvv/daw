CREATE TABLE ediciones (
    isbn                VARCHAR(13)     PRIMARY KEY,
    fecha_publicacion   DATE            NOT NULL
);

CREATE TABLE personas (
    dni         VARCHAR(9),
    nombre      VARCHAR(40)     NOT NULL,
    telefono    VARCHAR(9)      NOT NULL,
    tipo        VARCHAR(7),
    PRIMARY KEY(dni, tipo)
);

CREATE TABLE articulos (
    isbn        VARCHAR(13)     PRIMARY KEY,
    dni_autor   VARCHAR(9)      NOT NULL,
    titulo      VARCHAR(30)     NOT NULL,
    dni_cliente VARCHAR(9),

    FOREIGN KEY (isbn) REFERENCES ediciones(isbn),
    FOREIGN KEY (dni_autor) REFERENCES personas(dni),
    FOREIGN KEY (dni_cliente) REFERENCES personas(dni)
);

CREATE TABLE libros (
    isbn        VARCHAR(13)     PRIMARY KEY,
    dni_autor   VARCHAR(9)      NOT NULL,
    titulo      VARCHAR(30)     NOT NULL,
    dni_socio   VARCHAR(9),

    FOREIGN KEY (isbn) REFERENCES ediciones(isbn),
    FOREIGN KEY (dni_autor) REFERENCES personas(dni),
    FOREIGN KEY (dni_socio) REFERENCES personas(dni)
);

CREATE TABLE revistas (
    isbn    VARCHAR(13)     PRIMARY KEY,
    nombre  VARCHAR(20)     NOT NULL,

    FOREIGN KEY (isbn) REFERENCES ediciones(isbn)
);

CREATE TABLE ventas_revistas (
    isbn        VARCHAR(13)     PRIMARY KEY,
    dni_cliente VARCHAR(9)      NOT NULL,
    ejemplares  INTEGER         NOT NULL,

    FOREIGN KEY (isbn) REFERENCES ediciones(isbn),
    FOREIGN KEY (dni_cliente) REFERENCES personas(dni)
);