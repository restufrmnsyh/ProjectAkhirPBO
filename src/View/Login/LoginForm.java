package View.Login;

import Controller.ControllerLogin;
import Model.User.ModelUser;
import View.Admin.DashboardAdmin;
import View.Login.RegisterForm;
import View.User.DashboardUser;
import Helper.Session;
import Helper.UITheme;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class LoginForm extends JFrame {

    JTextField txtUsername = UITheme.makeTextField();
    JPasswordField txtPassword = UITheme.makePasswordField();
    JButton btnLogin = UITheme.makePrimaryButton("LOGIN");
    JButton btnRegister = UITheme.makeSecondaryButton("REGISTER");

    public LoginForm() {
        UITheme.applyGlobalTheme();
        setTitle("GameTopup – Login");
        setSize(420, 520);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setUndecorated(false);

        // Main panel
        JPanel main = UITheme.makeGradientPanel();
        main.setLayout(null);
        setContentPane(main);

        // Card
        JPanel card = UITheme.makeCardPanel();
        card.setLayout(null);
        card.setBounds(30, 50, 360, 400);
        main.add(card);

        // Logo / emoji icon
        JLabel logoLbl = new JLabel("🎮", SwingConstants.CENTER);
        logoLbl.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 48));
        logoLbl.setBounds(0, 20, 360, 60);
        card.add(logoLbl);

        // Title
        JLabel title = UITheme.makeTitle("GAME TOPUP");
        title.setBounds(0, 85, 360, 32);
        card.add(title);

        // Subtitle
        JLabel sub = UITheme.makeSubtitle("Masuk ke akun Anda");
        sub.setBounds(0, 117, 360, 20);
        card.add(sub);

        // Divider line
        JSeparator sep = new JSeparator();
        sep.setForeground(UITheme.BORDER_COLOR);
        sep.setBackground(UITheme.BORDER_COLOR);
        sep.setBounds(30, 147, 300, 2);
        card.add(sep);

        // Username
        JLabel lbUser = UITheme.makeLabel("Username");
        lbUser.setBounds(30, 163, 100, 24);
        card.add(lbUser);

        txtUsername.setBounds(30, 190, 300, 38);
        card.add(txtUsername);

        // Password
        JLabel lbPass = UITheme.makeLabel("Password");
        lbPass.setBounds(30, 238, 100, 24);
        card.add(lbPass);

        txtPassword.setBounds(30, 265, 300, 38);
        card.add(txtPassword);

        // Buttons
        btnLogin.setBounds(30, 325, 140, 40);
        btnRegister.setBounds(190, 325, 140, 40);
        card.add(btnLogin);
        card.add(btnRegister);

        // Version label
        JLabel ver = UITheme.makeSubtitle("v1.0 • Sistem Top Up Game");
        ver.setBounds(0, 460, 420, 20);
        main.add(ver);

        // Enter key triggers login
        KeyAdapter enterKey = new KeyAdapter() {
            @Override public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) login();
            }
        };
        txtUsername.addKeyListener(enterKey);
        txtPassword.addKeyListener(enterKey);

        btnLogin.addActionListener(e -> login());
        btnRegister.addActionListener(e -> new RegisterForm());

        setVisible(true);
    }

    public void login() {
        String username = txtUsername.getText();
        String password = new String(txtPassword.getPassword());

        ControllerLogin controller = new ControllerLogin();
        ModelUser user = controller.login(username, password);

        if (user != null) {
            JOptionPane.showMessageDialog(null, "Login Berhasil");
            Session.id_user = user.getId_user();
            Session.username = user.getUsername();
            Session.role = user.getRole();
            dispose();
            if (user.getRole().equals("admin")) {
                new DashboardAdmin();
            } else {
                new DashboardUser();
            }
        } else {
            JOptionPane.showMessageDialog(null, "Login Gagal");
        }
    }
}
