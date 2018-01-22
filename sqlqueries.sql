TWORZENIE ZE ZNAKAMI POLSKIMI
CREATE TABLE Posiłek (
  idPosiłek SERIAL NOT NULL,
  nazwa VARCHAR(50) NULL,
  PRIMARY KEY(idPosiłek)
);

CREATE TABLE Produkt (
  idProdukt SERIAL NOT NULL ,
  nazwa VARCHAR(30) NOT NULL,
  kcal REAL NULL,
  białko REAL NULL,
  węglowodany REAL NULL,
  tłuszcz REAL NULL,
  PRIMARY KEY(idProdukt)
);

CREATE TABLE Uzytkownik (
  idUzytkownik SERIAL NOT NULL,
  email VARCHAR(32) NULL,
  login VARCHAR(32) NULL,
  haslo VARCHAR(32) NULL,
  PRIMARY KEY(idUzytkownik)
);

CREATE TABLE DzieńŻywienia (
  idDzieńŻywienia SERIAL NOT NULL,
  Uzytkownik_idUzytkownik INTEGER NOT NULL,
  kiedy DATE NULL,
  PRIMARY KEY(idDzieńŻywienia),
  FOREIGN KEY(Uzytkownik_idUzytkownik)
    REFERENCES Uzytkownik(idUzytkownik)
      ON DELETE NO ACTION
      ON UPDATE NO ACTION
);

CREATE TABLE MasaCiala (
  idMasa SERIAL NOT NULL ,
  Uzytkownik_idUzytkownik INTEGER NOT NULL,
  kiedy DATE NULL,
  masa FLOAT NULL,
  PRIMARY KEY(idMasa),
  FOREIGN KEY(Uzytkownik_idUzytkownik)
    REFERENCES Uzytkownik(idUzytkownik)
      ON DELETE NO ACTION
      ON UPDATE NO ACTION
);

CREATE TABLE Uzytkownik_has_Produkt (
  Uzytkownik_idUzytkownik INTEGER NOT NULL,
  Produkt_idProdukt INTEGER NOT NULL,
  PRIMARY KEY(Uzytkownik_idUzytkownik, Produkt_idProdukt),
  FOREIGN KEY(Uzytkownik_idUzytkownik)
    REFERENCES Uzytkownik(idUzytkownik)
      ON DELETE NO ACTION
      ON UPDATE NO ACTION,
  FOREIGN KEY(Produkt_idProdukt)
    REFERENCES Produkt(idProdukt)
      ON DELETE NO ACTION
      ON UPDATE NO ACTION
);

CREATE TABLE DzieńŻywienia_has_Posiłek (
  Posiłek_idPosiłek INTEGER NOT NULL,
  DzieńŻywienia_idDzieńŻywienia INTEGER NOT NULL,
  ilośćPorcji REAL NULL,
  PRIMARY KEY(Posiłek_idPosiłek, DzieńŻywienia_idDzieńŻywienia),
  FOREIGN KEY(Posiłek_idPosiłek)
    REFERENCES Posiłek(idPosiłek)
      ON DELETE NO ACTION
      ON UPDATE NO ACTION,
  FOREIGN KEY(DzieńŻywienia_idDzieńŻywienia)
    REFERENCES DzieńŻywienia(idDzieńŻywienia)
      ON DELETE NO ACTION
      ON UPDATE NO ACTION
);

CREATE TABLE Moje_Posilki (
  Uzytkownik_idUzytkownik INTEGER NOT NULL,
  Posiłek_idPosiłek INTEGER NOT NULL,
  PRIMARY KEY(Uzytkownik_idUzytkownik, Posiłek_idPosiłek),
  FOREIGN KEY(Uzytkownik_idUzytkownik)
    REFERENCES Uzytkownik(idUzytkownik)
      ON DELETE NO ACTION
      ON UPDATE NO ACTION,
  FOREIGN KEY(Posiłek_idPosiłek)
    REFERENCES Posiłek(idPosiłek)
      ON DELETE NO ACTION
      ON UPDATE NO ACTION
);

