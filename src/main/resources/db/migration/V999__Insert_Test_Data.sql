INSERT INTO Employee (employee_id,firstName,lastName,address,postcode,city,gender,nationality,employedSince,isEmployed)
VALUES ((UNHEX(REPLACE("db8c2483-25fd-4da3-81da-4acc7572d271", "-", ""))),'TestVorname','TestNachname','Testadresse',
12345,'TestStadt1',0,1, CURRENT_TIMESTAMP ,TRUE);

INSERT INTO Employee (employee_id,firstName,lastName,address,postcode,city,gender,nationality,isEmployed)
VALUES ((UNHEX(REPLACE("e4d55aa6-99df-4ecd-a500-6fdbe34aee75", "-", ""))),'TestVorname1','TestNachname1','Testadresse1',
12345,'TestStadt2',1,2, FALSE);

INSERT INTO Employee (employee_id,firstName,lastName,address,postcode,city,gender,nationality,isEmployed)
VALUES ((UNHEX(REPLACE("0121ed12-c88b-4272-b004-c682114d4d7c", "-", ""))),'TestVorname2','TestNachname2','Testadresse2',
12345,'TestStadt3',2,3, FALSE);