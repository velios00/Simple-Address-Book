--Utenti
INSERT INTO R_USER VALUES ('simoneveniero00@gmail.com', 'simone00', 'velios', NULL),
('gianmarcolembo@gmail.com', 'password', 'mandr96', NULL);

--Contatti
INSERT INTO CONTACT VALUES (1, 'GIANMARCO', 'LEMBO', DEFAULT, TRUE, 'simoneveniero00@gmail.com'),
(2, 'TERESA', 'BUONAIUTO', DEFAULT, TRUE, 'simoneveniero00@gmail.com'),
(3, 'VALERIO', 'LUNDINI', DEFAULT, FALSE, 'simoneveniero00@gmail.com'),
(4, 'Marco', 'Pastore', DEFAULT, TRUE, 'gianmarcolembo@gmail.com'),
(5, 'Alfredo', 'Laino', DEFAULT, FALSE, 'gianmarcolembo@gmail.com'),
(6, 'LAPIPI', 'MISCAPPA', DEFAULT, FALSE, 'simoneveniero00@gmail.com'),
(7, 'BENITO', 'BECCOLINI', DEFAULT, TRUE, 'simoneveniero00@gmail.com'),
(8, 'NOHICANI', 'MISCAPPA', DEFAULT, TRUE, 'simoneveniero00@gmail.com'),
(9, 'PIETRO', 'SMUSI', 'DEFAULT', TRUE, 'simoneveniero00@gmail.com'),
(10, 'ILARIA', 'SMUSI', DEFAULT, FALSE, 'simoneveniero00@gmail.com'),
(11, 'UKATSU', 'MEKAGATU', DEFAULT, FALSE, 'simoneveniero00@gmail.com'),
(12, 'UKATSU', 'BUTTUNATSU', DEFAULT, FALSE, 'simoneveniero00@gmail.com'),
(13, 'ASSUNTA', 'CAPUOZZO', DEFAULT, FALSE, 'simoneveniero00@gmail.com'),
(14, 'ALFONSO', 'CUSANO', DEFAULT, TRUE, 'simoneveniero00@gmail.com'),
(15, 'BENEDETTA', 'CUSANO', DEFAULT, TRUE, 'simoneveniero00@gmail.com'),
(16, 'ANNA', 'CUSANO', DEFAULT, FALSE, 'simoneveniero00@gmail.com'),
(17, 'LUXANNA', 'CROWNGUARD', DEFAULT, FALSE, 'simoneveniero00@gmail.com'),
(18, 'GAREN', 'CROWNGUARD', DEFAULT, TRUE, 'simoneveniero00@gmail.com'),
(19, 'CLOUD', 'STRIFE', DEFAULT, FALSE, 'simoneveniero00@gmail.com'),
(20, 'TIFA', 'LOCKHART', DEFAULT, FALSE, 'simoneveniero00@gmail.com');

