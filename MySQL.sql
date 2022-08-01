-- use master; 
--  

-- IF EXISTS (SELECT name FROM sys.databases WHERE name = 'warehouse') THEN 
-- DROP DATABASE warehouse
-- END IF; 
 

create database warehouse;
 
use warehouse;
 

-- SQLINES LICENSE FOR EVALUATION USE ONLY
create table warehouses
(
	wh_code varchar(100) primary key,
	wh_desc longtext,
	wh_status tinyint , 
	wh_cmt longtext
);

-- SQLINES LICENSE FOR EVALUATION USE ONLY
create table location
(
	loc_code varchar(100) primary key ,
	loc_desc longtext,
	loc_type longtext,
	loc_status tinyint,
	loc_cap double,
	loc_remain double,
	loc_holding double,
	loc_outgo double,
	wh_code varchar(100), 
	foreign key (wh_code) references warehouses(wh_code),
	flammable tinyint
);

-- SQLINES LICENSE FOR EVALUATION USE ONLY
create table supplier
(
	sup_code varchar(100) primary key,
	sup_name longtext,
	sup_address longtext,
	sup_email longtext,
	city longtext,
	tax_code longtext,
	active tinyint
);

-- SQLINES LICENSE FOR EVALUATION USE ONLY
create table users
(
	id int primary key auto_increment , 
	username varchar(20),
	email varchar(50),
	password varchar(120)
);

-- SQLINES LICENSE FOR EVALUATION USE ONLY
create table roles
(
	id int primary key auto_increment ,
	name longtext
);

-- SQLINES LICENSE FOR EVALUATION USE ONLY
create table customer
(
	cust_code varchar(50) primary key,
	cust_name longtext,
	address longtext,
	city longtext,
	country longtext,
	email longtext,
	phone longtext,
	note longtext,
	short_name varchar(500),
	tax_code longtext,
	active tinyint
);

--  SQLINES DEMO *** ----------------
-- SQLINES LICENSE FOR EVALUATION USE ONLY
create table goods_data
(
	goods_no varchar(200) primary key , 
	image longtext,
	goods_name longtext,
	price int,
	active tinyint,
	goods_package varchar(100), 
	qty_per_package int
);

-- SQLINES LICENSE FOR EVALUATION USE ONLY
create table incomings
(
	ic_id int primary key auto_increment,
	sup_code varchar(100) ,
	foreign key(sup_code) references supplier(sup_code),
	vehicle varchar(100),
	driver varchar(100),
	delivery_date datetime(3),
	closed tinyint
);

-- SQLINES LICENSE FOR EVALUATION USE ONLY
create table goods_master
(
	pt_id int primary key auto_increment,
	goods_no varchar(200) ,
	foreign key(goods_no) references goods_data(goods_no),
	pt_qty double,
	accepted_qty double , --  SQLINES DEMO ***  được QC kiểm tra chất lượng (vd : nhập vào 50000 , QC pass 30000)
	qc varchar(100), -- tên nhân viên QC
	pt_hold double,
	loc_code varchar(100),
	pt_date_in datetime(3),
	sup_code varchar(100) ,
    foreign key (sup_code) references supplier(sup_code),
	patch_no varchar(100),
	passed tinyint ,
	ic_id int,
    foreign  key (ic_id) references incomings (ic_id),
	wh_code varchar(100) ,
	foreign key(wh_code) references warehouses(wh_code)
);

-- SQLINES LICENSE FOR EVALUATION USE ONLY
create table allocate_request
(
	alc_id int primary key auto_increment , 
	pt_id int, 
	foreign key(pt_id) references goods_master(pt_id),
	alc_moved_qty int,
	loc_code varchar(100) ,
    foreign key (loc_code) references location (loc_code) , 
	confirm tinyint,
	si_code varchar(100) ,
    foreign key (si_code) references warehouses(wh_code),
	movement_time datetime(3)
);


