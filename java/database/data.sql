BEGIN TRANSACTION;

INSERT INTO users (username,password_hash, role) VALUES ('user','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC', 'USER');
INSERT INTO users (username,password_hash, role) VALUES ('admin','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC', 'ADMIN');

INSERT INTO grouping (title, user_id)
	VALUES ('Work', 1),
			('Family', 1),
			('Business', 2);

INSERT INTO contact (first_name, last_name, address, city, state, zip,
					 phone_number, email, birth_date, note, grouping_id)
	VALUES ('Michael', 'Zura', '34 Applewood Dr', 'Dover', 'DE', '19901',
		   		'618-979-3422', 'michaelzura@bellsouth.net', '06-16-1973', 'My Info', 2),
			('Bob', 'Smith', '1 Main St', 'Austin', 'TX', '12345',
				'123-456-7890', 'bob@smith.com', '11-11-1990', 'Plumber', 1),
			('Jim', 'Dandy', '1 Main St', 'Dover', 'DE', '19904',
				'123-456-7890', 'jim.dandy@gmail.com', '11-10-1956', 'Insurance', 3);

--ROLLBACK;
COMMIT TRANSACTION;
