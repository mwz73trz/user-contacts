BEGIN TRANSACTION;

INSERT INTO users (username,password_hash,role) VALUES ('user','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_USER');
INSERT INTO users (username,password_hash,role) VALUES ('admin','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_ADMIN');
INSERT INTO users (username,password_hash,role) VALUES ('user1','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_USER');
INSERT INTO users (username,password_hash,role) VALUES ('admin1','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_ADMIN');
INSERT INTO users (username,password_hash,role) VALUES ('admin2','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_ADMIN');
INSERT INTO users (username,password_hash,role) VALUES ('user3','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_USER');

INSERT INTO patient(first_name, last_name, user_id)
	VALUES ('User', 'One', 1),
		   ('Chase', 'Test', 3),
		   ('Harry', 'Potter', 6);

INSERT INTO review(patient_id)
	VALUES	(3),
			(2);

INSERT INTO office_details(office_name, phone_number, open_time, close_time, address, city, state, zip, service_fee, review_id )
	VALUES  ('Mediwizard Clinc', '1-740-361-4729', '08:00:00', '17:00:00', '234 Diagon Ally', 'Orlando', 'Florida', '34287', 85.00, 1),
			('Chase Clinic', '1-908-862-0280', '07:00:00', '18:00:00', '0972 Chase Place', 'Dalas', 'Texas', '10824', 90.00, 2);

INSERT INTO employee(user_id, first_name, last_name, office_id)
	VALUES  (2, 'Abby', 'Admin', 2),
		    (5, 'Paul', 'Cross', 2),
		    (4, 'Ginny', 'Weasley',1);
		
INSERT INTO review_details(review_id, employee_id, patient_id, office_id, review)
	VALUES	(1, 3, 3, 2, 'Dr.Weasley is amazing and very friendly'),
			(2, 2, 2, 1, 'Chase Clinic always has times available! Staff is always on time and knowledgable');  
			
INSERT INTO review_reply(employee_id, patient_id, office_id, review_reply)
	VALUES	(2, 2, 1, 'Thanks for the lovely response'),
			(3, 1, 2, 'Thanks for the response!' );

INSERT INTO employee_schedule(employee_id, start_time, end_time, isBooked)
	VALUES	(1, '07:00:00', '15:00:00', 'No'),
			(2, '09:00:00', '18:00:00', 'Yes'),
			(3, '08:00:00', '17:00:00', 'No');
			
INSERT INTO appointment(created_time, patient_id, employee_id, appointment_date, start_time, end_time)
	VALUES	('2023-10-03 07:50:45', 1, 2, '2023-10-13', '10:30:00', '11:00:00'),
			('2023-09-30 14:26:09', 2, 1, '2023-11-22', '12:00:00', '13:00:00'),
			('2023-08-15 05:32:57', 3, 3, '2023-10-10', '08:45:00', '09:30:00');

--ROLLBACK;
COMMIT TRANSACTION;
