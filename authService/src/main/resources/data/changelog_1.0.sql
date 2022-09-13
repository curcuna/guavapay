create table "person" ("id" bigserial not null, "password" varchar(255), "username" varchar(255), primary key ("id"));
create table "person_role" ("person_id" int8 not null, "role" varchar(255));
alter table "person_role" add constraint "person_role_person_fk" foreign key ("person_id") references "person";

INSERT INTO person (id,username,password) values ('1', 'person1', 'pass1');
INSERT INTO person_role (person_id,role) values ('1', 'ROLE_USER');


INSERT INTO person (id,username,password) values ('2', 'person2', 'pass2');
INSERT INTO person_role (person_id,role) values ('2', 'ROLE_ADMIN');


INSERT INTO person (id,username,password) values ('3', 'person3', 'pass3');
INSERT INTO person_role (person_id,role) values ('3', 'ROLE_COURIER');

ALTER SEQUENCE person_id_seq RESTART WITH 4;