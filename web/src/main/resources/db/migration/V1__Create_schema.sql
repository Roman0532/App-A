CREATE TABLE USER (
  ID       INT AUTO_INCREMENT,
  LOGIN    VARCHAR(255),
  PASSWORD VARCHAR(255),
  SALT     VARCHAR(255),
  CONSTRAINT USER_PK PRIMARY KEY ("ID"),
  UNIQUE (LOGIN)
);

CREATE TABLE USER_RES (
  ID      INT AUTO_INCREMENT PRIMARY KEY,
  USER_ID INT,
  PATH    VARCHAR(255),
  ROLE    VARCHAR(255),
  CONSTRAINT USER_RES_FK FOREIGN KEY (USER_ID)
  REFERENCES USER (ID)
);

CREATE INDEX user_role
  ON USER_RES (USER_ID, ROLE);

CREATE TABLE ACCOUNTING (
  ID         INT AUTO_INCREMENT PRIMARY KEY,
  LOGIN      VARCHAR(255),
  ROLE       VARCHAR(255),
  DATA_START DATE,
  DATA_END   DATE,
  VOLUME     INT,
  AUTHORITY_ID INT,
  CONSTRAINT ACCOUNTING_FK FOREIGN KEY (AUTHORITY_ID)
  REFERENCES USER_RES (ID)
);
