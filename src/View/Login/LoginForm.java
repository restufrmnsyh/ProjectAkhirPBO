package View.Login;

import Controller.ControllerLogin;
import Model.User.ModelUser;
import View.Admin.DashboardAdmin;
import View.Login.RegisterForm;
import View.User.DashboardUser;
import Helper.Session;


import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class LoginForm
extends JFrame {

    JLabel title =
    new JLabel("LOGIN");

    JLabel lbUsername =
    new JLabel("Username");

    JLabel lbPassword =
    new JLabel("Password");

    JTextField txtUsername =
    new JTextField();

    JPasswordField txtPassword =
    new JPasswordField();

    JButton btnLogin =
    new JButton("LOGIN");
    JButton btnRegister =
    new JButton(
            "REGISTER"
    );

    public LoginForm(){

        setTitle(
                "Sistem Top Up Game"
        );

        setSize(
                350,
                250
        );

        setLayout(null);

        setLocationRelativeTo(null);

        setDefaultCloseOperation(
                JFrame.EXIT_ON_CLOSE
        );

        title.setBounds(
                140,
                10,
                100,
                30
        );

        lbUsername.setBounds(
                30,
                60,
                100,
                30
        );

        txtUsername.setBounds(
                120,
                60,
                150,
                30
        );

        lbPassword.setBounds(
                30,
                110,
                100,
                30
        );

        txtPassword.setBounds(
                120,
                110,
                150,
                30
        );

        btnLogin.setBounds(
            70,
            160,
            100,
            30
        );
        btnRegister.setBounds(
            180,
            160,
            100,
            30
        );

        add(title);

        add(lbUsername);

        add(txtUsername);

        add(lbPassword);

        add(txtPassword);

        add(btnLogin);
        add(btnRegister);

        btnLogin.addActionListener(

        new ActionListener(){

            @Override
            public void actionPerformed(
                    ActionEvent e
            ){

                login();

            }

        });
        
        btnRegister.addActionListener(
        new ActionListener(){

            @Override
            public void actionPerformed(
                    ActionEvent e
            ){

                new RegisterForm();

            }

        });

        setVisible(true);

    }

    public void login(){

        String username =

        txtUsername.getText();

        String password =

        txtPassword.getText();

        ControllerLogin controller =

        new ControllerLogin();

        ModelUser user =

        controller.login(
                username,
                password
        );

        if(user!=null){

            JOptionPane.showMessageDialog(

                    null,

                    "Login Berhasil"

            );
            Session.id_user =
            user.getId_user();
            Session.username =
            user.getUsername();
            Session.role =
            user.getRole();
            dispose();
            if(user.getRole().equals("admin")){
                new DashboardAdmin();
            } else {
                new DashboardUser();
            }
        }

        else{

            JOptionPane.showMessageDialog(

                    null,

                    "Login Gagal"

            );

        }

    }

}