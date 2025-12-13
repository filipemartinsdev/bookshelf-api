CREATE TABLE reading_status (
    id INT PRIMARY KEY,
    description VARCHAR(25) NOT NULL UNIQUE
);

INSERT INTO reading_status(id, description)
VALUES (0, 'unread'), (1, 'reading'), (2, 'read');