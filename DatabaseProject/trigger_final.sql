Drop table logged;
drop sequence TransacID_s ;
DROP TRIGGER check_tranr;

Create table logged(
Username Varchar2(10),
emp_no  number(3)
);




/
CREATE SEQUENCE TransacID_s 
  START WITH 6 
  INCREMENT BY 1;
/
SET SERVEROUTPUT ON;
CREATE OR REPLACE TRIGGER check_tranr
  before INSERT OR UPDATE OR DELETE ON EMPT
  FOR EACH ROW
  DECLARE
  n_username Varchar2(10);
  n_empid    Number(3);
  n_transid  Number(3);
BEGIN
n_transid := TransacID_s.nextVAL;
Select Username into n_username from logged;
Select Emp_no into n_empid from logged  where Username = n_username;
Insert into transaction values(n_transid, n_username, n_empid, SYSDATE);
END;
/
