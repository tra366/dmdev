insert into users (id, first_name, last_name, role, username, password)
values (1, 'Ivan', 'Ivanov', 'BUILDER', 'IIVanov', '{noop}12345'),
       (2, 'Petr', 'Petrov', 'BUILDER', 'PPetrov', '{noop}12345'),
       (3, 'Admin', 'Admin', 'ADMIN', 'Administrator', '{noop}12345');
select SETVAL('users_id_seq', (select max(id) from users));

