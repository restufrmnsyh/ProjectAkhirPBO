package View.Login;

import Controller.ControllerLogin;

import Model.User.ModelUser;

import javax.swing.*;

import java.awt.event.*;

public class RegisterForm
extends JFrame {

    JLabel title =
    new JLabel(
            "REGISTER"
    );

    JLabel lbUsername =
    new JLabel(
            "Username"
    );

    JLabel lbPassword =
    new JLabel(
            "Password"
    );

    JTextField txtUsername =
    new JTextField();

    JPasswordField txtPassword =
    new JPasswordField();

    JButton btnRegister =
    new JButton(
            "REGISTER"
    );

    JButton btnKembali =
    new JButton(
            "KEMBALI"
    );

    public RegisterForm(){

        setTitle(
                "Register"
        );

        setSize(
                350,
                280
        );

        setLayout(null);

        setLocationRelativeTo(null);

        setDefaultCloseOperation(
                JFrame.DISPOSE_ON_CLOSE
        );

        title.setBounds(
                120,
                20,
                100,
                30
        );

        lbUsername.setBounds(
                30,
                70,
                100,
                30
        );

        txtUsername.setBounds(
                120,
                70,
                150,
                30
        );

        lbPassword.setBounds(
                30,
                120,
                100,
                30
        );

        txtPassword.setBounds(
                120,
                120,
                150,
                30
        );

        btnRegister.setBounds(
                40,
                180,
                110,
                35
        );

        btnKembali.setBounds(
                170,
                180,
                110,
                35
        );

        add(title);

        add(lbUsername);
        add(txtUsername);

        add(lbPassword);
        add(txtPassword);

        add(btnRegister);
        add(btnKembali);

        // BUTTON REGISTER
        btnRegister.addActionListener(

        new ActionListener(){

            @Override
            public void actionPerformed(
                    ActionEvent e
            ){

                register();

            }

        });

        // BUTTON KEMBALI
        btnKembali.addActionListener(

        new ActionListener(){

            @Override
            public void actionPerformed(
                    ActionEvent e
            ){

                dispose();

            }

        });

        setVisible(true);

    }

    public void register(){

        if(

            txtUsername.getText().isEmpty()

            ||

            txtPassword.getText().isEmpty()

        ){

            JOptionPane.showMessageDialog(

                    null,

                    "Semua data wajib diisi"

            );

            return;

        }

        Controller.ControllerLogin controller =

        new ControllerLogin();

        ModelUser user =

        new ModelUser();

        user.setUsername(

                txtUsername.getText()

        );

        user.setPassword(

                txtPassword.getText()

        );

        controller.register(
                user
        );

        JOptionPane.showMessageDialog(

                null,

                "Register Berhasil"

        );

        dispose();

    }

}