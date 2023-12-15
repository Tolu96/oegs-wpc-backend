ALTER TABLE WorkingHours
DROP COLUMN break_time,
ADD employee_id binary(16) NOT NULL,
ADD client_id binary(16) NOT NULL,
ADD FOREIGN KEY (employee_id) REFERENCES Employee(employee_id),
ADD FOREIGN KEY (client_id) REFERENCES Client(client_id);