--DROP TABLE IF EXIST LEGAL_BASE_INFO;

CREATE TABLE LEGAL_BASE_INFO (
  id INT AUTO_INCREMENT PRIMARY KEY,
  organization_name varchar(250),
  address varchar(500),
  organization_code varchar(250)
);