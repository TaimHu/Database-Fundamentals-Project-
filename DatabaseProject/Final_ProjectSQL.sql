DROP TABLE EMPT;
DROP TABLE DEPT1;
DROP TABLE VEHICLE;
DROP TABLE PROJECT;
drop table grade;
DROP TABLE ALLOWANCE;
DROP TABLE POSITION;
drop table dept_project;
DROP TABLE LOGIN;
Drop table Position_Allowance;
drop table transaction;

Create TABLE Dept1(
Dept_no Number(2),
Dept_name VARCHAR2(30),
Description VARCHAR2(30),
total_Emp_no Number(4),
Constraint Dept_Dept_no_pk Primary Key (Dept_no),
Constraint check_dept_emp_no check(total_Emp_no <= 20 and total_Emp_no >= 5));

insert into dept1 values (1,'Sales', 'Sales department', 12);
insert into dept1 values (2,'Management', 'Management department', 9);
insert into dept1 values (3,'Accounting', 'Accounting department', 20);
insert into dept1 values (4,'Civil', 'Civil department', 5);
insert into dept1 values (5,'Traffic', 'Traffic department', 14);


Create TABLE Grade(
Grade_Type char(1),
Max_Salary Number(7),
Min_Salary Number(7),
Constraint Grade_Grade_Type_pk Primary Key (Grade_Type));

Insert into Grade values ('A',50000,40000);
Insert into Grade values ('B',39999,30000);
Insert into Grade values ('C',29999,20000);
Insert into Grade values ('D',19999,10000);
Insert into Grade values ('E',9999,1000);


Create TABLE Position(
Pos_ID Number(3),
Pos_Name VARCHAR2(15),
Constraint Position_Pos_Id_pk Primary Key (Pos_ID));

Insert into Position values (1,'Manager');
Insert into Position values (2,'Administrators');
Insert into Position values (3,'Clerk');
Insert into Position values (4,'Accounter');
Insert into Position values (5,'Worker');


Create TABLE EMPt(
Emp_no Number(4) not null,
Name VARCHAR2(18) not null,
Phone Number(8) unique not null,
Gender char(1) not null,
Hire_Date Date not null,
DOB DATE NOT NULL,
Salary Number(5) not null,
Commission Number(4),
grade char(1) not null,
Dept_no Number(2),
PositionId Number(3),
Constraint Emp_POSID_fk foreign key (PositionId) references Position(Pos_ID),
Constraint EMP_Gender_check CHECK(Gender = 'M' OR  Gender ='F'),
Constraint Emp_Emp_no_pk Primary Key(Emp_no),
Constraint Emp_Dept_no_fk Foreign Key (dept_no) references dept1 (dept_no),
Constraint Emp_grade_fk Foreign Key (grade) references grade (grade_type),
Constraint Emp_grade_check check(( grade = 'A' and salary <= 50000 and salary >= 40000) 
or (grade = 'B' and salary <= 39999 and salary >= 30000)
or (grade = 'C' and salary <= 29999 and salary >= 20000)
or (grade = 'D' and salary <= 19999 and salary >= 10000)
or (grade = 'E' and salary <= 9999 and salary >= 1000)));
 
/
SET SERVEROUTPUT ON;
CREATE OR REPLACE TRIGGER check_birth_date
  BEFORE INSERT OR UPDATE ON EMPT
  FOR EACH ROW
BEGIN
  IF( MONTHS_BETWEEN(:new.hire_date, :new.DOB) / 12 > 60 or 
      MONTHS_BETWEEN(:new.hire_date, :new.DOB) / 12 <18 or MONTHS_BETWEEN(:new.hire_date, :new.DOB) / 12 = 0 )
  THEN
    RAISE_APPLICATION_ERROR( 
      -20001, 
      'Employees age must be between 18 and 60 years' );
  END IF;
END;
/




insert into Empt values 
(1,'Ahmed Khalid',42342213,'M',to_date( '30-03-2021', 'dd-mm-yy')
                     ,to_date( '20-03-1980', 'dd-mm-yy'),33200,2000,'B',1,1);
                     
insert into Empt values 
(2,'Mohammed Azim',44272831,'M',to_date( '19-04-2012', 'dd-mm-yy')
                     ,to_date( '20-03-1990', 'dd-mm-yy'),42500,null,'A',3,3);   
                    
insert into Empt values 
(3,'Mohammed Ali',55823011,'M',to_date( '03-06-2019', 'dd-mm-yy')
                     ,to_date( '17-10-2000', 'dd-mm-yy'),10000,800,'D',4,2);
                     
insert into Empt values 
(4,'Assem Ziyad',77293120,'M',to_date( '23-08-2008', 'dd-mm-yy')
                     ,to_date( '17-10-1960', 'dd-mm-yy'),49000,5000,'A',2,4); 

insert into Empt values 
(5,'Ayman Riyad',66729321,'M',to_date( '23-08-2005', 'dd-mm-yy')
                     ,to_date( '31-01-1979', 'dd-mm-yy'),23000,8000,'C',5,5);

insert into Empt values 
(6,'Marwa Ibrahim',44283219,'F',to_date( '23-08-2017', 'dd-mm-yy')
                     ,to_date( '31-01-1984', 'dd-mm-yy'),9000,9000,'E',3,4);
                     

Create TABLE Vehicle(
VIN Number(5),
Model Number(5),
Make VARCHAR2(10),
Next_Main_Date Date,
Emp_no Number(4) unique,
Constraint Vehicle_VIN_pk Primary Key (VIN),
Constraint Vehicle_Emp_no_fk Foreign Key (Emp_no) references EMPt (Emp_no)on delete cascade);

