 ưq	CREATE database DuAnTotNghiep
go
use DuAnTotNghiep
--1
SELECT * FROM dbo.NhanVien
CREATE TABLE DacQuyen (
    ID_DacQuyen INT PRIMARY KEY IDENTITY(1,1),
    TenRank NVARCHAR(255) NOT NULL,
    DiemTichLuy INT NOT NULL,
    SoLuongMuonToiDa INT NOT NULL,
    ThoiGianMuonToiDa INT NOT NULL
);
select * from DacQuyen
--1
CREATE TABLE DocGia (
    ID_DocGia INT PRIMARY KEY IDENTITY(1,1),
    HoTen NVARCHAR(255) NOT NULL,
    SDT VARCHAR(15) NOT NULL,
    CCCD VARCHAR(12) NOT NULL,
    PointKhachHang INT NOT NULL,
    TrangThai NVARCHAR(50) NOT NULL,
    Rank NVARCHAR(50) NOT NULL
);
--2
CREATE TRIGGER trg_UpdateRank
ON DocGia
AFTER INSERT, UPDATE
AS
BEGIN
    UPDATE dg
    SET dg.Rank = dq.TenRank
    FROM DocGia dg
    JOIN inserted i ON dg.ID_DocGia = i.ID_DocGia
    OUTER APPLY (
        SELECT TOP 1 TenRank
        FROM DacQuyen
        WHERE i.PointKhachHang >= DacQuyen.DiemTichLuy
        ORDER BY DacQuyen.DiemTichLuy DESC
    ) dq;
END;
select * from DocGia

--3
CREATE TABLE DocGia_DacQuyen (
    ID_DocGia INT,
    ID_DacQuyen INT,
    CONSTRAINT PK_DocGia_DacQuyen PRIMARY KEY (ID_DocGia, ID_DacQuyen),
    CONSTRAINT FK_DocGia_DacQuyen_DocGia FOREIGN KEY (ID_DocGia) REFERENCES DocGia(ID_DocGia),
    CONSTRAINT FK_DocGia_DacQuyen_DacQuyen FOREIGN KEY (ID_DacQuyen) REFERENCES DacQuyen(ID_DacQuyen)
);
--3
CREATE TABLE ChuDe (
    ChuDeID INT PRIMARY KEY IDENTITY(1,1),
    TenChuDe NVARCHAR(255) NOT NULL
);
select * from ChuDe
--3
CREATE TABLE Sach (
    SachID INT PRIMARY KEY IDENTITY(1,1),
    TenSach NVARCHAR(255) NOT NULL,
    TacGia NVARCHAR(255) NOT NULL,
    NamXuatBan INT NOT NULL,
    SoTrang INT NOT NULL,
    GiaTien DECIMAL(18,2) NOT NULL,
    NhaXuatBan NVARCHAR(255) NOT NULL,
    Rank NVARCHAR(50) NOT NULL,
    TrangThai NVARCHAR(50) NOT NULL,
	MoTa NVARCHAR(255) NOT NULL,
	SoNgayToiDa INT NOT NULL,
    Avatar NVARCHAR(250) NOT NULL
);
--4
select * from Sach
CREATE TRIGGER trg_Set_SoNgayToiDa
ON Sach
AFTER INSERT
AS
BEGIN
    -- Cập nhật giá trị SoNgayToiDa cho các bản ghi vừa được chèn vào
    UPDATE s
    SET s.SoNgayToiDa = CASE 
        WHEN i.Rank = 'Bronze' THEN 7 
        WHEN i.Rank = 'Silver' THEN 10
        WHEN i.Rank = 'Gold' THEN 14
        WHEN i.Rank = 'Platinum' THEN 21
        WHEN i.Rank = 'Diamond' THEN 30
    END
    FROM Sach s
    INNER JOIN inserted i ON s.SachID = i.SachID;
END;

--5
CREATE TABLE SachChiTiet (
    ChiTietID INT PRIMARY KEY IDENTITY(1,1), 
    SachID INT NOT NULL,
    TrangThai NVARCHAR(50) NOT NULL, 
    TinhTrangSach NVARCHAR(50) NOT NULL,
    NgayNhap DATE DEFAULT GETDATE(),
    CONSTRAINT FK_ChiTietSach FOREIGN KEY (SachID) REFERENCES Sach(SachID)
);
select * from SachChiTiet
--6
CREATE VIEW Sach_SoLuong AS
SELECT 
    S.*, 
    ISNULL(COUNT(SC.ChiTietID), 0) AS SoLuongSach
FROM Sach S
LEFT JOIN SachChiTiet SC ON S.SachID = SC.SachID
GROUP BY S.SachID, S.TenSach, S.TacGia, S.NamXuatBan, S.SoTrang, S.GiaTien, S.NhaXuatBan, S.Rank, S.TrangThai,S.MoTa,S.SoNgayToiDa, S.Avatar;
SELECT * FROM Sach_SoLuong;
--7
CREATE TABLE ChuDeSach (
    ID INT PRIMARY KEY IDENTITY(1,1),
    ChuDeID INT NOT NULL,
    SachID INT NOT NULL,
    
    CONSTRAINT FK_ChuDeSach_ChuDe FOREIGN KEY (ChuDeID) REFERENCES ChuDe(ChuDeID),
    CONSTRAINT FK_ChuDeSach_Sach FOREIGN KEY (SachID) REFERENCES Sach(SachID)
);
select * from ChuDeSach
--10
CREATE TABLE ChiTietMuon (
    ChiTietMuon_ID INT PRIMARY KEY IDENTITY(1,1),
	PhieuMuonID INT NOT NULL,
	NhanVienID INT NOT NULL,
    ID_DocGia INT NOT NULL,
    Sach_ID INT NOT NULL,
	ChiTietID INT NOT NULL,
    NgayMuon DATE NOT NULL DEFAULT GETDATE(),
	SoNgayMuon INT NOT NULL CHECK (SoNgayMuon > 0),
    NgayTra AS DATEADD(DAY, SoNgayMuon, NgayMuon),
    TrangThai NVARCHAR(50) NOT NULL,
    GhiChu NVARCHAR(255) NULL,
	CONSTRAINT FK_ChiTietMuon_SachChiTiet FOREIGN KEY (ChiTietID) REFERENCES SachChiTiet(ChiTietID),
    CONSTRAINT FK_ChiTietMuon_Sach FOREIGN KEY (Sach_ID) REFERENCES Sach(SachID),
	CONSTRAINT FK_ChiTietMuon_PhieuMuon FOREIGN KEY (PhieuMuonID) REFERENCES PhieuMuon(PhieuMuonID),
	CONSTRAINT FK_PhieuMuon_NhanVien FOREIGN KEY (NhanVienID) REFERENCES NhanVien(NhanVienID),
    CONSTRAINT FK_PhieuMuon_DocGia FOREIGN KEY (ID_DocGia) REFERENCES DocGia(ID_DocGia),
);
select * from ChiTietMuon
--8
select * from ChiTietMuon
CREATE TABLE LichSuMuon (
    ID_LichSuMuon INT PRIMARY KEY IDENTITY(1,1),
    SachID INT NOT NULL,
	ChiTietID INT NOT NULL,
    ID_DocGia INT NOT NULL,
    NgayMuon DATE NOT NULL,
    NgayTra DATE NOT NULL,
    TrangThaiSach NVARCHAR(50) NOT NULL,

    CONSTRAINT FK_LichSuMuon_Sach FOREIGN KEY (SachID) REFERENCES Sach(SachID),
	CONSTRAINT FK_LichSuMuon_SachChiTiet FOREIGN KEY (ChiTietID) REFERENCES SachChiTiet(ChiTietID),
    CONSTRAINT FK_LichSuMuon_DocGia FOREIGN KEY (ID_DocGia) REFERENCES DocGia(ID_DocGia),
);
select * from LichSuMuon
--8
	CREATE TABLE NhanVien (
		NhanVienID INT PRIMARY KEY IDENTITY(1,1),
		NameAc nvarchar(100),
		Pass nvarchar(100),
		Role nvarchar(100) NOT NULL,
		TenNhanVien NVARCHAR(100),
		CCCD NVARCHAR(20) UNIQUE NOT NULL,
		NamSinh INT,
		SDT NVARCHAR(15) UNIQUE NOT NULL,
		Email NVARCHAR(100) UNIQUE NOT NULL,
		GioiTinh NVARCHAR(10),
		Avatar NVARCHAR(255)
	);
