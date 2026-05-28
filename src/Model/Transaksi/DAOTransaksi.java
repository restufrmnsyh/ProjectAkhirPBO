package Model.Transaksi;

import Model.Connector;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DAOTransaksi
implements InterfaceDAOTransaksi {

    Connection conn;

    public DAOTransaksi(){

        conn =
        Connector.Connect();

    }

    @Override
    public void topup(
            ModelTransaksi transaksi
    ){

        try{

            String query =

            "INSERT INTO transaksi "
            +

            "(id_user,"
            +

            "id_game,"
            +

            "id_nominal,"
            +

            "user_game,"
            +

            "metode_pembayaran,"
            +

            "status_pembayaran)"

            +

            " VALUES (?,?,?,?,?,?)";

            PreparedStatement statement =

            conn.prepareStatement(
                    query
            );

            statement.setInt(
                    1,
                    transaksi.getId_user()
            );

            statement.setInt(
                    2,
                    transaksi.getId_game()
            );

            statement.setInt(
                    3,
                    transaksi.getId_nominal()
            );

            statement.setString(
                    4,
                    transaksi.getUser_game()
            );

            statement.setString(
                    5,
                    transaksi
                    .getMetode_pembayaran()
            );
            statement.setString(
                    6,
                    transaksi
                    .getStatus_pembayaran()
            );

            statement.executeUpdate();

            System.out.println(
                    "Top Up Berhasil"
            );

        }

        catch(Exception e){

            System.out.println(
                    e.getMessage()
            );

        }

    }

    @Override
    public List<ModelTransaksi>
    getAll(){

        List<ModelTransaksi> list =

        new ArrayList<>();

        try{

            Statement statement =

            conn.createStatement();

            ResultSet result =

            statement.executeQuery(

            "SELECT transaksi.id_transaksi,"
            +
            "game.nama_game,"
            +
            "nominal_topup.nama_nominal,"
            +
            "transaksi.user_game,"
            +
            "transaksi.tanggal,"
            +
            "transaksi.metode_pembayaran,"
            +        
            "transaksi.status_pembayaran "
            +
            "FROM transaksi "
            +
            "JOIN game "
            +
            "ON transaksi.id_game = game.id_game "
            +
            "JOIN nominal_topup "
            +
            "ON transaksi.id_nominal = nominal_topup.id_nominal"

            );

            while(
                    result.next()
            ){

                ModelTransaksi t =

                new ModelTransaksi();

                t.setId_transaksi(

                        result.getInt(
                                "id_transaksi"
                        )

                );

                t.setNama_game(

                        result.getString(
                                "nama_game"
                        )

                );

                t.setNama_nominal(

                        result.getString(
                                "nama_nominal"
                        )

                );

                t.setUser_game(

                        result.getString(
                                "user_game"
                        )

                );

                t.setTanggal(

                        result.getString(
                                "tanggal"
                        )

                );
                
                t.setMetode_pembayaran(

                        result.getString(
                                "metode_pembayaran"
                        )

                );

                t.setStatus_pembayaran(

                        result.getString(
                                "status_pembayaran"
                        )

                );

                list.add(
                        t
                );

            }

        }

        catch(Exception e){

            System.out.println(
                    e.getMessage()
            );

        }

        return list;

    }
    public List<ModelTransaksi>
        getByUser(
                int id_user
        ){

            List<ModelTransaksi> list =

            new ArrayList<>();

            try{

                String query =

                "SELECT transaksi.id_transaksi,"
                +
                "game.nama_game,"
                +
                "nominal_topup.nama_nominal,"
                +
                "transaksi.user_game,"
                +
                "transaksi.metode_pembayaran,"
                +        
                "transaksi.tanggal,"
                +
                "transaksi.status_pembayaran "
                +
                "FROM transaksi "
                +
                "JOIN game "
                +
                "ON transaksi.id_game = game.id_game "
                +
                "JOIN nominal_topup "
                +
                "ON transaksi.id_nominal = nominal_topup.id_nominal "
                +
                "WHERE transaksi.id_user=?";

                PreparedStatement statement =

                conn.prepareStatement(
                        query
                );

                statement.setInt(
                        1,
                        id_user
                );

                ResultSet result =

                statement.executeQuery();

                while(result.next()){

                    ModelTransaksi t =

                    new ModelTransaksi();

                    t.setId_transaksi(

                            result.getInt(
                                    "id_transaksi"
                            )

                    );

                    t.setNama_game(

                            result.getString(
                                    "nama_game"
                            )

                    );

                    t.setNama_nominal(

                            result.getString(
                                    "nama_nominal"
                            )

                    );

                    t.setUser_game(

                            result.getString(
                                    "user_game"
                            )

                    );
                    
                    t.setMetode_pembayaran(

                            result.getString(
                                    "metode_pembayaran"
                            )

                    );

                    t.setTanggal(

                            result.getString(
                                    "tanggal"
                            )

                    );

                    t.setStatus_pembayaran(

                            result.getString(
                                    "status_pembayaran"
                            )

                    );

                    list.add(
                            t
                    );

                }

            }

            catch(Exception e){

                System.out.println(
                        e.getMessage()
                );
        }
        return list;
    }

}