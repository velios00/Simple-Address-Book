Vincolo emailFormat
-- Implementazione del vincolo emailFormat
ALTER TABLE Email ADD CONSTRAINT emailFormat CHECK ( email LIKE '_%@_%.__%')
ALTER TABLE R_user ADD CONSTRAINT emailFormat CHECK ( email LIKE '_%@_%.__%')


Vincoli passwordLen e usernameLen
-- Implementazione dei vincoli passwordLen e usernameLen
ALTER TABLE R_User 
ADD CONSTRAINT passwordLen CHECK (LENGTH(password)>7),
ADD CONSTRAINT usernameLen CHECK (LENGTH(nickname)>2)

Vincolo uniqueMainEmail
--Implementazione del vincolo uniqueMainEmail
CREATE FUNCTION uniqueMainEmail()
RETURNS TRIGGER AS $uniqueMainEmail$
	BEGIN
		IF NEW.main=true AND NEW.contactID IN (SELECT contactID FROM Email WHERE main=true) THEN
		RAISE EXCEPTION 'A main e-mail for this contact already exists!';
	END IF;
RETURN NEW;
END;
$uniqueMainEmail$ LANGUAGE plpgsql;

CREATE TRIGGER uniqueMainEmail
BEFORE INSERT OR UPDATE ON Email
FOR EACH ROW
EXECUTE PROCEDURE uniqueMainEmail();



Vincolo uniqueMainAddress
-- Implementazione del vincolo uniqueMainAddress
CREATE FUNCTION uniqueMainAddress() 
RETURNS TRIGGER AS $uniqueMainAddress$
	BEGIN
		IF NEW.main = true AND NEW.contactID IN (SELECT contactID FROM AssignedAddress WHERE main = true) THEN
			RAISE EXCEPTION 'A main Address for this contact already exists!';
		END IF;
	RETURN NEW;
	END;
$uniqueMainAddress$ LANGUAGE plpgsql;

CREATE TRIGGER uniqueMainAddress
BEFORE INSERT OR UPDATE ON AssignedAddress
FOR EACH ROW
EXECUTE PROCEDURE uniqueMainAddress();



Vincolo checkContactNumbers
-- Implementazione del vincolo checkContactNumbers
CREATE FUNCTION checkContactNumbers()
RETURNS TRIGGER AS $checkContactNumbers$
    DECLARE
        ConID integer := OLD.contactID;
	BEGIN
	    IF NOT EXISTS (SELECT * 
                       FROM AssignedPhone as AP, PhoneNumber as PN 
                       WHERE AP.phoneNumber = PN.phoneNumber and ConID = AP.contactID and PN.phoneType = 'MOBILE')
		OR NOT EXISTS (SELECT * 
                       FROM AssignedPhone as AP, PhoneNumber as PN 
                       WHERE AP.phoneNumber = PN.phoneNumber and ConID = AP.contactID and PN.phoneType = 'LANDLINE')
			THEN 
            RAISE EXCEPTION 'A contact must have at least a landline number and a mobile number';
		END IF;
	RETURN NEW;
	END;
$checkContactNumbers$ LANGUAGE plpgsql;

CREATE TRIGGER checkContactNumbers
AFTER DELETE OR UPDATE ON AssignedPhone
FOR EACH ROW
EXECUTE PROCEDURE checkContactNumbers();

Vincolo uniqueLinkedNumber
-- Implementazione del vincolo uniqueLinkedNumber
CREATE FUNCTION uniqueLinkedNumber()
RETURNS TRIGGER AS $uniqueLinkedNumber$
DECLARE
    Condizione boolean;
    Tipo varchar(10);
BEGIN
    SELECT PN.linkedNumber INTO Condizione FROM PhoneNumber AS PN WHERE  PN.phoneNumber = new.phoneNumber;
    SELECT PN.phoneType INTO Tipo FROM PhoneNumber AS PN WHERE PN.phoneType = new.phoneType;
	IF(Condizione = true) THEN
		IF EXISTS (SELECT * FROM AssignedPhone AS AP, PhoneNumber AS PN WHERE PN.linkedNumber = true AND AP.contactID = new.contactID AND PN.phoneType = Tipo AND AP.phoneNumber = PN.phoneNumber) THEN
    		RAISE EXCEPTION 'A linked number already exists for this contact !';
		END IF;
	END IF;
	RETURN NEW;
END;
$uniqueLinkedNumber$ LANGUAGE plpgsql;

CREATE TRIGGER uniqueLinkedNumber 
AFTER INSERT OR UPDATE ON AssignedPhone
FOR EACH ROW
EXECUTE PROCEDURE uniqueLinkedNumber();

Vincolo timeConsistancyGroup
-- Implementazione del vincolo timeConsistancyGroup
CREATE FUNCTION timeConsistancyGroup()
RETURNS TRIGGER AS $timeConsistancyGroup$
DECLARE
	DataCreateGrp DATE;
BEGIN
	SELECT creationDate INTO DataCreateGrp FROM R_GROUP WHERE groupID = new.groupID;
	IF(DataCreateGrp > new.joinDate) THEN
		RAISE EXCEPTION 'Participant join date shouldn’t be older than group creation date !';
	END IF;
	RETURN NEW;
END;
$timeConsistancyGroup$ LANGUAGE plpgsql;

CREATE TRIGGER timeConsistancyGroup
BEFORE INSERT OR UPDATE ON Participant
FOR EACH ROW
EXECUTE PROCEDURE timeConsistancyGroup()


Vincolo distinctEmail
-- Implementazione del vincolo distinctEmail
ALTER TABLE Email 
ADD CONSTRAINT distinctEmail UNIQUE (email, contactID)



Vincolo checkCallType
-- Implementazione del vincolo checkCallType
ALTER TABLE PhoneCall
ADD CONSTRAINT checkCallType CHECK (callType in ('SENT', 'ENTERED', 'MISSED'))


Vincolo checkNumberType
-- Implementazione del vincolo checkNumberType
ALTER TABLE PhoneNumber
ADD CONSTRAINT checkPhoneNumberType CHECK (phonetype in ('MOBILE', 'LANDLINE'))




















