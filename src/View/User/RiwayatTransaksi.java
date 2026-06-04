package View.User;

import Controller.ControllerTransaksi;
import Model.Transaksi.ModelTableTransaksi;
import Helper.Session;
import Helper.UITheme;

import javax.swing.*;
import java.awt.*;

public class RiwayatTransaksi extends JFrame {

    JTable table;
    JScrollPane scrollPane;
    JButton btnKembali = UITheme.makeSecondaryButton("← KEMBALI");

    public RiwayatTransaksi() {
        setTitle("Riwayat Transaksi");
        setSize(800, 580);
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
        header.setBounds(0, 0, 800, 62);
        main.add(header);

        JLabel logoLbl = new JLabel("📋");
        logoLbl.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 26));
        logoLbl.setBounds(18, 14, 36, 36);
        header.add(logoLbl);

        JLabel titleLbl = UITheme.makeTitle("RIWAYAT TRANSAKSI");
        titleLbl.setHorizontalAlignment(SwingConstants.LEFT);
        titleLbl.setBounds(60, 16, 360, 30);
        header.add(titleLbl);

        JLabel userBadge = new JLabel("👤 " + Session.username);
        userBadge.setFont(UITheme.FONT_SMALL);
        userBadge.setForeground(UITheme.TEXT_SECONDARY);
        userBadge.setBounds(60, 40, 200, 16);
        header.add(userBadge);

        // Table label
        JLabel tblLabel = UITheme.makeLabel("RIWAYAT TOP UP ANDA");
        tblLabel.setForeground(UITheme.TEXT_SECONDARY);
        tblLabel.setFont(new Font("Segoe UI", Font.BOLD, 11));
        tblLabel.setBounds(20, 74, 300, 18);
        main.add(tblLabel);

        // Table
        ControllerTransaksi controller = new ControllerTransaksi();
        table = new JTable(new ModelTableTransaksi(controller.tampilTransaksiUser(Session.id_user)));
        UITheme.styleTable(table);
        scrollPane = UITheme.makeScrollPane(table);
        scrollPane.setBounds(20, 96, 760, 340);
        main.add(scrollPane);

        btnKembali.setBounds(330, 455, 140, 38);
        main.add(btnKembali);

        btnKembali.addActionListener(e -> dispose());

        setVisible(true);
    }
}
