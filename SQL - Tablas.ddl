-- Generado por Oracle SQL Developer Data Modeler 20.2.0.167.1538
--   en:        2020-07-06 13:00:00 CLT
--   sitio:      Oracle Database 11g
--   tipo:      Oracle Database 11g



-- predefined type, no DDL - MDSYS.SDO_GEOMETRY

-- predefined type, no DDL - XMLTYPE

CREATE TABLE detta_accidentes (
    id                   NUMBER NOT NULL,
    fecha                DATE NOT NULL,
    hora                 VARCHAR2(5 CHAR) NOT NULL,
    direccion            VARCHAR2(250 CHAR) NOT NULL,
    lugar                VARCHAR2(250 CHAR) NOT NULL,
    circunstancia        VARCHAR2(250 CHAR) NOT NULL,
    detalles             VARCHAR2(4000 CHAR) NOT NULL,
    clasificacion        VARCHAR2(10 CHAR) NOT NULL,
    tipo                 VARCHAR2(10 CHAR) NOT NULL,
    medio_prueba         VARCHAR2(25 CHAR) NOT NULL,
    fecha_registro       TIMESTAMP DEFAULT current_timestamp NOT NULL,
    fecha_actualizacion  TIMESTAMP DEFAULT current_timestamp NOT NULL,
    empresa_id           NUMBER NOT NULL
);

ALTER TABLE detta_accidentes ADD CONSTRAINT accidentes_pk PRIMARY KEY ( id );

CREATE TABLE detta_capacitaciones (
    id              NUMBER NOT NULL,
    fecha           DATE NOT NULL,
    direccion       VARCHAR2(200 CHAR) NOT NULL,
    tema            VARCHAR2(150 CHAR) NOT NULL,
    participantes   NUMBER NOT NULL,
    realizada       CHAR(1) NOT NULL,
    profesional_id  NUMBER NOT NULL,
    empresa_id      NUMBER NOT NULL
);

ALTER TABLE detta_capacitaciones ADD CONSTRAINT capacitaciones_pk PRIMARY KEY ( id );

CREATE TABLE detta_empresas (
    id                   NUMBER NOT NULL,
    nombre               VARCHAR2(150 CHAR) NOT NULL,
    rut                  VARCHAR2(25 CHAR) NOT NULL,
    direccion            VARCHAR2(250 CHAR) NOT NULL,
    telefono             VARCHAR2(25 CHAR) NOT NULL,
    email                VARCHAR2(100 CHAR) NOT NULL,
    giro                 VARCHAR2(250 CHAR) NOT NULL,
    trabajadores         NUMBER NOT NULL,
    tipo                 VARCHAR2(35 CHAR) NOT NULL,
    password             VARCHAR2(250 CHAR) NOT NULL,
    fecha_registro       TIMESTAMP DEFAULT current_timestamp NOT NULL,
    fecha_actualizacion  TIMESTAMP DEFAULT current_timestamp NOT NULL,
    profesional_id       NUMBER NOT NULL
);

ALTER TABLE detta_empresas ADD CONSTRAINT detta_empresas_pk PRIMARY KEY ( id );

ALTER TABLE detta_empresas ADD CONSTRAINT empresas_uq_email UNIQUE ( email );

ALTER TABLE detta_empresas ADD CONSTRAINT empresas_uq_rut UNIQUE ( rut );

CREATE TABLE detta_profesionales (
    id               NUMBER NOT NULL,
    nombre           VARCHAR2(100 CHAR) NOT NULL,
    email            VARCHAR2(100 CHAR) NOT NULL,
    telefono         VARCHAR2(25 CHAR) NOT NULL,
    estado_contrato  VARCHAR2(25 CHAR) NOT NULL,
    password         VARCHAR2(250 CHAR) NOT NULL
);

ALTER TABLE detta_profesionales ADD CONSTRAINT profesionales_pk PRIMARY KEY ( id );

