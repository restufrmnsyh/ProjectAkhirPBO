package View.User;

import View.Topup.TopUpForm;
import View.User.RiwayatTransaksi;
import View.Login.LoginForm;
import javax.swing.*;

import java.awt.event.*;

public class DashboardUser
extends JFrame {

    JLabel title =
    new JLabel(
            "Dashboard User"
    );

    JButton btnTopup =
    new JButton(
            "Top Up Game"
    );

    JButton btnRiwayat =
    new JButton(
            "Riwayat Transaksi"
    );

    JButton btnLogout =
    new JButton(
            "Logout"
    );

    public DashboardUser(){

        setTitle(
                "User"
        );

        setSize(
                400,
                350
        );

        setLayout(null);

        setLocationRelativeTo(null);

        setDefaultCloseOperation(
                JFrame.EXIT_ON_CLOSE
        );

        title.setBounds(
                120,
                20,
                200,
                30
        );

        btnTopup.setBounds(
                100,
                80,
                180,
                40
        );

        btnRiwayat.setBounds(
                100,
                140,
                180,
                40
        );

        btnLogout.setBounds(
                100,
                200,
                180,
                40
        );

        add(title);

        add(btnTopup);

        add(btnRiwayat);

        add(btnLogout);

        // EVENT TOP UP
        btnTopup.addActionListener(

        new ActionListener(){

            @Override
            public void actionPerformed(
                    ActionEvent e
            ){

                new TopUpForm();

            }

        });

        // EVENT RIWAYAT
        btnRiwayat.addActionListener(

        new ActionListener(){

          @Override
            public void actionPerformed(
                    ActionEvent e
            ){

                new RiwayatTransaksi();

            }
        });

        // EVENT LOGOUT
        btnLogout.addActionListener(

        new ActionListener(){

            @Override
            public void actionPerformed(
                    ActionEvent e
            ){

                dispose();
                new LoginForm();

            }

        });

        setVisible(true);

    }

}