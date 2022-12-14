create database duan1_xay_dung_ung_dung_ban_kinh_thoi_trang_FourGlasses_9
go
use duan1_xay_dung_ung_dung_ban_kinh_thoi_trang_FourGlasses_9
go
--Tạo Bảng Login
create table Login(
    UserName varchar(max) not null,
    Passworld nvarchar(max) not null,
    Role nvarchar(10) null
)
go
Select * from Login
--Tạo Bảng NhanVien
create table NhanVien(
    MaNV varchar(10) primary key not null,
	Mahinhthucthanhtoan varchar(10)  ,
    HoTenNV nvarchar(200),
    NgaySinh date ,
    GioiTinh bit ,
    Sdt varchar(10) ,
    MaCV varchar(10) ,
	diachi nvarchar(50),
    TrangThai int,
    MatKhau nvarchar(50) null,
)
go
Select * from KhachHang

--Xóa Bảng Nhân Viên


--Tạo Bảng ChucVu
create table ChucVu(
    MaCV varchar(10) primary key not null,
    TenCV nvarchar(100),
    MoTa nvarchar(max) null
)
go

--Tạo Bảng KhachHang
create table KhachHang(
    MaKH varchar(10) primary key not null ,
    HoTenKH nvarchar(200) ,
    NgaySinh date,
    DiaChi nvarchar(200) ,
    Sdt varchar(10),
    ThanhPho nvarchar(100),
    QuocGia nvarchar(50)
)
go
select * from HoaDon where Sdt = ?

--Tạo Bảng HoaDon
create table HoaDon(
    MaHD varchar(10) primary key not null,
    MaNV varchar(10) not null,
    MaKH varchar(10) not null,
	Mahinhthucthanhtoan varchar(10) not null ,
    NgayTao date ,
    TinhTrang int ,
    TenNguoiNhan nvarchar(50) ,
    DiaChi nvarchar(100) ,
    Sdt varchar(10) ,
	tongtien  DECIMAL(20,0) DEFAULT 0,
	phantramgiamgia decimal(3,2) DEFAULT 0,
)
go
Select * from HoaDon
Select * from NhanVien
Select * from ChiTietHoaDon
Insert into HoaDon values
--('HD01','NV01','KH01','2022-11-29',0,10000,N'Nguyễn Thị Nguyệt','HCM','0935676798'),
('HD02','NV01','KH21','2022-11-30',0,50000,N'Nguyễn Văn Ngao','Hà Nội','0198371936'),
('HD03','NV01','KH07','2022-11-30',0,50000,N'Nguyễn Văn Ngao','HCM','0198371936')
Delete  from HoaDon where MaHD = 'HD02'
--Tạo Bảng ChiTietHoaDon
create table ChiTietHoaDon(
    MaHD varchar(10) not null,
    MaSP varchar(10) not null,
    SoLuong int not null,
	dongia DECIMAL(20,0) DEFAULT 0 not null,
	tiethua  DECIMAL(20,0) DEFAULT 0 not null,
	tiengiamgia  DECIMAL(20,0) DEFAULT 0 not null,
	trangthai nvarchar(100) ,
    constraint PK_ChiTietHoaDon primary key (MaHD, MaSP)
)
go
Select * from ChiTietHoaDon

create table hinhthucthanhtoan(
  Mahinhthucthanhtoan varchar(10)  PRIMARY KEY null,
	   MaNV varchar(10)  not null,
   MaHD varchar(10),
  ngaytao date DEFAULT null,
   ngaysua date DEFAULT null,
   loaihinhthanhtoan nvarchar(100) ,
   tongtienthanhtoan  DECIMAL(20,0) DEFAULT 0 ,
   trangthai nvarchar(100) 

)
go
Select * from hinhthucthanhtoan

--Tạo Bảng DongSP
create table DongSP(
    MaDSP varchar(10) primary key,
    TenDSP nvarchar(100) ,
    HinhAnh nvarchar(50) 
)
go
Insert into DongSP(MaDSP,TenDSP,HinhAnh) values
('DSP01',N'Gọng Kính','kinh.jpg'),
('DSP02',N'Mắt Kính','mat.jpg')
--Tạo Bảng MauSac
create table MauSac(
    MaMS varchar(10) primary key,
    TenMS nvarchar(100)
)
go
Insert into MauSac(MaMS,TenMS) values 
('MS01',N'Đen'),
('MS02',N'Trắng')
-- Tạo Bảng Hang
create table hang(
	MaHang varchar(10) primary key not null,
	tenhang nvarchar(100) 
)
select * from hang
insert into hang(MaHang,tenhang) values
('MH01','Gọng Kính')

