CREATE TABLE IF NOT EXISTS BreakTime(
    break_time_id binary(16) PRIMARY KEY,
    employee_id binary(16) NOT NULL,
    break_start DATETIME NOT NULL,
    break_end DATETIME NOT NULL,
    status BIGINT NOT NULL DEFAULT 0,
    created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    modified_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (employee_id) REFERENCES Employee(employee_id)
);