package View.Admin;

import View.Login.LoginForm;
import View.Admin.SemuaTransaksi;
import View.Admin.KelolaGame;
import View.Admin.KelolaNominal;
import Helper.Session;
import Helper.UITheme;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class DashboardAdmin extends JFrame {

    JButton btnGame      = UITheme.makeMenuButton("Kelola Game", "🕹");
    JButton btnTransaksi = UITheme.makeMenuButton("Lihat Transaksi", "📊");
    JButton btnNominal   = UITheme.makeMenuButton("Kelola Nominal", "💰");
    JButton btnLogout    = UITheme.makeDangerButton("Logout");

    public DashboardAdmin() {
        setTitle("Admin Dashboard");
        setSize(520, 540);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

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
        header.setBounds(0, 0, 520, 70);
        main.add(header);

        JLabel logoLbl = new JLabel("⚙️");
        logoLbl.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 26));
        logoLbl.setBounds(20, 18, 36, 36);
        header.add(logoLbl);

        JLabel titleLbl = UITheme.makeTitle("ADMIN PANEL");
        titleLbl.setHorizontalAlignment(SwingConstants.LEFT);
        titleLbl.setBounds(62, 10, 200, 28);
        header.add(titleLbl);

        // Admin badge
        JLabel badgeLbl = new JLabel("  ADMIN  ") {
            @Override protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(new Color(120, 60, 220, 50));
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 8, 8);
                g2.setColor(UITheme.ACCENT_PURPLE);
                g2.setStroke(new BasicStroke(1));
                g2.drawRoundRect(0, 0, getWidth()-1, getHeight()-1, 8, 8);
                g2.setColor(UITheme.ACCENT_PURPLE);
                g2.setFont(new Font("Segoe UI", Font.BOLD, 10));
                FontMetrics fm = g2.getFontMetrics();
                g2.drawString(getText().trim(), (getWidth()-fm.stringWidth(getText().trim()))/2, (getHeight()+fm.getAscent()-fm.getDescent())/2);
                g2.dispose();
            }
        };
        badgeLbl.setBounds(62, 40, 64, 18);
        header.add(badgeLbl);

        JLabel userLbl = new JLabel(Session.username);
        userLbl.setFont(UITheme.FONT_SMALL);
        userLbl.setForeground(UITheme.TEXT_SECONDARY);
        userLbl.setBounds(132, 40, 160, 18);
        header.add(userLbl);

        // Stats card
        JPanel sCard = UITheme.makeCardPanel();
        sCard.setLayout(null);
        sCard.setBounds(20, 88, 480, 70);
        main.add(sCard);

        JLabel sIcon = new JLabel("🛡", SwingConstants.LEFT);
        sIcon.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 28));
        sIcon.setBounds(20, 12, 40, 40);
        sCard.add(sIcon);

        JLabel sTitle = new JLabel("Control Panel – Sistem Top Up Game");
        sTitle.setFont(new Font("Segoe UI", Font.BOLD, 14));
        sTitle.setForeground(UITheme.TEXT_PRIMARY);
        sTitle.setBounds(68, 10, 380, 24);
        sCard.add(sTitle);

        JLabel sSub = UITheme.makeSubtitle("Kelola data game, nominal, dan transaksi");
        sSub.setHorizontalAlignment(SwingConstants.LEFT);
        sSub.setBounds(68, 36, 380, 20);
        sCard.add(sSub);

        // Menu label
        JLabel menuTitle = UITheme.makeLabel("KELOLA DATA");
        menuTitle.setForeground(UITheme.TEXT_SECONDARY);
        menuTitle.setFont(new Font("Segoe UI", Font.BOLD, 11));
        menuTitle.setBounds(20, 178, 200, 20);
        main.add(menuTitle);

        // 3 menu buttons in a row
        btnGame.setBounds(20, 204, 152, 100);
        btnTransaksi.setBounds(184, 204, 152, 100);
        btnNominal.setBounds(348, 204, 152, 100);
        main.add(btnGame);
        main.add(btnTransaksi);
        main.add(btnNominal);

        btnLogout.setBounds(190, 325, 140, 40);
        main.add(btnLogout);

        btnGame.addActionListener(e -> new KelolaGame());
        btnTransaksi.addActionListener(e -> new SemuaTransaksi());
        btnNominal.addActionListener(e -> new KelolaNominal());
        btnLogout.addActionListener(e -> { dispose(); new LoginForm(); });

        setVisible(true);
    }
}
