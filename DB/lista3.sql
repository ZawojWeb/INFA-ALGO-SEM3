CREATE Table People (
    person_id int AUTO_INCREMENT,
    PESEL CHAR(11),
    name VARCHAR(30), 
    surrname VARCHAR(30),
    birth_day date,
    sex enum('W', 'M'),
    PRIMARY KEY (person_id),
    CONSTRAINT PeopleConstraint CHECK (PESEL >= 0)
);

CREATE Table Professions (
    profession_id int AUTO_INCREMENT,
    name VARCHAR(50), 
    salary_min FLOAT,
    salary_max FLOAT,
    PRIMARY KEY (profession_id),
    CONSTRAINT ProfessionConstraint CHECK (salary_min >= 0 AND salary_max >= 0 AND salary_min < salary_max) 
);

CREATE Table Workers (
    worker int ,
    profession_id int, 
    salary FLOAT,
    FOREIGN KEY (worker) REFERENCES People(person_id),
    FOREIGN KEY (profession_id) REFERENCES Professions(profession_id),
    CONSTRAINT WorkersConstraint CHECK (salary >= 0)
);

INSERT INTO People (PESEL,name,surrname,birth_day,sex) VALUES ('11111111111', 'Person1', 'Young1','2005-01-01' ,'M'),('11111111112', 'Person2', 'Young2','2005-01-02' ,'M'),
('11111111113', 'Person3', 'Young3','2005-01-03' ,'W'),
('11111111114', 'Person4', 'Young4','2005-01-04' ,'W'),
('11111111115', 'Person5', 'Young5','2005-01-05' ,'M');


DELIMITER $$ CREATE PROCEDURE addPeople() BEGIN DECLARE i int DEFAULT 1;
    WHILE i <= 45 do 
        BEGIN DECLARE newPESEL char(11) ;
        SET newPESEL = (SELECT FLOOR(RAND() * 99999999999));

        BEGIN DECLARE WorM int;
        SET WorM = (SELECT FLOOR(RAND()*10) % 2);
        BEGIN DECLARE setSex varChar(2);

        IF WorM = 0 THEN
            SET setSex = 'W';
        ELSE
            SET setSex = 'M';
        END IF;

        BEGIN DECLARE setDate date;
        BEGIN DECLARE minDate date;
        BEGIN DECLARE maxDate date;
        SET minDate = '1963-01-01 00:00:00';
        SET maxDate = '2003-01-01 00:00:00';
        SET setDate =  TIMESTAMPADD(SECOND, FLOOR(RAND() * TIMESTAMPDIFF(SECOND, minDate, maxDate)), minDate);

        INSERT INTO `lista_3`.`People` (PESEL,name,surrname,birth_day,sex) VALUES (newPESEL, CONCAT('Person', i + 5), CONCAT('Adult', i),setDate ,setSex);
        SET i = i + 1;
    END;END;END;END;END;END;
    END WHILE;
END ; DELIMITER;

call addPeople();

INSERT INTO People (PESEL,name,surrname,birth_day,sex) VALUES ('11111111116', 'Person1', 'Old','1950-01-11' ,'M'),
('11111111117', 'Person2', 'Old2','1952-04-23' ,'M'),
('11111111118', 'Person3', 'Old3','1952-01-12' ,'W'),
('11111111119', 'Person4', 'Old4','1951-05-04' ,'W'),
('11111111121', 'Person5', 'Old5','1949-08-05' ,'M');

--  IF there is 55 colmuns that mean threre is not 2 same  pesels
SELECT surrname, PESEL FROM People GROUP BY PESEL