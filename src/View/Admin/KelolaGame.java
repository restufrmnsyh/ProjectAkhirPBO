package View.Admin;

import Controller.ControllerGame;
import Model.Game.ModelGame;
import Helper.UITheme;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.util.List;

public class KelolaGame extends JFrame {

    JTable table;
    JScrollPane scrollPane;
    DefaultTableModel tableModel;

    JTextField txtNama      = UITheme.makeTextField();
    JTextField txtDeveloper = UITheme.makeTextField();
    JTextField txtKategori  = UITheme.makeTextField();

    JButton btnTambah  = UITheme.makeSuccessButton("+ TAMBAH");
    JButton btnEdit    = UITheme.makePrimaryButton("✏ SIMPAN EDIT");
    JButton btnHapus   = UITheme.makeDangerButton("🗑 HAPUS");
    JButton btnBatal   = UITheme.makeSecondaryButton("✕ BATAL EDIT");
    JButton btnRefresh = UITheme.makeSecondaryButton("↻ REFRESH");
    JButton btnKembali = UITheme.makeSecondaryButton("← KEMBALI");

    JLabel formTitle = UITheme.makeLabel("TAMBAH GAME BARU");

    // Mode indicator panel
    JPanel modeIndicator;
    JLabel modeLbl;

    int selectedId = -1; // -1 = mode tambah, >0 = mode edit
    ControllerGame controller = new ControllerGame();

