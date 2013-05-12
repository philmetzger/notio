CREATE TABLE FEEDITEMS (ID VARCHAR, TEXT VARCHAR, CATEGORY VARCHAR, AUTHOR VARCHAR, PRIMARY KEY (ID));
CREATE TABLE accounts (
  account_id VARCHAR NOT NULL,
  username VARCHAR NOT NULL,
  password VARCHAR NOT NULL,
  PRIMARY KEY (account_id)
);
CREATE TABLE account_roles (
  account_id VARCHAR NOT NULL,
  authority VARCHAR NOT NULL,
  PRIMARY KEY (account_id),
  CONSTRAINT FK_account_roles FOREIGN KEY (account_id) REFERENCES accounts (account_id)
);
insert into accounts values('philip', 'philip', 'philip');
insert into account_roles values('philip', 'ROLE_USER');