CREATE TABLE Posiłek_has_Product (
  Produkt_idProdukt INTEGER NOT NULL,
  Posiłek_idPosiłek INTEGER NOT NULL,
  masaProduktu REAL NULL,
  PRIMARY KEY(Produkt_idProdukt, Posiłek_idPosiłek),
  FOREIGN KEY(Produkt_idProdukt)
    REFERENCES Produkt(idProdukt)
      ON DELETE NO ACTION
      ON UPDATE NO ACTION,
  FOREIGN KEY(Posiłek_idPosiłek)
    REFERENCES Posiłek(idPosiłek)
      ON DELETE NO ACTION
      ON UPDATE NO ACTION
);



//usuwanie zp olskimi znakami (niektorymi)
drop table Posiłek cascade;
drop table Produkt cascade;
drop table Uzytkownik cascade;
drop table DzieńŻywienia cascade;
drop table MasaCiala cascade;
drop table Uzytkownik_has_Produkt cascade;
drop table DzieńŻywienia_has_Posiłek cascade;
drop table Moje_Posilki cascade;
drop table Posiłek_has_Product cascade;

/////usuwanie bez polskich znakow
drop table Posilek cascade;
drop table Produkt cascade;
drop table Uzytkownik cascade;
drop table DzienZywienia cascade;
drop table MasaCiala cascade;
drop table Uzytkownik_has_Produkt cascade;
drop table DzienZywienia_has_Posilek cascade;
drop table Moje_Posilki cascade;
drop table Posilek_has_Product cascade;




TWORZENIE BEZ ZNAKOW POLSKICH


CREATE TABLE Posilek (
  idPosilek SERIAL NOT NULL,
  nazwa VARCHAR(50) NULL,
  PRIMARY KEY(idPosilek)
);

CREATE TABLE Produkt (
  idProdukt SERIAL NOT NULL ,
  nazwa VARCHAR(30) NOT NULL,
  kcal REAL NULL,
  bialko REAL NULL,
  węrglowodany REAL NULL,
  tluszcz REAL NULL,
  PRIMARY KEY(idProdukt)
);

CREATE TABLE Uzytkownik (
  idUzytkownik SERIAL NOT NULL,
  email VARCHAR(32) NULL,
  login VARCHAR(32) NULL,
  haslo VARCHAR(32) NULL,
  PRIMARY KEY(idUzytkownik)
);

CREATE TABLE DzienZywienia (
  idDzienZywienia SERIAL NOT NULL,
  Uzytkownik_idUzytkownik INTEGER NOT NULL,
  kiedy DATE NULL,
  PRIMARY KEY(idDzienZywienia),
  FOREIGN KEY(Uzytkownik_idUzytkownik)
    REFERENCES Uzytkownik(idUzytkownik)
      ON DELETE NO ACTION
      ON UPDATE NO ACTION
);

CREATE TABLE MasaCiala (
  idMasa SERIAL NOT NULL ,
  Uzytkownik_idUzytkownik INTEGER NOT NULL,
  kiedy DATE NULL,
  masa FLOAT NULL,
  PRIMARY KEY(idMasa),
  FOREIGN KEY(Uzytkownik_idUzytkownik)
    REFERENCES Uzytkownik(idUzytkownik)
      ON DELETE NO ACTION
      ON UPDATE NO ACTION
);

CREATE TABLE Uzytkownik_has_Produkt (
  Uzytkownik_idUzytkownik INTEGER NOT NULL,
  Produkt_idProdukt INTEGER NOT NULL,
  PRIMARY KEY(Uzytkownik_idUzytkownik, Produkt_idProdukt),
  FOREIGN KEY(Uzytkownik_idUzytkownik)
    REFERENCES Uzytkownik(idUzytkownik)
      ON DELETE NO ACTION
      ON UPDATE NO ACTION,
  FOREIGN KEY(Produkt_idProdukt)
    REFERENCES Produkt(idProdukt)
      ON DELETE NO ACTION
      ON UPDATE NO ACTION
);

CREATE TABLE DzienZywienia_has_Posilek (
  Posilek_idPosilek INTEGER NOT NULL,
  DzienZywienia_idDzienZywienia INTEGER NOT NULL,
  iloscPorcji REAL NULL,
  PRIMARY KEY(Posilek_idPosilek, DzienZywienia_idDzienZywienia),
  FOREIGN KEY(Posilek_idPosilek)
    REFERENCES Posilek(idPosilek)
      ON DELETE NO ACTION
      ON UPDATE NO ACTION,
  FOREIGN KEY(DzienZywienia_idDzienZywienia)
    REFERENCES DzienZywienia(idDzienZywienia)
      ON DELETE NO ACTION
      ON UPDATE NO ACTION
);