select * from NhanVien
--8
CREATE TABLE PhieuMuon (
    PhieuMuonID INT PRIMARY KEY NOT NULL CHECK (PhieuMuonID = 1),
);
select * from PhieuMuon
select * from LichSuMuon
--11
CREATE TABLE PhieuTra (
    PhieuTraID INT PRIMARY KEY IDENTITY(1,1),
    ChiTietMuon_ID INT NOT NULL,
    PhieuPhatID INT NULL,
    NhanVienID INT NOT NULL,
    ID_DocGia INT NOT NULL,
    SachID INT NOT NULL,
    ChiTietID INT NOT NULL,
    NgayTra DATE NOT NULL,
    GhiChu NVARCHAR(255) NULL,
    TrangThaiSach NVARCHAR(50) NOT NULL,

    CONSTRAINT FK_PhieuTra_ChiTietMuon FOREIGN KEY (ChiTietMuon_ID) REFERENCES ChiTietMuon(ChiTietMuon_ID),
    CONSTRAINT FK_PhieuTra_PhieuPhat FOREIGN KEY (PhieuPhatID) REFERENCES PhieuPhat(PhieuPhatID),
    CONSTRAINT FK_PhieuTra_NhanVien FOREIGN KEY (NhanVienID) REFERENCES NhanVien(NhanVienID),
    CONSTRAINT FK_PhieuTra_DocGia FOREIGN KEY (ID_DocGia) REFERENCES DocGia(ID_DocGia),
    CONSTRAINT FK_PhieuTra_Sach FOREIGN KEY (SachID) REFERENCES Sach(SachID),
    CONSTRAINT FK_PhieuTra_SachChiTiet FOREIGN KEY (ChiTietID) REFERENCES SachChiTiet(ChiTietID),
);

SELECT * FROM dbo.PhieuTra
SELECT * FROM dbo.ChiTietMuon
select * from NhanVien
--9
CREATE TABLE PhieuPhat (
    PhieuPhatID INT PRIMARY KEY IDENTITY(1,1),
	ID_DocGia INT NOT NULL,
    NgayLam DATE NOT NULL DEFAULT GETDATE(),
    LyDo NVARCHAR(255) NOT NULL,
    SoTienPhat DECIMAL(10,2),
    TrangThai NVARCHAR(50) NOT NULL
	CONSTRAINT FK_PhieuPhat_DocGia FOREIGN KEY (ID_DocGia) REFERENCES DocGia(ID_DocGia)
);

SELECT * FROM dbo.ChuDe
SELECT * FROM dbo.ChuDeSach
-- Bảng DacQuyen
--1
INSERT INTO DacQuyen (TenRank, DiemTichLuy, SoLuongMuonToiDa, ThoiGianMuonToiDa) VALUES
(N'Bronze', 0, 2, 7),
(N'Silver', 500, 4, 10),
(N'Gold', 1000, 6, 14),
(N'Platinum', 2000, 8, 21),
(N'Diamond', 5000, 10, 30);
select * from DacQuyen
-- Bảng DocGia
--1
INSERT INTO DocGia (HoTen, SDT, CCCD, PointKhachHang, TrangThai, Rank) VALUES
( N'Trương Minh Kid', '0987654321', '123456789012', 100, N'Hoạt động', N''),
( N'Bùi Minh Đức', '0912345678', '234567890123', 5000, N'Hoạt động', N''),
( N'Nguyễn Trộn Lòng', '0908765432', '345678901234', 1000, N'Hoạt động', N''),
( N'Trương Lê Da Đen', '0976543210', '456789012345', 2000, N'Hoạt động', N''),
( N'Lê Ngọc Minh Đức Anh', '0965432109', '567890123456', 500, N'Hoạt động', N'');
select * from DocGia
--1
INSERT INTO DocGia_DacQuyen (ID_DocGia, ID_DacQuyen) VALUES
(1, 1),
(2, 5),
(3, 3),
(4, 4),
(5, 2);

-- Bảng ChuDe
--1
INSERT INTO ChuDe (TenChuDe) VALUES 
(N'Khoa học'), 
(N'Văn học'), 
(N'Lịch sử'), 
(N'Kinh tế'), 
(N'Công nghệ');

