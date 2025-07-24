/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package duantotnghiep.VIEW;

import duantotnghiep.DAO.DaoChiTietSach;
import duantotnghiep.DAO.DaoSach;
import duantotnghiep.MODEL.ChiTietSach;
import duantotnghiep.MODEL.Sach;
import duantotnghiep.VIEW.ThongKe.ThongKeMuonSachPanel;
import duantotnghiep.VIEW.ThongKe.ThongKeTongTienPhatPanel;
import duantotnghiep.View.ThongKe.ThongKeSachDuocMuonNhieuNhat;
import duantotnghiep.View.ThongKe.ThongKeSoSachDangMuon;
import duantotnghiep.View.ThongKe.TyLeTraDungHanvaMuon;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.util.ArrayList;
import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;

/**
 *
 * @author MINHDUC
 */
public class QLSach extends javax.swing.JFrame {

    /**
     * Creates new form QLSach
     */
    public String currentFilter = "All";
    int idSelected = 0;
    ArrayList<ChiTietSach> danhsachchitiet;

    public QLSach() {
        initComponents();
        form();
    }

    public void form() {
        setTitle("Hệ thống quản lý thư viện");

        // Thiết lập kích thước cửa sổ bằng màn hình
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setSize(screenSize.width, screenSize.height);
        setExtendedState(JFrame.MAXIMIZED_BOTH);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JPanel sachPanel = new JPanel(new BorderLayout());
        JPanel docGiaPanel = new JPanel(new BorderLayout());
        JPanel thongKePanel = new JPanel(new BorderLayout());
        JPanel nhanVienPanel = new JPanel(new BorderLayout());
        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.setBackground(Color.white);
        sachPanel.setBackground(Color.white);
        docGiaPanel.setBackground(Color.white);
        thongKePanel.setBackground(Color.white);
        nhanVienPanel.setBackground(Color.white);
        tabbedPane.addTab("Sách", sachPanel);
        tabbedPane.addTab("Độc giả", new JPanel());
        tabbedPane.addTab("Nhân viên", new JPanel());
        tabbedPane.addTab("Phiếu mượn", new JPanel());
        tabbedPane.addTab("Phiếu trả", new JPanel());
        tabbedPane.addTab("Phiếu phạt", new JPanel());
        tabbedPane.addTab("Lịch Sử Mượn", new JPanel());
        tabbedPane.addTab("Đăng Ký Độc Giả", new JPanel());

        tabbedPane.setSelectedIndex(0);

        JTabbedPane tabThongKe = new JTabbedPane();
        tabThongKe.addTab("Thống kê phiếu phạt", new JScrollPane(new ThongKeTongTienPhatPanel()));
        tabThongKe.addTab("Thống kê Tỉ lệ trả sách đúng hạn và trễ hạn", new JScrollPane(new TyLeTraDungHanvaMuon()));
        tabThongKe.addTab("Thống kê số lượng sách đang mượn", new JScrollPane(new ThongKeSoSachDangMuon()));
        tabThongKe.addTab("Thống kê số lượng sách được mượn nhiều nhất", new JScrollPane(new ThongKeSachDuocMuonNhieuNhat()));
        tabThongKe.addTab("Thống kê số lượng sách mượn theo ngày", new JScrollPane(new ThongKeMuonSachPanel()));

        tabbedPane.addTab("Thống kê", tabThongKe);
        tabbedPane.addTab("Đăng xuất", new JPanel());
        add(tabbedPane, BorderLayout.NORTH);

        JPanel filterPanel = new JPanel();
        filterPanel.setBackground(Color.white);
        JButton allButton = new JButton("All");
        JButton conButton = new JButton("Còn");
        JButton hetButton = new JButton("Hết");
        JButton csButton = new JButton("Chỉnh Sửa");
        filterPanel.add(allButton);
        filterPanel.add(conButton);
        filterPanel.add(hetButton);
        filterPanel.add(csButton);
        sachPanel.add(filterPanel, BorderLayout.NORTH);
        sachPanel.setBackground(Color.white);
        JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JTextField searchField = new JTextField(20);
        ImageIcon icon = new ImageIcon(getClass().getResource("/duantotnghiep/IMAGE/KL.png"));
        Image image = icon.getImage();
        Image scaledImage = image.getScaledInstance(15, 15, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(scaledImage);
        searchPanel.setBackground(Color.white);
        JButton searchButton = new JButton(scaledIcon);
        filterPanel.add(searchField);
        filterPanel.add(searchButton);
        sachPanel.add(searchPanel, BorderLayout.SOUTH);
        searchField.setText("Tìm kiếm...");
        searchField.setForeground(Color.GRAY);

        searchField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (searchField.getText().equals("Tìm kiếm...")) {
                    searchField.setText("");
                    searchField.setForeground(Color.BLACK);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (searchField.getText().trim().isEmpty()) {
                    searchField.setText("Tìm kiếm...");
                    searchField.setForeground(Color.GRAY);
                }
            }
        });

