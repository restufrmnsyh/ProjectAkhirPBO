package View.Topup;

import Controller.ControllerTransaksi;
import Model.Connector;
import Model.Transaksi.ModelTransaksi;
import thread.LoadingTopUp;
import Helper.Session;
import javax.swing.*;

import java.awt.event.*;

import java.sql.*;

public class TopUpForm
extends JFrame {

    JLabel title =
    new JLabel(
            "TOP UP GAME"
    );

    JLabel lbGame =
    new JLabel(
            "Pilih Game"
    );

    JLabel lbUserGame =
    new JLabel(
            "ID User Game"
    );

    JLabel lbNominal =
    new JLabel(
            "Pilih Nominal"
    );

    JComboBox<String> cbGame =
    new JComboBox<>();

    JComboBox<String> cbNominal =
    new JComboBox<>();
    
    JLabel lbPembayaran =
    new JLabel(
            "Pembayaran"
    );

    JComboBox<String> cbPembayaran =
    new JComboBox<>();

    JTextField txtUserGame =
    new JTextField();

    JButton btnTopup =
    new JButton(
            "TOP UP"
    );
    JButton btnKembali =
    new JButton(
            "KEMBALI"
    );

    int selectedIdGame = 0;

    int selectedIdNominal = 0;

    public TopUpForm(){

        setTitle(
                "Top Up Game"
        );

        setSize(
                400,
                380
        );

        setLayout(null);

        setLocationRelativeTo(null);

        setDefaultCloseOperation(
                JFrame.DISPOSE_ON_CLOSE
        );

        title.setBounds(
                130,
                20,
                200,
                30
        );

        lbGame.setBounds(
                30,
                70,
                100,
                30
        );

        cbGame.setBounds(
                140,
                70,
                180,
                30
        );

        lbUserGame.setBounds(
                30,
                120,
                120,
                30
        );

        txtUserGame.setBounds(
                140,
                120,
                180,
                30
        );

        lbNominal.setBounds(
                30,
                170,
                120,
                30
        );

        cbNominal.setBounds(
                140,
                170,
                180,
                30
        );
        
        lbPembayaran.setBounds(
                30,
                220,
                120,
                30
        );

        cbPembayaran.setBounds(
                140,
                220,
                180,
                30
        );

        btnTopup.setBounds(
                120,
                280,
                120,
                35
        );
        btnKembali.setBounds(
        250,
        280,
        120,
        35
        );

        add(title);

        add(lbGame);
        add(cbGame);

        add(lbUserGame);
        add(txtUserGame);

        add(lbNominal);
        add(cbNominal);

        add(lbPembayaran);
        add(cbPembayaran);
        
        add(btnTopup);
        add(btnKembali);

        loadGame();
        cbPembayaran.addItem("Dana");
        cbPembayaran.addItem("OVO");
        cbPembayaran.addItem("GoPay");
        cbPembayaran.addItem("QRIS");

        cbGame.addActionListener(

        new ActionListener(){

            @Override
            public void actionPerformed(
                    ActionEvent e
            ){

                loadNominal();

            }

        });

        btnTopup.addActionListener(

        new ActionListener(){

            @Override
            public void actionPerformed(
                    ActionEvent e
            ){

                topup();

            }

        });
        
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

    public void loadNominal(){

        cbNominal.removeAllItems();

        try{

            Connection conn =
            Connector.Connect();

            String namaGame =

            cbGame.getSelectedItem()
            .toString();

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

                selectedIdGame =

                resultGame.getInt(
                        "id_game"
                );

            }

            String queryNominal =

            "SELECT * FROM nominal_topup "
            +
            "WHERE id_game=?";

            PreparedStatement statementNominal =

            conn.prepareStatement(
                    queryNominal
            );

            statementNominal.setInt(
                    1,
                    selectedIdGame
            );

            ResultSet resultNominal =

            statementNominal.executeQuery();

            while(resultNominal.next()){

                cbNominal.addItem(

                        resultNominal
                        .getString(
                                "nama_nominal"
                        )

                        +

                        " - Rp "

                        +

                        resultNominal
                        .getInt(
                                "harga"
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

    public void topup(){
        if(

            txtUserGame.getText().isEmpty()

        ){

            JOptionPane.showMessageDialog(

                    null,

                    "ID User Game wajib diisi"

            );

            return;

        }

        ControllerTransaksi controller =
        new ControllerTransaksi();
        ModelTransaksi transaksi =
        new ModelTransaksi();
        transaksi.setId_user(
            Session.id_user
        );

        transaksi.setId_game(
                selectedIdGame
        );

        try{

            Connection conn =
            Connector.Connect();

            String nominalDipilih =

            cbNominal.getSelectedItem()
            .toString();

            String namaNominal =

            nominalDipilih.split(
                    " - "
            )[0];

            String query =

            "SELECT * FROM nominal_topup "
            +
            "WHERE nama_nominal=?";

            PreparedStatement statement =

            conn.prepareStatement(
                    query
            );

            statement.setString(
                    1,
                    namaNominal
            );

            ResultSet result =

            statement.executeQuery();

            if(result.next()){

                selectedIdNominal =

                result.getInt(
                        "id_nominal"
                );

            }

        }

        catch(Exception e){

            System.out.println(
                    e.getMessage()
            );

        }

        transaksi.setId_nominal(
                selectedIdNominal
        );

        transaksi.setUser_game(

                txtUserGame.getText()

        );
        
        transaksi.setMetode_pembayaran(
            cbPembayaran
            .getSelectedItem()
            .toString()
        );

        transaksi.setStatus_pembayaran(
                "Berhasil"
        );

        LoadingTopUp loading =
        new LoadingTopUp(this);
        loading.start();
        controller.topup(
                transaksi
        );
        JOptionPane.showMessageDialog(

                null,

                "Top Up Berhasil"

        );

    }

}