CREATE TABLE Moje_Posilki (
  Uzytkownik_idUzytkownik INTEGER NOT NULL,
  Posilek_idPosilek INTEGER NOT NULL,
  PRIMARY KEY(Uzytkownik_idUzytkownik, Posilek_idPosilek),
  FOREIGN KEY(Uzytkownik_idUzytkownik)
    REFERENCES Uzytkownik(idUzytkownik)
      ON DELETE NO ACTION
      ON UPDATE NO ACTION,
  FOREIGN KEY(Posilek_idPosilek )
    REFERENCES Posilek(idPosilek )
      ON DELETE NO ACTION
      ON UPDATE NO ACTION
);

CREATE TABLE Posilek_has_Product (
  Produkt_idProdukt INTEGER NOT NULL,
  Posilek_idPosilek  INTEGER NOT NULL,
  masaProduktu REAL NULL,
  PRIMARY KEY(Produkt_idProdukt, Posilek_idPosilek ),
  FOREIGN KEY(Produkt_idProdukt)
    REFERENCES Produkt(idProdukt)
      ON DELETE NO ACTION
      ON UPDATE NO ACTION,
  FOREIGN KEY(Posilek_idPosilek )
    REFERENCES Posilek(idPosilek )
      ON DELETE NO ACTION
      ON UPDATE NO ACTION
);








////////USUWAMY I TWOZYMY NOWA BAZE DANYCH


drop table Posilek cascade;
drop table Produkt cascade;
drop table Uzytkownik cascade;
drop table DzienZywienia cascade;
drop table MasaCiala cascade;
drop table DzienZywienia_has_Posilek cascade;
drop table Moje_Posilki cascade;
drop table Moje_Produkty cascade;
drop table Posilek_has_Produkt cascade;
drop table MojeCele cascade;

CREATE TABLE Posilek (
  idPosilek SERIAL NOT NULL,
  nazwa VARCHAR(50) NULL,
  PRIMARY KEY(idPosilek)
);


CREATE TABLE Produkt (
  idProdukt SERIAL NOT NULL ,
  nazwa VARCHAR(30) NOT NULL,
  kcal REAL NULL,
  bialko REAL NULL,
  węrglowodany REAL NULL,
  tluszcz REAL NULL,
  PRIMARY KEY(idProdukt)
);

CREATE TABLE Uzytkownik (
  idUzytkownik SERIAL NOT NULL,
  email VARCHAR(32) NULL,
  login VARCHAR(32) NULL,
  haslo VARCHAR(32) NULL,
  PRIMARY KEY(idUzytkownik)
);



CREATE TABLE DzienZywienia (
  idDzienZywienia SERIAL NOT NULL,
  Uzytkownik_idUzytkownik INTEGER NOT NULL,
  kiedy DATE NULL,
  PRIMARY KEY(idDzienZywienia),
  FOREIGN KEY(Uzytkownik_idUzytkownik)
    REFERENCES Uzytkownik(idUzytkownik)
      ON DELETE NO ACTION
      ON UPDATE NO ACTION
);





CREATE TABLE MasaCiala (
  idMasa SERIAL NOT NULL ,
  Uzytkownik_idUzytkownik INTEGER NOT NULL,
  kiedy DATE NULL,
  masa FLOAT NULL,
  PRIMARY KEY(idMasa),
  FOREIGN KEY(Uzytkownik_idUzytkownik)
    REFERENCES Uzytkownik(idUzytkownik)
      ON DELETE NO ACTION
      ON UPDATE NO ACTION
);



CREATE TABLE Posilek_has_Produkt (
  Posilek_idPosilek  INTEGER NOT NULL,
  Produkt_idProdukt INTEGER NOT NULL,
  masaProduktu REAL NULL,																		
  PRIMARY KEY(Produkt_idProdukt, Posilek_idPosilek ),
  FOREIGN KEY(Produkt_idProdukt)
    REFERENCES Produkt(idProdukt)
      ON DELETE NO ACTION
      ON UPDATE NO ACTION,
  FOREIGN KEY(Posilek_idPosilek )
    REFERENCES Posilek(idPosilek )
      ON DELETE NO ACTION
      ON UPDATE NO ACTION
);



