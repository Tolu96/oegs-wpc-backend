INSERT INTO Client (client_id,client_name,address,postcode,city)
VALUES ((UNHEX(REPLACE("7a8d385c-c644-43fd-b36b-1281b3bf223f", "-", ""))),'Test Client 1','Clientstrasse 1',12345,
'Clientstadt');

INSERT INTO Client (client_id,client_name,address,postcode,city)
VALUES ((UNHEX(REPLACE("8b64d064-ec91-4257-a570-1ce37c56d3d9", "-", ""))),'Test Client 2','Clientstrasse 2',12345,
'Clientstadt');

INSERT INTO Client (client_id,client_name,address,postcode,city)
VALUES ((UNHEX(REPLACE("e572f3d3-2ffc-44d0-9ff8-110db8cfb4a2", "-", ""))),'Test Client 3','Clientstrasse 3',12345,
'Clientstadt');
