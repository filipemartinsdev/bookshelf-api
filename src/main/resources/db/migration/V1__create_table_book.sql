CREATE TABLE book(
	id SERIAL PRIMARY KEY,
	title VARCHAR(50) UNIQUE NOT NULL,
	subtitle VARCHAR(50),
	author VARCHAR(50) NOT NULL,
	publisher VARCHAR(25) NOT NULL,
	pages INT NOT NULL,
	status INT  REFERENCES reading_status NOT NULL,
	rating FLOAT,
	book_cover BYTEA NOT NULL
);