CREATE TABLE DzienZywienia_has_Posilek (
  Posilek_idPosilek INTEGER NOT NULL,
  DzienZywienia_idDzienZywienia INTEGER NOT NULL,
  iloscPorcji REAL NULL,
  PRIMARY KEY(Posilek_idPosilek, DzienZywienia_idDzienZywienia),
  FOREIGN KEY(Posilek_idPosilek)
    REFERENCES Posilek(idPosilek)
      ON DELETE NO ACTION
      ON UPDATE NO ACTION,
  FOREIGN KEY(DzienZywienia_idDzienZywienia)
    REFERENCES DzienZywienia(idDzienZywienia)
      ON DELETE NO ACTION
      ON UPDATE NO ACTION
);

CREATE TABLE MojePosilki (
  Uzytkownik_idUzytkownik INTEGER NOT NULL,
  Posilek_idPosilek INTEGER NOT NULL,
  PRIMARY KEY(Uzytkownik_idUzytkownik, Posilek_idPosilek),
  FOREIGN KEY(Uzytkownik_idUzytkownik)
    REFERENCES Uzytkownik(idUzytkownik)
      ON DELETE NO ACTION
      ON UPDATE NO ACTION,
  FOREIGN KEY(Posilek_idPosilek )
    REFERENCES Posilek(idPosilek )
      ON DELETE NO ACTION
      ON UPDATE NO ACTION
);



CREATE TABLE MojeProdukty (
  Uzytkownik_idUzytkownik INTEGER NOT NULL,
  Produkt_idProdukt INTEGER NOT NULL,
  PRIMARY KEY(Uzytkownik_idUzytkownik, Produkt_idProdukt),
  FOREIGN KEY(Uzytkownik_idUzytkownik)
    REFERENCES Uzytkownik(idUzytkownik)
      ON DELETE NO ACTION
      ON UPDATE NO ACTION,
  FOREIGN KEY(Produkt_idProdukt)
    REFERENCES Produkt(idProdukt)
      ON DELETE NO ACTION
      ON UPDATE NO ACTION
);

CREATE TABLE MojeCele (
  idMojeCele INTEGER UNSIGNED NOT NULL AUTO_INCREMENT,
  Waga REAL NULL,
  kcal REAL NULL,
  bialko REAL NULL,
  weglowodany REAL NULL,
  tluszcz REAL NULL,
  PRIMARY KEY(idMojeCele)
);






INSERT INTO Posilek VALUES(1,'Obiad1');



INSERT INTO Produkt VALUES(1,'filet z piersi kurczaka',116.00,21.80,0.00,3.20 );
INSERT INTO Produkt VALUES(2,'makaron pelnoziarnisty',335,13.10,67.90,1.90 );
INSERT INTO Produkt VALUES(3,'pesto',401,4.7,7.6,39 );




INSERT INTO Uzytkownik VALUES(1,'mojemail@gmail.com','mojlogin','mojehaslo');




INSERT INTO DzienZywienia VALUES(1,1,'2018-01-14');



INSERT INTO MasaCiala VALUES(1,1,'2018-01-14',83.0);




INSERT INTO Posilek_has_Produkt VALUES(1,1,150);
INSERT INTO Posilek_has_Produkt VALUES(1,2,170);
INSERT INTO Posilek_has_Produkt VALUES(1,3, 40);



INSERT INTO DzienZywienia_has_Posilek VALUES(1,1,1);


INSERT INTO MojePosilki VALUES(1,1);


INSERT INTO MojeProdukty VALUES(1,1);
INSERT INTO MojeProdukty VALUES(1,2);





////////////////////////////////////////////////////////////////////////UPDATE


drop table Posilek cascade;
drop table Produkt cascade;
drop table Uzytkownik cascade;
drop table DzienZywienia cascade;
drop table MasaCiala cascade;
drop table DzienZywienia_has_Posilek cascade;
drop table MojePosilki cascade;
drop table MojeProdukty cascade;
drop table Posilek_has_Produkt cascade;
drop table MojeCele cascade;




