CREATE Table People (
    person_id int AUTO_INCREMENT,
    PESEL CHAR(11),
    firstName VARCHAR(30), 
    surrname VARCHAR(30),
    birth_day date,
    sex enum('W', 'M'),
    PRIMARY KEY (person_id),
    CONSTRAINT PESELRR CHECK (LEFT(PESEL, 2) = DATE_FORMAT(birth_day, '%y')),
    CONSTRAINT PESELMM CHECK (SUBSTR(PESEL,3,2) = IF(YEAR(birth_day) < 2000, DATE_FORMAT(birth_day, '%m'), DATE_FORMAT(birth_day, '%m') + 20 )),
    CONSTRAINT PESELDD CHECK (SUBSTR(PESEL,5,2) = DATE_FORMAT(birth_day, '%d')),
    CONSTRAINT PESELPPPP CHECK (SUBSTR(PESEL,10,1) % 2 = IF(sex = 'M', 1, 0) ),
    CONSTRAINT PESELK CHECK (RIGHT(PESEL,1) = (10 - ( SUBSTR(PESEL,1,1) * 1 +
                                                                                                             SUBSTR(PESEL,2,1) * 3 +      
                                                                                                             SUBSTR(PESEL,3,1) * 7 +      
                                                                                                             SUBSTR(PESEL,4,1) * 9 +      
                                                                                                             SUBSTR(PESEL,5,1) * 1 +      
                                                                                                             SUBSTR(PESEL,6,1) * 3 +      
                                                                                                             SUBSTR(PESEL,7,1) * 7 +      
                                                                                                             SUBSTR(PESEL,8,1) * 9 +      
                                                                                                             SUBSTR(PESEL,9,1) * 1 +      
                                                                                                             SUBSTR(PESEL,10,1) * 3       
                                                                                                    ) % 10
                                                                                                ) %10 
                                                            )
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



DELIMITER $$ CREATE PROCEDURE addPeople(IN count INT, IN minDate DATE, IN maxDate DATE) BEGIN DECLARE i int DEFAULT 1;
    WHILE i <= count do 
      
        BEGIN DECLARE WorM int;
        SET WorM = (SELECT FLOOR(RAND()*10) % 2);
        BEGIN DECLARE setSex varChar(1);
        BEGIN DECLARE firstName varChar(20);
        BEGIN DECLARE surrName varChar(20);
        IF WorM = 0 THEN
            SET setSex = 'W';
            SET firstName = ELT(0.5 + RAND() * 6 , 'Asia', 'Lena', 'Wiktoria', 'Marcela', 'Malwina', 'Basia');
            SET surrName = ELT(0.5 + RAND() * 6 , 'Kowalska', 'Zawojska', 'Nowak', 'Iksinska', 'Rzezniczek', 'Zozla');
        ELSE
            SET setSex = 'M';
            SET firstName = ELT(0.5 + RAND() * 6 , 'Kacper', 'Tomasz', 'Bartek', 'Artur', 'Dawid', 'Piotr');
            SET surrName = ELT(0.5 + RAND() * 6 , 'Zawojski', 'Szwed', 'Grelewski', 'Trzesniewski', 'Kowalski', 'Piotrowski');
        END IF;

        BEGIN DECLARE setDate date;
        SET setDate =  TIMESTAMPADD(SECOND, FLOOR(RAND() * TIMESTAMPDIFF(SECOND, minDate, maxDate)), minDate);

        BEGIN DECLARE newPESEL char(11) ;
        SET newPESEL = generatePESEL(setDate,setSex);

        INSERT INTO `lista_3`.`People` (PESEL,firstName,surrname,birth_day,sex) VALUES (newPESEL,firstName , surrName,setDate ,setSex);
        SET i = i + 1;
    END;END;END;END;END;END;
    END WHILE;
END ; DELIMITER;

DELIMITER $$ 
    CREATE FUNCTION generatePESEL( birth_day DATE, sex  varChar(1)) RETURNS  CHAR(11) DETERMINISTIC 

        BEGIN DECLARE RR CHAR(2);
        SET RR = SUBSTRING(LEFT(YEAR(birth_day),4),3,4);

        BEGIN DECLARE MM CHAR(2);
        IF (YEAR(birth_day) < 2000) THEN
            SET MM = DATE_FORMAT(birth_day, '%m');
        ELSEIF (YEAR(birth_day) >= 2000) THEN
            SET MM = DATE_FORMAT(birth_day, '%m') + 20;
        END IF;

        BEGIN DECLARE DD CHAR(2);
        SET DD = DATE_FORMAT(birth_day, '%d');

        BEGIN DECLARE PPPP CHAR(4);
        IF (sex = 'W') THEN
            SET PPPP = CONCAT(FLOOR((RAND()*(1000-100))+100),  ELT(0.5 + RAND() * 5 , '0', '2', '4', '6', '8')  );
        ELSEIF (sex = 'M') THEN
            SET PPPP = CONCAT(FLOOR((RAND()*(1000-100))+100), ELT(0.5 + RAND() * 5 , '1', '3', '5', '7', '9')   );
        END IF;

        BEGIN DECLARE K CHAR(1);
        BEGIN DECLARE i INT;
        
        BEGIN DECLARE sum INT;
        SET sum = 0;
        SET i = 1;
        BEGIN DECLARE k_sand CHAR(10);
        SET k_sand = CONCAT(RR,MM,DD,PPPP);

        WHILE i <= 10 DO
           IF( ELT(i, '1', '3', '7', '9', '1', '3', '7', '9','1','3') * SUBSTRING(k_sand, i,1) > 10 ) THEN
                SET sum = sum + SUBSTRING( (ELT(i, '1', '3', '7', '9', '1', '3', '7', '9','1','3') * SUBSTRING(k_sand, i,1)),2,2);
            ELSE
                SET sum = sum + ((ELT(i, '1', '3', '7', '9', '1', '3', '7', '9','1','3') * SUBSTRING(k_sand, i,1)));
            END IF;
            SET i = i+1;
        END WHILE;

        IF (sum >= 10) THEN
            SET sum = SUBSTRING(sum,2,1);
        END IF;

        SET K = (10 - sum) % 10;
        BEGIN DECLARE genPESEL CHAR(11);
        SET genPESEL = CONCAT(RR,MM,DD,PPPP,K);
    RETURN genPESEL;
    END;END;END;END;END;END;END;END;END;
DELIMITER;

SELECT generatePESEL('2011-04-28', 'W');


call addPeople(45,'2003-01-01','1954-01-01');
call addPeople(5,'2021-01-01','2004-01-01');
call addPeople(5,'1954-01-01','1930-01-01');
SELECT surrname, PESEL FROM People GROUP BY PESEL;
SELECT * FROM People WHERE firstName='Malwina' AND surrName='Zawojska'

ALTER TABLE People AUTO_INCREMENT = 1;


INSERT INTO Professions (name,salary_min,salary_max) 
VALUES ("Polityk", 2000,10000),("Nauczyciel", 2000,4000),("Lekarz", 2000,8000),("Inforamtyk", 10000,400000);



DELIMITER $$ CREATE PROCEDURE  addJob()
    DECLARE CURSOR c1 IS
        SELECT person_id, birth_day, sex FROM People; 
    BEGIN DECLARE minSalary INT;
    SET minS
    BEGIN
        OPEN c1;
        LOOP
            FETCH c1 INTO person_id,birth_day,sex
            IF (c1 = NULL )THEN
                LEAVE LOOP;
            END IF;
            
            IF (YEAR(birth_day) < YEAR(CURDATE() - INTERVAL 18 YEARS ) AND YEAR(birth_day) >  YEAR(CURDATE() - INTERVAL 60 YEARS )   sex = "W" ) THEN
                INSERT INTO `lista_3`.`Workers` (worker,profession_id,salary) VALUES (person_id,firstName , surrName,setDate ,setSex);
        END LOOP;
        CLOSE c1;
    END;
DELIMITER;

DELIMITER $$ CREATE PROCEDURE  addJob()
    BEGIN
        DECLARE p_id, p_pesel, p_firstName, p_lastName, p_birthDay, p_sex, profession_id, salary,salaryMin,salaryMax VARCHAR(30);
        DECLARE addJobCursor CURSOR FOR SELECT * FROM People WHERE (YEAR(birth_day) <= YEAR(CURDATE() - INTERVAL 18 YEAR));

        OPEN addJobCursor;
        FETCH addJobCursor INTO p_id, p_pesel, p_firstName, p_lastName, p_birthDay, p_sex;
        WHILE p_id IS NOT NULL DO
            IF p_sex = "M"THEN
                IF (YEAR(p_birthDay) <=YEAR(CURDATE() - INTERVAL 65 YEAR)) THEN
                    SET profession_id = FLOOR(RAND()*3)+1;
                    SET profession_id = IF(profession_id = 3, 4 , profession_id);
                    SET salaryMin = (SELECT salary_min FROM Professions WHERE Professions.profession_id = profession_id);
                    SET salaryMax = (SELECT salary_max FROM Professions WHERE Professions.profession_id = profession_id);
                    SET salary = salaryMin  + (RAND()*(salaryMax - salaryMin));
                    INSERT INTO Workers (worker, profession_id, salary) VALUES (p_id,profession_id,salary);
                ELSE
                    SET profession_id = FLOOR(RAND()*4)+1;
                    SET salaryMin = (SELECT salary_min FROM Professions WHERE Professions.profession_id = profession_id);
                    SET salaryMax = (SELECT salary_max FROM Professions WHERE Professions.profession_id = profession_id);
                    SET salary = salaryMin  + (RAND()*(salaryMax - salaryMin));
                    INSERT INTO Workers (worker, profession_id, salary) VALUES (p_id,profession_id,salary);
                END IF;
            ELSE
                 IF (YEAR(p_birthDay) <=YEAR(CURDATE() - INTERVAL 60 YEAR)) THEN
                    SET profession_id = FLOOR(RAND()*3)+1;
                    SET profession_id = IF(profession_id = 3, 4 , profession_id);
                    SET salaryMin = (SELECT salary_min FROM Professions WHERE Professions.profession_id = profession_id);
                    SET salaryMax = (SELECT salary_max FROM Professions WHERE Professions.profession_id = profession_id);
                    SET salary = salaryMin  + (RAND()*(salaryMax - salaryMin));
                    INSERT INTO Workers (worker, profession_id, salary) VALUES (p_id,profession_id,salary);
                ELSE
                    SET profession_id = FLOOR(RAND()*4)+1;
                    SET salaryMin = (SELECT salary_min FROM Professions WHERE Professions.profession_id = profession_id);
                    SET salaryMax = (SELECT salary_max FROM Professions WHERE Professions.profession_id = profession_id);
                    SET salary = salaryMin  + (RAND()*(salaryMax - salaryMin));
                    INSERT INTO Workers (worker, profession_id, salary) VALUES (p_id,profession_id,salary);
                END IF;
            END IF;
            FETCH addJobCursor INTO p_id, p_pesel, p_firstName, p_lastName, p_birthDay, p_sex;
        END WHILE;
        CLOSE addJobCursor;
    END;
DELIMITER;

call addJob();





-- Zad4

PREPARE statment FROM 'SELECT COUNT(*) FROM People p JOIN Workers w ON p.person_id = w.worker JOIN Professions pro ON pro.profession_id = w.profession_id  WHERE p.sex LIKE \'W\' AND pro.name like ?';
SET @a = 'Programista';
EXECUTE statment USING @a; -- 5