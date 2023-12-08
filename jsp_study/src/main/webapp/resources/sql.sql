--2022-11-23


--2023-11-27




--2023-12-04
create table comment(
cno int auto_increment,
bno int not null,
writer varchar(100) default "unknown",
content varchar(1000) not null,
regdate datetime default now(),
primary key(cno));  

--2023-12-07
alter table board add imageFile varchar(100);