CREATE TABLE Posilek (
  idPosilek SERIAL NOT NULL,
  nazwa VARCHAR(50) NULL,
  PRIMARY KEY(idPosilek)
);


CREATE TABLE Produkt (
  idProdukt SERIAL NOT NULL ,
  nazwa VARCHAR(30) NOT NULL,
  kcal REAL NULL,
  bialko REAL NULL,
  weglowodany REAL NULL,
  tluszcze REAL NULL,
  PRIMARY KEY(idProdukt)
);

CREATE TABLE Uzytkownik (
  idUzytkownik SERIAL NOT NULL,
  email VARCHAR(32) NULL,
  login VARCHAR(32) NULL,
  haslo VARCHAR(32) NULL,
  PRIMARY KEY(idUzytkownik)
);



CREATE TABLE DzienZywienia (
  idDzienZywienia SERIAL NOT NULL,
  Uzytkownik_idUzytkownik INTEGER NOT NULL,
  kiedy DATE NULL,
  PRIMARY KEY(idDzienZywienia),
  FOREIGN KEY(Uzytkownik_idUzytkownik)
    REFERENCES Uzytkownik(idUzytkownik)
      ON DELETE NO ACTION
      ON UPDATE NO ACTION
);





CREATE TABLE MasaCiala (
  idMasa SERIAL NOT NULL ,
  Uzytkownik_idUzytkownik INTEGER NOT NULL,
  kiedy DATE NULL,
  masa FLOAT NULL,
  PRIMARY KEY(idMasa),
  FOREIGN KEY(Uzytkownik_idUzytkownik)
    REFERENCES Uzytkownik(idUzytkownik)
      ON DELETE NO ACTION
      ON UPDATE NO ACTION
);



CREATE TABLE Posilek_has_Produkt (
  Posilek_idPosilek  INTEGER NOT NULL,
  Produkt_idProdukt INTEGER NOT NULL,
  masaProduktu REAL NULL,																		
  PRIMARY KEY(Produkt_idProdukt, Posilek_idPosilek ),
  FOREIGN KEY(Produkt_idProdukt)
    REFERENCES Produkt(idProdukt)
      ON DELETE NO ACTION
      ON UPDATE NO ACTION,
  FOREIGN KEY(Posilek_idPosilek )
    REFERENCES Posilek(idPosilek )
      ON DELETE NO ACTION
      ON UPDATE NO ACTION
);



CREATE TABLE DzienZywienia_has_Posilek (
  Posilek_idPosilek INTEGER NOT NULL,
  DzienZywienia_idDzienZywienia INTEGER NOT NULL,
  iloscPorcji REAL NULL,
  PRIMARY KEY(Posilek_idPosilek, DzienZywienia_idDzienZywienia),
  FOREIGN KEY(Posilek_idPosilek)
    REFERENCES Posilek(idPosilek)
      ON DELETE NO ACTION
      ON UPDATE NO ACTION,
  FOREIGN KEY(DzienZywienia_idDzienZywienia)
    REFERENCES DzienZywienia(idDzienZywienia)
      ON DELETE NO ACTION
      ON UPDATE NO ACTION
);

CREATE TABLE MojePosilki (
  Uzytkownik_idUzytkownik INTEGER NOT NULL,
  Posilek_idPosilek INTEGER NOT NULL,
  PRIMARY KEY(Uzytkownik_idUzytkownik, Posilek_idPosilek),
  FOREIGN KEY(Uzytkownik_idUzytkownik)
    REFERENCES Uzytkownik(idUzytkownik)
      ON DELETE NO ACTION
      ON UPDATE NO ACTION,
  FOREIGN KEY(Posilek_idPosilek )
    REFERENCES Posilek(idPosilek )
      ON DELETE NO ACTION
      ON UPDATE NO ACTION
);



CREATE TABLE MojeProdukty (
  Uzytkownik_idUzytkownik INTEGER NOT NULL,
  Produkt_idProdukt INTEGER NOT NULL,
  PRIMARY KEY(Uzytkownik_idUzytkownik, Produkt_idProdukt),
  FOREIGN KEY(Uzytkownik_idUzytkownik)
    REFERENCES Uzytkownik(idUzytkownik)
      ON DELETE NO ACTION
      ON UPDATE NO ACTION,
  FOREIGN KEY(Produkt_idProdukt)
    REFERENCES Produkt(idProdukt)
      ON DELETE NO ACTION
      ON UPDATE NO ACTION
);


