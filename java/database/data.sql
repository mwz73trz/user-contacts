BEGIN TRANSACTION;

INSERT INTO users (username,password_hash,role) VALUES ('user','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_USER');
INSERT INTO users (username,password_hash,role) VALUES ('admin','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_ADMIN');
INSERT INTO users (username,password_hash,role) VALUES ('user1','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_USER');
INSERT INTO users (username,password_hash,role) VALUES ('admin1','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_ADMIN');
INSERT INTO users (username,password_hash,role) VALUES ('admin2','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_ADMIN');
INSERT INTO users (username,password_hash,role) VALUES ('user3','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_USER');
INSERT INTO users (username,password_hash,role) VALUES ('admin3','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_ADMIN');
INSERT INTO users (username,password_hash,role) VALUES ('admin4','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_ADMIN');
INSERT INTO users (username,password_hash,role) VALUES ('admin5','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_ADMIN');
INSERT INTO users (username,password_hash,role) VALUES ('admin6','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_ADMIN');
INSERT INTO users (username,password_hash,role) VALUES ('admin7','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_ADMIN');
INSERT INTO users (username,password_hash,role) VALUES ('user4','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_USER');
INSERT INTO users (username,password_hash,role) VALUES ('user5','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_USER');
INSERT INTO users (username,password_hash,role) VALUES ('user6','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_USER');
INSERT INTO users (username,password_hash,role) VALUES ('user7','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_USER');
INSERT INTO users (username,password_hash,role) VALUES ('user8','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_USER');
INSERT INTO users (username,password_hash,role) VALUES ('user9','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_USER');
INSERT INTO users (username,password_hash,role) VALUES ('user10','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_USER');
INSERT INTO users (username,password_hash,role) VALUES ('user11','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_USER');
INSERT INTO users (username,password_hash,role) VALUES ('user12','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_USER');
INSERT INTO users (username,password_hash,role) VALUES ('user13','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_USER');
INSERT INTO users (username,password_hash,role) VALUES ('user14','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_USER');
INSERT INTO users (username,password_hash,role) VALUES ('user15','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_USER');
INSERT INTO users (username,password_hash,role) VALUES ('user16','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_USER');

INSERT INTO patient(patient_id, first_name, last_name, address, city, state, zip, phone_number, email)
	VALUES (1, 'User', 'One', '123 Main St', 'Cleveland', 'OH', '55412', '519-321-0987', 'helpme@patient.com'),
		   (3, 'Chase', 'Test', '13 Tom St', 'Lewistown', 'OH', '43333', '419-668-4333', 'iamsick@patient.com'),
		   (6, 'Harry', 'Potter', '1 Ache St', 'Dover', 'DE', '19904', '302-697-3813', 'sos911@patient.com'),
		   (12, 'Maddie', 'Thomas', '1 Pine St', 'Cleveland', 'OH', '55412', '519-321-2234', 'mpine@patient.com'),
		   (13, 'Chase', 'Elliott', '4 Dutch Lane', 'Dawsonville', 'NC', '99881', '378-907-1234', 'chase.elliott@gmail.com'),
		   (14, 'Bob', 'Builder', '23 Conn St', 'Newark', 'DE', '19963', '302-697-4545', 'bob.theBuilder@patient.com'),
		   (15, 'Humpty', 'Dumpty', '123 Wall St', 'Fort Worth', 'TX', '63371', '817-841-0987', 'humpty.dumpty@patient.com'),
		   (16, 'Captain', 'Crunch', '171 Cereal Box', 'Bowling', 'ID', '19197', '333-222-1111', 'captianK@patient.com'),
		   (17, 'Lou', 'How', '1 Bull Ct', 'St Louis', 'MO', '68712', '618-987-1234', 'lou.how@patient.com'),
		   (18, 'Richie', 'Rich', '1 Howl St', 'Kent', 'OH', '76512', '519-321-6512', 'richie.rich@patient.com'),
		   (19, 'Mildred', 'Kennedy', '1300 Pennsylvania Ave', 'Chestertown', 'MD', '57432', '317-123-0987', 'mildred@patient.com'),
		   (20, 'Nancy', 'Potts', '1 Heel Spur Lane', 'Madison', 'WI', '99123', '678-123-5455', 'nancy@patient.com'),
		   (21, 'Sally', 'Shesells', '234 Ocean Ave', 'Atlanta', 'GA', '45678', '453-123-0931', 'seashells@patient.com'),
		   (22, 'John', 'Shoe', '1 Toy Box St', 'Charleston', 'SC', '65432', '217-602-0987', 'john.shoe@patient.com'),
		   (23, 'Everson', 'Gillicutty', '19 James St', 'Gap', 'PA', '19198', '555-555-5555', 'everson@patient.com'),
		   (24, 'Vanna', 'White', '1 Wheeler Lane', 'Los Angeles', 'CA', '11223', '456-098-1234', 'vanna.white@patient.com');

