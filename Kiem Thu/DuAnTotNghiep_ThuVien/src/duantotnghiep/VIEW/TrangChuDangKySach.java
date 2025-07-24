/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package duantotnghiep.VIEW;

import duantotnghiep.DAO.CRUD;
import duantotnghiep.MODEL.ChuDe;
import duantotnghiep.MODEL.Sach1;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/**
 *
 * @author thuyz
 */
public class TrangChuDangKySach extends javax.swing.JFrame {

    /**
     * Creates new form TrangChuUser
     */
    JFrame detailFrame = new JFrame();
    private List<Sach1> SachList = new ArrayList<>();
    private List<Sach1> filteredSachList = new ArrayList<>();
    private List<Sach1> ChuDeSach = new ArrayList<>(); // Danh sách các Sách lấy ra từ db
    private JPanel contentPanel;
    private JLabel pageLabel;
    private JButton nextButton, backButton, DKButton;
    private int currentPage = 1;
    private int itemsPerPage = 6;
    private List<ChuDe> ChuDeList = new ArrayList();
    List<Sach1> listToShow; //List de hien thi Sach/Sach theo chu de/ List sach tim kiem

    public TrangChuDangKySach() {
        initComponents();
        getContentPane().setBackground(Color.WHITE);

        setTitle("Hệ thống quản lý thư viện Thoth Library");
        getContentPane().setBackground(Color.WHITE);
        setLayout(new BorderLayout()); // Đặt layout chính
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setSize(screenSize.width, screenSize.height);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        panel1.setForeground(Color.WHITE);
        add(panel1, BorderLayout.NORTH);
        SachList = new ArrayList<>();
        filteredSachList = new ArrayList<>();
        String select = "select * from Sach";
        List<Object> selectparam = new ArrayList<>();

        try {
            ResultSet rs = (ResultSet) CRUD.executeQuery(select, selectparam);
            while (rs.next()) {
                Sach1 cd = new Sach1();
                cd.setSachID(rs.getInt(1));
                cd.setTenSach(rs.getString(2));
                cd.setTacGia(rs.getString(3));
                cd.setNamXuatBan(rs.getInt(4));
                cd.setSoTrang(rs.getInt(5));
                cd.setGiaTien(rs.getDouble(6));
                cd.setNhaXuatBan(rs.getString(7));
                cd.setRank(rs.getString(8));
                cd.setTrangThai(rs.getString(9));
                cd.setMota(rs.getString(10));
                cd.setSoNgayToiDa(rs.getInt(11));
                cd.setAvatar(rs.getString(12));
                SachList.add(cd);
            }
            rs.close(); // Đóng ResultSet sau khi đọc xong
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Panel chứa danh sách sách
        contentPanel = new JPanel();
        contentPanel.setLayout(new GridLayout(2, 3, 20, 20));
        contentPanel.setPreferredSize(new Dimension(600, 400)); // Điều chỉnh kích thước hợp lý 
        add(contentPanel, BorderLayout.CENTER);
        contentPanel.setBackground(Color.decode("#FFF5EE"));

        // Panel chứa điều hướng trang
        JPanel paginationPanel = new JPanel();
        ImageIcon arrowIcon = new ImageIcon(getClass().getResource("/duantotnghiep/IMAGE/out-removebg-preview.png"));
        Image arrowimage = arrowIcon.getImage();
        Image scaledarrowImage = arrowimage.getScaledInstance(15, 15, Image.SCALE_SMOOTH);
        ImageIcon scaledarrowIcon = new ImageIcon(scaledarrowImage);
        JButton arrowButton = new JButton(scaledarrowIcon);
        paginationPanel.add(arrowButton);
        arrowButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        paginationPanel.setBackground(Color.decode("#FFF5EE"));

        backButton = new JButton("Back");
        nextButton = new JButton("Next");
        pageLabel = new JLabel("Page: 1");
        DKButton = new JButton("Đăng ký");

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (currentPage > 1) {
                    currentPage--;
                    loadPage();
                }
            }
        });
        nextButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int totalPages = (int) Math.ceil((double) listToShow.size() / itemsPerPage);
                if (currentPage < totalPages) {
                    currentPage++;
                    loadPage();
                }
            }
        });
        DKButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DangKyPhieuMuon dkpm = new DangKyPhieuMuon();
                dkpm.setVisible(true);
            }
        });
        paginationPanel.add(backButton);
        paginationPanel.add(pageLabel);
        paginationPanel.add(nextButton);
        paginationPanel.add(DKButton);
        add(paginationPanel, BorderLayout.SOUTH);

        // Tải trang đầu tiên
        loadPage();

        revalidate(); // Cập nhật giao diện
        repaint();
        this.setVisible(true);
    }

    private JPanel createSachPanel(Sach1 cd) { // Tạo Panel, mỗi panel là 1 chuyên đề
        JPanel bookPanel = new JPanel();

        bookPanel.setLayout(new BorderLayout());
        bookPanel.setPreferredSize(new Dimension(150, 220));
        bookPanel.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 2));
        bookPanel.setBackground(Color.WHITE);

        // Ảnh sách
        JLabel imageLabel = new JLabel();
        imageLabel.setHorizontalAlignment(JLabel.CENTER);
        imageLabel.setPreferredSize(new Dimension(120, 200));

        if (cd.getAvatar() != null && !cd.getAvatar().isEmpty()) {
            ImageIcon bookImage = new ImageIcon(cd.getAvatar());
            Image img = bookImage.getImage().getScaledInstance(120, 200, Image.SCALE_SMOOTH);
            imageLabel.setIcon(new ImageIcon(img));
        } else {
            imageLabel.setText("No Image");
        }

        // Tên sách
        JLabel nameLabel = new JLabel(cd.getTenSach(), JLabel.CENTER);
        nameLabel.setFont(new Font("Arial", Font.BOLD, 14));

        // Giá sách
        JLabel priceLabel = new JLabel(cd.getGiaTien() + " đ", JLabel.CENTER);
        priceLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        priceLabel.setForeground(Color.DARK_GRAY);

        // Nút chi tiết
        JButton detailButton = new JButton("Chi tiết");
        detailButton.setPreferredSize(new Dimension(80, 30));

        detailButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showSachDialog(cd);
            }
        });

        // Panel chứa thông tin sách
        JPanel infoPanel = new JPanel(new GridLayout(3, 1));
        infoPanel.add(nameLabel);
        infoPanel.add(priceLabel);
        infoPanel.add(detailButton);

        // Thêm các phần tử vào bookPanel
        bookPanel.add(imageLabel, BorderLayout.NORTH);
        bookPanel.add(infoPanel, BorderLayout.CENTER);
        return bookPanel;
    }

    public void loadPage() {
        contentPanel.removeAll(); // Xóa sách cũ

        if (filteredSachList != null && !filteredSachList.isEmpty()) {
            listToShow = filteredSachList;
        } else if (ChuDeSach != null && !ChuDeSach.isEmpty()) {
            listToShow = ChuDeSach;
        } else {
            listToShow = SachList;
        }

        int start = (currentPage - 1) * itemsPerPage;

        int end = Math.min(start + itemsPerPage, listToShow.size());

        for (int i = start; i < end; i++) {
            Sach1 sach = listToShow.get(i);
            contentPanel.add(createSachPanel(sach));
        }
        // Nếu trang cuối có ít hơn 4 chuyên đề, thêm các panel rỗng để giữ layout
        int emptyPanels = itemsPerPage - (end - start);
        for (int i = 0; i < emptyPanels; i++) {
            contentPanel.add(new JPanel()); // Panel rỗng để giữ bố cục
        }
        // Cập nhật số trang
        int totalItems = listToShow.size(); // Lấy tổng số sách từ danh sách hiển thị
        int totalPages = (int) Math.ceil((double) totalItems / itemsPerPage);

        pageLabel.setText("< " + currentPage + " / " + totalPages + " >");

        // Cập nhật giao diện
        contentPanel.revalidate();
        contentPanel.repaint();
    }

    private void CapnhatTongTrang() {
        int totalItems = 0;

        if (filteredSachList != null && !filteredSachList.isEmpty()) {
            totalItems = filteredSachList.size();
        } else if (ChuDeSach != null && !ChuDeSach.isEmpty()) {  // Kiểm tra null
            totalItems = ChuDeSach.size();
        } else if (SachList != null) {
            totalItems = SachList.size();
        }

        int totalPages = (int) Math.ceil((double) totalItems / itemsPerPage);
        pageLabel.setText("< " + currentPage + " / " + totalPages + " >");
    }

    public void searchSach(String keyword) {
        filteredSachList.clear();  // Xóa kết quả tìm kiếm trước đó
        if (keyword == null || keyword.trim().isEmpty()) {
            filteredSachList.addAll(SachList);  // Nếu ô tìm kiếm trống, hiển thị toàn bộ sách
        } else {
            String lowerKeyword = keyword.toLowerCase();// Chuyển keyword về chữ thường để tìm kiếm không phân biệt hoa thường
            if (ChuDeSach != null && !ChuDeSach.isEmpty()) {// Nếu đang ở màn hình hiển thị sách theo chủ đề sẽ 
                for (Sach1 sach : ChuDeSach) {
                    if (sach.getTenSach().toLowerCase().contains(lowerKeyword)) {
                        filteredSachList.add(sach);// Nếu tên sách chứa từ khóa, thêm vào danh sách kết quả
                    }
                }
            } else {
                for (Sach1 sach : SachList) {// Nếu ở màn hình hiện sách bthg thì tìm tất cả các sách
                    if (sach.getTenSach().toLowerCase().contains(lowerKeyword)) {
                        filteredSachList.add(sach);// Nếu tên sách chứa từ khóa, thêm vào danh sách kết quả
                    }
                }
            }
        }
        currentPage = 1;  // Reset về trang đầu tiên
        CapnhatTongTrang();  // Cập nhật số trang
        loadPage();   // Cập nhật lại giao diện với kết quả tìm kiếm
    }

    private void showSachDialog(Sach1 sach) {
        JFrame detailFrame = new JFrame("Chi tiết sách");
        detailFrame.setSize(400, 600);
        detailFrame.setLocationRelativeTo(null);
        detailFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Tạo panel chính
        JPanel panelchinh = new JPanel(new BorderLayout());
        panelchinh.setBackground(Color.WHITE);
        panelchinh.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); // Tạo padding cho toàn bộ panel
        // Panel chứa ảnh
        JPanel imagePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JLabel imageLabel = new JLabel();
        if (sach.getAvatar() != null && !sach.getAvatar().isEmpty()) {
            ImageIcon bookImage = new ImageIcon(sach.getAvatar());
            Image img = bookImage.getImage().getScaledInstance(250, 250, Image.SCALE_SMOOTH);
            imageLabel.setIcon(new ImageIcon(img));
        } else {
            imageLabel.setText("Không có ảnh");
        }
        imagePanel.add(imageLabel);
        panelchinh.add(imagePanel, BorderLayout.NORTH); // Đặt ảnh ở trên cùng

        // Panel chứa thông tin sách
        JPanel infoPanel = new JPanel(new GridLayout(3, 1, 10, 10)); // Chia thành 3 hàng, có khoảng cách 10px
        infoPanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0)); // Tạo khoảng cách trên/dưới

        // Tên sách
        JLabel nameLabel = new JLabel(sach.getTenSach(), JLabel.CENTER);
        nameLabel.setFont(new Font("Arial", Font.BOLD, 20));
        infoPanel.add(nameLabel);

        // Mô tả sách
        JTextArea descriptionArea = new JTextArea(sach.getMota());
        descriptionArea.setFont(new Font("Arial", Font.PLAIN, 14));
        descriptionArea.setWrapStyleWord(true);
        descriptionArea.setLineWrap(true);
        descriptionArea.setEditable(false);
        descriptionArea.setOpaque(false);
        descriptionArea.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10)); // Thêm padding
        JScrollPane descriptionScroll = new JScrollPane(descriptionArea);

        descriptionScroll.setBorder(null);
        infoPanel.add(descriptionScroll);

        panelchinh.add(infoPanel, BorderLayout.CENTER); // Đặt thông tin sách ở giữa

        // Panel chứa nút bấm
        // Đặt nút ở dưới cùng
        detailFrame.add(panelchinh);
        detailFrame.setVisible(true);
    }

    private void hienThiSachTheoChuDe(String tenChuDe) {
        ChuDeSach.clear();
        filteredSachList.clear();
        contentPanel.removeAll(); // Xóa sách cũ
        try {
            Connection conn = DriverManager.getConnection(CRUD.connectionUrl);
            String sql = "SELECT S.SachID, S.TenSach, S.TacGia, S.NamXuatBan,  "
                    + "S.SoTrang, S.GiaTien, S.NhaXuatBan, S.Rank, S.TrangThai,S.Mota, S.Avatar "
                    + "FROM Sach S "
                    + "JOIN ChuDeSach CDS ON S.SachID = CDS.SachID "
                    + "JOIN ChuDe CD ON CDS.ChuDeID = CD.ChuDeID "
                    + "WHERE CD.TenChuDe = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, tenChuDe);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Sach1 cd = new Sach1();
                cd.setSachID(rs.getInt(1));
                cd.setTenSach(rs.getString(2));
                cd.setTacGia(rs.getString(3));
                cd.setNamXuatBan(rs.getInt(4));
                cd.setSoTrang(rs.getInt(5));
                cd.setGiaTien(rs.getDouble(6));
                cd.setNhaXuatBan(rs.getString(7));
                cd.setRank(rs.getString(8));
                cd.setTrangThai(rs.getString(9));
                cd.setMota(rs.getString(10));
                cd.setAvatar(rs.getString(11));
                ChuDeSach.add(cd);
//            contentPanel.add(createSachPanel(cd));
            }
            contentPanel.revalidate();
            contentPanel.repaint();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        currentPage = 1;
        CapnhatTongTrang();  // Cập nhật tổng số trang
        loadPage();
    }

    private void HienThiFullChuDe(JComponent anchor) {
        String select = "select * from ChuDe where ChuDeID>4";
        List<Object> selectparam = new ArrayList<>();
        ChuDeList.clear();

        try {
            ResultSet rs = (ResultSet) CRUD.executeQuery(select, selectparam);
            while (rs.next()) {
                ChuDe cd = new ChuDe();
                cd.setChudeid(rs.getInt(1));
                cd.setTenchude(rs.getString(2));
                ChuDeList.add(cd);
            }
            rs.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        JPopupMenu popupMenu = new JPopupMenu();

        for (ChuDe CD : ChuDeList) {
            JMenuItem menuItem = new JMenuItem(CD.getTenchude());
            menuItem.setFont(new Font("Arial", Font.PLAIN, 14));
            menuItem.addActionListener(e -> {
                hienThiSachTheoChuDe(CD.getTenchude());
            });
            popupMenu.add(menuItem);
        }

        // Hiển thị menu ngay dưới JLabel (lblThemChuDe)
        popupMenu.show(anchor, 0, anchor.getHeight());
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenu1 = new javax.swing.JMenu();
        jPopupMenu1 = new javax.swing.JPopupMenu();
        jMenu2 = new javax.swing.JMenu();
        panel1 = new javax.swing.JPanel();
        lblKhoaHoc = new javax.swing.JLabel();
        lblVanHoc = new javax.swing.JLabel();
        lblLichSu = new javax.swing.JLabel();
        lblKinhTe = new javax.swing.JLabel();
        txtTimKiem = new javax.swing.JTextField();
        btnTImKiem = new javax.swing.JButton();
        lblThemChuDe = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        jMenu1.setText("jMenu1");

        jMenu2.setText("jMenu2");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(java.awt.Color.white);

        panel1.setBackground(new java.awt.Color(102, 51, 0));
        panel1.setBorder(BorderFactory.createMatteBorder(2, 0, 2, 0, Color.BLACK));
        panel1.setForeground(new java.awt.Color(255, 255, 255));

        lblKhoaHoc.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblKhoaHoc.setForeground(new java.awt.Color(255, 255, 255));
        lblKhoaHoc.setText("Khoa học");
        lblKhoaHoc.setBorder(BorderFactory.createMatteBorder(0, 0, 0,1, Color.BLACK));
        lblKhoaHoc.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblKhoaHocMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lblKhoaHocMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lblKhoaHocMouseExited(evt);
            }
        });

        lblVanHoc.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblVanHoc.setForeground(new java.awt.Color(255, 255, 255));
        lblVanHoc.setText("Văn học");
        lblVanHoc.setBorder(BorderFactory.createMatteBorder(0, 0, 0,1, Color.BLACK));
        lblVanHoc.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblVanHocMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lblVanHocMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lblVanHocMouseExited(evt);
            }
        });

        lblLichSu.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblLichSu.setForeground(new java.awt.Color(255, 255, 255));
        lblLichSu.setText("Lịch sử");
        lblLichSu.setBorder(BorderFactory.createMatteBorder(0, 0, 0,1, Color.BLACK));
        lblLichSu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblLichSuMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lblLichSuMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lblLichSuMouseExited(evt);
            }
        });

        lblKinhTe.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblKinhTe.setForeground(new java.awt.Color(255, 255, 255));
        lblKinhTe.setText("Kinh tế");
        lblKinhTe.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblKinhTeMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lblKinhTeMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lblKinhTeMouseExited(evt);
            }
        });

        txtTimKiem.setBackground(new java.awt.Color(204, 204, 204));
        txtTimKiem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTimKiemActionPerformed(evt);
            }
        });

        btnTImKiem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/duantotnghiep/IMAGE/kinhlup.png"))); // NOI18N
        btnTImKiem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTImKiemActionPerformed(evt);
            }
        });

        lblThemChuDe.setForeground(new java.awt.Color(255, 255, 255));
        lblThemChuDe.setText("++\n++");
        lblThemChuDe.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                lblThemChuDeMouseMoved(evt);
            }
        });
        lblThemChuDe.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblThemChuDeMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lblThemChuDeMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lblThemChuDeMouseExited(evt);
            }
        });

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/duantotnghiep/IMAGE/home (2).png"))); // NOI18N
        jLabel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel1MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout panel1Layout = new javax.swing.GroupLayout(panel1);
        panel1.setLayout(panel1Layout);
        panel1Layout.setHorizontalGroup(
            panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(51, 51, 51)
                .addComponent(lblKhoaHoc, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblVanHoc, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblLichSu, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lblKinhTe, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblThemChuDe)
                .addGap(18, 18, 18)
                .addComponent(txtTimKiem, javax.swing.GroupLayout.DEFAULT_SIZE, 376, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnTImKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(14, 14, 14))
        );
        panel1Layout.setVerticalGroup(
            panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblKhoaHoc, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblVanHoc, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblLichSu, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblKinhTe, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblThemChuDe, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(jLabel1))
                    .addGroup(panel1Layout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addComponent(btnTImKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(9, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(464, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void lblKhoaHocMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblKhoaHocMouseEntered
        lblKhoaHoc.setForeground(new Color(245, 245, 220)); 
        lblKhoaHoc.setFont(new Font("Arial", Font.BOLD, 15)); 
    }//GEN-LAST:event_lblKhoaHocMouseEntered

    private void lblKhoaHocMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblKhoaHocMouseExited
        lblKhoaHoc.setForeground(Color.white);
        lblKhoaHoc.setFont(new Font("Arial", Font.PLAIN, 14)); // Chữ in đậm
    }//GEN-LAST:event_lblKhoaHocMouseExited

    private void lblVanHocMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblVanHocMouseEntered
        lblVanHoc.setForeground(new Color(245, 245, 220)); 
        lblVanHoc.setFont(new Font("Arial", Font.BOLD, 15)); 
    }//GEN-LAST:event_lblVanHocMouseEntered

    private void lblVanHocMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblVanHocMouseExited
        lblVanHoc.setForeground(Color.white);
        lblVanHoc.setFont(new Font("Arial", Font.PLAIN, 14));
    }//GEN-LAST:event_lblVanHocMouseExited

    private void lblLichSuMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblLichSuMouseEntered
        lblLichSu.setForeground(new Color(245, 245, 220)); 
        lblLichSu.setFont(new Font("Arial", Font.BOLD, 15)); 
    }//GEN-LAST:event_lblLichSuMouseEntered

    private void lblLichSuMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblLichSuMouseExited
        lblLichSu.setForeground(Color.white);
        lblLichSu.setFont(new Font("Arial", Font.PLAIN, 14));
    }//GEN-LAST:event_lblLichSuMouseExited

    private void lblKinhTeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblKinhTeMouseClicked
        hienThiSachTheoChuDe(lblKinhTe.getText());
    }//GEN-LAST:event_lblKinhTeMouseClicked

    private void lblKinhTeMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblKinhTeMouseEntered
        lblKinhTe.setForeground(new Color(245, 245, 220)); 
        lblKinhTe.setFont(new Font("Arial", Font.BOLD, 15)); 
    }//GEN-LAST:event_lblKinhTeMouseEntered

    private void lblKinhTeMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblKinhTeMouseExited
        lblKinhTe.setForeground(Color.white);
        lblKinhTe.setFont(new Font("Arial", Font.PLAIN, 14));
    }//GEN-LAST:event_lblKinhTeMouseExited

    private void btnTImKiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTImKiemActionPerformed
        String keyword = txtTimKiem.getText();  // Lấy nội dung trong ô tìm kiếm
        searchSach(keyword);
    }//GEN-LAST:event_btnTImKiemActionPerformed

    private void txtTimKiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTimKiemActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTimKiemActionPerformed

    private void lblThemChuDeMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblThemChuDeMouseEntered

    }//GEN-LAST:event_lblThemChuDeMouseEntered

    private void lblThemChuDeMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblThemChuDeMouseExited

    }//GEN-LAST:event_lblThemChuDeMouseExited

    private void lblThemChuDeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblThemChuDeMouseClicked

        HienThiFullChuDe((JComponent) evt.getSource());
    }//GEN-LAST:event_lblThemChuDeMouseClicked

    private void lblThemChuDeMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblThemChuDeMouseMoved

    }//GEN-LAST:event_lblThemChuDeMouseMoved

    private void lblKhoaHocMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblKhoaHocMouseClicked
        hienThiSachTheoChuDe(lblKhoaHoc.getText());
    }//GEN-LAST:event_lblKhoaHocMouseClicked

    private void lblVanHocMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblVanHocMouseClicked
        hienThiSachTheoChuDe(lblVanHoc.getText());
    }//GEN-LAST:event_lblVanHocMouseClicked

    private void lblLichSuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblLichSuMouseClicked
        hienThiSachTheoChuDe(lblLichSu.getText());
    }//GEN-LAST:event_lblLichSuMouseClicked

    private void jLabel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MouseClicked
        ChuDeSach.clear();
        filteredSachList.clear();
        currentPage = 1;
        listToShow = SachList;
        CapnhatTongTrang();
        loadPage();
    }//GEN-LAST:event_jLabel1MouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(TrangChuDangKySach.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TrangChuDangKySach.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TrangChuDangKySach.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TrangChuDangKySach.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TrangChuDangKySach().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnTImKiem;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JPopupMenu jPopupMenu1;
    private javax.swing.JLabel lblKhoaHoc;
    private javax.swing.JLabel lblKinhTe;
    private javax.swing.JLabel lblLichSu;
    private javax.swing.JLabel lblThemChuDe;
    private javax.swing.JLabel lblVanHoc;
    private javax.swing.JPanel panel1;
    private javax.swing.JTextField txtTimKiem;
    // End of variables declaration//GEN-END:variables
}