CREATE TABLE MojeCele (
  idMojeCele SERIAL NOT NULL,
  Waga REAL NULL,
  kcal REAL NULL,
  bialko REAL NULL,
  weglowodany REAL NULL,
  tluszcz REAL NULL,
  PRIMARY KEY(idMojeCele)

);





INSERT INTO Posilek VALUES(1,'Obiad1');



INSERT INTO Produkt VALUES(1,'filet z piersi kurczaka',116.00,21.80,0.00,3.20 );
INSERT INTO Produkt VALUES(2,'makaron pelnoziarnisty',335,13.10,67.90,1.90 );
INSERT INTO Produkt VALUES(3,'pesto',401,4.7,7.6,39 );




INSERT INTO Uzytkownik VALUES(1,'mojemail@gmail.com','mojlogin','mojehaslo');




INSERT INTO DzienZywienia VALUES(1,1,'2018-01-14');



INSERT INTO MasaCiala VALUES(1,1,'2018-01-14',83.0);




INSERT INTO Posilek_has_Produkt VALUES(1,1,150);
INSERT INTO Posilek_has_Produkt VALUES(1,2,170);
INSERT INTO Posilek_has_Produkt VALUES(1,3, 40);



INSERT INTO DzienZywienia_has_Posilek VALUES(1,1,1);


INSERT INTO MojePosilki VALUES(1,1);


INSERT INTO MojeProdukty VALUES(1,1);
INSERT INTO MojeProdukty VALUES(1,2);

INSERT INTO MojeCele VALUES(1,82.5,3000,150,400,91,1);







select * from  Posilek ;
select * from Produkt ;
select * from  Uzytkownik ;
select * from DzienZywienia ;
select * from MasaCiala ;
select * from DzienZywienia_has_Posilek ;
select * from MojePosilki ;
select * from MojeProdukty ;
select * from Posilek_has_Produkt ;
select * from MojeCele ;





drop view PosilekProdukt;
create view PosilekProdukt AS select Posilek.nazwa as "Nazwa Posilku" ,masaproduktu,Produkt.nazwa,kcal,bialko,weglowodany,tluszcze from Posilek INNER JOIN Posilek_has_Produkt  on idPosilek=Posilek_idPosilek  INNER JOIN Produkt on Produkt_idProdukt=idProdukt; 


select * from PosilekProdukt;





CREATE OR REPLACE FUNCTION check_password(uname TEXT, pass TEXT)
RETURNS BOOLEAN AS $$
DECLARE passed BOOLEAN;
BEGIN
        SELECT  (pwd = $2) INTO passed
        FROM    Uzytkownik
        WHERE   username = $1;

        RETURN passed;
END;
$$  LANGUAGE plpgsql;


CREATE OR REPLACE FUNCTION PosilekZProduktem (nazwaProduktu TEXT) 
 RETURNS TABLE (
 "Nazwa Posilku" VARCHAR,
 masaproduktu REAL,
 nazwa VARCHAR,
 kcal REAL,
 bialko REAL,
 weglowodany REAL,
 tluszcze REAL
) 
AS $$
BEGIN
 RETURN QUERY SELECT
 *
 FROM
 PosilekProdukt as PP
 WHERE
 PP."Nazwa Posilku"= 
 (SELECT 
 PosilekProdukt."Nazwa Posilku" 
 from 
 PosilekProdukt
 where 
 PosilekProdukt.nazwa LIKE concat('%',nazwaProduktu,'%'));
END; $$ 
 
LANGUAGE 'plpgsql';

Select * from PosilekZProduktem('pest');


//////////////////////////////////te widoki i funkcje zwracaja wszystko ( potrzeba do obiektowego podejscia)
drop view PosilekProduktWszystkieKolumny;
create view PosilekProduktWszystkieKolumny AS select idPosilek, Posilek.nazwa as "Nazwa Posilku" ,masaproduktu,idProdukt,Produkt.nazwa,kcal,bialko,weglowodany,tluszcze from Posilek INNER JOIN Posilek_has_Produkt  on idPosilek=Posilek_idPosilek  INNER JOIN Produkt on Produkt_idProdukt=idProdukt; 


