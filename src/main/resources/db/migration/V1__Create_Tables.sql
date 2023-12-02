CREATE TABLE IF NOT EXISTS Employee(
	employee_id BINARY(16) PRIMARY KEY,
	first_name VARCHAR(255) NOT NULL,
	last_name VARCHAR(255) NOT NULL,
	address VARCHAR(255) NOT NULL,
	postcode INT NOT NULL,
	city VARCHAR(189) NOT NULL,
	gender TINYINT NOT NULL,
	nationality TINYINT NOT NULL,
	employed_since DATE,
	is_employed BOOLEAN NOT NULL DEFAULT FALSE,
	created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
	modified_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP
);
CREATE TABLE IF NOT EXISTS Client(
	client_id BINARY(16) PRIMARY KEY,
	client_name VARCHAR(255) NOT NULL,
	address VARCHAR(255) NOT NULL,
	postcode INT NOT NULL,
	city VARCHAR(189) NOT NULL,
	created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
	modified_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP
);
CREATE TABLE IF NOT EXISTS Absence(
    absence_id BINARY(16) PRIMARY KEY,
    employee_id BINARY(16) NOT NULL,
    absence_status TINYINT NOT NULL,
    description VARCHAR(255),
    absence_start DATETIME NOT NULL,
    absence_end DATETIME NOT NULL,
    total_vacation int NOT NULL,
    remaining_vacation int NOT NULL,
    created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    modified_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (employee_id) REFERENCES Employee(employee_id)
);
CREATE TABLE IF NOT EXISTS WorkingHours(
    working_hours_id BINARY(16) PRIMARY KEY,
    worked_hours_today float NOT NULL DEFAULT 0,
    worked_hours_sum float NOT NULL DEFAULT 0,
    work_hour_start DATETIME NOT NULL,
    work_hour_end DATETIME NOT NULL,
    break_time float,
    created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    modified_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP
);
CREATE TABLE IF NOT EXISTS EmployeeAssignment(
    employee_id binary(16) NOT NULL,
    client_id binary(16) NOT NULL,
    working_hours_id binary(16) NOT NULL,
    PRIMARY KEY (employee_id, client_id),
    FOREIGN KEY (employee_id) REFERENCES Employee(employee_id),
    FOREIGN KEY (client_id) REFERENCES Client(client_id),
    FOREIGN KEY (working_hours_id) REFERENCES WorkingHours(working_hours_id)
);