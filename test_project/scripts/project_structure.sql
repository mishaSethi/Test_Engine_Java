CREATE DATABASE IF NOT EXISTS test_engine_pro;
USE test_engine_pro;

CREATE TABLE `logininfo` (
  `username` varchar(100) NOT NULL,
  `password` varchar(100) default NULL,
  `rolename` varchar(50) default NULL,
  `lastlogin` datetime default NULL,
  `emailid` varchar(100) default NULL,
  PRIMARY KEY  (`username`),
  UNIQUE KEY `emailid` (`emailid`)
);

CREATE TABLE `subjectinfo` (
  `subjectid` varchar(20) NOT NULL,
  `subjectname` varchar(100) default NULL,
  `description` text,
  PRIMARY KEY  (`subjectid`)
);

CREATE TABLE `sectioninfo` (
  `sectionid` varchar(20) NOT NULL,
  `sectionname` varchar(100) default NULL,
  `subjectid` varchar(20) default NULL,
  PRIMARY KEY  (`sectionid`),
  KEY `subjectid` (`subjectid`),
  CONSTRAINT `sectioninfo_ibfk_1` FOREIGN KEY (`subjectid`) REFERENCES `subjectinfo` (`subjectid`)
);

CREATE TABLE `testinfo` (
  `testid` int(11) NOT NULL auto_increment,
  `testname` varchar(50) default NULL,
  `subjectid` varchar(20) default NULL,
  `testlevel` enum('tough') default NULL,
  `totalquestion` int(11) default NULL,
  `username` varchar(100) default NULL,
  PRIMARY KEY  (`testid`),
  KEY `subjectid` (`subjectid`),
  KEY `username` (`username`),
  CONSTRAINT `testinfo_ibfk_1` FOREIGN KEY (`subjectid`) REFERENCES `subjectinfo` (`subjectid`),
  CONSTRAINT `testinfo_ibfk_2` FOREIGN KEY (`username`) REFERENCES `logininfo` (`username`)
);

CREATE TABLE `testquestion` (
  `questionid` int(11) NOT NULL auto_increment,
  `questiontext` text,
  `optiona` text,
  `optionb` text,
  `optionc` text,
  `optiond` text,
  `answer` enum('a','b','c','d') default NULL,
  `sectionid` varchar(20) default NULL,
  `testid` int(11) default NULL,
  `marks` int(11) default NULL,
  PRIMARY KEY  (`questionid`),
  KEY `sectionid` (`sectionid`),
  KEY `testid` (`testid`),
  CONSTRAINT `testquestion_ibfk_1` FOREIGN KEY (`sectionid`) REFERENCES `sectioninfo` (`sectionid`),
  CONSTRAINT `testquestion_ibfk_2` FOREIGN KEY (`testid`) REFERENCES `testinfo` (`testid`)
);

