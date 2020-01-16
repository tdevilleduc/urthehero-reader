-- pageId, image, text
DROP TABLE IF EXISTS page;
CREATE TABLE page(id INT PRIMARY KEY NOT NULL, image VARCHAR(100), text VARCHAR(255));
INSERT INTO page(id, image, text) VALUES(1, 'image3', 'Ulysse');
INSERT INTO page(id, image, text)  VALUES(2, 'image3', 'Dès');
INSERT INTO page(id, image, text)  VALUES(3, 'image3', 'Le roman');
INSERT INTO page(id, image, text)  VALUES(4, 'image3', 'Voyage au bout de la nuit est le premier roman de Céline, publié en 1932. Ce livre manqua de deux voix le prix Goncourt mais obtient le prix Renaudot1. Il est traduit en 37 langues2.');
INSERT INTO page(id, image, text)  VALUES(5, 'image3', 'Le roman est notamment célèbre');
INSERT INTO page(id, image, text)  VALUES(6, 'image3', 'Toutefois');
INSERT INTO page(id, image, text)  VALUES(7, 'image3', 'Madame Bovary');
INSERT INTO page(id, image, text)  VALUES(8, 'image2', 'En plein centre');