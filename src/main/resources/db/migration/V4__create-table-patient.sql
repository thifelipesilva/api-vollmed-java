create table patients(

    id bigint not null auto_increment,
    name varchar(100) not null,
    email varchar(100) not null unique,
    phone varchar(20) not null,
    cpf varchar(14) not null unique,
    street_address varchar(100) not null,
    neighborhood varchar(100) not null,
    zip_code varchar(9) not null,
    address_line2 varchar(100),
    number varchar(20),
    state char(2) not null,
    city varchar(100) not null,
    active tinyint not null,

    primary key(id)

);