INSERT INTO `subjectinfo` (`subjectid`,`subjectname`,`description`) VALUES 
 ('C','C programming','C is a structured programming language.'),
 ('C++','C++ programming','C++ is oops oriented language.'),
 ('DS','Data Structures','A data structure defines how data is stored in the computer system.'),
 ('Java','Java programming','A java is purely OOPS oriented language.');
 
 INSERT INTO `logininfo` (`username`,`password`,`rolename`,`lastlogin`,`emailid`) VALUES 
 ('Admin','Admin@1234','Admin',SYSDATE(),'admin@gmail.com'),
 ('Raman','12345678','Student',SYSDATE(),'raman@gmail.com'),
 ('Misha','misha@1234','User',SYSDATE(),'misha@gmail.com');
 
  INSERT INTO `logininfo` (`username`,`password`,`rolename`,`lastlogin`,`emailid`) VALUES 
 ('Nandini','Nandini@1234','Student',SYSDATE(),'nandini@gmail.com');
 
 INSERT INTO `sectioninfo` (`sectionid`,`sectionname`,`subjectid`) VALUES 
 ('C++Q','Check Your C++ Skills','C++'),
 ('CQ','Check Your C Skills','C'),
 ('DSQ','Check Your Data StructureSkills','DS'),
 ('JavaQ','Check Your Java Skills','Java');
 
 INSERT INTO `testinfo` (`testid`,`testname`,`subjectid`,`testlevel`,`totalquestion`,`username`) VALUES 
 (1,'Check Your C Skills','C','tough',10,'Misha'),
 (2,'Check Your C++ Skills','C++','tough',10,'Misha'),
 (3,'Check Your Java Skills','Java','tough',10,'Misha'),
 (4,'Check Your Data Structure Skills','DS','tough',10,'Misha');
 
 INSERT INTO `testquestion` (`questionid`,`questiontext`,`optiona`,`optionb`,`optionc`,`optiond`,`answer`,`sectionid`,`testid`,`marks`) VALUES 
 (12,' Which of the following is not an operator in C?',',','sizeof','~','None of the mentioned','d','CQ',1,1),
 (14,' What is the output of the code?\r\n #include <stdio.h>\r\nint main()\r\n{\r\nint a = 10, b = 20, c = 30;\r\nif (c > b > a)\r\nprintf(\"TRUE\");\r\nelse\r\nprintf(\"FALSE\");\r\nreturn 0;\r\n}','True','False','Compiler Error','Depend on compiler','b','CQ',1,1),
 (15,' What is the output of code given below:\r\n #include<stdio.h> \r\nint main() \r\n{ \r\n   int a; \r\n   char *x; \r\n   x = (char *) &a; \r\n   a = 512; \r\n   x[0] = 1; \r\n   x[1] = 2; \r\n   printf(\"%d\\n\",a);   \r\n   return 0; \r\n}','Machine Dependent','513','258','Compiler Error','a','CQ',1,1),
 (16,' Find the output of code?\r\n#include<stdio.h>\r\nint main()\r\n{    int  x=10;\r\n    printf(“%d,%d,%d,\\n”,x<=10,x=40,x>=10);\r\n    return 0;\r\n}','1,1,1','0,40,1','0,1,1','1,40,1','d','CQ',1,1),
 (17,' What is the output of this code?\r\n#include<stdio.h>\r\nvoid main()\r\n{   int  x=5.3%2;\r\n    printf(“Value of x is %d”,x);\r\n}','Value of  x is 2.3','Value of x is 1','Value of  x is 0.3','Compile time errors','d','CQ',1,1),
 (18,' What will be the output of the  program?\r\n#include<stdio.h>\r\nvoid main()\r\n{    int  j=1;\r\n     printf(“%d”,j+++j+++j);\r\n}','7','6','3','9','b','CQ',1,1),
 (19,' What is the output of the code?\r\n#include<stdio.h>\r\nint main()\r\n{        int i=3;\r\n         int l=i/-2;\r\n         int k=i%-2;\r\n         printf(“%d%d\\n”,l,k);\r\n         return 0;\r\n}','Compile time error','-1       1','1     -1','Implementation defined','b','CQ',1,1),
 (20,' What is the output of the code?\r\n#include<stdio.h>\r\nvoid main()\r\n{       if(7&8)\r\n        printf(“Honesty”)\r\n        If((~7& 0x000f)==8)\r\n        printf(“is the best policy\\n”);\r\n}','Honesty is the best policy','Honesty','is the best  policy','None','c','CQ',1,1),
 (21,' Which  bitwise operator is suitable for\r\n       turning off a particular bit in a number?','&&','&','|','!','b','CQ',1,1),
 (22,' What is the output of the code?\r\n#include<stdio.h>\r\nvoid main{\r\nint *ptr,a=10;\r\nptr=&a;\r\n*ptr+=1;\r\nprintf(“%d,%d/n”,*ptr,a);\r\n}','10,10','10,11','11,10','11,11','d','CQ',1,1),
 (23,'Which of the following operators\r\n       can’t be overloaded?','::','+','-','[]','a','CQ',1,1),
 (24,'What is the output of this code?\r\n#include<iostream.h>\r\nint main(){\r\nint i;\r\nchar *arr[]={“C”,”C++”,”JAVA”,”VBA”};\r\nchar *(*ptr)[4]=&arr;\r\ncout<<++(*ptr)[2];\r\nreturn 0;\r\n}','ava','JAVA','C++','C','b','CQ',1,1),
 (25,'What is the output of the code?\r\n#include<iostream.h>\r\nint main()\r\n{    int a[2][4]={3,6,9,12,15,18,21,24};\r\n     cout<<*(a[1]+2)<<*(*(a+1)+2)<<2[1[a]];\r\n     return 0;\r\n}','15,18,20','21,21,21','24,24,24','Compile time error','b','CQ',1,1),
 (26,' Which of the following is not a valid \r\n       variable name declaration?','int_a3;','int a_3;','int 3_a;','int_3a;','c','CQ',1,1),
 (27,' All keywords in C are in','Lower case letters','Upper case','Camel case','None','a','CQ',1,1),
 (28,' What is the output of this code?\r\n#include<stdio.h>\r\nint main()\r\n{   enum{ORANGE=5,MANGO,BANANA=4,PEACH};\r\n    printf(“PEACH=%d\\n”,PEACH);\r\n}','PEACH =3','PEACH=4','PEACH=5','PEACH=6','c','CQ',1,1),
 (33,'Which among the following has\n the highest precedence?','&','<<','sizeof()','&&','c','CQ',1,1),
 (34,'What  will be output if you\n will execute following c code?\n#include<stdio.h>\nvoid main()\n{    int arr[][3]={{1,2},{3,4,5},{5}};\nprintf(“%d%d%d”,sizeof(arr),arr[0][2],arr[1][2]);\n}','6    0     4','6     1     5','18    0     5','18     1       5','c','CQ',1,1),
 (35,'Which of the following correctly accesses\n the seventh element stored in food, \nan array with 100 elements?','food[6];','food[7];','food(7);','food;','a','CQ',1,1),
 (36,'Which of the following sets first n\n characters of a string to a given \n character?','strinit()','strnset()','strset()','strcset()','b','CQ',1,1);
 
 SELECT * FROM logininfo;
 
 CREATE TABLE `testschedule` (
  `scheduleid` int(11) NOT NULL auto_increment,
  `fusername` varchar(100) default NULL,
  `susername` varchar(100) default NULL,
  `testdate` date default NULL,
  `testid` int(11) default NULL,
  PRIMARY KEY  (`scheduleid`),
  KEY `fusername` (`fusername`),
  KEY `susername` (`susername`),
  KEY `testid` (`testid`),
  CONSTRAINT `testschedule_ibfk_1` FOREIGN KEY (`fusername`) REFERENCES `logininfo` (`username`),
  CONSTRAINT `testschedule_ibfk_2` FOREIGN KEY (`susername`) REFERENCES `logininfo` (`username`),
  CONSTRAINT `testschedule_ibfk_3` FOREIGN KEY (`testid`) REFERENCES `testinfo` (`testid`)
);
CREATE TABLE resultinfo
(
	resultid INT primary key auto_increment,
    username varchar(100),
    testid int,
    totalquestion int,
    attemptquestion int,
    rightquestion int,
    obtainedmarks int,
    resultdate datetime
);