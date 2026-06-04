package View.Topup;

import Controller.ControllerTransaksi;
import Model.Connector;
import Model.Transaksi.ModelTransaksi;
import thread.LoadingTopUp;
import Helper.Session;
import Helper.UITheme;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class TopUpForm extends JFrame {

    JComboBox<String> cbGame      = UITheme.makeComboBox();
    JComboBox<String> cbNominal   = UITheme.makeComboBox();
    JComboBox<String> cbPembayaran = UITheme.makeComboBox();
    JTextField txtUserGame        = UITheme.makeTextField();
    JButton btnTopup  = UITheme.makePrimaryButton("TOP UP");
    JButton btnKembali = UITheme.makeSecondaryButton("KEMBALI");

    int selectedIdGame    = 0;
    int selectedIdNominal = 0;

    public TopUpForm() {
        setTitle("Top Up Game");
        setSize(480, 540);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel main = UITheme.makeGradientPanel();
        main.setLayout(null);
        setContentPane(main);

        // Header
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
        header.setBounds(0, 0, 480, 62);
        main.add(header);

        JLabel logoLbl = new JLabel("⚡");
        logoLbl.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 26));
        logoLbl.setBounds(18, 14, 36, 36);
        header.add(logoLbl);

        JLabel titleLbl = UITheme.makeTitle("TOP UP GAME");
        titleLbl.setHorizontalAlignment(SwingConstants.LEFT);
        titleLbl.setBounds(60, 16, 280, 30);
        header.add(titleLbl);

        // Form card
        JPanel card = UITheme.makeCardPanel();
        card.setLayout(null);
        card.setBounds(20, 80, 440, 380);
        main.add(card);

        int lx = 24, fx = 160, fw = 252, lw = 130, lh = 22, fh = 36, gap = 54;
        int y = 24;

        // Pilih Game
        JLabel lbGame = UITheme.makeLabel("Pilih Game");
        lbGame.setBounds(lx, y+7, lw, lh);
        card.add(lbGame);
        cbGame.setBounds(fx, y, fw, fh);
        card.add(cbGame);
        y += gap;

        // ID User Game
        JLabel lbUserGame = UITheme.makeLabel("ID User Game");
        lbUserGame.setBounds(lx, y+7, lw, lh);
        card.add(lbUserGame);
        txtUserGame.setBounds(fx, y, fw, fh);
        card.add(txtUserGame);
        y += gap;

        // Pilih Nominal
        JLabel lbNominal = UITheme.makeLabel("Pilih Nominal");
        lbNominal.setBounds(lx, y+7, lw, lh);
        card.add(lbNominal);
        cbNominal.setBounds(fx, y, fw, fh);
        card.add(cbNominal);
        y += gap;

        // Pembayaran
        JLabel lbPembayaran = UITheme.makeLabel("Metode Bayar");
        lbPembayaran.setBounds(lx, y+7, lw, lh);
        card.add(lbPembayaran);
        cbPembayaran.setBounds(fx, y, fw, fh);
        card.add(cbPembayaran);
        y += gap + 10;

        // Divider
        JSeparator sep = new JSeparator();
        sep.setForeground(UITheme.BORDER_COLOR);
        sep.setBounds(24, y, 392, 2);
        card.add(sep);
        y += 14;

        // Info text
        JLabel infoLbl = new JLabel("⚠  Pastikan ID User Game sudah benar");
        infoLbl.setFont(new Font("Segoe UI", Font.PLAIN, 11));
        infoLbl.setForeground(UITheme.ACCENT_GOLD);
        infoLbl.setBounds(24, y, 370, 20);
        card.add(infoLbl);
        y += 32;

        // Buttons
        btnTopup.setBounds(24, y, 180, 40);
        btnKembali.setBounds(220, y, 180, 40);
        card.add(btnTopup);
        card.add(btnKembali);

        // Load data
        loadGame();
        cbPembayaran.addItem("Dana");
        cbPembayaran.addItem("OVO");
        cbPembayaran.addItem("GoPay");
        cbPembayaran.addItem("QRIS");

        cbGame.addActionListener(e -> loadNominal());
        btnTopup.addActionListener(e -> topup());
        btnKembali.addActionListener(e -> dispose());

        setVisible(true);
    }

    public void loadGame() {
        try {
            Connection conn = Connector.Connect();
            Statement statement = conn.createStatement();
            ResultSet result = statement.executeQuery("SELECT * FROM game");
            while (result.next()) {
                cbGame.addItem(result.getString("nama_game"));
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void loadNominal() {
        cbNominal.removeAllItems();
        try {
            Connection conn = Connector.Connect();
            String namaGame = cbGame.getSelectedItem().toString();
            PreparedStatement stGame = conn.prepareStatement("SELECT * FROM game WHERE nama_game=?");
            stGame.setString(1, namaGame);
            ResultSet resGame = stGame.executeQuery();
            if (resGame.next()) {
                selectedIdGame = resGame.getInt("id_game");
            }
            PreparedStatement stNominal = conn.prepareStatement("SELECT * FROM nominal_topup WHERE id_game=?");
            stNominal.setInt(1, selectedIdGame);
            ResultSet resNominal = stNominal.executeQuery();
            while (resNominal.next()) {
                cbNominal.addItem(resNominal.getString("nama_nominal") + " - Rp " + resNominal.getInt("harga"));
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void topup() {
        if (txtUserGame.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "ID User Game wajib diisi");
            return;
        }
        ControllerTransaksi controller = new ControllerTransaksi();
        ModelTransaksi transaksi = new ModelTransaksi();
        transaksi.setId_user(Session.id_user);
        transaksi.setId_game(selectedIdGame);
        try {
            Connection conn = Connector.Connect();
            String nominalDipilih = cbNominal.getSelectedItem().toString();
            String namaNominal = nominalDipilih.split(" - ")[0];
            PreparedStatement st = conn.prepareStatement("SELECT * FROM nominal_topup WHERE nama_nominal=?");
            st.setString(1, namaNominal);
            ResultSet result = st.executeQuery();
            if (result.next()) {
                selectedIdNominal = result.getInt("id_nominal");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        transaksi.setId_nominal(selectedIdNominal);
        transaksi.setUser_game(txtUserGame.getText());
        transaksi.setMetode_pembayaran(cbPembayaran.getSelectedItem().toString());
        transaksi.setStatus_pembayaran("Berhasil");
        LoadingTopUp loading = new LoadingTopUp(this);
        loading.start();
        controller.topup(transaksi);
        JOptionPane.showMessageDialog(null, "Top Up Berhasil");
    }
}