ALTER TABLE detta_profesionales ADD CONSTRAINT profesionales_uq_email UNIQUE ( email );

ALTER TABLE detta_accidentes
    ADD CONSTRAINT accidente_empresa_fk FOREIGN KEY ( empresa_id )
        REFERENCES detta_empresas ( id );

ALTER TABLE detta_capacitaciones
    ADD CONSTRAINT capacitacion_empresa_fk FOREIGN KEY ( empresa_id )
        REFERENCES detta_empresas ( id );

ALTER TABLE detta_capacitaciones
    ADD CONSTRAINT capacitacion_profesional_fk FOREIGN KEY ( profesional_id )
        REFERENCES detta_profesionales ( id );

ALTER TABLE detta_empresas
    ADD CONSTRAINT empresa_profesional_fk FOREIGN KEY ( profesional_id )
        REFERENCES detta_profesionales ( id );

CREATE SEQUENCE detta_accidentes_id_seq START WITH 1 NOCACHE ORDER;

CREATE OR REPLACE TRIGGER detta_accidentes_id_trg BEFORE
    INSERT ON detta_accidentes
    FOR EACH ROW
    WHEN ( new.id IS NULL )
BEGIN
    :new.id := detta_accidentes_id_seq.nextval;
END;
/

CREATE SEQUENCE detta_capacitaciones_id_seq START WITH 1 NOCACHE ORDER;

CREATE OR REPLACE TRIGGER detta_capacitaciones_id_trg BEFORE
    INSERT ON detta_capacitaciones
    FOR EACH ROW
BEGIN
    :new.id := detta_capacitaciones_id_seq.nextval;
END;
/

CREATE SEQUENCE detta_empresas_id_seq START WITH 1 NOCACHE ORDER;

CREATE OR REPLACE TRIGGER detta_empresas_id_trg BEFORE
    INSERT ON detta_empresas
    FOR EACH ROW
BEGIN
    :new.id := detta_empresas_id_seq.nextval;
END;
/

CREATE SEQUENCE detta_profesionales_id_seq START WITH 1 NOCACHE ORDER;

CREATE OR REPLACE TRIGGER detta_profesionales_id_trg BEFORE
    INSERT ON detta_profesionales
    FOR EACH ROW
BEGIN
    :new.id := detta_profesionales_id_seq.nextval;
END;
/



-- Informe de Resumen de Oracle SQL Developer Data Modeler: 
-- 
-- CREATE TABLE                             4
-- CREATE INDEX                             0
-- ALTER TABLE                             11
-- CREATE VIEW                              0
-- ALTER VIEW                               0
-- CREATE PACKAGE                           0
-- CREATE PACKAGE BODY                      0
-- CREATE PROCEDURE                         0
-- CREATE FUNCTION                          0
-- CREATE TRIGGER                           4
-- ALTER TRIGGER                            0
-- CREATE COLLECTION TYPE                   0
-- CREATE STRUCTURED TYPE                   0
-- CREATE STRUCTURED TYPE BODY              0
-- CREATE CLUSTER                           0
-- CREATE CONTEXT                           0
-- CREATE DATABASE                          0
-- CREATE DIMENSION                         0
-- CREATE DIRECTORY                         0
-- CREATE DISK GROUP                        0
-- CREATE ROLE                              0
-- CREATE ROLLBACK SEGMENT                  0
-- CREATE SEQUENCE                          4
-- CREATE MATERIALIZED VIEW                 0
-- CREATE MATERIALIZED VIEW LOG             0
-- CREATE SYNONYM                           0
-- CREATE TABLESPACE                        0
-- CREATE USER                              0
-- 
-- DROP TABLESPACE                          0
-- DROP DATABASE                            0
-- 
-- REDACTION POLICY                         0
-- 
-- ORDS DROP SCHEMA                         0
-- ORDS ENABLE SCHEMA                       0
-- ORDS ENABLE OBJECT                       0
-- 
-- ERRORS                                   0
-- WARNINGS                                 0
