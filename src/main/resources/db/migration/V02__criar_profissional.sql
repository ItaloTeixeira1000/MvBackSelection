create table profissional (
	codigo bigInt(50) primary key auto_increment,
    nome varchar(50) not null,
    telefone_celular varchar(30),
    telefone_residencial varchar(30),
	funcao varchar(50)
)Engine=InnoDB DEFAULT CHARSET=utf8;