--Numeri telefonici
INSERT INTO PHONENUMBER VALUES ('MOBILE', '3457541235', FALSE), 
('LANDLINE', '0818702356', FALSE), 
('MOBILE', '3335558888', FALSE), 
('MOBILE', '3957541835', FALSE),
('MOBILE', '3206969420', FALSE), 
('LANDLINE', '0818703845', TRUE), 
('MOBILE', '3420194219', TRUE), 
('MOBILE', '3662558143', TRUE), 
('MOBILE', '3497806290', TRUE), 
('MOBILE', '3203032134', TRUE), 
('MOBILE', '3214234210', TRUE), 
('MOBILE', '3215423123', TRUE), 
('MOBILE', '3902156940', TRUE), 
('MOBILE', '3410041234', TRUE), 
('MOBILE', '3913219521', TRUE), 
('MOBILE', '3214210521', TRUE), 
('MOBILE', '3950234012', TRUE), 
('MOBILE', '3900001201', TRUE),
('MOBILE', '3532554863', TRUE), 
('MOBILE', '3215486211', TRUE), 
('MOBILE', '3910230549', TRUE), 
('MOBILE', '3203920510', TRUE), 
('MOBILE', '3912341250', TRUE), 
('MOBILE', '3219421004', FALSE), 
('MOBILE', '3925921039', FALSE), 
('MOBILE', '3295912095', FALSE), 
('MOBILE', '3205291052', FALSE), 
('MOBILE', '3950201942', FALSE), 
('MOBILE', '3959201025', FALSE),
('MOBILE', '3925921925', FALSE), 
('MOBILE', '3201204012', FALSE), 
('LANDLINE', '0819294223', TRUE), 
('LANDLINE', '0812499510', TRUE), 
('LANDLINE', '0818249210', TRUE), 
('LANDLINE', '0819230421', TRUE), 
('LANDLINE', '0812381284', TRUE),
('LANDLINE', '0818739241', TRUE), 
('LANDLINE', '0818493823', TRUE), 
('LANDLINE', '0819348929', TRUE), 
('LANDLINE', '0812489128', TRUE), 
('LANDLINE', '0812480124', TRUE), 
('LANDLINE', '0818744535', TRUE),   
('LANDLINE', '0818598232', FALSE), 
('LANDLINE', '0812301204', FALSE), 
('LANDLINE', '0812394503', FALSE);

--Assegnazione numeri telefonici
INSERT INTO ASSIGNEDPHONE VALUES ('3457541235', '1'),
('0818703845', '1'),
('3420194219', '1'),
('3662558143', '2'),
('3925921925', '2'),
('0819294223', '2'),
('3497806290', '3'),
('3201204012', '3'),
('0812499510', '3'),
('3203032134', '6'),
('3295912095', '6'),
('0818249210', '6'),
('3214234210', '7'),
('3957541835', '7'),
('0819230421', '7'),
('3215423123', '8'),
('0818249210', '8'),
('3902156940', '9'),
('3205291052', '9'),
('0812381284', '9'),
('3410041234', '10'),
('0812381284', '10'),
('3913219521', '11'),
('3206969420', '11'),
('0818739241', '11'),
('3214210521', '12'),
('0818739241', '12'),
('3950234012', '13'),
('0818493823', '13'),
('0818598232', '13'),
('3950234012', '14'),
('0819348929', '14'),
('3900001201', '15'),
('3950201942', '15'),
('0819348929', '15'),
('0812301204', '15'),
('3910230549', '16'),
('3959201025', '16'),
('0819348929', '16'),
('3203920510', '17'),
('3219421004', '17'),
('3925921039', '17'),
('3295912095', '17'),
('0812489128', '17'),
('3912341250', '18'),
('0812489128', '18'),
('3532554863', '19'),
('0812480124', '19'),
('0812394503', '19'),
('3215486211', '20'),
('0818744535', '20');

--Indirizzi
INSERT INTO ADDRESS VALUES ('VIA ROMA 32', '80054', 'GRAGNANO', 'NA', 'ITALIA'),
('VIA LAMMA 24', '80054', 'GRAGNANO', 'NA', 'ITALIA'),
('VIA CASTELLAMMARE 245', '80054', 'GRAGNANO', 'NA', 'ITALIA'),
('VIA SCQUACQUERONE 43', '80046', 'SAN GIORGIO A CREMANO', 'NA', 'ITALIA'),
('VIA RISVEGLIO DEI MORTI 7', '88100', 'CATANZARO', 'CT', 'ITALIA'),
('VIA DEI COLTELLI VOLANTI 32', '87512', 'LAVAGNA', 'GE', 'ITALIA'),
('VIA ANELLO ANCESTRALE 30', '81392', 'LEYNDELL', 'LN', 'LANDS BETWEEN'),
('VIA ABISSO 59', '81249', 'ANOR LONDO', 'AL', 'LORDRAN'),
('VIA PASQUALE NASTRO 20', '80054', 'GRAGNANO', 'NA', 'ITALIA'),
('VIA REGINA MARGHERITA 82', '80053', 'CASTELLAMMARE DI STABIA', 'NA', 'ITALIA'),
('VIA NASCENTE 92', '80332', 'VITERBO', 'VB', 'ITALIA'),
('VIA RIFUGIO DEI MARTIRI 10', '80234', 'COSENZA', 'CS', 'ITALIA'),
('VIA LE MANI 2', '80231', 'CHIAVARI', 'GE', 'ITALIA'),
('VIA CASTELLAMMARE 2', '80054', 'GRAGNANO', 'NA', 'ITALIA'),
('VIA ROMA 8', '80054', 'GRAGNANO', 'NA', 'ITALIA'),
('VIA ROMA 10', '80054', 'GRAGNANO', 'NA', 'ITALIA');

