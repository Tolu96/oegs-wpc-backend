ALTER TABLE WorkingHours MODIFY employee_id binary(16) NOT NULL AFTER work_hour_end;
ALTER TABLE WorkingHours MODIFY client_id binary(16) NOT NULL AFTER employee_id;