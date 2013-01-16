drop table address;
drop table donation;
drop table member;
drop table app_user;

delete from app_user;
insert into app_user(username,user_password,user_firstname,user_lastname,is_admin,user_since) values('ushma','ushma','ushma','khakhar',0, CURDATE());
select * from app_user;
select * from donation;
CREATE TABLE app_user
(
user_id int NOT NULL AUTO_INCREMENT,
username varchar(20) not null unique,
user_password varchar(20) not null,
user_firstname varchar(255) NOT NULL,
user_lastname varchar(255) NOT NULL,
user_since datetime NOT NULL,
is_admin int(1) not null,
PRIMARY KEY (user_id)
);

CREATE TABLE member
(
member_id int NOT NULL AUTO_INCREMENT,
firstname varchar(255) NOT NULL,
lastname varchar(255) NOT NULL,
tel_no varchar(20),
email_id varchar(255),
gender varchar(1),
fax varchar(20),
mobile_no varchar(20),
member_since datetime NOT NULL,
PRIMARY KEY (member_id)
);

CREATE TABLE donation
(
donation_id int NOT NULL AUTO_INCREMENT,
member_id int,
donor_firstname varchar(64) not null,
donor_lastname varchar(64),
donation_amount double not null,
donated_for varchar(255),
donation_accepted_by int not null,
donation_date datetime NOT NULL,
PRIMARY KEY (donation_id),
FOREIGN KEY (member_id) REFERENCES member(member_id),
FOREIGN KEY (donation_accepted_by) REFERENCES app_user(user_id)
);

CREATE TABLE address
(
member_id int NOT NULL,
line1 varchar(255) NOT NULL,
line2 varchar(255) NOT NULL,
city varchar(255),
pin int(6),
FOREIGN KEY (member_id) REFERENCES member(member_id)
);

