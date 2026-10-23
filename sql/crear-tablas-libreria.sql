use Doo
go

create table departamento(
id uniqueidentifier primary key default newid(),
nombre varchar(50) not null unique,
id_pais uniqueidentifier not null
)