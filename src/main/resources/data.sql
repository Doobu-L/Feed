insert into user(id,user_id,password,email,activated)
values(1,'lgs','1111','l@2.com',1);

insert into authority (authority_name) values('ROLE_USER');
insert into authority (authority_name) values('ROLE_ADMIN');

insert into user_autority (id,autority_name) values(1,'ROLE_USER');
insert into user_autority (id,autority_name) values(1,'ROLE_ADMIN');