    public KelolaGame() {
        setTitle("Kelola Game");
        setSize(820, 620);
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
        header.setBounds(0, 0, 820, 62);
        main.add(header);

        JLabel logoLbl = new JLabel("🕹");
        logoLbl.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 26));
        logoLbl.setBounds(18, 14, 36, 36);
        header.add(logoLbl);

        JLabel titleLbl = UITheme.makeTitle("KELOLA GAME");
        titleLbl.setHorizontalAlignment(SwingConstants.LEFT);
        titleLbl.setBounds(60, 16, 300, 30);
        header.add(titleLbl);

        JLabel hintLbl = new JLabel("Klik baris tabel untuk memilih data");
        hintLbl.setFont(UITheme.FONT_SMALL);
        hintLbl.setForeground(UITheme.TEXT_SECONDARY);
        hintLbl.setBounds(60, 40, 280, 16);
        header.add(hintLbl);

        // ── Table section ────────────────────────────────────────────
        JLabel tblLabel = UITheme.makeLabel("DAFTAR GAME");
        tblLabel.setForeground(UITheme.TEXT_SECONDARY);
        tblLabel.setFont(new Font("Segoe UI", Font.BOLD, 11));
        tblLabel.setBounds(20, 74, 200, 18);
        main.add(tblLabel);

        String[] kolom = {"ID", "Nama Game", "Developer", "Kategori"};
        tableModel = new DefaultTableModel(kolom, 0) {
            @Override public boolean isCellEditable(int r, int c) { return false; }
        };
        table = new JTable(tableModel);
        UITheme.styleTable(table);
        table.getColumnModel().getColumn(0).setMaxWidth(50);
        scrollPane = UITheme.makeScrollPane(table);
        scrollPane.setBounds(20, 96, 780, 200);
        main.add(scrollPane);

        // ── Mode indicator bar ────────────────────────────────────────
        modeIndicator = new JPanel() {
            @Override protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                Color bg = selectedId > 0
                    ? new Color(255, 184, 0, 20)
                    : new Color(0, 220, 130, 15);
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
        modeIndicator.setBounds(20, 308, 780, 36);
        main.add(modeIndicator);

        JLabel modeIcon = new JLabel("●");
        modeIcon.setFont(new Font("Segoe UI", Font.BOLD, 10));
        modeIcon.setForeground(UITheme.SUCCESS);
        modeIcon.setBounds(12, 0, 16, 36);
        modeIndicator.add(modeIcon);

        modeLbl = new JLabel("Mode: Tambah Data Baru");
        modeLbl.setFont(new Font("Segoe UI", Font.BOLD, 12));
        modeLbl.setForeground(UITheme.SUCCESS);
        modeLbl.setBounds(30, 0, 500, 36);
        modeIndicator.add(modeLbl);

        // ── Form card ─────────────────────────────────────────────────
        JPanel formCard = UITheme.makeCardPanel();
        formCard.setLayout(null);
        formCard.setBounds(20, 355, 780, 222);
        main.add(formCard);

        formTitle.setForeground(UITheme.ACCENT_CYAN);
        formTitle.setFont(new Font("Segoe UI", Font.BOLD, 12));
        formTitle.setBounds(20, 14, 400, 20);
        formCard.add(formTitle);

        // Fields – left side
        int lx = 20, fx = 140, fw = 220, lh = 22, fh = 34, gap = 50;
        int y = 44;

        JLabel lbNama = UITheme.makeLabel("Nama Game");
        lbNama.setBounds(lx, y+6, 115, lh);
        formCard.add(lbNama);
        txtNama.setBounds(fx, y, fw, fh);
        formCard.add(txtNama);
        y += gap;

        JLabel lbDev = UITheme.makeLabel("Developer");
        lbDev.setBounds(lx, y+6, 115, lh);
        formCard.add(lbDev);
        txtDeveloper.setBounds(fx, y, fw, fh);
        formCard.add(txtDeveloper);
        y += gap;

        JLabel lbKat = UITheme.makeLabel("Kategori");
        lbKat.setBounds(lx, y+6, 115, lh);
        formCard.add(lbKat);
        txtKategori.setBounds(fx, y, fw, fh);
        formCard.add(txtKategori);

        // Buttons – right side (2 columns)
        int bx1 = 410, bx2 = 580, bw = 160, bh = 38;
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

        // Initially hide edit-only buttons
        setEditMode(false);

        loadTable();

        // ── Table row selection → auto-fill form ─────────────────────
        table.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                int row = table.getSelectedRow();
                if (row >= 0) {
                    selectedId   = Integer.parseInt(tableModel.getValueAt(row, 0).toString());
                    txtNama.setText     (tableModel.getValueAt(row, 1).toString());
                    txtDeveloper.setText(tableModel.getValueAt(row, 2).toString());
                    txtKategori.setText (tableModel.getValueAt(row, 3).toString());
                    setEditMode(true);
                }
            }
        });

        btnTambah.addActionListener(e -> tambahGame());
        btnEdit.addActionListener  (e -> editGame());
        btnHapus.addActionListener (e -> hapusGame());
        btnBatal.addActionListener (e -> resetForm());
        btnRefresh.addActionListener(e -> { resetForm(); loadTable(); });
        btnKembali.addActionListener(e -> dispose());

        setVisible(true);
    }

    // ── Helpers ──────────────────────────────────────────────────────

    private void setEditMode(boolean editMode) {
        if (editMode) {
            formTitle.setText("EDIT DATA GAME  (ID: " + selectedId + ")");
            formTitle.setForeground(UITheme.ACCENT_GOLD);
            modeLbl.setText("Mode: Edit Data Game — Klik \"Simpan Edit\" untuk menyimpan, atau \"Batal Edit\" untuk membatalkan");
            modeLbl.setForeground(UITheme.ACCENT_GOLD);
            btnEdit.setVisible(true);
            btnBatal.setVisible(true);
            btnTambah.setEnabled(false);
        } else {
            selectedId = -1;
            formTitle.setText("TAMBAH GAME BARU");
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
        txtNama.setText("");
        txtDeveloper.setText("");
        txtKategori.setText("");
        setEditMode(false);
    }

    public void loadTable() {
        tableModel.setRowCount(0);
        List<ModelGame> data = controller.tampilGame();
        for (ModelGame game : data) {
            tableModel.addRow(new Object[]{
                game.getId_game(), game.getNama_game(),
                game.getDeveloper(), game.getKategori()
            });
        }
    }

    public void tambahGame() {
        if (txtNama.getText().isEmpty() || txtDeveloper.getText().isEmpty() || txtKategori.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Semua data wajib diisi", "Peringatan", JOptionPane.WARNING_MESSAGE);
            return;
        }
        ModelGame game = new ModelGame();
        game.setNama_game(txtNama.getText());
        game.setDeveloper(txtDeveloper.getText());
        game.setKategori(txtKategori.getText());
        controller.tambahGame(game);
        JOptionPane.showMessageDialog(this, "Game berhasil ditambah!", "Sukses", JOptionPane.INFORMATION_MESSAGE);
        resetForm();
        loadTable();
    }

    public void editGame() {
        if (selectedId == -1) {
            JOptionPane.showMessageDialog(this, "Pilih data dari tabel dulu", "Peringatan", JOptionPane.WARNING_MESSAGE);
            return;
        }
        if (txtNama.getText().isEmpty() || txtDeveloper.getText().isEmpty() || txtKategori.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Semua data wajib diisi", "Peringatan", JOptionPane.WARNING_MESSAGE);
            return;
        }
        int confirm = JOptionPane.showConfirmDialog(this,
            "Simpan perubahan untuk game ini?", "Konfirmasi Edit",
            JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (confirm != JOptionPane.YES_OPTION) return;

        ModelGame game = new ModelGame();
        game.setId_game(selectedId);
        game.setNama_game(txtNama.getText());
        game.setDeveloper(txtDeveloper.getText());
        game.setKategori(txtKategori.getText());
        controller.editGame(game);
        JOptionPane.showMessageDialog(this, "Data game berhasil diperbarui!", "Sukses", JOptionPane.INFORMATION_MESSAGE);
        resetForm();
        loadTable();
    }

    public void hapusGame() {
        int baris = table.getSelectedRow();
        if (baris == -1) {
            JOptionPane.showMessageDialog(this, "Pilih data dari tabel dulu", "Peringatan", JOptionPane.WARNING_MESSAGE);
            return;
        }
        int id_game = Integer.parseInt(table.getValueAt(baris, 0).toString());
        String nama  = table.getValueAt(baris, 1).toString();
        int confirm = JOptionPane.showConfirmDialog(this,
            "Hapus game \"" + nama + "\"?", "Konfirmasi Hapus",
            JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
        if (confirm != JOptionPane.YES_OPTION) return;

        controller.hapusGame(id_game);
        JOptionPane.showMessageDialog(this, "Game berhasil dihapus!", "Sukses", JOptionPane.INFORMATION_MESSAGE);
        resetForm();
        loadTable();
    }
}
