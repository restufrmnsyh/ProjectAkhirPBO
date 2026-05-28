package View.Admin;

import View.Login.LoginForm;
import View.Admin.SemuaTransaksi;
import View.Admin.KelolaGame;
import View.Admin.KelolaNominal;
import javax.swing.*;

import java.awt.event.*;

public class DashboardAdmin
extends JFrame {

    JLabel title =
    new JLabel(
            "Dashboard Admin"
    );

    JButton btnGame =
    new JButton(
            "Kelola Game"
    );

    JButton btnNominal =
    new JButton(
            "Kelola Nominal"
    );
    
    JButton btnTransaksi =
    new JButton(
            "Lihat Transaksi"
    );

    JButton btnLogout =
    new JButton(
            "Logout"
    );

    public DashboardAdmin(){

        setTitle(
                "Admin"
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

        btnGame.setBounds(
                100,
                80,
                180,
                40
        );
        
        btnNominal.setBounds(
        100,
        200,
        180,
        40
        );

        btnTransaksi.setBounds(
                100,
                140,
                180,
                40
        );

        btnLogout.setBounds(
        100,
        260,
        180,
        40
        );

        add(title);

        add(btnGame);
        add(btnNominal);    
        add(btnTransaksi);

        add(btnLogout);

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

        // BUTTON GAME
        btnGame.addActionListener(
        new ActionListener(){

            @Override
            public void actionPerformed(
                    ActionEvent e
            ){

                new KelolaGame();

            }

        });
        
        btnNominal.addActionListener(
        new ActionListener(){

            @Override
            public void actionPerformed(
                    ActionEvent e
            ){

                new KelolaNominal();

            }
        });

        // BUTTON TRANSAKSI
        btnTransaksi.addActionListener(
        new ActionListener(){

            @Override
            public void actionPerformed(
                    ActionEvent e
            ){
                new SemuaTransaksi();
            }
        });

        setVisible(true);

    }

}