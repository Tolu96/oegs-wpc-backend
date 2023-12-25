INSERT INTO Employee (employee_id,first_name,last_name,address,postcode,city,gender,nationality,employed_since,employment_active, remaining_vacation)
VALUES ((UNHEX(REPLACE("db8c2483-25fd-4da3-81da-4acc7572d271", "-", ""))),'TestVorname1','TestNachname1','Testadresse 1',
12345,'Teststadt',0,1, CURRENT_DATE ,TRUE, 24);

INSERT INTO Employee (employee_id,first_name,last_name,address,postcode,city,gender,nationality,employed_since,employment_active,remaining_vacation)
VALUES ((UNHEX(REPLACE("e4d55aa6-99df-4ecd-a500-6fdbe34aee75", "-", ""))),'TestVorname2','TestNachname2','Testadresse 2',
12345,'Teststadt',1,2, null, FALSE, 0);

INSERT INTO Employee (employee_id,first_name,last_name,address,postcode,city,gender,nationality,employed_since,employment_active,remaining_vacation)
VALUES ((UNHEX(REPLACE("0121ed12-c88b-4272-b004-c682114d4d7c", "-", ""))),'TestVorname3','TestNachname3','Testadresse 3',
12345,'Teststadt',2,3, null, FALSE, 0);

INSERT INTO Client (client_id,client_name,address,postcode,city)
VALUES ((UNHEX(REPLACE("7a8d385c-c644-43fd-b36b-1281b3bf223f", "-", ""))),'Test Client 1','Clientstrasse 1',12345,
'Clientstadt');

INSERT INTO Client (client_id,client_name,address,postcode,city)
VALUES ((UNHEX(REPLACE("8b64d064-ec91-4257-a570-1ce37c56d3d9", "-", ""))),'Test Client 2','Clientstrasse 2',12345,
'Clientstadt');

INSERT INTO Client (client_id,client_name,address,postcode,city)
VALUES ((UNHEX(REPLACE("e572f3d3-2ffc-44d0-9ff8-110db8cfb4a2", "-", ""))),'Test Client 3','Clientstrasse 3',12345,
'Clientstadt');