INSERT INTO office_details(office_name, phone_number, open_time, close_time, address, city, state, zip, service_fee )
	VALUES  ('Mediwizard Clinic', '1-740-361-4729', '08:00:00', '17:00:00', '234 Diagon Ally', 'Orlando', 'FL', '34287', 85.00),
			('Chase Clinic', '1-908-862-0280', '07:00:00', '18:00:00', '0972 Chase Place', 'Dallas', 'TX', '10824', 90.00),
			('Health Associates', '1-302-697-1911', '08:00:00', '16:30:00', '19 Governors Ave', 'Dover', 'DE', '19901', 55.00),
			('Doctors R Us', '1-513-686-1234', '06:00:00', '18:00:00', '112 E Bay Shore Rd', 'Bellofontaine', 'OH', '43333', 110.00);

INSERT INTO employee(employee_id, first_name, last_name, email, mobile_phone)
	VALUES  (2, 'Abby', 'Admin', 'abby.admin@email.com', '302-123-4567'),
		    (5, 'Paul', 'Cross', 'paul.cross@email.com', '817-223-5678'),
		    (4, 'Ginny', 'Weasley', 'ginny.weasley@email.com', '513-663-7658'),
			(7, 'Jeff', 'Smith', 'jeff.smith@email.com', '302-123-6932'),
		    (8, 'Paula', 'Fuss', 'paula.fuss@email.com', '513-456-0112'),
		    (9, 'Harold', 'Jameson', 'harold.jameson@email.com', '618-999-1234'),
			(10, 'Guy', 'Ford', 'guy.ford.com', '419-734-2534'),
		    (11, 'Sue', 'Clause', 'sue.clause@email.com', '302-987-0033');
			
INSERT INTO employee_office(employee_id, office_id)
	VALUES  (2, 2),
		    (5, 2),
		    (4, 1),
			(7, 1),
		    (8, 3),
		    (9, 3),
			(10, 4),
		    (11, 4);
			
INSERT INTO review(user_id, review, office_id)
	VALUES	(3, 'Dr. Weasley is always very nice and friendly, I recommend Weasley!', 2),
			(6, 'Chase Clinic always has times available! Staff is always on time and knowledgable', 1 ),
			(14, 'Very friendly staff and handled my issue well!', 3),
			(24, 'I would not come back.  Terrible customer service.', 4 ),
			(13, 'Great service!  Highly recommended.', 2),
			(16, 'Chase Clinic  is clean and very friendly staffl', 1 ),
			(17, 'Easy to schedule appointments and great doctors.', 3),
			(23, 'Receptionist was rude and doctor was 45 minutes late.', 4 );
			
INSERT INTO review_reply(review_id, user_id, review_reply)
	VALUES	(1, 2, 'Thanks for the lovely response'),
			(2, 5, 'Thanks for the response!' ),
			(3, 8, 'Glad we could help.'),
			(4, 10, 'Sorry for everything. Come back and give us another chance.' ),
			(5, 2, 'Thanks for the lovely response'),
			(6, 5, 'Thanks for the response!' ),
			(7, 8, 'Glad we could help.'),
			(8, 10, 'Sorry for everything. Come back and give us another chance.' );

INSERT INTO employee_schedule(employee_id, start_time, end_time)
	VALUES	(2, '07:00:00', '15:00:00'),
			(5, '09:00:00', '18:00:00'),
			(4, '08:00:00', '17:00:00'),
			(7, '17:00:00', '05:00:00'),
			(8, '09:00:00', '18:00:00'),
			(9, '08:00:00', '17:00:00'),
			(10, '09:00:00', '18:00:00'),
			(11, '08:00:00', '17:00:00');
			
INSERT INTO appointment(patient_id, employee_id, appointment_date_start, appointment_time_start, appointment_date_end, appointment_time_end)
	VALUES	(1, 2, '2023-10-12', '10:30:00', '2023-10-12', '11:00:00'),
			(3, 5, '2023-10-12', '12:00:00', '2023-10-12', '13:00:00'),
			(6, 4, '2023-10-12', '08:45:00', '2023-10-12', '09:30:00'),
			(12, 7, '2023-10-12', '18:45:00', '2023-10-12', '19:30:00'),
		   	(13, 8, '2023-10-12', '18:45:00', '2023-10-12', '19:30:00'),
		   	(14, 9, '2023-10-12', '10:30:00', '2023-10-12', '11:00:00'),
		   	(15, 10, '2023-10-12', '12:00:00', '2023-10-12', '13:00:00'),
		   	(16, 11, '2023-10-12', '08:45:00', '2023-10-12', '09:30:00'),
		   	(17, 11, '2023-10-13', '08:45:00', '2023-10-13', '09:30:00'),
		   	(18, 10, '2023-10-13', '12:00:00', '2023-10-13', '13:00:00'),
		   	(19, 9, '2023-10-13', '10:30:00', '2023-10-13', '11:00:00'),
		   	(20, 8, '2023-10-13', '18:45:00', '2023-10-13', '19:30:00'),
		   	(21, 7, '2023-10-12', '19:45:00', '2023-10-12', '20:45:00'),
		   	(22, 4, '2023-10-13', '08:45:00', '2023-10-13', '09:30:00'),
		   	(23, 5, '2023-10-13', '12:00:00', '2023-10-13', '13:00:00'),
		   	(24, 2, '2023-10-13', '10:30:00', '2023-10-13', '11:00:00');

--ROLLBACK;
COMMIT TRANSACTION;