--Tạo Bảng SanPham
create table SanPham(
    MaSP varchar(10) primary key,
    TenSP nvarchar(200) ,
    MaMS varchar(10)not null ,
    MaDSP varchar(10) not null,
	MaHang varchar(10) not null,
    MoTa nvarchar(100) null,
	loaihang nvarchar(100) ,
    SoLuong int ,
    GiaNhap int ,
    GiaBan int ,
    HinhAnh nvarchar(50),
	giamgia decimal(5,2) DEFAULT 0
)
go
Select * from SanPham
Insert into SanPham(MaSP,TenSP,MaMS,MaDSP,MaHang,MoTa,loaihang,SoLuong,GiaNhap,GiaBan,HinhAnh,giamgia) values
('SP01',N'Kính Prada Symbole sunglasses ‘Chromed Lenses’ SPR 17W
','MS02','DSP01','MH01',N'phù hợp thay thế kính râm axetat có thiết kế hình học quá khổ.',N'Gọng Kính',350,7290000,8290000,'Kinh.jpg'),
('SP03',N'Kính Gucci Light-Blue Eyeglasses GG0566O','MS01','DSP01','MH01',N'Hàng CHính Hãng',N'Gọng Kính',790,6290000,7290000,'Kinh.jpg'),
('SP04',N'Kính Prada Sunglasses SPR 17W','MS01','DSP01','MH01',N'Hàng CHính Hãng',N'Gọng Kính',210,8290000,8290000,'Kinh.jpg'),
('SP05',N'Kính Prada Sunglasses SPR 17W','MS01','DSP01','MH01',N'Hàng CHính Hãng',N'Gọng Kính',199,8290000,8290000,'Kinh.jpg')
update SanPham set  SoLuong = 390 where MaSP = 'SP02'

-- NhanVien liên kết với ChucVu
alter table NhanVien add constraint PK_NhanVien_MaCV foreign key (MaCV) references ChucVu(MaCV)
go
-- Liên kết hình thức thanh toán với nhân viên
alter table hinhthucthanhtoan add constraint PK_hinhthucthanhtoan_MaNV foreign key (MaNV) references NhanVien(MaNV)
go
-- Liên kết hình thức thanh toán với hoa don
alter table hinhthucthanhtoan add constraint PK_hinhthucthanhtoan_MaHD foreign key (MaHD) references HoaDon(MaHD)
go
-- HoaDon liên kết với NhanVien
alter table HoaDon add constraint PK_HoaDon_MaNV foreign key (MaNV) references NhanVien(MaNV)
go

-- HoaDon liên kết với KhachHang
alter table HoaDon add constraint PK_HoaDon_MaKH foreign key (MaKH) references KhachHang(MaKH)
go

-- HoaDonChiTiet liên kết với HoaDon
alter table CHiTIetHoaDon add constraint PK_ChiTietHoaDon_MaHD foreign key (MaHD) references HoaDon(MaHD)
go

-- HoaDonChiTiet liên kết với SanPham
alter table ChiTietHoaDon add constraint PK_ChiTietHoaDon_MaSP foreign key (MaSP) references SanPham(MaSP)
go
-- Hang liên kết với SanPham
alter table SanPham add constraint PK_SanPham_MaHang foreign key (MaHang) references hang(MaHang)
go
-- SanPham liên kết với DongSP
alter table SanPham add constraint PK_SanPham_MaDSP foreign key (MaDSP) references DongSP(MaDSP)
go

-- SanPham liên kết với MauSac
alter table SanPham add constraint PK_SanPham_MaMS foreign key (MaMS) references MauSac(MaMS)
go

-- Thêm Dữ Liệu Bảng Login
insert into Login 
values 
('adminkha', N'kha12345', N'admin'),
('duchoang2336', N'hoang12345', N'nhanvien'),
('kha2887', N'kha12345', N'nhanvien'),
('nam1996', N'nam12345', N'nhanvien')
go
select * from Login

-- Thêm dữ liệu bảng ChucVu
insert into ChucVu
values
('GD', N'Giám Đốc', null),
('QL', N'Nhân Viên', null),
('NV', N'Nhân Viên', null)
go
select * from ChucVu
update ChucVu set TenCV = N'Quản Lý' where MaCV = 'QL' 
-- Thêm dữ liệu bảng NhanVien
insert into NhanVien 
values
--('Nv01','HT01' ,N'Nguyễn Văn Kha', '1998-12-19', 0, '0123456789', 'GD', N'Hà Nội', 0, 'kha12345'),
('Nv02','HT02' ,N'Nguyễn Đức Hoàng', '1999-09-12', 0, '0123456789', 'NV', N'Hà Nội', 0, 'hoang12345'),
('Nv03','HT02', N'Nguyễn Minh Khả', '2000-05-24', 0, '0123456789', 'NV', N'Hà Nội', 0, 'kha12345'),
('Nv04','HT01', N'Nguyễn Hoàng Nam', '2000-04-10', 0, '0123456789', 'NV', N'Hà Nội', 0, 'nam12345')
go
select * from NhanVien
select top 1 MaKH from KhachHang order  by MaKH desc
SELECT * FROM KhachHang WHERE Sdt = ?

SELECT        dbo.HoaDon.MaHD, dbo.NhanVien.HoTenNV, dbo.HoaDon.TinhTrang
FROM            dbo.HoaDon INNER JOIN
                         dbo.NhanVien ON dbo.HoaDon.MaNV = dbo.NhanVien.MaNV

Select count(*) from HoaDon where MaHD like ?

Select MaHD from HoaDon where MaHD = ?

Select Max(MaHD) from HoaDon

Select * from SanPham WHERE QRCode = ?

select * from KhachHang where Sdt = ?


Select count(*) as N'Sản Phẩm' from sanpham
Select * from sanpham