create table "cargo" ("id" bigserial not null, "order_id" bigserial not null, "courier_id" bigserial not null, "cargo_status" varchar(255), primary key ("id"));
INSERT INTO cargo (id,order_id,courier_id,cargo_status) values (1,1,3,'ASSIGNED');

create table "courier" ("id" bigserial not null, "name" varchar(255) not null, "courier_status" varchar(255), primary key ("id"));
INSERT INTO courier (id,name, courier_status) values (1,'courier1', 'FREE');

ALTER SEQUENCE cargo_id_seq RESTART WITH 2;
ALTER SEQUENCE courier_id_seq RESTART WITH 2;