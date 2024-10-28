create schema if not exists TenantRentalManagement;

create table if not exists Persons(
    personId int primary key not null auto_increment,
    name varchar(100) not null,
    document varchar(11) not null,
    email varchar(100) not null
);

insert into Persons (name, document, email) values ('Arthur', '61702316025', 'arthur@email.com');
insert into Persons (name, document, email) values ('Luigi', '72078225029', 'liz@email.com');
insert into Persons (name, document, email) values ('Ana Lice', '20109806034', 'aninha@email.com');
insert into Persons (name, document, email) values ('Liz', '57966017095', 'liz@email.com');