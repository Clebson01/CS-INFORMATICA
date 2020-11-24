use csinformatica;
describe tbusuario;
select * from tbusuario;

-- a linha abaixo adiciona um campo a tabela
alter table tbusuario add column perfil varchar(20) not null;

-- alinha abaixo remove um campo de uma tabela
alter table tbusuario drop column perfil;
update tbusuario set perfil='admin' where iduser=1;
update tbusuario set perfil='admin' where iduser=2;
update tbusuario set perfil='user' where iduser=3;

describe tbclientes;
select * from tbclientes;
select idcli,nomecli,telefonecli from tbclientes where nomecli like 'jo%';
-- a linha abaixo criar apelido aos campos da tabela
select idcli as Id,nomecli as Nome,telefonecli as Telefone from tbclientes where nomecli like 'jo%';

use csinformatica;
describe tbordemserv;
-- a linha abaixo altera a tabela adicionando um campo em uma determinada posiçãoptimize
alter table tbordemserv add tipo varchar(15) not null after data_servico;
alter table tbordemserv add situacao varchar(20) not null after tipo;
select * from tbordemserv;

-- a instrução abaixo seleciona a ordem por nome todos os clientes cadastrados
select * from tbclientes order by nomecli;

use csinformatica;
select * from tbclientes;
-- a instrução abaixo seleciona e ordena por nome todos os clientes cadastrados
select * from tbclientes order by nomecli;

