--liquibase formatted sql

--changeset tra:1
ALTER TABLE users_aud
RENAME COLUMN firstname TO first_name;

--changeset tra:2
ALTER TABLE users_aud
RENAME COLUMN lastname TO last_name;