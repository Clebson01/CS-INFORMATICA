-- a linha abaixo criar um banco de dados
create database csinformatica;

-- a linha abaixo escolhe o banco de dados
use csinformatica;

-- a bloco de instruçoes abaixo criar uma tabela
create table tbusuario(
iduser int primary key,
usuario varchar(50) not null,
telefone varchar(15),
login varchar(15) not null unique,
senha varchar(15) not null
);
-- a comando abaixo descreve a tabala
describe tbusuario;

-- a linha abaixo insere dados na tabela (CRUD)
-- create -> insert
insert into tbusuario(iduser,usuario,telefone,login,senha)
values(1,'Clebson Souza','99999-9999','clebsonsouza','123');

-- a linha abaixo exibe os dados tabela (CRUD)
-- read -> select
select * from tbusuario;

insert into tbusuario(iduser,usuario,telefone,login,senha)
values(2,'Administrador','99999-9999','admin','admin');
insert into tbusuario(iduser,usuario,telefone,login,senha)
values(3,'Carmen Lopes','99999-9999','carmen','123');

-- a linha abaixo modifica dados na tabela (CRUD)
-- update -> update
update tbusuario set telefone='88888-8888' where iduser=2;

-- a linha abaixo apaga um registro da tabela (CRUD)
-- delete -> delete
-- delete from tbusuario where iduser=3;

select * from tbusuario;

create table tbclientes(
idcli int primary key auto_increment,
nomecli varchar(50) not null,
endcli varchar(100),
telefonecli varchar(50)
);
alter table tbclientes
add emailcli varchar(50);

describe tbclientes;

insert into tbclientes(nomecli,endcli,telefonecli,emailcli)
values('Linus Torvalds','Rua Tux, 2015','22222-2222','linux@linus.com');

select * from tbclientes;

create table tbordemserv(
ordserv int primary key auto_increment,
data_servico timestamp default current_timestamp,
equipamento varchar(150) not null,
defeito varchar(150) not null,
servico varchar(150),
tecnico varchar(30),
valor decimal(10,2),
idcli int not null,
foreign key(idcli) references tbclientes(idcli)
);

describe tbordemserv;

insert into tbordemserv(equipamento,defeito,servico,tecnico,valor,idcli)
values('Notebook','Não liga','Troca da fonte','Zé',87.50,1);

select * from tbordemserv;

-- o código abaixo traz informações de duas tabelas
select
O.ordemserv,equipamento,defeito,servico,valor,
C.nomecli,telefonecli
from tbordemserv as O
inner join tbclientes as C
on (O.idcli = C.idcli);
