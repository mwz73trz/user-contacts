BEGIN TRANSACTION;

INSERT INTO users (username,password_hash,role) VALUES ('user','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_USER');
INSERT INTO users (username,password_hash,role) VALUES ('admin','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_ADMIN');
INSERT INTO users (username,password_hash,role) VALUES ('user1','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_USER');
INSERT INTO users (username,password_hash,role) VALUES ('admin1','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_ADMIN');
INSERT INTO users (username,password_hash,role) VALUES ('admin2','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_ADMIN');
INSERT INTO users (username,password_hash,role) VALUES ('user3','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_USER');

INSERT INTO patient(patient_id, first_name, last_name)
	VALUES (1, 'User', 'One' ),
		   (3, 'Chase', 'Test'),
		   (6, 'Harry', 'Potter');

INSERT INTO office_details(office_name, phone_number, open_time, close_time, address, city, state, zip, service_fee )
	VALUES  ('Mediwizard Clinc', '1-740-361-4729', '08:00:00', '17:00:00', '234 Diagon Ally', 'Orlando', 'Florida', '34287', 85.00),
			('Chase Clinic', '1-908-862-0280', '07:00:00', '18:00:00', '0972 Chase Place', 'Dalas', 'Texas', '10824', 90.00);

INSERT INTO employee(employee_id, first_name, last_name)
	VALUES  (2, 'Abby', 'Admin'),
		    (5, 'Paul', 'Cross'),
		    (4, 'Ginny', 'Weasley');
			
INSERT INTO employee_office(employee_id, office_id)
	VALUES  (2, 2),
		    (5, 2),
		    (4, 1);
			
INSERT INTO review(user_id, review, office_id)
	VALUES	(3, 'Dr. Weasley is always very nice and friendly, I recommend Weasley!', 2),
			(2, 'Chase Clinic always has times available! Staff is always on time and knowledgable', 1 );
			
INSERT INTO review_reply(review_id, user_id, review_reply)
	VALUES	(1, 2, 'Thanks for the lovely response'),
			(2, 5, 'Thanks for the response!' );

INSERT INTO employee_schedule(employee_id, start_time, end_time)
	VALUES	(2, '07:00:00', '15:00:00'),
			(5, '09:00:00', '18:00:00'),
			(4, '08:00:00', '17:00:00');
			
INSERT INTO appointment(created_time, patient_id, employee_id, appointment_date, start_time, end_time)
	VALUES	('2023-10-03 07:50:45', 1, 2, '2023-10-13', '10:30:00', '11:00:00'),
			('2023-09-30 14:26:09', 3, 5, '2023-11-22', '12:00:00', '13:00:00'),
			('2023-08-15 05:32:57', 6, 4, '2023-10-10', '08:45:00', '09:30:00');

--ROLLBACK;
COMMIT TRANSACTION;
