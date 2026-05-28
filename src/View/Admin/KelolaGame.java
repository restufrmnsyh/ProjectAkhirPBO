package View.Admin;

import Controller.ControllerGame;

import Model.Game.ModelGame;

import javax.swing.*;

import javax.swing.table.DefaultTableModel;

import java.awt.event.*;

import java.util.List;

public class KelolaGame
extends JFrame {

    JTable table;

    JScrollPane scrollPane;

    DefaultTableModel tableModel;

    JLabel lbNama =
    new JLabel(
            "Nama Game"
    );

    JLabel lbDeveloper =
    new JLabel(
            "Developer"
    );

    JLabel lbKategori =
    new JLabel(
            "Kategori"
    );

    JTextField txtNama =
    new JTextField();

    JTextField txtDeveloper =
    new JTextField();

    JTextField txtKategori =
    new JTextField();

    JButton btnTambah =
    new JButton(
            "TAMBAH"
    );

    JButton btnHapus =
    new JButton(
            "HAPUS"
    );

    JButton btnRefresh =
    new JButton(
            "REFRESH"
    );

    JButton btnKembali =
    new JButton(
            "KEMBALI"
    );

    ControllerGame controller =
    new ControllerGame();

    public KelolaGame(){

        setTitle(
                "Kelola Game"
        );

        setSize(
                750,
                500
        );

        setLayout(null);

        setLocationRelativeTo(null);

        setDefaultCloseOperation(
                JFrame.DISPOSE_ON_CLOSE
        );

        String kolom[] = {

            "ID",
            "Nama Game",
            "Developer",
            "Kategori"

        };

        tableModel =

        new DefaultTableModel(
                kolom,
                0
        );

        table =

        new JTable(
                tableModel
        );

        scrollPane =

        new JScrollPane(
                table
        );

        scrollPane.setBounds(
                20,
                20,
                680,
                200
        );

        lbNama.setBounds(
                20,
                250,
                100,
                30
        );

        txtNama.setBounds(
                120,
                250,
                180,
                30
        );

        lbDeveloper.setBounds(
                20,
                300,
                100,
                30
        );

        txtDeveloper.setBounds(
                120,
                300,
                180,
                30
        );

        lbKategori.setBounds(
                20,
                350,
                100,
                30
        );

        txtKategori.setBounds(
                120,
                350,
                180,
                30
        );

        btnTambah.setBounds(
                350,
                250,
                120,
                35
        );

        btnHapus.setBounds(
                500,
                250,
                120,
                35
        );

        btnRefresh.setBounds(
                350,
                310,
                120,
                35
        );

        btnKembali.setBounds(
                500,
                310,
                120,
                35
        );

        add(scrollPane);

        add(lbNama);
        add(txtNama);

        add(lbDeveloper);
        add(txtDeveloper);

        add(lbKategori);
        add(txtKategori);

        add(btnTambah);
        add(btnHapus);
        add(btnRefresh);
        add(btnKembali);

        loadTable();

        // TAMBAH GAME
        btnTambah.addActionListener(

        new ActionListener(){

            @Override
            public void actionPerformed(
                    ActionEvent e
            ){

                tambahGame();

            }

        });

        // HAPUS GAME
        btnHapus.addActionListener(

        new ActionListener(){

            @Override
            public void actionPerformed(
                    ActionEvent e
            ){

                hapusGame();

            }

        });

        // REFRESH
        btnRefresh.addActionListener(

        e -> loadTable()

        );

        // KEMBALI
        btnKembali.addActionListener(

        e -> dispose()

        );

        setVisible(true);

    }

    public void loadTable(){

        tableModel.setRowCount(0);

        List<ModelGame> data =

        controller.tampilGame();

        for(
                ModelGame game
                :
                data
        ){

            Object row[] = {

                game.getId_game(),

                game.getNama_game(),

                game.getDeveloper(),

                game.getKategori()

            };

            tableModel.addRow(
                    row
            );

        }

    }

    public void tambahGame(){

        if(

            txtNama.getText().isEmpty()

            ||

            txtDeveloper.getText().isEmpty()

            ||

            txtKategori.getText().isEmpty()

        ){

            JOptionPane.showMessageDialog(

                    null,

                    "Semua data wajib diisi"

            );

            return;

        }

        ModelGame game =

        new ModelGame();

        game.setNama_game(

                txtNama.getText()

        );

        game.setDeveloper(

                txtDeveloper.getText()

        );

        game.setKategori(

                txtKategori.getText()

        );

        controller.tambahGame(
                game
        );

        JOptionPane.showMessageDialog(

                null,

                "Game berhasil ditambah"

        );

        loadTable();

    }

    public void hapusGame(){

        int baris =

        table.getSelectedRow();

        if(baris==-1){

            JOptionPane.showMessageDialog(

                    null,

                    "Pilih data dulu"

            );

            return;

        }

        int id_game =

        Integer.parseInt(

                table.getValueAt(
                        baris,
                        0
                ).toString()

        );

        controller.hapusGame(
                id_game
        );

        JOptionPane.showMessageDialog(

                null,

                "Game berhasil dihapus"

        );

        loadTable();

    }

}