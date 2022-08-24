use master 
go

IF EXISTS (SELECT name FROM sys.databases WHERE name = 'warehouse') 
DROP DATABASE [warehouse] 
GO

create database warehouse
go
use warehouse
go


create table warehouses
(
	wh_code varchar(100) primary key,
	wh_desc varchar(max),
	wh_status bit , 
	wh_cmt varchar(max),
	si_code varchar(100)
)
go

create table [location]
(
	loc_code varchar(100) primary key ,
	loc_desc varchar(max),
	loc_type varchar(max),
	loc_status bit,
	loc_cap float,
	loc_remain float,
	loc_holding float,
	loc_outgo float,
	wh_code varchar(100) foreign key references warehouses,
	flammable bit
)
go

create table supplier
(
	sup_code varchar(100) primary key,
	sup_name varchar(max),
	sup_address varchar(max),
	sup_email varchar(max),
	city varchar(max),
	tax_code varchar(max),
	active bit
)
go

create table [users]
(
	id int primary key identity , 
	username varchar(20),
	email varchar(50),
	[password] varchar(120),
	active bit
)
go

create table roles
(
	id int primary key identity ,
	name nvarchar(max)
)
go

create table customer
(
	cust_code varchar(50) primary key,
	cust_name varchar(max),
	address varchar(max),
	city varchar(max),
	country varchar(max),
	email varchar(max),
	phone varchar(max),
	note varchar(max),
	short_name varchar(500),
	tax_code varchar(max),
	active bit
)

------------------------------------
create table goods_data
(
	goods_no varchar(255) primary key , 
	image varchar(max),
	goods_name nvarchar(max),
	price int,
	active bit,
	goods_package varchar(100), --Loại đóng gói (thùng , bao bì),
	qty_per_package int --Số lượng trong 1 thùng
)
go

create table incomings
(
	ic_id int primary key identity,
	sup_code varchar(100) foreign key references supplier,
	vehicle varchar(100),
	driver varchar(100),
	delivery_date datetime,
	closed bit
)

create table goods_master
(
	pt_id int primary key identity,
	goods_no varchar(255) foreign key references goods_data,
	pt_qty float,
	accepted_qty float , --số lượng đã được QC kiểm tra chất lượng (vd : nhập vào 50000 , QC pass 30000)
	qc varchar(100), -- tên nhân viên QC
	pt_hold float,
	loc_code varchar(100),
	pt_date_in datetime,
	sup_code varchar(100) foreign key references supplier,
	patch_no varchar(100),
	passed bit ,
	ic_id int foreign key references incomings,
	wh_code varchar(100) foreign key references warehouses
)

create table allocate_request
(
	alc_id int primary key identity , 
	pt_id int foreign key references goods_master,
	alc_moved_qty int,
	loc_code varchar(100) foreign key references location , 
	confirm bit,
	si_code varchar(100) foreign key references warehouses,
	movement_time datetime
)

create table sale_order
(
	so_id varchar(100) primary key , 
	cust_code varchar(50) foreign key references customer(cust_code),
	order_date datetime,
	req_date datetime,
	ship varchar(100),
	confirm bit, 
	closed bit,
	currency varchar(100),
	address varchar(300),
	complete bit
)

create table so_detail
(
	id int primary key identity,
	so_id varchar(100) foreign key references sale_order,
	goods_no varchar(255) foreign key references goods_data,
	quantity int , 
	picked int,
	discount int,
	booked int
)

create table issue_order
(
	id int primary key identity ,
	locations varchar(200),
	quantity int,
	goods_name varchar(200),
	so_id varchar(100), 
	closed bit,
	pt_id int foreign key references goods_master,
	si_code varchar(200),
	movemen_date datetime,
	
)

create table transactions
(
	id int primary key identity ,
	type varchar(100),
	goods_name varchar(200),
	from_loc varchar (100),
	to_loc varchar(100),
	quantity int
)
go
create table unqualified
(
	id int primary key identity ,
	goods_name varchar(200),
	quantity int,
	so_id varchar(100),
	status int,
	si_code varchar(200),
	action_qty int
)
insert into warehouses values('WH007','Warehouse 7',1,'des')







