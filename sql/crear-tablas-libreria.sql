use Doo
go

create table departamento(
id uniqueidentifier primary key default newsequentialid(),
nombre varchar(50) not null unique,
id_pais uniqueidentifier not null,
constraint fk_pais foreign key (id_pais) references Pais(id)

)
insert into departamento(id, nombre, id_pais) values
('01A168D5-BAF5-7A8E-8C0F-EDF2DC411F6D','Antioquia' , '01A168D5-BAF5-7A8E-8C0F-EDF2DC411F6C' )

