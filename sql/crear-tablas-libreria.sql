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


CREATE TABLE SPRING_SESSION (
                                PRIMARY_ID CHAR(36) NOT NULL PRIMARY KEY ,
                                SESSION_ID CHAR(36) NOT NULL,
                                CREATION_TIME BIGINT NOT NULL,
                                LAST_ACCESS_TIME BIGINT NOT NULL,
                                MAX_INACTIVE_INTERVAL INT NOT NULL,
                                EXPIRY_TIME BIGINT NOT NULL,
                                PRINCIPAL_NAME VARCHAR(100)
)
CREATE UNIQUE INDEX SPRING_SESSION_IX1 ON SPRING_SESSION (SESSION_ID);
CREATE INDEX SPRING_SESSION_IX2 ON SPRING_SESSION (EXPIRY_TIME);

CREATE TABLE SPRING_SESSION_ATTRIBUTES (
                                           SESSION_PRIMARY_ID CHAR(36) NOT NULL,
                                           ATTRIBUTE_NAME VARCHAR(200) NOT NULL,
                                           ATTRIBUTE_BYTES IMAGE NOT NULL,
                                           CONSTRAINT SPRING_SESSION_ATTRIBUTES_PK PRIMARY KEY (SESSION_PRIMARY_ID, ATTRIBUTE_NAME),
                                           CONSTRAINT SPRING_SESSION_ATTRIBUTES_FK FOREIGN KEY (SESSION_PRIMARY_ID) REFERENCES SPRING_SESSION(PRIMARY_ID) ON DELETE CASCADE
)

)