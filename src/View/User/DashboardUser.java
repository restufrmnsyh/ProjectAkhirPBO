package View.User;

import View.Topup.TopUpForm;
import View.User.RiwayatTransaksi;
import View.Login.LoginForm;
import Helper.Session;
import Helper.UITheme;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class DashboardUser extends JFrame {

    JButton btnTopup   = UITheme.makeMenuButton("Top Up Game", "🎮");
    JButton btnRiwayat = UITheme.makeMenuButton("Riwayat Transaksi", "📋");
    JButton btnLogout  = UITheme.makeDangerButton("Logout");

    public DashboardUser() {
        setTitle("Dashboard – " + Session.username);
        setSize(480, 520);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel main = UITheme.makeGradientPanel();
        main.setLayout(null);
        setContentPane(main);

        // Header bar
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
        header.setBounds(0, 0, 480, 70);
        main.add(header);

        JLabel logoLbl = new JLabel("🎮");
        logoLbl.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 28));
        logoLbl.setBounds(20, 18, 40, 36);
        header.add(logoLbl);

        JLabel titleLbl = UITheme.makeTitle("GAME TOPUP");
        titleLbl.setHorizontalAlignment(SwingConstants.LEFT);
        titleLbl.setBounds(65, 10, 200, 28);
        header.add(titleLbl);

        JLabel userLbl = new JLabel("👤 " + Session.username);
        userLbl.setFont(UITheme.FONT_LABEL);
        userLbl.setForeground(UITheme.TEXT_SECONDARY);
        userLbl.setBounds(65, 38, 200, 18);
        header.add(userLbl);

        // Welcome card
        JPanel wCard = UITheme.makeCardPanel();
        wCard.setLayout(null);
        wCard.setBounds(20, 88, 440, 90);
        main.add(wCard);

        JLabel wIcon = new JLabel("👋", SwingConstants.LEFT);
        wIcon.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 30));
        wIcon.setBounds(20, 15, 44, 44);
        wCard.add(wIcon);

        JLabel wTitle = new JLabel("Selamat Datang, " + Session.username + "!");
        wTitle.setFont(new Font("Segoe UI", Font.BOLD, 15));
        wTitle.setForeground(UITheme.TEXT_PRIMARY);
        wTitle.setBounds(70, 12, 340, 26);
        wCard.add(wTitle);

        JLabel wSub = UITheme.makeSubtitle("Apa yang ingin kamu lakukan hari ini?");
        wSub.setHorizontalAlignment(SwingConstants.LEFT);
        wSub.setBounds(70, 40, 340, 20);
        wCard.add(wSub);

        // Menu buttons
        JLabel menuTitle = UITheme.makeLabel("MENU UTAMA");
        menuTitle.setForeground(UITheme.TEXT_SECONDARY);
        menuTitle.setFont(new Font("Segoe UI", Font.BOLD, 11));
        menuTitle.setBounds(20, 200, 200, 20);
        main.add(menuTitle);

        btnTopup.setBounds(20, 226, 200, 100);
        btnRiwayat.setBounds(240, 226, 200, 100);
        main.add(btnTopup);
        main.add(btnRiwayat);

        btnLogout.setBounds(170, 355, 140, 40);
        main.add(btnLogout);

        btnTopup.addActionListener(e -> new TopUpForm());
        btnRiwayat.addActionListener(e -> new RiwayatTransaksi());
        btnLogout.addActionListener(e -> {
            dispose();
            new LoginForm();
        });

        setVisible(true);
    }
}
