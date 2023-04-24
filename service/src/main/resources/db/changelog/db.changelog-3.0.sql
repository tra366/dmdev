--liquibase formatted sql

--changeset tra:1
CREATE TABLE IF NOT EXISTS revision
(
    id SERIAL PRIMARY KEY ,
    timestamp BIGINT NOT NULL
);

--changeset tra:2
CREATE TABLE IF NOT EXISTS users_aud
(
    id BIGINT,
    rev INT REFERENCES revision (id),
    revtype SMALLINT ,
    username VARCHAR(50),
    password VARCHAR(128),
    firstname VARCHAR(100),
    lastname VARCHAR(100),
    role VARCHAR(20)
    );