--E-mail
INSERT INTO EMAIL VALUES ('gianmarcolembo@gmail.com', TRUE, 1),
('teresabuonaiuto@hotmail.it', TRUE, 2),
('teresinalafatina@hotmail.it', FALSE, 2),
('valeriolundini@libero.it', TRUE, 3),
('unapezzadilundini@outlook.it', FALSE, 3),
('lapipimiscappa00@hotmail.it', TRUE, 6),
('benitobeckolini1@libero.it', TRUE, 7),
('nohicaniilmeglio23@icloud.com', TRUE, 8),
('pietrosmusi_fiero89@outlook.it', TRUE, 9),
('ilariettasmusi2@hotmail.it', TRUE, 10),
('mekagatukatsu69@gmail.com', TRUE, 11),
('smettilatiprego312@gmail.com', FALSE, 11),
('mbuttunatukatsu@gmail.com', TRUE, 12),
('merlino45magia@hotmail.it', FALSE, 12),
('assunta_capuozzo@hotmail.it', TRUE, 13),
('adorobettinocraxi@outlook.it', TRUE, 14),
('odiobettinocraxi@outlook.it', TRUE, 15),
('inrealtaadorobettinocraxi@libero.it', FALSE, 15),
('lamiafamigliaconbettinocraxi@outlook.it', TRUE, 16),
('sonolaluce@riot.com', TRUE, 17),
('infedelivibrucio@riot.com', TRUE, 18),
('aicubettinoncigioco@gmail.com', TRUE, 19),
('laragionepercuinondovresti@gmail.com', TRUE, 20),
('tifa.lock@libero.it', FALSE, 20);

--Messaging Account
INSERT INTO MESSAGING ('Whatsapp', 'xX_magomerlino_Xx', 'merlino45magia@hotmail.it', 'In viaggio'),
('Whatsapp', 'PapaX', 'infedelivibrucio@riot.com', 'Sono in chiesa'),
('WeChat', 'falce_martello', 'tifa.lock@libero.it', '...'),
('Messenger', 'Lvce22', 'sonolaluce@riot.com', 'Al lavoro'),
('Messenger', 'PietroTornaIndietro', 'pietrosmusi_fiero89@outlook.it', 'Disponibile'),
('Whatsapp', 'steve90', 'aicubettinoncigioco@gmail.com', 'Occupato a giocare a minecraft');

--Assegnazione indirizzi
INSERT INTO ASSIGNEDADDRESS VALUES (1, 'VIA ROMA 32', '80054', TRUE),
(2, 'VIA LAMMA 24', '80054', TRUE),
(3, 'VIA CASTELLAMMARE 245', '80054', TRUE),
(6, 'VIA SCQUACQUERONE 43', '80046', TRUE), 
(7, 'VIA RISVEGLIO DEI MORTI 7', '88100', TRUE),
(8, 'VIA SCQUACQUERONE 43', '80046', TRUE),
(9, 'VIA ANELLO ANCESTRALE 30', '81392', TRUE),
(10, 'VIA ANELLO ANCESTRALE 30', '81392', TRUE),
(11, 'VIA PASQUALE NASTRO 20', '80054', TRUE),
(12, 'VIA REGINA MARGHERITA 82', '80053', TRUE),
(13, 'VIA NASCENTE 92', '80332', TRUE),
(13, 'VIA RIFUGIO DEI MARTIRI 10', '80234', FALSE),
(14, 'VIA LE MANI 2', '80231', TRUE),
(15, 'VIA LE MANI 2', '80231', FALSE),
(15, 'VIA DEI COLTELLI VOLANTI 32', '87512', TRUE),
(16, 'VIA LE MANI 2', '80231', TRUE),
(17, 'VIA CASTELLAMMARE 2', '80054', TRUE),
(18, 'VIA CASTELLAMMARE 2', '80054', TRUE),
(19, 'VIA ROMA 8', '80054', TRUE),
(19, 'VIA ABISSO 59', '81249', FALSE),
(20, 'VIA ROMA 10', '80054', TRUE);

