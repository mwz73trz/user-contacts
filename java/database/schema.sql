BEGIN TRANSACTION;

DROP TABLE IF EXISTS review_reply CASCADE;
DROP TABLE IF EXISTS employee_office CASCADE;
DROP TABLE IF EXISTS review CASCADE;
DROP TABLE IF EXISTS appointment CASCADE;
DROP TABLE IF EXISTS employee_schedule CASCADE;
DROP TABLE IF EXISTS office_details CASCADE;
DROP TABLE IF EXISTS employee CASCADE;
DROP TABLE IF EXISTS patient CASCADE;
DROP TABLE IF EXISTS users CASCADE;

CREATE TABLE users (
	user_id SERIAL,
	username varchar(50) NOT NULL UNIQUE,
	password_hash varchar(200) NOT NULL,
	role varchar(50) NOT NULL,
	CONSTRAINT PK_user PRIMARY KEY (user_id)
);

CREATE TABLE  patient (
	patient_id INT NOT NULL,
	first_name VARCHAR(64) NOT NULL,
	last_name VARCHAR(64) NOT NULL,
	
	CONSTRAINT PK_patient PRIMARY KEY(patient_id),
	CONSTRAINT FK_patient_users FOREIGN KEY(patient_id) REFERENCES users(user_id)
);

CREATE TABLE office_details(
	office_id SERIAL UNIQUE,
	office_name VARCHAR(64) NOT NULL,
	phone_number VARCHAR(64) NOT NULL,
	open_time TIME,
	close_time TIME,
	address VARCHAR(64) NOT NULL,
	city VARCHAR(64) NOT NULL,
	state VARCHAR(64) NOT NULL,
	zip VARCHAR(64) NOT NULL,
	service_fee DECIMAL(10, 2),
	
	CONSTRAINT PK_office_details PRIMARY KEY(office_id)
);

CREATE TABLE employee (
	employee_id INT NOT NULL,
	first_name VARCHAR (64) NOT NULL,
	last_name VARCHAR (64) NOT NULL,
	office_id INT NOT NULL,
	
	CONSTRAINT PK_employee_id PRIMARY KEY (employee_id),
	CONSTRAINT FK_employee_users FOREIGN KEY(employee_id) REFERENCES users(user_id),
	CONSTRAINT FK_employee_office_details FOREIGN KEY(office_id) REFERENCES office_details(office_id)
);

CREATE TABLE employee_office(
	employee_id INT NOT NULL, 
	office_id INT NOT NULL,
	
	CONSTRAINT FK_employee_office_employee FOREIGN KEY(employee_id) REFERENCES employee(employee_id),
	CONSTRAINT FK_employee_office_office_details FOREIGN KEY(office_id) REFERENCES office_details(office_id)
);

CREATE TABLE review(
	review_id SERIAL,
	user_id INT, 
	review_date DATE DEFAULT CURRENT_DATE,
	review TEXT NOT NULL,
	office_id INT NOT NULL,
	
	CONSTRAINT PK_review PRIMARY KEY (review_id),
	CONSTRAINT FK_review_users FOREIGN KEY (user_id) REFERENCES users(user_id),
	CONSTRAINT FK_review_office_details FOREIGN KEY(office_id) REFERENCES office_details(office_id)
);

CREATE TABLE review_reply(
	review_reply_id SERIAL,
	review_id INT,
	user_id INT, 
	review_date DATE DEFAULT now(),
	review_reply TEXT NOT NULL,
	
	CONSTRAINT PK_review_reply PRIMARY KEY (review_reply_id),
	CONSTRAINT FK_review_reply_review FOREIGN KEY (review_id) REFERENCES review(review_id),
	CONSTRAINT FK_review_reply_users FOREIGN KEY (user_id) REFERENCES users(user_id)
);

CREATE TABLE employee_schedule(
	schedule_id SERIAL,
	employee_id INT NOT NULL,
	start_time TIME NOT NULL,
	end_time TIME NOT NULL,
	
	CONSTRAINT PK_employee_schedule PRIMARY KEY (schedule_id),
	CONSTRAINT FK_employee_schedule_employee FOREIGN KEY (employee_id) REFERENCES employee(employee_id)
);

CREATE TABLE appointment(
	appointment_id SERIAL,
	created_time TIMESTAMP NOT NULL,
	patient_id INT NOT NULL,
	employee_id INT NOT NULL,
	appointment_date DATE NOT NULL,
	start_time TIME NOT NULL,
	end_time TIME NOT NULL,
		
	CONSTRAINT PK_appointment PRIMARY KEY (appointment_id),
	CONSTRAINT FK_appointment_employee FOREIGN KEY (employee_id) REFERENCES employee(employee_id),
	CONSTRAINT FK_appointment_patient FOREIGN KEY (patient_id) REFERENCES patient(patient_id)
);

--ROLLBACK;
COMMIT TRANSACTION;