-- Bảng Sach
--1
INSERT INTO Sach (TenSach, TacGia, NamXuatBan, SoTrang, GiaTien, NhaXuatBan, Rank, TrangThai,MoTa,SoNgayToiDa, Avatar) VALUES
(N'Vũ trụ và chúng ta', N'Stephen Hawking', 2018, 350, 250000, N'NXB Khoa Học', N'Platinum', N'Còn',N'Là tác phẩm kinh điển về thiên văn học, kể về sự hình thành vũ trụ, sự sống và nền văn minh nhân loại. Với lối viết cuốn hút, sách giúp độc giả hiểu về khoa học một cách dễ dàng và truyền cảm hứng khám phá vũ trụ.',0, N'C:\Users\vuhie\Downloads\Ảnh sách DATN\Ảnh sách DATN\VuTruVaChungTa.png'),
(N'Sinh học phân tử', N'James Watson', 2015, 400, 320000, N'NXB Giáo Dục', N'Silver', N'Còn',N'Là sách chuyên sâu về cấu trúc, chức năng và cơ chế hoạt động của tế bào, phù hợp cho sinh viên và nhà nghiên cứu sinh học.',0, N'C:\Users\vuhie\Downloads\Ảnh sách DATN\Ảnh sách DATN\SinhHocPhanTu.png'),
(N'Hóa học hữu cơ', N'Linus Pauling', 2017, 500, 290000, N'NXB Đại Học', N'Gold', N'Còn',N'Là tài liệu cơ bản về hóa học hữu cơ, trình bày các khái niệm, cấu trúc, tính chất và phản ứng của các hợp chất hữu cơ. Sách phù hợp cho học sinh, sinh viên chuyên ngành hóa học và các kỳ thi đại học.',0, N'C:\Users\vuhie\Downloads\Ảnh sách DATN\Ảnh sách DATN\HoaHocHuuCo.png'),
(N'Vật lý lượng tử', N'Albert Einstein', 2019, 600, 350000, N'NXB Hàn Lâm', N'Diamond', N'Còn',N'Là sách giới thiệu những khái niệm cơ bản về cơ học lượng tử, bao gồm thuyết tương đối, rối lượng tử và các nguyên lý nền tảng. Nội dung được trình bày đơn giản, dễ hiểu, phù hợp cho người mới tìm hiểu về vật lý lượng tử.',0, N'C:\Users\vuhie\Downloads\Ảnh sách DATN\Ảnh sách DATN\VatLyLuongTu.png'),
(N'Địa chất học', N'Charles Lyell', 2020, 450, 280000, N'NXB Địa Lý', N'Bronze', N'Còn',N'Là sách cung cấp kiến thức về độc chất học, bao gồm bản chất, cơ chế tác động và phương pháp xử lý các chất độc trong cơ thể. Đây là tài liệu quan trọng dành cho sinh viên ngành dược và y khoa.',0, N'C:\Users\vuhie\Downloads\Ảnh sách DATN\Ảnh sách DATN\DiaChatHoc.png'),
(N'Truyện Kiều', N'Nguyễn Du', 1815, 300, 150000, N'NXB Văn Học', N'Gold', N'Còn',N'Là kiệt tác văn học kể về cuộc đời truân chuyên của Thúy Kiều, phản ánh số phận con người trong xã hội phong kiến.',0, N'C:\Users\vuhie\Downloads\Ảnh sách DATN\Ảnh sách DATN\TruyenKieu.png'),
(N'Chí Phèo', N'Nam Cao', 1941, 200, 120000, N'NXB Hội Nhà Văn', N'Silver', N'Còn',N'Là truyện ngắn nổi tiếng về số phận bi thảm của Chí Phèo – một nông dân lương thiện bị xã hội đẩy vào con đường lưu manh, phản ánh sâu sắc hiện thực xã hội Việt Nam trước Cách mạng.',0, N'C:\Users\vuhie\Downloads\Ảnh sách DATN\Ảnh sách DATN\ChiPheo.png'),
(N'Tắt đèn', N'Ngô Tất Tố', 1939, 250, 140000, N'NXB Kim Đồng', N'Bronze', N'Còn',N'Là tiểu thuyết hiện thực phản ánh số phận bi thảm của người nông dân Việt Nam dưới ách áp bức của xã hội phong kiến và thực dân, tiêu biểu qua nhân vật chị Dậu.',0, N'C:\Users\vuhie\Downloads\Ảnh sách DATN\Ảnh sách DATN\TatDen.png'),
(N'Số đỏ', N'Vũ Trọng Phụng', 1936, 280, 130000, N'NXB Trẻ', N'Diamond', N'Còn',N'Là tiểu thuyết châm biếm sâu sắc về xã hội Việt Nam giai đoạn nửa đầu thế kỷ 20, phê phán lối sống giả dối, lố lăng qua nhân vật Xuân Tóc Đỏ.',0, N'C:\Users\vuhie\Downloads\Ảnh sách DATN\Ảnh sách DATN\SoDo.png'),
(N'Nhật ký trong tù', N'Hồ Chí Minh', 1943, 150, 100000, N'NXB Chính Trị', N'Platinum', N'Hết',N'Là tập thơ chữ Hán ghi lại những suy tư và trải nghiệm trong thời gian Người bị giam cầm, thể hiện tinh thần kiên cường, ý chí cách mạng và tình yêu tự do.',0, N'C:\Users\vuhie\Downloads\Ảnh sách DATN\Ảnh sách DATN\NhatKyTrongTu.png'),
(N'Đại Việt sử ký toàn thư', N'Ngô Sĩ Liên', 1697, 600, 500000, N'NXB Lịch Sử', N'Gold', N'Còn',N'Là bộ chính sử quan trọng nhất của Việt Nam thời phong kiến, ghi chép lịch sử từ thời Hồng Bàng đến thời Hậu Lê, do nhiều sử gia biên soạn, trong đó có Ngô Sĩ Liên.',0, N'C:\Users\vuhie\Downloads\Ảnh sách DATN\Ảnh sách DATN\DaiSuKyVN.png'),
(N'Lịch sử thế giới', N'Will Durant', 1954, 700, 550000, N'NXB Quốc Tế', N'Silver', N'Hết',N'Là một cuốn sách cung cấp cái nhìn tổng quan về lịch sử nhân loại qua các giai đoạn quan trọng, với chân dung nhân vật và sự kiện tiêu biểu, giúp người đọc hiểu sâu hơn về quá trình phát triển của thế giới.',0, N'C:\Users\vuhie\Downloads\Ảnh sách DATN\Ảnh sách DATN\LichSuTG.png'),
(N'Việt Nam sử lược', N'Trần Trọng Kim', 1919, 450, 300000, N'NXB Giáo Dục', N'Bronze', N'Còn',N'Là một trong những cuốn sách lịch sử đầu tiên hệ thống lại quá trình phát triển của Việt Nam từ thời kỳ dựng nước đến đầu thế kỷ 20, giúp người đọc có cái nhìn tổng quan và dễ hiểu về lịch sử dân tộc.',0, N'C:\Users\vuhie\Downloads\Ảnh sách DATN\Ảnh sách DATN\VietNamSuLuoc.png'),
(N'Chiến tranh và hòa bình', N'Lev Tolstoy', 1869, 800, 600000, N'NXB Nga', N'Diamond', N'Còn',N'Là một cuốn sách thuộc tủ sách Thưởng thức triết học, giúp độc giả, đặc biệt là trẻ em, hiểu rõ hơn về bản chất của chiến tranh và hòa bình, từ đó hình thành tư duy phản biện và giá trị nhân văn.',0, N'C:\Users\vuhie\Downloads\Ảnh sách DATN\Ảnh sách DATN\ChienTranhVaHoaBinh.png'),
(N'Những người khốn khổ', N'Victor Hugo', 1862, 1200, 700000, N'NXB Văn Học', N'Platinum', N'Còn',N'Là một trong những tác phẩm văn học kinh điển nhất thế giới. Cuốn sách này được trình bày dưới dạng rút gọn với 800 từ, kèm theo hình minh họa và CD, giúp người học tiếng Anh tiếp cận câu chuyện một cách dễ dàng hơn.',0, N'C:\Users\vuhie\Downloads\Ảnh sách DATN\Ảnh sách DATN\NhungNguoiKhonKho.png'),
(N'Kinh tế học vĩ mô', N'Paul Samuelson', 2001, 550, 450000, N'NXB Kinh Tế', N'Gold', N'Còn',N'Là sách về nền kinh tế, GDP, lạm phát, thất nghiệp, chính sách tài khóa và tiền tệ.',0, N'C:\Users\vuhie\Downloads\Ảnh sách DATN\Ảnh sách DATN\KinhTeHocViMo.png'),
(N'Chiến lược kinh doanh', N'Michael Porter', 1996, 500, 400000, N'NXB Doanh Nhân', N'Silver', N'Còn', N'Là cuốn sách phân tích về chiến lược kinh doanh nền tảng, cách các hệ sinh thái kỹ thuật số hoạt động và tác động của chúng đến thị trường.',0, N'C:\Users\vuhie\Downloads\Ảnh sách DATN\Ảnh sách DATN\ChienLuocKinhDoanh.png'),
(N'Tài chính cá nhân', N'Robert Kiyosaki', 2010, 300, 350000, N'NXB Đầu Tư', N'Bronze', N'Còn',N'Là cuốn sách hướng dẫn cách quản lý tài chính cá nhân một cách hiệu quả.',0, N'C:\Users\vuhie\Downloads\Ảnh sách DATN\Ảnh sách DATN\TaiChinhCaNhan.png'),
(N'Đầu tư chứng khoán', N'Benjamin Graham', 1949, 450, 500000, N'NXB Wall Street', N'Diamond', N'Còn',N'Giúp hướng dẫn 45 bí quyết đầu tư hiệu quả theo phong cách Nhật Bản, giúp phân tích biểu đồ và đánh giá thị trường chính xác.',0, N'C:\Users\vuhie\Downloads\Ảnh sách DATN\Ảnh sách DATN\DauTuChungKhoan.png'),
(N'Quản trị doanh nghiệp', N'Peter Drucker', 1985, 600, 550000, N'NXB Harvard', N'Platinum', N'Còn',N'Là tài liệu hướng dẫn về kế toán quản trị, giúp doanh nghiệp Việt Nam tối ưu hóa quản lý tài chính và ra quyết định hiệu quả.',0, N'C:\Users\vuhie\Downloads\Ảnh sách DATN\Ảnh sách DATN\QuanTriDoanhNghiep.png'),
(N'Lập trình Python', N'Guido van Rossum', 2021, 400, 300000, N'NXB Công Nghệ', N'Gold', N'Còn',N'Là tài liệu nhập môn lập trình dành cho người mới, giúp tiếp cận Python một cách dễ hiểu và thú vị.',0, N'C:\Users\vuhie\Downloads\Ảnh sách DATN\Ảnh sách DATN\LapTrinhPython.png'),
(N'Khoa học dữ liệu', N'Andrew Ng', 2020, 450, 320000, N'NXB AI', N'Silver', N'Còn',N'Là tài liệu giới thiệu 50 nguyên tắc quan trọng trong lĩnh vực khoa học dữ liệu, được trình bày ngắn gọn, dễ hiểu, giúp người đọc nắm bắt nhanh kiến thức.',0, N'C:\Users\vuhie\Downloads\Ảnh sách DATN\Ảnh sách DATN\KhoaHocDuLieu.png'),
(N'Trí tuệ nhân tạo', N'Stuart Russell', 2019, 500, 350000, N'NXB Machine Learning', N'Bronze', N'Còn',N'Là tài liệu khoa học dành cho trẻ em, giúp khám phá về robot, trí tuệ nhân tạo và cách chúng hoạt động trong cuộc sống.',0, N'C:\Users\vuhie\Downloads\Ảnh sách DATN\Ảnh sách DATN\TriTueNhanTao.png'),
(N'Lập trình Java', N'James Gosling', 2018, 550, 370000, N'NXB Công Nghệ', N'Diamond', N'Còn',N'Là tài liệu hướng dẫn học Java từ cơ bản, giúp người mới tiếp cận và nắm vững các khái niệm lập trình quan trọng.',0, N'C:\Users\vuhie\Downloads\Ảnh sách DATN\Ảnh sách DATN\LapTrinhJaVa.png'),
(N'Cấu trúc dữ liệu và giải thuật', N'Donald Knuth', 1997, 600, 400000, N'NXB IT', N'Platinum', N'Còn',N'Là giới thiệu về tư duy logic và cách tổ chức dữ liệu trong khoa học máy tính, phù hợp cho trẻ em từ 6 tuổi trở lên.',0, N'C:\Users\vuhie\Downloads\Ảnh sách DATN\Ảnh sách DATN\CauTrucDuLieuVaGiaiThuat.png');
select * from Sach