/
SET SERVEROUTPUT ON;
CREATE OR REPLACE TRIGGER check_Maintenance
  BEFORE INSERT OR UPDATE ON Vehicle
  FOR EACH ROW
BEGIN
  IF( MONTHS_BETWEEN(:new.Next_Main_Date, SYSDATE) < 3 
  or MONTHS_BETWEEN(:new.Next_Main_Date, SYSDATE) > 3)
  THEN
    RAISE_APPLICATION_ERROR( 
      -20001, 
      'Vehicle maintenance should be within 3 months of current date. i.e => ' || SYSDATE ||chr(10)||
      '            so expected maintenance should be => ' || Add_months(SYSDATE, 3) );
  END IF;
END;
/
Insert into vehicle values(1,2005,'BMW',to_date(Add_months(SYSDATE, 3)),1);
Insert into vehicle values(2,2012,'Toyota',to_date( Add_months(SYSDATE, 3)),2);
Insert into vehicle values(3,2020,'KIA',to_date( Add_months(SYSDATE, 3)),3);
Insert into vehicle values(4,2016,'Nissan',to_date( Add_months(SYSDATE, 3)),4);
Insert into vehicle values(5,2019,'Toyota',to_date( Add_months(SYSDATE, 3)),6);

Create TABLE Project(
Project_Id Number(3),
Project_Name VARCHAR2(20),
Constraint Porject_ID_pk Primary Key (Project_ID));

Insert into project values(1, 'Civilization');
Insert into project values(2, 'Management');
Insert into project values(3, 'Research');
Insert into project values(4, 'GreenHouse');
Insert into project values(5, 'History');


create table dept_project(
dept_id number(2),
proj_id number(3),
constraint dp_deptid_fk foreign key (dept_id) references dept1(dept_no),
constraint dp_projid_fk foreign key (proj_id) references project(Project_Id));

insert into dept_project values(1,1);
insert into dept_project values(2,1);
insert into dept_project values(3,2);
insert into dept_project values(4,2);
insert into dept_project values(5,4);


Create TABLE Login(
Username VARCHAR2(10),
Password VARCHAR2(10),
Emp_no Number(4) unique,
Constraint Login_Username_pk Primary Key (Username),
Constraint Grade_Emp_no_fk Foreign Key (Emp_no) references EMPT (Emp_no) on delete cascade);

insert into login values ('aa173292','admin', 1);
insert into login values ('aa532812','12513', 2);
insert into login values ('aa903723','Ali3124', 3);
insert into login values ('aa832012','Moahmmed42', 4);
insert into login values ('aa903271','Marwan32', 5);
insert into login values ('aa423212','Mar238wa', 6);




Create TABLE Allowance(
A_ID Number(3),
Allow_name VARCHAR2(20),
Descrip VARCHAR2(30),
Constraint Allowance_Allow_ID_pk Primary Key (A_ID));

Insert into Allowance values(1,'Fuel allowance','800');
Insert into Allowance values(2,'house allowance','2000');
Insert into Allowance values(3,'social allowance','400');
Insert into Allowance values(4,'managers allowance','1200');
Insert into Allowance values(5,'uniform allowance','250');


Create Table Position_Allowance(
Position_ID number(3),
Allowance_ID number(3),
Constraint P_A_AllowID_fk Foreign Key (Allowance_ID) references Allowance (A_ID),
Constraint P_A_PosID_fk Foreign Key (Position_ID) references Position (Pos_ID),
Constraint ch_posID CHECK((Position_ID = 1 and (Allowance_ID >= 1  and Allowance_ID <= 4)) or 
(Position_ID = 2 and (Allowance_ID = 1  or Allowance_ID = 3)) or
(Position_ID = 3 and (Allowance_ID = 3  or Allowance_ID = 5)) or
(Position_ID = 4 and (Allowance_ID = 2 or Allowance_ID = 3  or Allowance_ID = 5)))); 


Insert into Position_Allowance values(1, 1);
Insert into Position_Allowance values(1, 2);
Insert into Position_Allowance values(1, 3);
Insert into Position_Allowance values(1, 4);

Insert into Position_Allowance values(2, 1);
Insert into Position_Allowance values(2, 3);

Insert into Position_Allowance values(3, 3);
Insert into Position_Allowance values(3, 5);

Insert into Position_Allowance values(4, 2);
Insert into Position_Allowance values(4, 3);
Insert into Position_Allowance values(4, 5);




Create TABLE Transaction(
Trans_no Number(3),
Username VARCHAR2(10),
Emp_no Number(4),
Date_Time DATE,
Constraint Transaction_Trans_no_pk primary key (Trans_no));

Insert into transaction values (1, 'aa173292'
,1,to_date( '05-07-2021-18-34-23', 'dd-mm-yy-hh24-mi-ss'));

Insert into transaction values (2, 'aa532812'
,2,to_date( '23-01-2019-15-22-10', 'dd-mm-yy-hh24-mi-ss'));

Insert into transaction values (3, 'aa903723',
3,to_date( '09-12-2020-20-56-40', 'dd-mm-yy-hh24-mi-ss'));

Insert into transaction values (4, 'aa832012',
4,to_date( '15-09-2004-12-37-10', 'dd-mm-yy-hh24-mi-ss'));

Insert into transaction values (5, 'aa903271',
5,to_date( '19-05-2012-22-00-55', 'dd-mm-yy-hh24-mi-ss'));


commit;

select * from dept1;
select * from grade;
select * from empt;
select * from vehicle;
select * from login;
select * from project;
select * from dept_project;
select * from allowance;
select * from position;
select * from Position_Allowance;
select * from transaction;

