BEGIN TRANSACTION;

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
	employee_id SERIAL UNIQUE,
	user_id INT NOT NULL,
	first_name VARCHAR (64) NOT NULL,
	last_name VARCHAR (64) NOT NULL,
	office_id INT NOT NULL,
	
	CONSTRAINT PK_employee_id PRIMARY KEY (employee_id),
	CONSTRAINT FK_employee_users FOREIGN KEY(user_id) REFERENCES users(user_id),
	CONSTRAINT FK_employee_office_details FOREIGN KEY(office_id) REFERENCES office_details(office_id)
);

CREATE TABLE  patient (
	patient_id SERIAL UNIQUE,
	first_name VARCHAR(64) NOT NULL,
	last_name VARCHAR(64) NOT NULL,
	user_id INT NOT NULL,
	
	CONSTRAINT PK_patient PRIMARY KEY(patient_id),
	CONSTRAINT FK_patient_users FOREIGN KEY(user_id) REFERENCES users(user_id)
);




--ROLLBACK;
COMMIT TRANSACTION;

