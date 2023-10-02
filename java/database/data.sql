BEGIN TRANSACTION;

INSERT INTO users (username,password_hash,role) VALUES ('user','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_USER');
INSERT INTO users (username,password_hash,role) VALUES ('admin','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_ADMIN');
INSERT INTO users (username,password_hash,role) VALUES ('user1','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_USER');
INSERT INTO users (username,password_hash,role) VALUES ('user2','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_USER');
INSERT INTO users (username,password_hash,role) VALUES ('admin1','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_ADMIN');
INSERT INTO users (username,password_hash,role) VALUES ('user3','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_USER');

INSERT INTO office_details(office_name, phone_number, open_time, close_time, address, city, state, zip, service_fee )
	VALUES  ('Mediwizard', '1-740-361-4729', '08:00:00', '17:00:00', '234 Diagon Ally', 'Columbus', 'Ohio', 43298, 85.00),
			('Chase Clinic', '1-908-862-0280', '07:00:00', '18:00:00', '0972 Chase Place', 'Dalas', 'Texas', 10824, 90.00);
			
INSERT INTO employee(user_id, first_name, last_name, office_id)
	VALUES( 1, 'User', 'One', 1),
		   (2, 'Abby', 'Admin', 1),
		   (5, 'Paul', 'Cross', 2);
		  
INSERT INTO patient(first_name, last_name, user_id)
	VALUES ('Chase', 'Test', 3),
		   ('Ginny', 'Weasley', 4),
		   ('Harry', 'Potter', 6);
		  

--ROLLBACK;
COMMIT TRANSACTION;
