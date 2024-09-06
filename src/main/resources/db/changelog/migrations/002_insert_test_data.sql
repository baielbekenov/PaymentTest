--liquibase formatted sql
--changeset author:angryelizar

-- Add default role
insert into AUTHORITIES(AUTHORITY)
values ('FULL');

-- Add default user
-- Password: qwerty123451
insert into USERS(PHONE, USERNAME, PASSWORD, ENABLED, AUTHORITY_ID)
values ('+996708801562', 'angryelizar', '$2a$12$5OnRPe4Hyg2IZA6R1ch3oeg2EgzpblGEb5AF9TA3/Ek3GanD0XtBy', true,
        (select id from AUTHORITIES where AUTHORITY = 'FULL'));

-- Add account for user
insert into ACCOUNTS(user_id, balance)
values (select id from USERS where PHONE = '+996708801562', 8);

-- Add transaction for make changes on account
insert into TRANSACTIONS(account_id, amount, execution_time)
values (select id from ACCOUNTS where user_id = (select id from USERS where PHONE = '+996708801562'), 8,
        '2024-09-07')