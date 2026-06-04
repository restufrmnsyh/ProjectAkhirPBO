package View.Login;

import Controller.ControllerLogin;
import Model.User.ModelUser;
import Helper.UITheme;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class RegisterForm extends JFrame {

    JTextField txtUsername = UITheme.makeTextField();
    JPasswordField txtPassword = UITheme.makePasswordField();
    JButton btnRegister = UITheme.makeSuccessButton("DAFTAR");
    JButton btnKembali = UITheme.makeSecondaryButton("KEMBALI");

    public RegisterForm() {
        setTitle("GameTopup – Register");
        setSize(420, 480);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel main = UITheme.makeGradientPanel();
        main.setLayout(null);
        setContentPane(main);

        JPanel card = UITheme.makeCardPanel();
        card.setLayout(null);
        card.setBounds(30, 40, 360, 380);
        main.add(card);

        JLabel logoLbl = new JLabel("✨", SwingConstants.CENTER);
        logoLbl.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 42));
        logoLbl.setBounds(0, 18, 360, 52);
        card.add(logoLbl);

        JLabel title = UITheme.makeTitle("BUAT AKUN");
        title.setBounds(0, 75, 360, 32);
        card.add(title);

        JLabel sub = UITheme.makeSubtitle("Daftarkan akun baru Anda");
        sub.setBounds(0, 107, 360, 20);
        card.add(sub);

        JSeparator sep = new JSeparator();
        sep.setForeground(UITheme.BORDER_COLOR);
        sep.setBounds(30, 135, 300, 2);
        card.add(sep);

        JLabel lbUser = UITheme.makeLabel("Username");
        lbUser.setBounds(30, 150, 100, 24);
        card.add(lbUser);

        txtUsername.setBounds(30, 177, 300, 38);
        card.add(txtUsername);

        JLabel lbPass = UITheme.makeLabel("Password");
        lbPass.setBounds(30, 225, 100, 24);
        card.add(lbPass);

        txtPassword.setBounds(30, 252, 300, 38);
        card.add(txtPassword);

        btnRegister.setBounds(30, 310, 140, 40);
        btnKembali.setBounds(190, 310, 140, 40);
        card.add(btnRegister);
        card.add(btnKembali);

        btnRegister.addActionListener(e -> register());
        btnKembali.addActionListener(e -> dispose());

        setVisible(true);
    }

    public void register() {
        if (txtUsername.getText().isEmpty() || new String(txtPassword.getPassword()).isEmpty()) {
            JOptionPane.showMessageDialog(null, "Semua data wajib diisi");
            return;
        }

        ControllerLogin controller = new ControllerLogin();
        ModelUser user = new ModelUser();
        user.setUsername(txtUsername.getText());
        user.setPassword(new String(txtPassword.getPassword()));
        controller.register(user);

        JOptionPane.showMessageDialog(null, "Register Berhasil");
        dispose();
    }
}
