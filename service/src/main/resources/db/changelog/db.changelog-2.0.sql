--liquibase formatted sql

--changeset tra:1
ALTER TABLE users
ALTER COLUMN password TYPE VARCHAR(128);

 --changeset tra:2
 ALTER TABLE users
 ALTER COLUMN password SET DEFAULT '{noop}12345';