-- Bảng ChuDeSach
--1
INSERT INTO ChuDeSach (ChuDeID, SachID) VALUES
(1, 1),
(1, 2),
(1, 3),
(1, 4),
(1, 5),
(2, 6),
(2, 7),
(2, 8),
(2, 9),
(2, 10),
(3, 11),
(3, 12),
(3, 13),
(3, 14),
(3, 15),
(4, 16),
(4, 17),
(4, 18),
(4, 19),
(4, 20),
(5, 21),
(5, 22),
(5, 23),
(5, 24),
(5, 25);

-- Bảng ChiTietMuon
--6
INSERT INTO ChiTietMuon (PhieuMuonID,NhanVienID,ID_DocGia,Sach_ID,ChiTietID, NgayMuon, SoNgayMuon, TrangThai, GhiChu) VALUES
(1,1,1,1,3, '2025-03-01', 5, N'Đang Mượn', NULL),
(1,2,2,2,12, '2025-03-02', 8, N'Đang Mượn', NULL),
(1,3,3,3,19, '2025-03-03', 12, N'Đang Mượn', NULL),
(1,4,4,4,28, '2025-03-04', 16, N'Đang Mượn', NULL),
(1,5,5,5,33, '2025-03-05', 24, N'Đang Mượn', NULL);

