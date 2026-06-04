package View.Admin;

import Model.Connector;
import Helper.UITheme;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class KelolaNominal extends JFrame {

    JTable table;
    JScrollPane scrollPane;
    DefaultTableModel tableModel;

    JComboBox<String> cbGame = UITheme.makeComboBox();
    JTextField txtNominal    = UITheme.makeTextField();
    JTextField txtHarga      = UITheme.makeTextField();

    JButton btnTambah  = UITheme.makeSuccessButton("+ TAMBAH");
    JButton btnEdit    = UITheme.makePrimaryButton("✏ SIMPAN EDIT");
    JButton btnHapus   = UITheme.makeDangerButton("🗑 HAPUS");
    JButton btnBatal   = UITheme.makeSecondaryButton("✕ BATAL EDIT");
    JButton btnRefresh = UITheme.makeSecondaryButton("↻ REFRESH");
    JButton btnKembali = UITheme.makeSecondaryButton("← KEMBALI");

    JLabel formTitle = UITheme.makeLabel("TAMBAH NOMINAL BARU");

    JPanel modeIndicator;
    JLabel modeLbl;

    int selectedId = -1;

    public KelolaNominal() {
        setTitle("Kelola Nominal");
        setSize(860, 650);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel main = UITheme.makeGradientPanel();
        main.setLayout(null);
        setContentPane(main);

        // ── Header ──────────────────────────────────────────────────
        JPanel header = new JPanel() {
            @Override protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setColor(UITheme.BG_CARD);
                g2.fillRect(0, 0, getWidth(), getHeight());
                g2.setColor(UITheme.BORDER_COLOR);
                g2.drawLine(0, getHeight()-1, getWidth(), getHeight()-1);
                g2.dispose();
            }
            @Override public boolean isOpaque() { return false; }
        };
        header.setLayout(null);
        header.setBounds(0, 0, 860, 62);
        main.add(header);

        JLabel logoLbl = new JLabel("💰");
        logoLbl.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 26));
        logoLbl.setBounds(18, 14, 36, 36);
        header.add(logoLbl);

        JLabel titleLbl = UITheme.makeTitle("KELOLA NOMINAL");
        titleLbl.setHorizontalAlignment(SwingConstants.LEFT);
        titleLbl.setBounds(60, 16, 320, 30);
        header.add(titleLbl);

        JLabel hintLbl = new JLabel("Klik baris tabel untuk memilih data");
        hintLbl.setFont(UITheme.FONT_SMALL);
        hintLbl.setForeground(UITheme.TEXT_SECONDARY);
        hintLbl.setBounds(60, 40, 280, 16);
        header.add(hintLbl);

        // ── Table ────────────────────────────────────────────────────
        JLabel tblLabel = UITheme.makeLabel("DAFTAR NOMINAL TOPUP");
        tblLabel.setForeground(UITheme.TEXT_SECONDARY);
        tblLabel.setFont(new Font("Segoe UI", Font.BOLD, 11));
        tblLabel.setBounds(20, 74, 300, 18);
        main.add(tblLabel);

        String[] kolom = {"ID", "Game", "Nama Nominal", "Harga (Rp)"};
        tableModel = new DefaultTableModel(kolom, 0) {
            @Override public boolean isCellEditable(int r, int c) { return false; }
        };
        table = new JTable(tableModel);
        UITheme.styleTable(table);
        table.getColumnModel().getColumn(0).setMaxWidth(50);
        scrollPane = UITheme.makeScrollPane(table);
        scrollPane.setBounds(20, 96, 820, 220);
        main.add(scrollPane);

        // ── Mode indicator bar ───────────────────────────────────────
        modeIndicator = new JPanel() {
            @Override protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                Color bg = selectedId > 0 ? new Color(255, 184, 0, 20) : new Color(0, 220, 130, 15);
                g2.setColor(bg);
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 10, 10);
                Color border = selectedId > 0 ? UITheme.ACCENT_GOLD : UITheme.SUCCESS;
                g2.setColor(border);
                g2.setStroke(new BasicStroke(1.5f));
                g2.drawRoundRect(0, 0, getWidth()-1, getHeight()-1, 10, 10);
                g2.dispose();
            }
            @Override public boolean isOpaque() { return false; }
        };
        modeIndicator.setLayout(null);
        modeIndicator.setBounds(20, 328, 820, 36);
        main.add(modeIndicator);

        JLabel modeIcon = new JLabel("●");
        modeIcon.setFont(new Font("Segoe UI", Font.BOLD, 10));
        modeIcon.setForeground(UITheme.SUCCESS);
        modeIcon.setBounds(12, 0, 16, 36);
        modeIndicator.add(modeIcon);

        modeLbl = new JLabel("Mode: Tambah Data Baru");
        modeLbl.setFont(new Font("Segoe UI", Font.BOLD, 12));
        modeLbl.setForeground(UITheme.SUCCESS);
        modeLbl.setBounds(30, 0, 760, 36);
        modeIndicator.add(modeLbl);

        // ── Form card ─────────────────────────────────────────────────
        JPanel formCard = UITheme.makeCardPanel();
        formCard.setLayout(null);
        formCard.setBounds(20, 375, 820, 230);
        main.add(formCard);

        formTitle.setForeground(UITheme.ACCENT_CYAN);
        formTitle.setFont(new Font("Segoe UI", Font.BOLD, 12));
        formTitle.setBounds(20, 14, 460, 20);
        formCard.add(formTitle);

        // Fields – left side
        int lx = 20, fx = 148, fw = 210, lh = 22, fh = 34, gap = 50;
        int y = 44;

        JLabel lbGame = UITheme.makeLabel("Pilih Game");
        lbGame.setBounds(lx, y+6, 124, lh);
        formCard.add(lbGame);
        cbGame.setBounds(fx, y, fw, fh);
        formCard.add(cbGame);
        y += gap;

        JLabel lbNominal = UITheme.makeLabel("Nama Nominal");
        lbNominal.setBounds(lx, y+6, 124, lh);
        formCard.add(lbNominal);
        txtNominal.setBounds(fx, y, fw, fh);
        formCard.add(txtNominal);
        y += gap;

        JLabel lbHarga = UITheme.makeLabel("Harga (Rp)");
        lbHarga.setBounds(lx, y+6, 124, lh);
        formCard.add(lbHarga);
        txtHarga.setBounds(fx, y, fw, fh);
        formCard.add(txtHarga);

        // Buttons – right side (2 columns)
        int bx1 = 420, bx2 = 600, bw = 168, bh = 38;
        btnTambah.setBounds(bx1, 44,  bw, bh);
        btnEdit.setBounds  (bx2, 44,  bw, bh);
        btnHapus.setBounds (bx1, 96,  bw, bh);
        btnBatal.setBounds (bx2, 96,  bw, bh);
        btnRefresh.setBounds(bx1, 148, bw, bh);
        btnKembali.setBounds(bx2, 148, bw, bh);

        formCard.add(btnTambah);
        formCard.add(btnEdit);
        formCard.add(btnHapus);
        formCard.add(btnBatal);
        formCard.add(btnRefresh);
        formCard.add(btnKembali);

        loadGame();
        loadTable();
        setEditMode(false);

        // ── Table row selection → auto-fill form ─────────────────────
        table.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                int row = table.getSelectedRow();
                if (row >= 0) {
                    selectedId = Integer.parseInt(tableModel.getValueAt(row, 0).toString());
                    String namaGame = tableModel.getValueAt(row, 1).toString();
                    // Select the matching game in combobox
                    for (int i = 0; i < cbGame.getItemCount(); i++) {
                        if (cbGame.getItemAt(i).equals(namaGame)) {
                            cbGame.setSelectedIndex(i);
                            break;
                        }
                    }
                    txtNominal.setText(tableModel.getValueAt(row, 2).toString());
                    txtHarga.setText  (tableModel.getValueAt(row, 3).toString());
                    setEditMode(true);
                }
            }
        });

        btnTambah.addActionListener (e -> tambahNominal());
        btnEdit.addActionListener   (e -> editNominal());
        btnHapus.addActionListener  (e -> hapusNominal());
        btnBatal.addActionListener  (e -> resetForm());
        btnRefresh.addActionListener(e -> { resetForm(); loadTable(); });
        btnKembali.addActionListener(e -> dispose());

        setVisible(true);
    }

    // ── Helpers ──────────────────────────────────────────────────────

    private void setEditMode(boolean editMode) {
        if (editMode) {
            formTitle.setText("EDIT NOMINAL  (ID: " + selectedId + ")");
            formTitle.setForeground(UITheme.ACCENT_GOLD);
            modeLbl.setText("Mode: Edit Data Nominal — Klik \"Simpan Edit\" untuk menyimpan, atau \"Batal Edit\" untuk membatalkan");
            modeLbl.setForeground(UITheme.ACCENT_GOLD);
            btnEdit.setVisible(true);
            btnBatal.setVisible(true);
            btnTambah.setEnabled(false);
        } else {
            selectedId = -1;
            formTitle.setText("TAMBAH NOMINAL BARU");
            formTitle.setForeground(UITheme.ACCENT_CYAN);
            modeLbl.setText("Mode: Tambah Data Baru — Isi form lalu klik \"+ Tambah\"");
            modeLbl.setForeground(UITheme.SUCCESS);
            btnEdit.setVisible(false);
            btnBatal.setVisible(false);
            btnTambah.setEnabled(true);
        }
        modeIndicator.repaint();
    }

    private void resetForm() {
        table.clearSelection();
        txtNominal.setText("");
        txtHarga.setText("");
        if (cbGame.getItemCount() > 0) cbGame.setSelectedIndex(0);
        setEditMode(false);
    }

    public void loadGame() {
        cbGame.removeAllItems();
        try {
            Connection conn = Connector.Connect();
            ResultSet result = conn.createStatement().executeQuery("SELECT * FROM game");
            while (result.next()) cbGame.addItem(result.getString("nama_game"));
        } catch (Exception e) { System.out.println(e.getMessage()); }
    }

    public void loadTable() {
        tableModel.setRowCount(0);
        try {
            Connection conn = Connector.Connect();
            String query = "SELECT nominal_topup.id_nominal, game.nama_game, nominal_topup.nama_nominal, nominal_topup.harga "
                    + "FROM nominal_topup JOIN game ON nominal_topup.id_game = game.id_game ORDER BY game.nama_game";
            ResultSet result = conn.createStatement().executeQuery(query);
            while (result.next()) {
                tableModel.addRow(new Object[]{
                    result.getInt("id_nominal"),
                    result.getString("nama_game"),
                    result.getString("nama_nominal"),
                    result.getInt("harga")
                });
            }
        } catch (Exception e) { System.out.println(e.getMessage()); }
    }

    private int getIdGameFromCombo() throws Exception {
        String namaGame = cbGame.getSelectedItem().toString();
        Connection conn = Connector.Connect();
        PreparedStatement st = conn.prepareStatement("SELECT id_game FROM game WHERE nama_game=?");
        st.setString(1, namaGame);
        ResultSet rs = st.executeQuery();
        if (rs.next()) return rs.getInt("id_game");
        throw new Exception("Game tidak ditemukan");
    }

    public void tambahNominal() {
        if (txtNominal.getText().isEmpty() || txtHarga.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Semua data wajib diisi", "Peringatan", JOptionPane.WARNING_MESSAGE);
            return;
        }
        try {
            int idGame = getIdGameFromCombo();
            Connection conn = Connector.Connect();
            PreparedStatement st = conn.prepareStatement(
                "INSERT INTO nominal_topup (id_game, nama_nominal, harga) VALUES (?,?,?)");
            st.setInt(1, idGame);
            st.setString(2, txtNominal.getText());
            st.setInt(3, Integer.parseInt(txtHarga.getText()));
            st.executeUpdate();
            JOptionPane.showMessageDialog(this, "Nominal berhasil ditambah!", "Sukses", JOptionPane.INFORMATION_MESSAGE);
            resetForm();
            loadTable();
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Harga harus berupa angka", "Peringatan", JOptionPane.WARNING_MESSAGE);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void editNominal() {
        if (selectedId == -1) {
            JOptionPane.showMessageDialog(this, "Pilih data dari tabel dulu", "Peringatan", JOptionPane.WARNING_MESSAGE);
            return;
        }
        if (txtNominal.getText().isEmpty() || txtHarga.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Semua data wajib diisi", "Peringatan", JOptionPane.WARNING_MESSAGE);
            return;
        }
        int confirm = JOptionPane.showConfirmDialog(this,
            "Simpan perubahan untuk nominal ini?", "Konfirmasi Edit",
            JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (confirm != JOptionPane.YES_OPTION) return;

        try {
            int idGame = getIdGameFromCombo();
            Connection conn = Connector.Connect();
            PreparedStatement st = conn.prepareStatement(
                "UPDATE nominal_topup SET id_game=?, nama_nominal=?, harga=? WHERE id_nominal=?");
            st.setInt(1, idGame);
            st.setString(2, txtNominal.getText());
            st.setInt(3, Integer.parseInt(txtHarga.getText()));
            st.setInt(4, selectedId);
            st.executeUpdate();
            JOptionPane.showMessageDialog(this, "Nominal berhasil diperbarui!", "Sukses", JOptionPane.INFORMATION_MESSAGE);
            resetForm();
            loadTable();
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Harga harus berupa angka", "Peringatan", JOptionPane.WARNING_MESSAGE);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void hapusNominal() {
        int baris = table.getSelectedRow();
        if (baris == -1) {
            JOptionPane.showMessageDialog(this, "Pilih data dari tabel dulu", "Peringatan", JOptionPane.WARNING_MESSAGE);
            return;
        }
        String nama = tableModel.getValueAt(baris, 2).toString();
        int confirm = JOptionPane.showConfirmDialog(this,
            "Hapus nominal \"" + nama + "\"?", "Konfirmasi Hapus",
            JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
        if (confirm != JOptionPane.YES_OPTION) return;

        try {
            int idNominal = Integer.parseInt(tableModel.getValueAt(baris, 0).toString());
            Connection conn = Connector.Connect();
            PreparedStatement st = conn.prepareStatement("DELETE FROM nominal_topup WHERE id_nominal=?");
            st.setInt(1, idNominal);
            st.executeUpdate();
            JOptionPane.showMessageDialog(this, "Nominal berhasil dihapus!", "Sukses", JOptionPane.INFORMATION_MESSAGE);
            resetForm();
            loadTable();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
}
