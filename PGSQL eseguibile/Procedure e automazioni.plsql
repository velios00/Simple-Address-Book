-- Alla rimozione di un contatto da un Gruppo, se quel Gruppo non ha più partecipanti viene eliminato
CREATE FUNCTION AutoDeleteGroup()
RETURNS TRIGGER AS $AutoDeleteGroup$
	BEGIN
		IF NOT EXISTS (SELECT * FROM Participant as Par WHERE OLD.groupID = Par.groupID)
		THEN
			DELETE FROM R_Group as rg
			WHERE OLD.groupID = rg.groupID;
		END IF;
	RETURN NEW;
	END;
$AutoDeleteGroup$ LANGUAGE plpgsql;

CREATE TRIGGER AutoDeleteGroup
AFTER DELETE ON Participant
FOR EACH ROW
EXECUTE PROCEDURE AutoDeleteGroup();





-- La seguente procedura prende in input un numero telefonico e se non ci sono contatti associati ad esso lo elimina
CREATE PROCEDURE AutoDeletePhone(PNumber char(10))
LANGUAGE plpgsql AS $AutoDeletePhone$
	BEGIN
		IF NOT EXISTS (SELECT * FROM AssignedPhone as AP WHERE PNumber = AP.phoneNumber and AP.contactID <> NULL)
		THEN
			DELETE FROM PhoneNumber as PN
			WHERE PN.phoneNumber = PNumber;
		END IF;
	END;
$AutoDeletePhone$;


-- il seguente trigger chiama la procedura AutoDeletePhone alla rimozione di un contatto dalla tabella AssignedPhone
CREATE FUNCTION CallDeletePhone()
RETURNS TRIGGER AS $CallDeletePhone$
	BEGIN
		CALL AutoDeletePhone(OLD.phonenumber);
	RETURN NEW;
	END;
$CallDeletePhone$ LANGUAGE plpgsql;

CREATE TRIGGER CallDeletePhone
AFTER UPDATE ON AssignedPhone
FOR EACH ROW
WHEN (NEW.contactID = NULL)
EXECUTE PROCEDURE CallDeletePhone();
-- NOTA: Quando un contatto viene eliminato, la foreign key all’interno della tabella AssignedPhone viene impostata a NULL (si veda tabella di definizione) per evitare conflitti con i trigger che implementano i vincoli





-- Alla rimozione di un assegnazione di un indirizzo ad un contatto, se quell’indirizzo non ha altre assegnazioni viene eliminato
CREATE FUNCTION AutoDeleteAddress()
RETURNS TRIGGER AS $AutoDeleteAddress$
	BEGIN
		IF NOT EXISTS (SELECT * FROM AssignedAddress as AA WHERE OLD.addressStr = AA.addressStr and OLD.addressZip = AA.addressZip)
		THEN
			DELETE FROM Address
			WHERE Address.street = OLD.addressStr and Address.zipcode = OLD.addressZip;
		END IF;
	RETURN NEW;
	END;
$AutoDeleteAddress$ LANGUAGE plpgsql;

CREATE TRIGGER AutoDeleteAddress
AFTER DELETE ON AssignedAddress
FOR EACH ROW
EXECUTE PROCEDURE AutoDeleteAddress();





-- Alla creazione di un Gruppo, la data di creazione viene impostata a quella corrente
CREATE FUNCTION SetGroupDate()
RETURNS TRIGGER AS $SetGroupDate$
	BEGIN
		UPDATE R_Group as RG
		SET NEW.creationDate = CURRENT_DATE
		WHERE NEW.groupID = RG.groupID;
		RETURN NEW;
	END;
$SetGroupDate$ LANGUAGE plpgsql;

CREATE TRIGGER SetGroupDate
AFTER INSERT ON R_Group
FOR EACH ROW
EXECUTE PROCEDURE SetGroupDate();





-- La seguente procedura riceve in ingresso un numero telefonico e un utente, cerca il nome e il cognome dei contatti associati ad essi, e li scrive nella colonna contactName di PhoneCall dove il numero telefonico corrisponde. Se non ci sono contatti associati al numero telefonico, scrive “Unknown”
CREATE PROCEDURE UpdCallContactName(PNumber char(10), usr R_User.email%type)
LANGUAGE plpgsql AS $UpdCallContactName$
DECLARE
	resultString varchar(1024) := '';
	namesur CURSOR  FOR (SELECT contactName, surname FROM PhoneNumber as PN, AssignedPhone as AP, Contact as Con WHERE PN.phoneNumber = AP.phoneNumber and AP.contactID = Con.contactID and PN.phoneNumber = PNumber and Con.r_user = usr);
BEGIN
	FOR nome IN namesur LOOP
		resultString := resultString || nome.contactName || ' ' || nome.surname || ', ';
	END LOOP;
	resultString := rtrim(resultString, ', ');
	IF resultString = '' THEN
		resultString := 'Unknown';
	END IF;
	UPDATE PhoneCall as PC
	SET contactName = resultString
	WHERE PC.phoneNumber = PNumber;
END;
$UpdCallContactName$


-- Chiamata della procedura UpdCallContactName all’inserimento di una nuova riga nella tabella PhoneCall
CREATE FUNCTION UpdPhoneCall()
RETURNS TRIGGER AS $UpdPhoneCall$
	BEGIN
		CALL UpdCallContactName(NEW.phoneNumber, NEW.r_user);
		RETURN NEW;
	END;
$UpdPhoneCall$ LANGUAGE plpgsql;

CREATE TRIGGER UpdPhoneCall
AFTER INSERT ON PhoneCall
FOR EACH ROW
EXECUTE PROCEDURE UpdPhoneCall();


-- Chiamata della procedura UpdCallContactName all’assegnazione di un numero di telefono ad un contatto
CREATE FUNCTION UpdCallFromAP()
RETURNS TRIGGER AS $UpdCallFromAP$
	DECLARE
		currentUser varchar(10);
	BEGIN
		SELECT Con.r_user into currentUser 
		FROM Contact as Con
		WHERE NEW.contactID = Con.contactID;
		CALL UpdCallContactName(NEW.phoneNumber, currentUser);
		RETURN NEW;
	END;
$UpdCallFromAP$ LANGUAGE plpgsql;

CREATE TRIGGER UpdCallFromAP
AFTER INSERT or DELETE ON AssignedPhone
FOR EACH ROW
EXECUTE PROCEDURE UpdCallFromAP();