--Gruppi
INSERT INTO R_GROUP VALUES (1, 'I PIPONI', '2022-03-23', 'Il gruppo dei piponi della VA', 'simoneveniero00@gmail.com'),
(2, 'I CRIMINALI', '2022-01-10', 'Gruppo di uscite', 'simoneveniero00@gmail.com'),
(3, 'Univerità', '2020-10-10', 'Gruppo di studio', 'gianmarcolembo@gmail.com');

--Partecipanti a gruppi
INSERT INTO PARTICIPANT VALUES (2, 1, '2022-03-23'),
(1, 7, '2022-04-20'),
(1, 10, '2022-03-24'),
(1, 11, '2022-03-24'),
(1, 13, '2022-04-21'),
(1, 15, '2022-03-25'),
(1, 16, '2022-03-23'),
(2, 10, '2022-04-20'),
(2, 9, '2022-04-21'),
(2, 12, '2022-04-22'),
(2, 18, '2022-04-26'),
(3, 1, '2020-10-15'),
(3, 4, '2021-03-08'),
(3, 5, '2021-09-27');

--Chiamate
INSERT INTO PHONECALL VALUES (1, 'ENTERED', '2022-03-12 14:55:28', '0818598232', '00:02:15', 'default', 'simoneveniero00@gmail.com'),
(2, 'SENT', '2022-07-15 12:30:15', '0818373010', '00:03:06', 'default', 'simoneveniero00@gmail.com'),
(3, 'ENTERED', '2022-01-01 00:16:32', '3925921039', '00:01:45', 'default', 'simoneveniero00@gmail.com'),
(4, 'SENT', '2022-02-28 17:36:02', '3950234012', '00:00:26', 'default', 'simoneveniero00@gmail.com'),
(5, 'MISSED', '2022-02-28 16:57:47', '3950234012', '00:00:00', 'default', 'simoneveniero00@gmail.com'),
(6, 'ENTERED', '2022-05-05 11:05:59', '3343795144', '00:01:24', 'default', 'simoneveniero00@gmail.com'),
(7, 'ENTERED', '2022-04-01 22:15:45', '0812394503', '00:00:41', 'default', 'simoneveniero00@gmail.com'),
(8, 'SENT', '2021-10-31 21:20:00', '3215486211', '00:06:06', 'default', 'simoneveniero00@gmail.com'),
(9, 'MISSED', '2022-06-02 18:45:57', '3290741949', '00:00:00', 'default', 'simoneveniero00@gmail.com'),
(10, 'ENTERED', '2022-03-21 09:35:42', '3913219521', '00:02:15', 'default', 'simoneveniero00@gmail.com'),
(11, 'SENT', '2022-07-11 12:30:15', '3511065999', '00:01:11', 'default', 'gianmarcolembo@gmail.com');





-- Alla creazione di un Gruppo, la data di creazione viene impostata a quella corrente
CREATE FUNCTION SetGroupDate()
RETURNS TRIGGER AS $SetGroupDate$
	BEGIN
		UPDATE R_Group as RG
		SET creationDate = CURRENT_DATE
		WHERE NEW.groupID = RG.groupID;
		RETURN NEW;
	END;
$SetGroupDate$ LANGUAGE plpgsql;

CREATE TRIGGER SetGroupDate
AFTER INSERT ON R_Group
FOR EACH ROW
EXECUTE PROCEDURE SetGroupDate();
