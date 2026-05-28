package View.User;

import Controller.ControllerTransaksi;

import Model.Transaksi.ModelTableTransaksi;
import Helper.Session;
import javax.swing.*;

import java.awt.*;

public class RiwayatTransaksi
extends JFrame {

    JTable table;

    JScrollPane scrollPane;

    JButton btnKembali =
    new JButton(
            "KEMBALI"
    );

    public RiwayatTransaksi(){

        setTitle(
                "Riwayat Transaksi"
        );

        setSize(
                700,
                400
        );

        setLocationRelativeTo(null);

        setDefaultCloseOperation(
                JFrame.DISPOSE_ON_CLOSE
        );

        ControllerTransaksi controller =

        new ControllerTransaksi();

        table =

        new JTable(
                new ModelTableTransaksi(
                    controller.tampilTransaksiUser(Session.id_user)
                )

        );

        scrollPane =

        new JScrollPane(
                table
        );

        // TOMBOL KEMBALI
        btnKembali.addActionListener(

                e -> dispose()

        );

        add(
                scrollPane,
                BorderLayout.CENTER
        );

        add(
                btnKembali,
                BorderLayout.SOUTH
        );

        setVisible(true);

    }

}