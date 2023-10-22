BEGIN TRANSACTION;

DROP TABLE IF EXISTS contact CASCADE;
DROP TABLE IF EXISTS grouping CASCADE;
DROP TABLE IF EXISTS users CASCADE;

CREATE TABLE users (
	user_id SERIAL,
	username varchar(50) NOT NULL UNIQUE,
	password_hash varchar(200) NOT NULL,
	role varchar(50),
	CONSTRAINT PK_user PRIMARY KEY (user_id)
);

CREATE TABLE grouping (
	grouping_id SERIAL,
	title VARCHAR(25) NOT NULL,
	user_id INT,

	CONSTRAINT PK_grouping_id PRIMARY KEY (grouping_id),
	CONSTRAINT FK_grouping_user FOREIGN KEY (user_id) REFERENCES users (user_id)
);

CREATE TABLE contact (
	contact_id SERIAL,
	first_name VARCHAR(25) NOT NULL,
	last_name VARCHAR(25) NOT NULL,
	address VARCHAR(100) NOT NULL,
	city VARCHAR(25) NOT NULL,
	state VARCHAR(25) NOT NULL,
	zip VARCHAR(10) NOT NULL,
	phone_number VARCHAR(25) NOT NULL,
	email VARCHAR(50) NOT NULL,
	birth_date DATE,
	note TEXT,
	grouping_id INT,

		CONSTRAINT PK_contact_id PRIMARY KEY(contact_id),
		CONSTRAINT FK_contact_grouping FOREIGN KEY (grouping_id) REFERENCES grouping(grouping_id)
);

--ROLLBACK;
COMMIT TRANSACTION;