select * from PosilekProduktWszystkieKolumny;


CREATE OR REPLACE FUNCTION PosilekZProduktemWszystkieKolumny (nazwaProduktu TEXT) 
 RETURNS TABLE (
 idPosilek INTEGER,
 "Nazwa Posilku" VARCHAR,
 masaproduktu REAL,
 idProdukt INTEGER,
 nazwa VARCHAR,
 kcal REAL,
 bialko REAL,
 weglowodany REAL,
 tluszcze REAL
) 
AS $$
BEGIN
 RETURN QUERY SELECT
 *
 FROM
 PosilekProduktWszystkieKolumny as PP
 WHERE
 PP."Nazwa Posilku"= 
 (SELECT 
 PosilekProduktWszystkieKolumny."Nazwa Posilku" 
 from 
 PosilekProduktWszystkieKolumny
 where 
 PosilekProduktWszystkieKolumny.nazwa LIKE concat('%',nazwaProduktu,'%'));
END; $$ 
 
LANGUAGE 'plpgsql';

Select * from PosilekZProduktemWszystkieKolumny('pest');


//////////////////////////////////////////jakby ten na gorze nie dzialal, to ten dziala na 100
drop view PosilekProduktWszystkieKolumny;
create view PosilekProduktWszystkieKolumny AS select idPosilek, Posilek.nazwa as "Nazwa Posilku" ,masaproduktu,idProdukt,Produkt.nazwa,kcal,bialko,weglowodany,tluszcze from Posilek INNER JOIN Posilek_has_Produkt  on idPosilek=Posilek_idPosilek  INNER JOIN Produkt on Produkt_idProdukt=idProdukt; 


select * from PosilekProduktWszystkieKolumny;


CREATE OR REPLACE FUNCTION PosilekZProduktemWszystkieKolumny (nazwaProduktu TEXT) 
 RETURNS TABLE (
 idPosilek INTEGER,
 "Nazwa Posilku" VARCHAR,
 masaproduktu REAL,
 idProdukt INTEGER,
 nazwa VARCHAR,
 kcal REAL,
 bialko REAL,
 weglowodany REAL,
 tluszcze REAL
) 
AS $$
BEGIN
 RETURN QUERY SELECT
 *
 FROM
 PosilekProduktWszystkieKolumny as PP
 WHERE
 PP."Nazwa Posilku"= 
 (SELECT 
 PosilekProduktWszystkieKolumny."Nazwa Posilku" 
 from 
 PosilekProduktWszystkieKolumny
 where 
 PosilekProduktWszystkieKolumny.nazwa LIKE concat('%',nazwaProduktu,'%'));
END; $$ 
 
LANGUAGE 'plpgsql';

Select * from PosilekZProduktemWszystkieKolumny('pest');



drop view MojeProduktyDlaId;

create view MojeProduktyDlaId AS select  idProdukt,nazwa,kcal,bialko,weglowodany,tluszcze, idUzytkownik
 from Uzytkownik INNER JOIN MojeProdukty  on idUzytkownik=Uzytkownik_idUzytkownik  
 INNER JOIN Produkt on Produkt_idProdukt=idProdukt; 


select * from MojeProduktyDlaId where idUzytkownik=1;

///////////////////////////////jakis syf


drop view MojPosilekProduktWszystkieKolumny;
create view MojPosilekProduktWszystkieKolumny 
AS select idUzytkownik, idPosilek, Posilek.nazwa as "Nazwa Posilku" ,masaproduktu,idProdukt,Produkt.nazwa,kcal,bialko,weglowodany,tluszcze 
from Uzytkownik
INNER JOIN MojePosilki  on Uzytkownik_idUzytkownik=idUzytkownik
INNER JOIN  Posilek on idPosilek=Posilek_idPosilek   
INNER JOIN Posilek_has_Produkt  on idPosilek=Posilek_has_Produkt.Posilek_idPosilek  
INNER JOIN Produkt on Produkt_idProdukt=idProdukt; 

select * from MojPosilekProduktWszystkieKolumny;




select * from Produkt;

select * from MasaCIala;
///////////////////////////////////////koniec jakis syf
/////////////////////zmieniamy tabele mojecele
alter table MojeCele ADD Column Uzytkownik_idUzytkownik Integer References Uzytkownik(idUzytkownik);

