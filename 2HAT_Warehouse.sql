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
	wh_cmt varchar(max)
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
	TaxCode varchar(max),
	active bit
)
go

create table [users]
(
	id int primary key identity , 
	username varchar(20),
	email varchar(50),
	[password] varchar(120)
)
go

create table roles
(
	id int primary key identity ,
	name nvarchar(max),
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
	goods_no varchar(200) primary key , 
	image varchar(max),
	goods_name nvarchar(max),
	price int,
	active bit,
	goods_package varchar(100), --Loại đóng gói (thùng , bao bì),
	qty_per_package int, --Số lượng trong 1 thùng
)
go

create table incomings
(
	ic_id int primary key identity,
	sup_code varchar(100) foreign key references supplier,
	vehicle varchar(100),
	driver varchar(100),
	delivery_date datetime,
	closed bit,
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



