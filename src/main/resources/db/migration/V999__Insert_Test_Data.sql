INSERT INTO Employee (employee_id,first_name,last_name,address,postcode,city,gender,nationality,employed_since,is_employed)
VALUES ((UNHEX(REPLACE("db8c2483-25fd-4da3-81da-4acc7572d271", "-", ""))),'TestVorname1','TestNachname1','Testadresse 1',
12345,'Teststadt',0,1, CURRENT_DATE ,TRUE);

INSERT INTO Employee (employee_id,first_name,last_name,address,postcode,city,gender,nationality,employed_since,is_employed)
VALUES ((UNHEX(REPLACE("e4d55aa6-99df-4ecd-a500-6fdbe34aee75", "-", ""))),'TestVorname2','TestNachname2','Testadresse 2',
12345,'Teststadt',1,2, null, FALSE);

INSERT INTO Employee (employee_id,first_name,last_name,address,postcode,city,gender,nationality,employed_since,is_employed)
VALUES ((UNHEX(REPLACE("0121ed12-c88b-4272-b004-c682114d4d7c", "-", ""))),'TestVorname3','TestNachname3','Testadresse 3',
12345,'Teststadt',2,3, null, FALSE);

INSERT INTO Client (client_id,client_name,address,postcode,city)
VALUES ((UNHEX(REPLACE("7a8d385c-c644-43fd-b36b-1281b3bf223f", "-", ""))),'Test Client 1','Clientstrasse 1',12345,
'Clientstadt');

INSERT INTO Client (client_id,client_name,address,postcode,city)
VALUES ((UNHEX(REPLACE("8b64d064-ec91-4257-a570-1ce37c56d3d9", "-", ""))),'Test Client 2','Clientstrasse 2',12345,
'Clientstadt');

INSERT INTO Client (client_id,client_name,address,postcode,city)
VALUES ((UNHEX(REPLACE("e572f3d3-2ffc-44d0-9ff8-110db8cfb4a2", "-", ""))),'Test Client 3','Clientstrasse 3',12345,
'Clientstadt');

INSERT INTO Absence (absence_id,employee_id,absence_status,description,absence_start,absence_end)
VALUES ((UNHEX(REPLACE("adcce215-f8c2-4935-a445-5c63a1459462", "-", ""))),
(UNHEX(REPLACE("db8c2483-25fd-4da3-81da-4acc7572d271", "-", ""))),0, "Test Description", "2023-02-01", "2023-02-10");

INSERT INTO Absence (absence_id,employee_id,absence_status,description,absence_start,absence_end)
VALUES ((UNHEX(REPLACE("0c489eea-92ba-4ed7-a6ed-dc4e3d26688a", "-", ""))),
(UNHEX(REPLACE("e4d55aa6-99df-4ecd-a500-6fdbe34aee75", "-", ""))),1, "Test Description", "2023-02-01", "2023-02-10");

INSERT INTO Absence (absence_id,employee_id,absence_status,description,absence_start,absence_end)
VALUES ((UNHEX(REPLACE("48256ebd-d1de-4063-aa6c-5320d054e7f7", "-", ""))),
(UNHEX(REPLACE("0121ed12-c88b-4272-b004-c682114d4d7c", "-", ""))),0, "Test Description", "2023-01-01", "2023-01-13");

INSERT INTO Absence (absence_id,employee_id,absence_status,description,absence_start,absence_end)
VALUES ((UNHEX(REPLACE("48246ebd-d1de-4063-aa6c-5320d054e7f7", "-", ""))),
(UNHEX(REPLACE("0121ed12-c88b-4272-b004-c682114d4d7c", "-", ""))),0, "Test Description", "2023-01-01", "2023-01-13");

INSERT INTO WorkingHours (working_hours_id,work_hour_start,work_hour_end,employee_id,client_id)
VALUES ((UNHEX(REPLACE("a670819b-184f-4929-a832-9c31fbdc8b12", "-", ""))),"2023-01-01T10:00:00", "2023-01-01T11:00:00",
(UNHEX(REPLACE("db8c2483-25fd-4da3-81da-4acc7572d271", "-", ""))),(UNHEX(REPLACE("7a8d385c-c644-43fd-b36b-1281b3bf223f", "-", ""))));

INSERT INTO WorkingHours (working_hours_id,work_hour_start,work_hour_end,employee_id,client_id)
VALUES ((UNHEX(REPLACE("a7058f82-fcbf-4200-9228-35a4ef34ef47", "-", ""))),"2023-10-10T10:00:00", "2023-10-10T11:00:00",
(UNHEX(REPLACE("e4d55aa6-99df-4ecd-a500-6fdbe34aee75", "-", ""))),(UNHEX(REPLACE("8b64d064-ec91-4257-a570-1ce37c56d3d9", "-", ""))));

INSERT INTO WorkingHours (working_hours_id,work_hour_start,work_hour_end,employee_id,client_id)
VALUES ((UNHEX(REPLACE("29a5fbc4-3843-491a-bc66-b75c56de789e", "-", ""))),"2023-10-11T10:00:00", "2023-10-11T11:00:00",
(UNHEX(REPLACE("0121ed12-c88b-4272-b004-c682114d4d7c", "-", ""))),(UNHEX(REPLACE("e572f3d3-2ffc-44d0-9ff8-110db8cfb4a2", "-", ""))));