INSERT INTO ChiTietMuon (PhieuMuonID,NhanVienID,ID_DocGia,Sach_ID,ChiTietID, NgayMuon, SoNgayMuon, TrangThai, GhiChu) VALUES
(1,1,1,25,3, '2025-03-01', 25, N'Đúng hạn', NULL),
(1,2,2,8,12, '2025-03-02', 8, N'Trễ hạn', NULL),
(1,3,3,10,19, '2025-03-03', 12, N'Đang Mượn', NULL),
(1,4,4,20,28, '2025-03-04', 16, N'Đang Mượn', NULL),
(1,5,5,11,33, '2025-03-05', 24, N'Đang Mượn', NULL);
select * from ChiTietMuon

DROP TABLE dbo.ChiTietMuon

-- Bảng LichSuMuon
--7
INSERT INTO LichSuMuon (SachID,ChiTietID, ID_DocGia, NgayMuon, NgayTra, TrangThaiSach) VALUES
(1,3, 1, '2025-03-01', '2025-03-15', N'Tốt'),
(2,12, 2, '2025-03-02', '2025-03-16', N'Tốt'),
(3,19, 3, '2025-03-03', '2025-03-17', N'Tốt'),
(4,28, 4, '2025-03-04', '2025-03-20', N'Tốt'),
(5,33, 5, '2025-03-05', '2025-03-19', N'Hơi cũ');
select * from LichSuMuon
--2
INSERT INTO NhanVien (NameAC,Pass,Role, TenNhanVien, CCCD, NamSinh, SDT, Email, GioiTinh,Avatar)
VALUES
('bduc','123',N'Thủ Thư', N'Nguyễn Văn A', '012345678901', 1990, '0901234567', 'ducbmth02367@fpt.edu.vn', N'Nam','C:\Users\vuhie\Downloads\t_500x300.jpg'),
('lduc','345',N'Nhân Viên', N'Trần Thị B', '012345678902', 1992, '0902234567', 'duclnmth02926@fpt.edu.vn', N'Nam','C:\Users\vuhie\Downloads\t_500x300.jpg'),
('tduc','456',N'Nhân Viên', N'Lê Văn C', '012345678903', 1985, '0903234567', 'ducntmth04333@fpt.edu.vn', N'Nữ','C:\Users\vuhie\Downloads\t_500x300.jpg'),
('trduc','678',N'Nhân Viên', N'Hoàng Thị D', '012345678904', 1998, '0904234567', 'ductmth04341@fpt.edu.vn', N'Nữ','C:\Users\vuhie\Downloads\t_500x300.jpg'),
('anhduc','890',N'Nhân Viên', N'Phạm Văn E', '012345678905', 1995, '0905234567', 'anhvdth04374@fpt.edu.vn', N'Nam','C:\Users\vuhie\Downloads\t_500x300.jpg');
select * from NhanVien

-- Thêm dữ liệu vào bảng PhieuPhat
--3
INSERT INTO PhieuPhat (ID_DocGia,NgayLam, LyDo, SoTienPhat, TrangThai)
VALUES
(1,GETDATE(), N'Trả sách trễ', 50000, N'Chưa thanh toán'),
(2,GETDATE(), N'Mất sách', 200000, N'Đã thanh toán'),
(3,GETDATE(), N'Hỏng sách', 100000, N'Chưa thanh toán'),
(4,GETDATE(), N'Không trả sách', 300000, N'Đã thanh toán'),
(5,GETDATE(), N'Vẽ bậy lên sách', 150000, N'Chưa thanh toán');
select * from PhieuPhat

-- Thêm dữ liệu vào bảng PhieuMuon
--4
INSERT INTO PhieuMuon (PhieuMuonID)
VALUES
(1);
select * from PhieuMuon

-- Thêm dữ liệu vào bảng PhieuTra
--8
INSERT INTO PhieuTra (ChiTietMuon_ID, PhieuPhatID, NhanVienID, ID_DocGia, SachID, ChiTietID, NgayTra, GhiChu, TrangThaiSach)
VALUES
(2, 1, 1, 1, 1, 3,  '2025-03-10', N'Trả đúng hạn', N'Tốt'),
(3, 2, 2, 2, 2, 12,  '2025-03-11', N'Mất sách', N'Mất'),
(4, 3, 3, 3, 3, 19,  '2025-03-12', N'Bìa sách bị rách', N'Hỏng'),
(5, 4, 4, 4, 4, 28,  '2025-03-13', N'Trả trễ 2 ngày', N'Trễ hạn'),
(6, 5, 5, 5, 5, 33,  '2025-03-14', N'Viết vào sách', N'Hỏng');

