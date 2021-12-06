DROP TABLE IF EXISTS TBL_EMPLOYEES;

CREATE TABLE MEMBERS (
  id INT AUTO_INCREMENT  PRIMARY KEY,
  name VARCHAR(250) NOT NULL,
  sex VARCHAR(1) NOT NULL,
  email VARCHAR(250) NOT NULL,
  skills VARCHAR(500) NOT NULL,
  mainSkill VARCHAR(50),
  status VARCHAR(20) NOT NULL,
  UNIQUE KEY member_email_UNIQUE (email)
);