        JPanel arrowPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        ImageIcon arrowIcon = new ImageIcon(getClass().getResource("/duantotnghiep/IMAGE/out-removebg-preview.png"));
        Image arrowimage = arrowIcon.getImage();
        Image scaledarrowImage = arrowimage.getScaledInstance(15, 15, Image.SCALE_SMOOTH);
        ImageIcon scaledarrowIcon = new ImageIcon(scaledarrowImage);
        JButton arrowButton = new JButton(scaledarrowIcon);
        filterPanel.add(arrowButton);
        arrowPanel.setBackground(Color.white);
        sachPanel.add(arrowPanel, BorderLayout.SOUTH);
        arrowButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        String[] columnNames = {
            "STT", "Tên sách", "Tác giả", "Năm sản xuất", "Số trang", "Giá tiền",
            "Nhà xuất bản", "Rank", "TrangThai", "MoTa", "SoNgayToiDa", "Avatar", "Số lượng"
        };

        DefaultTableModel model = new DefaultTableModel(columnNames, 0);

        JTable table = new JTable(model) {
            @Override
            public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
                Component comp = super.prepareRenderer(renderer, row, column);

                // Màu xen kẽ trắng và #FFF5EE
                if (!isRowSelected(row)) {
                    Color seashell = new Color(0xFF, 0xF5, 0xEE); // #FFF5EE
                    comp.setBackground(row % 2 == 0 ? Color.WHITE : seashell);
                } else {
                    comp.setBackground(getSelectionBackground());
                }

                return comp;
            }
        };

        table.setFillsViewportHeight(true);
        table.setShowGrid(true);
        table.setGridColor(Color.LIGHT_GRAY);

// Thêm vào JScrollPane
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setPreferredSize(new Dimension(780, 350));
        sachPanel.add(scrollPane, BorderLayout.CENTER);

