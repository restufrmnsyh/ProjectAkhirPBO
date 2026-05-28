package View.Admin;

import Controller.ControllerTransaksi;

import Model.Transaksi.ModelTableTransaksi;

import javax.swing.*;

import java.awt.*;

public class SemuaTransaksi
extends JFrame {

    JTable table;

    JScrollPane scrollPane;

    JButton btnKembali =
    new JButton(
            "KEMBALI"
    );

    public SemuaTransaksi(){

        setTitle(
                "Semua Transaksi"
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

                        controller
                        .tampilTransaksi()

                )

        );

        scrollPane =

        new JScrollPane(
                table
        );

        // BUTTON KEMBALI
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