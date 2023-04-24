--liquibase formatted sql

--changeset tra:1
ALTER TABLE users_aud
ALTER COLUMN id TYPE INT;

