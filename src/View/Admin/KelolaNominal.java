package View.Admin;

import Model.Connector;

import javax.swing.*;

import javax.swing.table.DefaultTableModel;

import java.awt.event.*;

import java.sql.*;

public class KelolaNominal
extends JFrame {

    JTable table;

    JScrollPane scrollPane;

    DefaultTableModel tableModel;

    JLabel lbGame =
    new JLabel(
            "Pilih Game"
    );

    JLabel lbNominal =
    new JLabel(
            "Nominal"
    );

    JLabel lbHarga =
    new JLabel(
            "Harga"
    );

    JComboBox<String> cbGame =
    new JComboBox<>();

    JTextField txtNominal =
    new JTextField();

    JTextField txtHarga =
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

    public KelolaNominal(){

        setTitle(
                "Kelola Nominal"
        );

        setSize(
                800,
                500
        );

        setLayout(null);

        setLocationRelativeTo(null);

        setDefaultCloseOperation(
                JFrame.DISPOSE_ON_CLOSE
        );

        String kolom[] = {

            "ID",
            "GAME",
            "NOMINAL",
            "HARGA"

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
                740,
                220
        );

        lbGame.setBounds(
                20,
                270,
                100,
                30
        );

        cbGame.setBounds(
                120,
                270,
                180,
                30
        );

        lbNominal.setBounds(
                20,
                320,
                100,
                30
        );

        txtNominal.setBounds(
                120,
                320,
                180,
                30
        );

        lbHarga.setBounds(
                20,
                370,
                100,
                30
        );

        txtHarga.setBounds(
                120,
                370,
                180,
                30
        );

        btnTambah.setBounds(
                400,
                270,
                120,
                35
        );

        btnHapus.setBounds(
                550,
                270,
                120,
                35
        );

        btnRefresh.setBounds(
                400,
                330,
                120,
                35
        );

        btnKembali.setBounds(
                550,
                330,
                120,
                35
        );

        add(scrollPane);

        add(lbGame);
        add(cbGame);

        add(lbNominal);
        add(txtNominal);

        add(lbHarga);
        add(txtHarga);

        add(btnTambah);
        add(btnHapus);
        add(btnRefresh);
        add(btnKembali);

        loadGame();

        loadTable();

        // TAMBAH
        btnTambah.addActionListener(

        new ActionListener(){

            @Override
            public void actionPerformed(
                    ActionEvent e
            ){

                tambahNominal();

            }

        });

        // HAPUS
        btnHapus.addActionListener(

        new ActionListener(){

            @Override
            public void actionPerformed(
                    ActionEvent e
            ){

                hapusNominal();

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

    public void loadGame(){

        try{

            Connection conn =
            Connector.Connect();

            Statement statement =
            conn.createStatement();

            ResultSet result =

            statement.executeQuery(

                    "SELECT * FROM game"

            );

            while(result.next()){

                cbGame.addItem(

                        result.getString(
                                "nama_game"
                        )

                );

            }

        }

        catch(Exception e){

            System.out.println(
                    e.getMessage()
            );

        }

    }

    public void loadTable(){

        tableModel.setRowCount(0);

        try{

            Connection conn =
            Connector.Connect();

            String query =

            "SELECT nominal_topup.id_nominal,"
            +
            "game.nama_game,"
            +
            "nominal_topup.nama_nominal,"
            +
            "nominal_topup.harga "
            +
            "FROM nominal_topup "
            +
            "JOIN game "
            +
            "ON nominal_topup.id_game = game.id_game";

            Statement statement =
            conn.createStatement();

            ResultSet result =

            statement.executeQuery(
                    query
            );

            while(result.next()){

                Object row[] = {

                    result.getInt(
                            "id_nominal"
                    ),

                    result.getString(
                            "nama_game"
                    ),

                    result.getString(
                            "nama_nominal"
                    ),

                    result.getInt(
                            "harga"
                    )

                };

                tableModel.addRow(
                        row
                );

            }

        }

        catch(Exception e){

            System.out.println(
                    e.getMessage()
            );

        }

    }

    public void tambahNominal(){

        if(

            txtNominal.getText().isEmpty()

            ||

            txtHarga.getText().isEmpty()

        ){

            JOptionPane.showMessageDialog(

                    null,

                    "Semua data wajib diisi"

            );

            return;

        }

        try{

            Connection conn =
            Connector.Connect();

            String namaGame =

            cbGame.getSelectedItem()
            .toString();

            int idGame = 0;

            String queryGame =

            "SELECT * FROM game "
            +
            "WHERE nama_game=?";

            PreparedStatement statementGame =

            conn.prepareStatement(
                    queryGame
            );

            statementGame.setString(
                    1,
                    namaGame
            );

            ResultSet resultGame =

            statementGame.executeQuery();

            if(resultGame.next()){

                idGame =

                resultGame.getInt(
                        "id_game"
                );

            }

            String query =

            "INSERT INTO nominal_topup "
            +
            "(id_game,nama_nominal,harga)"
            +
            " VALUES (?,?,?)";

            PreparedStatement statement =

            conn.prepareStatement(
                    query
            );

            statement.setInt(
                    1,
                    idGame
            );

            statement.setString(
                    2,
                    txtNominal.getText()
            );

            statement.setInt(
                    3,
                    Integer.parseInt(
                            txtHarga.getText()
                    )
            );

            statement.executeUpdate();

            JOptionPane.showMessageDialog(

                    null,

                    "Nominal berhasil ditambah"

            );

            loadTable();

        }

        catch(Exception e){

            System.out.println(
                    e.getMessage()
            );

        }

    }

    public void hapusNominal(){

        int baris =

        table.getSelectedRow();

        if(baris==-1){

            JOptionPane.showMessageDialog(

                    null,

                    "Pilih data dulu"

            );

            return;

        }

        int idNominal =

        Integer.parseInt(

                table.getValueAt(
                        baris,
                        0
                ).toString()

        );

        try{

            Connection conn =
            Connector.Connect();

            String query =

            "DELETE FROM nominal_topup "
            +
            "WHERE id_nominal=?";

            PreparedStatement statement =

            conn.prepareStatement(
                    query
            );

            statement.setInt(
                    1,
                    idNominal
            );

            statement.executeUpdate();

            JOptionPane.showMessageDialog(

                    null,

                    "Nominal berhasil dihapus"

            );

            loadTable();

        }

        catch(Exception e){

            System.out.println(
                    e.getMessage()
            );

        }

    }

}