INSERT INTO Employee (employee_id,first_name,last_name,address,postcode,city,gender,nationality,employed_since,is_employed)
VALUES ((UNHEX(REPLACE("db8c2483-25fd-4da3-81da-4acc7572d271", "-", ""))),'TestVorname1','TestNachname1','Testadresse 1',
12345,'Teststadt',0,1, CURRENT_TIMESTAMP ,TRUE);

INSERT INTO Employee (employee_id,first_name,last_name,address,postcode,city,gender,nationality,employed_since,is_employed)
VALUES ((UNHEX(REPLACE("e4d55aa6-99df-4ecd-a500-6fdbe34aee75", "-", ""))),'TestVorname2','TestNachname2','Testadresse 2',
12345,'Teststadt',1,2, null, FALSE);

INSERT INTO Employee (employee_id,first_name,last_name,address,postcode,city,gender,nationality,employed_since,is_employed)
VALUES ((UNHEX(REPLACE("0121ed12-c88b-4272-b004-c682114d4d7c", "-", ""))),'TestVorname3','TestNachname3','Testadresse 3',
12345,'Teststadt',2,3, null, FALSE);