// Đổ dữ liệu vào bảng
        ArrayList<Sach> danhSachSach = DaoSach.getAll();
        int STT = 1;
        for (Sach sach : danhSachSach) {
            Object[] rowData = {
                STT++ + "",
                sach.getTenSach(),
                sach.getTacGia(),
                sach.getNamXuatBan(),
                sach.getSoTrang(),
                sach.getGiaTien(),
                sach.getNhaXuatBan(),
                sach.getRank(),
                sach.getTrangThai(),
                sach.getMota(),
                sach.getSoNgayMuon(),
                sach.getAvatar(),
                sach.getSoLuong()
            };
            model.addRow(rowData);
        }

        table.getColumnModel().getColumn(8).setCellRenderer(new TrangThaiTableCellRenderer());
        setVisible(true);
        allButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                currentFilter = "All";
                model.setRowCount(0);
                ArrayList<Sach> danhSachSach = DaoSach.getAll();
                int STT = 1;
                for (Sach sach : danhSachSach) {
                    Object[] rowData = {
                        STT++ + "",
                        sach.getTenSach(),
                        sach.getTacGia(),
                        sach.getNamXuatBan(),
                        sach.getSoTrang(),
                        sach.getGiaTien(),
                        sach.getNhaXuatBan(),
                        sach.getRank(),
                        sach.getTrangThai(),
                        sach.getMota(),
                        sach.getSoNgayMuon(),
                        sach.getAvatar(),
                        sach.getSoLuong(),};
                    model.addRow(rowData);

                }
            }
        });

        conButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                currentFilter = "Con";
                model.setRowCount(0);
                ArrayList<Sach> danhSachSach = DaoSach.getAllCon();
                int STT = 1;
                for (Sach sach : danhSachSach) {
                    Object[] rowData = {
                        STT++ + "",
                        sach.getTenSach(),
                        sach.getTacGia(),
                        sach.getNamXuatBan(),
                        sach.getSoTrang(),
                        sach.getGiaTien(),
                        sach.getNhaXuatBan(),
                        sach.getRank(),
                        sach.getTrangThai(),
                        sach.getMota(),
                        sach.getSoNgayMuon(),
                        sach.getAvatar(),
                        sach.getSoLuong(),};
                    model.addRow(rowData);

                }
            }
        });
        hetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                currentFilter = "Het";
                model.setRowCount(0);
                ArrayList<Sach> danhSachSach = DaoSach.getAllHet();
                int STT = 1;
                for (Sach sach : danhSachSach) {
                    Object[] rowData = {
                        STT++ + "",
                        sach.getTenSach(),
                        sach.getTacGia(),
                        sach.getNamXuatBan(),
                        sach.getSoTrang(),
                        sach.getGiaTien(),
                        sach.getNhaXuatBan(),
                        sach.getRank(),
                        sach.getTrangThai(),
                        sach.getMota(),
                        sach.getSoNgayMuon(),
                        sach.getAvatar(),
                        sach.getSoLuong(),};
                    model.addRow(rowData);

                }
            }
        });
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String searchText = searchField.getText();
                if (searchText.equals("Tìm kiếm...")) {
                    searchText = "";
                }
                ArrayList<Sach> sachs;
                DefaultTableModel model = (DefaultTableModel) table.getModel();
                model.setRowCount(0);
                if (currentFilter.equals("All")) {
                    sachs = DaoSach.getPBByName(searchText);
                } else if (currentFilter.equals("Con")) {
                    sachs = DaoSach.getPBByNameCon(searchText);
                } else {
                    sachs = DaoSach.getPBByNameHet(searchText);
                }
                int STT = 1;
                for (Sach sach : sachs) {
                    model.addRow(new Object[]{
                        STT++ + "",
                        sach.getTenSach(),
                        sach.getTacGia(),
                        sach.getNamXuatBan(),
                        sach.getSoTrang(),
                        sach.getGiaTien(),
                        sach.getNhaXuatBan(),
                        sach.getRank(),
                        sach.getTrangThai(),
                        sach.getMota(),
                        sach.getSoNgayMuon(),
                        sach.getAvatar(),
                        sach.getSoLuong(),});
                }
            }
        });
        csButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FormSach form = new FormSach();
                form.setVisible(true);
                dispose();
            }
        });
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int selectedRow = table.getSelectedRow();
                dispose();
                int sachID = danhSachSach.get(selectedRow).getSachID();
                hienThiChiTietSach(sachID);
            }
        }
        );
        tabbedPane.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int selected = tabbedPane.getSelectedIndex();
                String tabname = tabbedPane.getTitleAt(selected);
                if ("Sách".equals(tabname)) {
                    QLSach qls = new QLSach();
                    qls.setVisible(true);
                    dispose();
                } else if ("Độc giả".equals(tabname)) {
                    QLDocGia qldg = new QLDocGia();
                    qldg.setVisible(true);
                    dispose();
                } else if ("Phiếu mượn".equals(tabname)) {
                    QLPMuon qlpm = new QLPMuon();
                    qlpm.setVisible(true);
                    dispose();
                } else if ("Nhân viên".equals(tabname)) {
                    QLNhanVien qlnv = new QLNhanVien();
                    qlnv.setVisible(true);
                    dispose();
                } else if ("Phiếu trả".equals(tabname)) {
                    QLPhieuTra qldg = new QLPhieuTra();
                    qldg.setVisible(true);
                } else if ("Lịch Sử Mượn".equals(tabname)) {
                    QLLichSuMuon qllsm = new QLLichSuMuon();
                    qllsm.setVisible(true);
                } else if ("Đăng Ký Độc Giả".equals(tabname)) {
                    DangKyDocGia dk = new DangKyDocGia();
                    dk.setVisible(true);
                } else if ("Phiếu phạt".equals(tabname)) {
                    QuanLyPP qlpp = new QuanLyPP();
                    qlpp.setVisible(true);
                    dispose();
                } else if ("Đăng xuất".equals(tabname)) {
                    int confirm = JOptionPane.showConfirmDialog(
                            null,
                            "Bạn có chắc chắn muốn đăng xuất ?",
                            "Xác nhận đăng xuất",
                            JOptionPane.YES_NO_OPTION
                    );
                    if (confirm == JOptionPane.YES_OPTION) {
                        dispose();
                        new Login().setVisible(true);
                    }
                }
            }
        });
    }

    public void hienThiChiTietSach(int sachID) {
        JFrame chiTietFrame = new JFrame("Hệ thống quản lý thư viện");

        // Đặt kích thước cửa sổ bằng màn hình và mở rộng tối đa
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        chiTietFrame.setSize(screenSize.width, screenSize.height);
        chiTietFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);

        chiTietFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        chiTietFrame.setLocationRelativeTo(null);
        chiTietFrame.setLayout(new BorderLayout());

        JPanel northPanel = new JPanel();
        northPanel.setLayout(new BoxLayout(northPanel, BoxLayout.Y_AXIS));

        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JPanel undertopPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JPanel buttontopPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));

        northPanel.add(topPanel);
        northPanel.add(undertopPanel);
        northPanel.add(buttontopPanel);

        chiTietFrame.add(northPanel, BorderLayout.NORTH);

        JLabel lblId = new JLabel("STT");
        lblId.setPreferredSize(new Dimension(80, 30));
        topPanel.add(lblId);
        JTextField txtId = new JTextField("");
        txtId.setPreferredSize(new Dimension(230, 30));
        topPanel.add(txtId);

        JLabel lblSachID = new JLabel("SachID");
        lblSachID.setPreferredSize(new Dimension(80, 30));
        topPanel.add(lblSachID);
        JTextField txtSachID = new JTextField(String.valueOf(sachID));
        txtSachID.setPreferredSize(new Dimension(150, 30));
        topPanel.add(txtSachID);
        txtId.setEditable(false);
        txtSachID.setEditable(false);

        JLabel lblTrangThai = new JLabel("TrangThai");
        lblTrangThai.setPreferredSize(new Dimension(80, 30));
        undertopPanel.add(lblTrangThai);
        JRadioButton rdoCon = new JRadioButton("Đang có sẵn");
        rdoCon.setPreferredSize(new Dimension(100, 30));
        undertopPanel.add(rdoCon);
        JRadioButton rdoHet = new JRadioButton("Đang được mượn");
        rdoHet.setPreferredSize(new Dimension(120, 30));
        undertopPanel.add(rdoHet);
        ButtonGroup groupTrangThai = new ButtonGroup();
        groupTrangThai.add(rdoCon);
        groupTrangThai.add(rdoHet);
        rdoCon.setEnabled(false);
        rdoHet.setEnabled(false);

        JLabel lblTinhTrangSach = new JLabel("TinhTrangSach");
        lblTinhTrangSach.setPreferredSize(new Dimension(100, 30));
        undertopPanel.add(lblTinhTrangSach);
        JRadioButton rdoMoi = new JRadioButton("Mới");
        rdoMoi.setPreferredSize(new Dimension(50, 30));
        undertopPanel.add(rdoMoi);
        JRadioButton rdoCu = new JRadioButton("Cũ");
        rdoCu.setPreferredSize(new Dimension(80, 30));
        undertopPanel.add(rdoCu);
        ButtonGroup groupTinhTrang = new ButtonGroup();
        groupTinhTrang.add(rdoMoi);
        groupTinhTrang.add(rdoCu);

        JLabel lblNgayNhap = new JLabel("NgayNhap");
        lblNgayNhap.setPreferredSize(new Dimension(80, 30));
        undertopPanel.add(lblNgayNhap);
        JTextField txtNgayNhap = new JTextField("");
        txtNgayNhap.setPreferredSize(new Dimension(150, 30));
        undertopPanel.add(txtNgayNhap);

        JButton btnThemSach = new JButton("Thêm");
        btnThemSach.setPreferredSize(new Dimension(100, 30));
        JButton btnSua = new JButton("Sửa");
        btnSua.setPreferredSize(new Dimension(100, 30));
        JButton btnXoa = new JButton("Xoá");
        btnXoa.setPreferredSize(new Dimension(100, 30));
        JButton btnClose = new JButton("Close");
        btnClose.setPreferredSize(new Dimension(100, 30));
        btnClose.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                chiTietFrame.dispose();
                QLSach qls = new QLSach();
                qls.setVisible(true);
            }
        });

        buttontopPanel.add(btnThemSach);
        buttontopPanel.add(btnSua);
        buttontopPanel.add(btnXoa);
        buttontopPanel.add(btnClose);

        String[] columnNames = {"STT", "Sách ID", "ChiTietID", "Trạng thái", "Tình trạng sách", "Ngày nhập"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);
        JTable table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setPreferredSize(new Dimension(780, 350));

        chiTietFrame.add(scrollPane, BorderLayout.CENTER);

        model.setRowCount(0);
        danhsachchitiet = DaoChiTietSach.getBySachID(sachID);

        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(50, 0, 0, 0));
        panel.add(scrollPane, BorderLayout.CENTER);

        chiTietFrame.add(panel, BorderLayout.CENTER);

        int stt = 1;
        for (ChiTietSach chiTiet : danhsachchitiet) {
            model.addRow(new Object[]{
                stt++,
                chiTiet.getSachID(),
                chiTiet.getChiTietID(),
                chiTiet.getTrangThai(),
                chiTiet.getTinhTrangSach(),
                chiTiet.getNgayNhap()
            });
        }

        chiTietFrame.setVisible(true);
        LocalDate today = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        txtNgayNhap.setText(today.format(formatter));
        btnThemSach.addActionListener(
                new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e
            ) {

                Integer idSach = Integer.parseInt(txtSachID.getText());
                String trangthai = rdoCon.isSelected() ? "Đang có sẵn" : "Đang được mượn";
                String tinhtrangsach = rdoMoi.isSelected() ? "Mới" : "Cũ";
                Date ngaynhap = Date.valueOf(txtNgayNhap.getText());
                int results = JOptionPane.showConfirmDialog(rootPane, "Bạn có muốn thêm ko", "Xac nhan", JOptionPane.YES_NO_OPTION);
                if (results == JOptionPane.YES_OPTION) {

                    boolean result = DaoChiTietSach.createSachChiTiet(idSach, "Đang có sẵn", "Mới", ngaynhap);

                    if (result) {
                        JOptionPane.showMessageDialog(null, "Thêm sách thành công!");
                        DefaultTableModel model = (DefaultTableModel) table.getModel();
                        model.setRowCount(0);

                        danhsachchitiet = DaoChiTietSach.getBySachID(idSach);
                        int stt = 1;
                        for (ChiTietSach chiTiet : danhsachchitiet) {
                            model.addRow(new Object[]{
                                stt++,
                                chiTiet.getSachID(),
                                chiTiet.getChiTietID(),
                                chiTiet.getTrangThai(),
                                chiTiet.getTinhTrangSach(),
                                chiTiet.getNgayNhap()
                            });
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Thêm sách thất bại!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                } else {
                    return;
                }
            }
        }
        );
        btnSua.addActionListener(
                new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e
            ) {
                System.out.println("ID Selected: " + idSelected);
                int id = idSelected;
                if (idSelected == -1) {
                    JOptionPane.showMessageDialog(null, "Vui lòng chọn một sách để xoá.");
                    return;
                }
                int idSach = Integer.parseInt(txtSachID.getText());
                String trangthai = rdoCon.isSelected() ? "Đang có sẵn" : "Đang được mượn";
                String tinhtrangsach = rdoMoi.isSelected() ? "Mới" : "Cũ";
                Date ngaynhap = Date.valueOf(txtNgayNhap.getText());
                int results = JOptionPane.showConfirmDialog(rootPane, "Bạn có muốn sửa ko", "Xac nhan", JOptionPane.YES_NO_OPTION);
                if (results == JOptionPane.YES_OPTION) {
                    boolean result = DaoChiTietSach.updateSachChiTiet(id, idSach, trangthai, tinhtrangsach, ngaynhap);

                    if (result) {
                        JOptionPane.showMessageDialog(null, "Sửa sách thành công!");
                        DefaultTableModel model = (DefaultTableModel) table.getModel();
                        model.setRowCount(0);

                        danhsachchitiet = DaoChiTietSach.getBySachID(sachID);
                        int stt = 1;
                        for (ChiTietSach chiTiet : danhsachchitiet) {
                            model.addRow(new Object[]{
                                stt++,
                                chiTiet.getSachID(),
                                chiTiet.getChiTietID(),
                                chiTiet.getTrangThai(),
                                chiTiet.getTinhTrangSach(),
                                chiTiet.getNgayNhap()
                            });
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Sửa sách thất bại!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                } else {
                    return;
                }
            }
        }
        );
        btnXoa.addActionListener(
                new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e
            ) {
                int id = idSelected;
                int idSach = Integer.parseInt(txtSachID.getText());
                if (idSelected == -1) {
                    JOptionPane.showMessageDialog(null, "Vui lòng chọn một sách để xoá.");
                    return;
                }
                String trangThai = table.getValueAt(table.getSelectedRow(), 3).toString(); // cột 3 là "Trạng Thái"

                if (trangThai.equalsIgnoreCase("Đang được mượn")) {
                    JOptionPane.showMessageDialog(null, "Không thể xoá sách đang được mượn.");
                    return;
                }
                int results = JOptionPane.showConfirmDialog(rootPane, "Bạn có muốn xoá ko", "Xac nhan", JOptionPane.YES_NO_OPTION);
                if (results == JOptionPane.YES_OPTION) {
                    boolean result = DaoChiTietSach.deleteSachChiTiet(id, idSach);

                    if (result) {
                        JOptionPane.showMessageDialog(null, "Xoá sách thành công!");
                        DefaultTableModel model = (DefaultTableModel) table.getModel();
                        model.setRowCount(0);

                        danhsachchitiet = DaoChiTietSach.getBySachID(sachID);
                        int stt = 1;
                        for (ChiTietSach chiTiet : danhsachchitiet) {
                            model.addRow(new Object[]{
                                stt++,
                                chiTiet.getSachID(),
                                chiTiet.getChiTietID(),
                                chiTiet.getTrangThai(),
                                chiTiet.getTinhTrangSach(),
                                chiTiet.getNgayNhap()
                            });
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Xoá sách thất bại!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                } else {
                    return;
                }
            }
        }
        );
        table.addMouseListener(
                new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e
            ) {
                int selectedRow = table.getSelectedRow();
                if (selectedRow != -1) {
                    txtId.setText(table.getValueAt(selectedRow, 0).toString());
                    txtSachID.setText(table.getValueAt(selectedRow, 1).toString());
                    String trangThai = table.getValueAt(selectedRow, 3).toString();
                    if (trangThai.equals("Đang có sẵn")) {
                        rdoCon.setSelected(true);
                    } else {
                        rdoHet.setSelected(true);
                    }
                    String tinhTrang = table.getValueAt(selectedRow, 4).toString();
                    if (tinhTrang.equals("Mới")) {
                        rdoMoi.setSelected(true);
                    } else {
                        rdoCu.setSelected(true);
                    }
                    txtNgayNhap.setText(table.getValueAt(selectedRow, 5).toString());
                    idSelected = danhsachchitiet.get(selectedRow).getChiTietID();
                }
            }
        }
        );
        chiTietFrame.setVisible(
                true);

    }

    public class TrangThaiTableCellRenderer extends DefaultTableCellRenderer {

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            Component cell = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

            if (value != null) {
                String trangThai = value.toString();
                if (trangThai.equalsIgnoreCase("Còn")) {
                    cell.setForeground(Color.GREEN);
                } else if (trangThai.equalsIgnoreCase("Hết")) {
                    cell.setForeground(Color.RED);
                } else {
                    cell.setForeground(Color.BLACK);
                }
            }

            return cell;
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenu4 = new javax.swing.JMenu();

        jMenu4.setText("jMenu4");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

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
            java.util.logging.Logger.getLogger(QLSach.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(QLSach.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(QLSach.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(QLSach.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new QLSach().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenu jMenu4;
    // End of variables declaration//GEN-END:variables
}