ALTER TABLE Produkt ADD UNIQUE (nazwa);
ALTER TABLE Posilek ADD UNIQUE (nazwa);
ALTER TABLE DzienZywienia ADD UNIQUE (kiedy);
ALTER TABLE Uzytkownik ADD UNIQUE (email);
ALTER TABLE Uzytkownik ADD UNIQUE (login);
ALTER TABLE MojeCele ADD UNIQUE (Uzytkownik_idUzytkownik);



//////////////////////////////////////////////

delete  from MasaCiala ;
delete  from MojeCele ;
delete  from DzienZywienia_has_Posilek ;
delete  from DzienZywienia ;
delete  from MojePosilki ;
delete  from MojeProdukty ;
delete  from Posilek_has_Produkt ;
delete  from  Posilek ;
delete  from Produkt ;
delete  from  Uzytkownik ;


ALTER TABLE Produkt ADD UNIQUE (nazwa);
ALTER TABLE Posilek ADD UNIQUE (nazwa);
ALTER TABLE DzienZywienia ADD UNIQUE (kiedy);
ALTER TABLE Uzytkownik ADD UNIQUE (email);
ALTER TABLE Uzytkownik ADD UNIQUE (login);
ALTER TABLE MojeCele ADD UNIQUE (Uzytkownik_idUzytkownik);



////////////////////////////////////////////////komenda do JDBC 
String SQL = "INSERT INTO DzienZywienia (Uzytkownik_idUzytkownik,kiedy) SELECT ?,?"+
                 "WHERE NOT EXISTS (SELECT kiedy FROM DzienZywienia WHERE kiedy = ?);";
				 
				 //////////////////////////////////////
				 
				 
				 
				 
drop view zjedzonednia;
CREATE VIEW ZjedzoneDnia AS SELECT Uzytkownik_idUzytkownik as uID, kiedy, SUM(kcal) as kcal, SUM(bialko)as bialko , SUM(weglowodany)as weglowodany, SUM(tluszcze)as tluszcze FROM Produkt INNER JOIN Posilek_has_Produkt ON idProdukt=Produkt_idProdukt INNER JOIN Posilek ON Posilek_idPosilek = idPosilek INNER JOIN DzienZywienia_has_Posilek as dzhp ON dzhp.Posilek_idPosilek=Posilek.idPosilek
INNER JOIN DzienZywienia ON DzienZywienia_idDzienZywienia=idDzienZywienia group by kiedy,Uzytkownik_idUzytkownik order by kiedy;
select * from zjedzonednia where kiedy='2018-01-14';






CREATE OR REPLACE FUNCTION sprawdzProdukt() RETURNS trigger AS $sprawdzProdukt$
    BEGIN
        IF NEW.nazwa IS NULL THEN
            RAISE EXCEPTION 'nazwa nie moze byc null';
        END IF;
        IF NEW.kcal IS NULL THEN
            RAISE EXCEPTION 'kcal nie moze byc null';
        END IF;
        IF NEW.bialko IS NULL THEN
            RAISE EXCEPTION 'bialko nie moze byc null';
        END IF;
        IF NEW.weglowodany IS NULL THEN
            RAISE EXCEPTION 'weglowodany nie moga byc null';
        END IF;
        IF NEW.tluszcze IS NULL THEN
            RAISE EXCEPTION 'tluszcze nie moga byc null';
        END IF;

        IF NEW.kcal>10000  THEN
            RAISE EXCEPTION 'kcal nie moze byc null';
        END IF;
        IF NEW.bialko >10000 THEN
            RAISE EXCEPTION 'bialko nie moze byc null';
        END IF;
        IF NEW.weglowodany >10000 THEN
            RAISE EXCEPTION 'weglowodany nie moga byc null';
        END IF;
        IF NEW.tluszcze >10000 THEN
            RAISE EXCEPTION 'tluszcze nie moga byc null';
        END IF;

    END;
$sprawdzProdukt$ LANGUAGE plpgsql;

CREATE TRIGGER sprawdzProdukt BEFORE INSERT OR UPDATE ON Produkt
    FOR EACH ROW EXECUTE PROCEDURE sprawdzProdukt();