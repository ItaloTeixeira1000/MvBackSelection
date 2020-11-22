create table estabelecimento (
	codigo bigInt(50) primary key auto_increment,
    nome varchar(50) not null,
    telefone varchar(30),
	endereco varchar(50)
)Engine=InnoDB DEFAULT CHARSET=utf8;