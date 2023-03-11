create table USUARIO(
	id bigserial not null primary key, 
	name varchar(100) not null, 
	age integer not null);

create table POSTAGENS(
    id bigserial not null primary key,
    texto text not null,
    data timestamp not null,
    id_usuario bigint not null references USUARIO(id)
);

create table SEGUIDORES(
    id bigserial not null primary key,
    id_usuario bigint not null  references USUARIO(id),
    id_seguidor bigint not null  references USUARIO(id)
);