SELECT * FROM dbo.ChiTietMuon
--5
INSERT INTO SachChiTiet (SachID, TrangThai, TinhTrangSach, NgayNhap) VALUES
(1, N'Đang có sẵn', N'Mới', '2025-01-01'),
(1, N'Đang có sẵn', N'Cũ', '2025-01-02'),
(1, N'Đang được mượn', N'Mới', '2025-01-03'),
(1, N'Đang có sẵn', N'Cũ', '2025-01-04'),
(1, N'Đang được mượn', N'Mới', '2025-01-05'),
(1, N'Đang có sẵn', N'Mới', '2025-01-06'),
(1, N'Đang được mượn', N'Mới', '2025-01-07'),
(1, N'Đang có sẵn', N'Cũ', '2025-01-08'),
(1, N'Đang có sẵn', N'Mới', '2025-01-09'),
(1, N'Đang được mượn', N'Mới', '2025-01-10'),
(2, N'Đang có sẵn', N'Mới', '2025-01-11'),
(2, N'Đang được mượn', N'Mới', '2025-01-12'),
(2, N'Đang có sẵn', N'Mới', '2025-01-13'),
(2, N'Đang có sẵn', N'Mới', '2025-01-14'),
(2, N'Đang có sẵn', N'Mới', '2025-01-15'),
(2, N'Đang được mượn', N'Mới', '2025-01-16'),
(2, N'Đang có sẵn', N'Mới', '2025-01-17'),
(2, N'Đang có sẵn', N'Mới', '2025-01-18'),
(3, N'Đang được mượn', N'Mới', '2025-01-19'),
(3, N'Đang có sẵn', N'Mới', '2025-01-20'),
(3, N'Đang có sẵn', N'Mới', '2025-01-21'),
(3, N'Đang có sẵn', N'Mới', '2025-01-22'),
(3, N'Đang được mượn', N'Mới', '2025-01-23'),
(3, N'Đang có sẵn', N'Mới', '2025-01-24'),
(3, N'Đang có sẵn', N'Mới', '2025-01-25'),
(3, N'Đang có sẵn', N'Mới', '2025-01-26'),
(3, N'Đang được mượn', N'Mới', '2025-01-27'),
(3, N'Đang có sẵn', N'Mới', '2025-01-28'),
(3, N'Đang có sẵn', N'Mới', '2025-01-29'),
(3, N'Đang được mượn', N'Mới', '2025-01-30'),
(4, N'Đang có sẵn', N'Mới', '2025-02-01'),
(4, N'Đang được mượn', N'Mới', '2025-02-02'),
(4, N'Đang có sẵn', N'Mới', '2025-02-03'),
(4, N'Đang có sẵn', N'Mới', '2025-02-04'),
(4, N'Đang được mượn', N'Mới', '2025-02-05'),
(4, N'Đang có sẵn', N'Mới', '2025-02-06'),
(5, N'Đang được mượn', N'Mới', '2025-02-07'),
(5, N'Đang có sẵn', N'Mới', '2025-02-08'),
(5, N'Đang có sẵn', N'Mới', '2025-02-09'),
(5, N'Đang được mượn', N'Mới', '2025-02-10'),
(5, N'Đang có sẵn', N'Mới', '2025-02-11'),
(5, N'Đang có sẵn', N'Mới', '2025-02-12'),
(5, N'Đang được mượn', N'Mới', '2025-02-13'),
(5, N'Đang có sẵn', N'Mới', '2025-02-14'),
(5, N'Đang có sẵn', N'Mới', '2025-02-15'),
(6, N'Đang được mượn', N'Mới', '2025-02-16'),
(6, N'Đang có sẵn', N'Mới', '2025-02-17'),
(6, N'Đang có sẵn', N'Mới', '2025-02-18'),
(6, N'Đang có sẵn', N'Mới', '2025-02-19'),
(6, N'Đang được mượn', N'Mới', '2025-02-20'),
(6, N'Đang có sẵn', N'Mới', '2025-02-21'),
(6, N'Đang có sẵn', N'Mới', '2025-02-22'),
(6, N'Đang được mượn', N'Mới', '2025-02-23'),
(6, N'Đang có sẵn', N'Mới', '2025-02-24'),
(6, N'Đang có sẵn', N'Mới', '2025-02-25'),
(6, N'Đang có sẵn', N'Mới', '2025-02-26'),
(6, N'Đang được mượn', N'Mới', '2025-02-27'),
(6, N'Đang có sẵn', N'Mới', '2025-02-28'),
(6, N'Đang có sẵn', N'Mới', '2025-03-01'),
(6, N'Đang có sẵn', N'Mới', '2025-03-02'),
(6, N'Đang có sẵn', N'Mới', '2025-03-03'),
(6, N'Đang được mượn', N'Mới', '2025-03-04'),
(6, N'Đang có sẵn', N'Mới', '2025-03-05'),
(6, N'Đang có sẵn', N'Mới', '2025-03-06'),
(6, N'Đang được mượn', N'Mới', '2025-03-07'),
(7, N'Đang có sẵn', N'Mới', '2025-03-08'),
(7, N'Đang có sẵn', N'Mới', '2025-03-09'),
(7, N'Đang được mượn', N'Mới', '2025-03-10'),
(7, N'Đang có sẵn', N'Mới', '2025-03-11'),
(7, N'Đang có sẵn', N'Mới', '2025-03-12'),
(7, N'Đang có sẵn', N'Mới', '2025-03-13'),
(7, N'Đang có sẵn', N'Mới', '2025-03-14'),
(7, N'Đang được mượn', N'Mới', '2025-03-15'),
(7, N'Đang có sẵn', N'Mới', '2025-03-16'),
(7, N'Đang có sẵn', N'Mới', '2025-03-17'),
(7, N'Đang được mượn', N'Mới', '2025-03-18'),
(7, N'Đang có sẵn', N'Mới', '2025-03-19'),
(7, N'Đang có sẵn', N'Mới', '2025-03-20'),
(7, N'Đang có sẵn', N'Mới', '2025-03-21'),
(7, N'Đang được mượn', N'Mới', '2025-03-22'),
(8, N'Đang có sẵn', N'Mới', '2025-06-01'),
(8, N'Đang có sẵn', N'Mới', '2025-06-02'),
(8, N'Đang có sẵn', N'Mới', '2025-06-03'),
(8, N'Đang được mượn', N'Cũ', '2025-06-04'),
(8, N'Đang có sẵn', N'Mới', '2025-06-05'),
(8, N'Đang có sẵn', N'Mới', '2025-06-06'),
(8, N'Đang có sẵn', N'Mới', '2025-06-07'),
(8, N'Đang có sẵn', N'Mới', '2025-06-08'),
(8, N'Đang có sẵn', N'Mới', '2025-06-09'),
(8, N'Đang có sẵn', N'Mới', '2025-06-10'),
(8, N'Đang được mượn', N'Cũ', '2025-06-11'),
(8, N'Đang có sẵn', N'Mới', '2025-06-12'),
(8, N'Đang có sẵn', N'Mới', '2025-06-13'),
(8, N'Đang có sẵn', N'Mới', '2025-06-14'),
(8, N'Đang có sẵn', N'Mới', '2025-06-15'),
(8, N'Đang được mượn', N'Mới', '2025-06-16'),
(8, N'Đang có sẵn', N'Mới', '2025-06-17'),
(8, N'Đang có sẵn', N'Cũ', '2025-06-18'),
(9, N'Đang có sẵn', N'Mới', '2025-06-01'),
(9, N'Đang có sẵn', N'Mới', '2025-06-02'),
(9, N'Đang có sẵn', N'Mới', '2025-06-03'),
(9, N'Đang được mượn', N'Cũ', '2025-06-04'),
(9, N'Đang có sẵn', N'Mới', '2025-06-05'),
(9, N'Đang có sẵn', N'Mới', '2025-06-06'),
(9, N'Đang có sẵn', N'Mới', '2025-06-07'),
(9, N'Đang có sẵn', N'Mới', '2025-06-08'),
(9, N'Đang có sẵn', N'Mới', '2025-06-09'),
(9, N'Đang được mượn', N'Mới', '2025-06-10'),
(11, N'Đang có sẵn', N'Mới', '2025-06-01'),
(11, N'Đang có sẵn', N'Mới', '2025-06-02'),
(11, N'Đang được mượn', N'Cũ', '2025-06-03'),
(11, N'Đang có sẵn', N'Mới', '2025-06-04'),
(11, N'Đang có sẵn', N'Mới', '2025-06-05'),
(13, N'Đang có sẵn', N'Mới', '2025-06-01'),
(13, N'Đang có sẵn', N'Mới', '2025-06-02'),
(13, N'Đang có sẵn', N'Mới', '2025-06-03'),
(13, N'Đang được mượn', N'Mới', '2025-06-04'),
(13, N'Đang có sẵn', N'Mới', '2025-06-05'),
(13, N'Đang có sẵn', N'Mới', '2025-06-06'),
(13, N'Đang có sẵn', N'Cũ', '2025-06-07'),
(14, N'Đang có sẵn', N'Mới', '2025-06-01'),
(14, N'Đang có sẵn', N'Mới', '2025-06-02'),
(14, N'Đang có sẵn', N'Mới', '2025-06-03'),
(14, N'Đang có sẵn', N'Mới', '2025-06-04'),
(14, N'Đang được mượn', N'Mới', '2025-06-05'),
(14, N'Đang có sẵn', N'Mới', '2025-06-06'),
(14, N'Đang có sẵn', N'Mới', '2025-06-07'),
(14, N'Đang có sẵn', N'Mới', '2025-06-08'),
(14, N'Đang có sẵn', N'Cũ', '2025-06-09'),
(15, N'Đang có sẵn', N'Mới', '2025-06-01'),
(15, N'Đang có sẵn', N'Mới', '2025-06-02'),
(15, N'Đang có sẵn', N'Cũ', '2025-06-03'),
(15, N'Đang có sẵn', N'Mới', '2025-06-04'),
(15, N'Đang được mượn', N'Cũ', '2025-06-05'),
(15, N'Đang có sẵn', N'Cũ', '2025-06-06'),
(15, N'Đang có sẵn', N'Mới', '2025-06-07'),
(15, N'Đang được mượn', N'Mới', '2025-06-08'),
(15, N'Đang có sẵn', N'Cũ', '2025-06-09'),
(15, N'Đang có sẵn', N'Mới', '2025-06-10'),
(15, N'Đang được mượn', N'Cũ', '2025-06-11'),
(16, N'Đang có sẵn', N'Mới', '2025-06-01'),
(16, N'Đang có sẵn', N'Mới', '2025-06-02'),
(16, N'Đang có sẵn', N'Cũ', '2025-06-03'),
(16, N'Đang được mượn', N'Cũ', '2025-06-04'),
(16, N'Đang có sẵn', N'Mới', '2025-06-05'),
(16, N'Đang có sẵn', N'Mới', '2025-06-06'),
(16, N'Đang có sẵn', N'Cũ', '2025-06-07'),
(16, N'Đang có sẵn', N'Mới', '2025-06-08'),
(16, N'Đang được mượn', N'Cũ', '2025-06-09'),
(16, N'Đang có sẵn', N'Mới', '2025-06-10'),
(16, N'Đang có sẵn', N'Cũ', '2025-06-11'),
(16, N'Đang được mượn', N'Cũ', '2025-06-12'),
(16, N'Đang có sẵn', N'Mới', '2025-06-13'),
(16, N'Đang có sẵn', N'Mới', '2025-06-14'),
(17, N'Đang có sẵn', N'Mới', '2025-06-01'),
(17, N'Đang có sẵn', N'Mới', '2025-06-02'),
(17, N'Đang có sẵn', N'Mới', '2025-06-03'),
(17, N'Đang được mượn', N'Cũ', '2025-06-04'),
(17, N'Đang có sẵn', N'Cũ', '2025-06-05'),
(17, N'Đang có sẵn', N'Mới', '2025-06-06'),
(17, N'Đang có sẵn', N'Mới', '2025-06-07'),
(17, N'Đang được mượn', N'Mới', '2025-06-08'),
(17, N'Đang có sẵn', N'Cũ', '2025-06-09'),
(17, N'Đang có sẵn', N'Mới', '2025-06-10'),
(17, N'Đang có sẵn', N'Cũ', '2025-06-11'),
(17, N'Đang có sẵn', N'Mới', '2025-06-12'),
(17, N'Đang được mượn', N'Cũ', '2025-06-13'),
(18, N'Đang có sẵn', N'Mới', '2025-06-01'),
(18, N'Đang có sẵn', N'Mới', '2025-06-02'),
(18, N'Đang có sẵn', N'Cũ', '2025-06-03'),
(18, N'Đang được mượn', N'Cũ', '2025-06-04'),
(18, N'Đang có sẵn', N'Mới', '2025-06-05'),
(18, N'Đang có sẵn', N'Mới', '2025-06-06'),
(18, N'Đang có sẵn', N'Cũ', '2025-06-07'),
(18, N'Đang có sẵn', N'Mới', '2025-06-08'),
(18, N'Đang được mượn', N'Cũ', '2025-06-09'),
(18, N'Đang có sẵn', N'Mới', '2025-06-10'),
(18, N'Đang có sẵn', N'Cũ', '2025-06-11'),
(18, N'Đang có sẵn', N'Mới', '2025-06-12'),
(18, N'Đang được mượn', N'Cũ', '2025-06-13'),
(18, N'Đang có sẵn', N'Mới', '2025-06-14'),
(18, N'Đang có sẵn', N'Mới', '2025-06-15'),
(18, N'Đang có sẵn', N'Cũ', '2025-06-16'),
(18, N'Đang có sẵn', N'Mới', '2025-06-17'),
(19, N'Đang có sẵn', N'Mới', '2025-06-01'),
(19, N'Đang có sẵn', N'Cũ', '2025-06-02'),
(19, N'Đang có sẵn', N'Mới', '2025-06-03'),
(19, N'Đang được mượn', N'Cũ', '2025-06-04'),
(19, N'Đang có sẵn', N'Mới', '2025-06-05'),
(19, N'Đang có sẵn', N'Cũ', '2025-06-06'),
(19, N'Đang có sẵn', N'Mới', '2025-06-07'),
(19, N'Đang có sẵn', N'Cũ', '2025-06-08'),
(19, N'Đang được mượn', N'Mới', '2025-06-09'),
(19, N'Đang có sẵn', N'Cũ', '2025-06-10'),
(19, N'Đang có sẵn', N'Mới', '2025-06-11'),
(19, N'Đang có sẵn', N'Cũ', '2025-06-12'),
(19, N'Đang được mượn', N'Mới', '2025-06-13'),
(19, N'Đang có sẵn', N'Cũ', '2025-06-14'),
(19, N'Đang có sẵn', N'Mới', '2025-06-15'),
(19, N'Đang được mượn', N'Cũ', '2025-06-16'),
(20, N'Đang có sẵn', N'Mới', '2025-06-01'),
(20, N'Đang có sẵn', N'Cũ', '2025-06-02'),
(20, N'Đang có sẵn', N'Mới', '2025-06-03'),
(20, N'Đang được mượn', N'Cũ', '2025-06-04'),
(20, N'Đang có sẵn', N'Mới', '2025-06-05'),
(20, N'Đang có sẵn', N'Cũ', '2025-06-06'),
(20, N'Đang có sẵn', N'Mới', '2025-06-07'),
(20, N'Đang có sẵn', N'Cũ', '2025-06-08'),
(20, N'Đang được mượn', N'Mới', '2025-06-09'),
(20, N'Đang có sẵn', N'Cũ', '2025-06-10'),
(21, N'Đang có sẵn', N'Mới', '2025-06-01'),
(21, N'Đang có sẵn', N'Cũ', '2025-06-02'),
(21, N'Đang có sẵn', N'Mới', '2025-06-03'),
(21, N'Đang được mượn', N'Cũ', '2025-06-04'),
(21, N'Đang có sẵn', N'Mới', '2025-06-05'),
(21, N'Đang có sẵn', N'Cũ', '2025-06-06'),
(21, N'Đang có sẵn', N'Mới', '2025-06-07'),
(21, N'Đang có sẵn', N'Cũ', '2025-06-08'),
(21, N'Đang được mượn', N'Mới', '2025-06-09'),
(21, N'Đang có sẵn', N'Cũ', '2025-06-10'),
(21, N'Đang có sẵn', N'Mới', '2025-06-11'),
(21, N'Đang có sẵn', N'Cũ', '2025-06-12'),
(21, N'Đang được mượn', N'Mới', '2025-06-13'),
(21, N'Đang có sẵn', N'Cũ', '2025-06-14'),
(21, N'Đang có sẵn', N'Mới', '2025-06-15'),
(21, N'Đang có sẵn', N'Cũ', '2025-06-16'),
(21, N'Đang có sẵn', N'Mới', '2025-06-17'),
(21, N'Đang có sẵn', N'Cũ', '2025-06-18'),
(21, N'Đang được mượn', N'Mới', '2025-06-19'),
(21, N'Đang có sẵn', N'Cũ', '2025-06-20'),
(22, N'Đang có sẵn', N'Mới', '2025-06-01'),
(22, N'Đang có sẵn', N'Cũ', '2025-06-02'),
(22, N'Đang có sẵn', N'Mới', '2025-06-03'),
(22, N'Đang được mượn', N'Cũ', '2025-06-04'),
(22, N'Đang có sẵn', N'Mới', '2025-06-05'),
(22, N'Đang có sẵn', N'Cũ', '2025-06-06'),
(22, N'Đang có sẵn', N'Mới', '2025-06-07'),
(22, N'Đang có sẵn', N'Cũ', '2025-06-08'),
(22, N'Đang được mượn', N'Mới', '2025-06-09'),
(22, N'Đang có sẵn', N'Cũ', '2025-06-10'),
(22, N'Đang có sẵn', N'Mới', '2025-06-11'),
(22, N'Đang có sẵn', N'Cũ', '2025-06-12'),
(22, N'Đang có sẵn', N'Mới', '2025-06-13'),
(22, N'Đang có sẵn', N'Cũ', '2025-06-14'),
(22, N'Đang có sẵn', N'Mới', '2025-06-15'),
(22, N'Đang có sẵn', N'Cũ', '2025-06-16'),
(22, N'Đang có sẵn', N'Mới', '2025-06-17'),
(22, N'Đang được mượn', N'Cũ', '2025-06-18'),
(23, N'Đang có sẵn', N'Mới', '2025-06-01'),
(23, N'Đang có sẵn', N'Cũ', '2025-06-02'),
(23, N'Đang có sẵn', N'Mới', '2025-06-03'),
(23, N'Đang được mượn', N'Cũ', '2025-06-04'),
(23, N'Đang có sẵn', N'Mới', '2025-06-05'),
(23, N'Đang có sẵn', N'Cũ', '2025-06-06'),
(23, N'Đang có sẵn', N'Mới', '2025-06-07'),
(23, N'Đang có sẵn', N'Cũ', '2025-06-08'),
(23, N'Đang được mượn', N'Mới', '2025-06-09'),
(23, N'Đang có sẵn', N'Cũ', '2025-06-10'),
(23, N'Đang có sẵn', N'Mới', '2025-06-11'),
(23, N'Đang có sẵn', N'Cũ', '2025-06-12'),
(23, N'Đang được mượn', N'Mới', '2025-06-13'),
(23, N'Đang có sẵn', N'Cũ', '2025-06-14'),
(23, N'Đang có sẵn', N'Mới', '2025-06-15'),
(24, N'Đang có sẵn', N'Mới', '2025-06-01'),
(24, N'Đang có sẵn', N'Cũ', '2025-06-02'),
(24, N'Đang có sẵn', N'Mới', '2025-06-03'),
(24, N'Đang được mượn', N'Cũ', '2025-06-04'),
(24, N'Đang có sẵn', N'Mới', '2025-06-05'),
(24, N'Đang có sẵn', N'Cũ', '2025-06-06'),
(24, N'Đang có sẵn', N'Mới', '2025-06-07'),
(24, N'Đang có sẵn', N'Cũ', '2025-06-08'),
(24, N'Đang được mượn', N'Mới', '2025-06-09'),
(24, N'Đang có sẵn', N'Cũ', '2025-06-10'),
(24, N'Đang có sẵn', N'Mới', '2025-06-11'),
(24, N'Đang được mượn', N'Cũ', '2025-06-12'),
(25, N'Đang có sẵn', N'Mới', '2025-06-01'),
(25, N'Đang có sẵn', N'Cũ', '2025-06-02'),
(25, N'Đang có sẵn', N'Mới', '2025-06-03'),
(25, N'Đang được mượn', N'Cũ', '2025-06-04'),
(25, N'Đang có sẵn', N'Mới', '2025-06-05'),
(25, N'Đang có sẵn', N'Cũ', '2025-06-06'),
(25, N'Đang có sẵn', N'Mới', '2025-06-07'),
(25, N'Đang có sẵn', N'Cũ', '2025-06-08'),
(25, N'Đang được mượn', N'Mới', '2025-06-09'),
(25, N'Đang có sẵn', N'Cũ', '2025-06-10');