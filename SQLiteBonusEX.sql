DROP TABLE if EXISTS Players;

--a
CREATE TABLE Players (
	name TEXT NOT NULL,
   	width TEXT NOT NULL,
	height TEXT NOT NULL,
	birthday DATE NOT NULL
);

INSERT INTO Players
VALUES ('Gal', '65', '178', '1991-06-17');
INSERT INTO Players
VALUES ('Shlomo', '75', '195', '1992-09-17');
INSERT INTO Players
VALUES ('Dan', '65', '185', '1990-10-17');
INSERT INTO Players
VALUES ('Tom', '90', '191', '1994-11-17');

--b
select name FROM Players ORDER BY width DESC, height DESC;

--c
SELECT AVG(height) FROM Players where birthday BETWEEN '1990-01-01' and '1993-01-01'; 

--d
DROP VIEW IF EXISTS proportion;
CREATE VIEW proportion AS
    SELECT p.name, p.width, p.height, p.birthday, (
    ((SELECT AVG(height) FROM Players where birthday BETWEEN '1990-01-01' and '1993-01-01') / p.height)
    ) AS proportion_height
    FROM (SELECT * FROM Players where birthday BETWEEN '1990-01-01' and '1993-01-01') p;

--e
SELECT p.name ,p.width ,p.height ,p.birthday ,pr.proportion_height
FROM (SELECT * FROM Players where width > 75 and height > 185) AS p 
LEFT OUTER JOIN proportion AS pr
ON p.name == pr.name;

