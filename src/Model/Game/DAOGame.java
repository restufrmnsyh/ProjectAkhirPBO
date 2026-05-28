package Model.Game;

import Model.Connector;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DAOGame
implements InterfaceDAOGame {

    Connection conn;

    public DAOGame(){

        conn =
        Connector.Connect();

    }

    @Override
    public void insert(
            ModelGame game
    ){

        try{

            String query =

            "INSERT INTO game "
            +
            "(nama_game,developer,kategori)"
            +
            " VALUES (?,?,?)";

            PreparedStatement statement =

            conn.prepareStatement(
                    query
            );

            statement.setString(
                    1,
                    game.getNama_game()
            );

            statement.setString(
                    2,
                    game.getDeveloper()
            );

            statement.setString(
                    3,
                    game.getKategori()
            );

            statement.executeUpdate();

            System.out.println(
                    "Data berhasil ditambah"
            );

        }

        catch(Exception e){

            System.out.println(
                    e.getMessage()
            );

        }

    }

    @Override
    public void update(
            ModelGame game
    ){

        try{

            String query =

            "UPDATE game "
            +
            "SET nama_game=?,"
            +
            "developer=?,"
            +
            "kategori=? "
            +
            "WHERE id_game=?";

            PreparedStatement statement =

            conn.prepareStatement(
                    query
            );

            statement.setString(
                    1,
                    game.getNama_game()
            );

            statement.setString(
                    2,
                    game.getDeveloper()
            );

            statement.setString(
                    3,
                    game.getKategori()
            );

            statement.setInt(
                    4,
                    game.getId_game()
            );

            statement.executeUpdate();

            System.out.println(
                    "Data berhasil diupdate"
            );

        }

        catch(Exception e){

            System.out.println(
                    e.getMessage()
            );

        }

    }

    @Override
    public void delete(
            int id_game
    ){

        try{

            String query =

            "DELETE FROM game "
            +
            "WHERE id_game=?";

            PreparedStatement statement =

            conn.prepareStatement(
                    query
            );

            statement.setInt(
                    1,
                    id_game
            );

            statement.executeUpdate();

            System.out.println(
                    "Data berhasil dihapus"
            );

        }

        catch(Exception e){

            System.out.println(
                    e.getMessage()
            );

        }

    }

    @Override
    public List<ModelGame> getAll(){

        List<ModelGame> list =
        new ArrayList<>();

        try{

            Statement statement =
            conn.createStatement();

            ResultSet result =

            statement.executeQuery(

                    "SELECT * FROM game"

            );

            while(
                    result.next()
            ){

                ModelGame game =

                new ModelGame();

                game.setId_game(

                        result.getInt(
                                "id_game"
                        )

                );

                game.setNama_game(

                        result.getString(
                                "nama_game"
                        )

                );

                game.setDeveloper(

                        result.getString(
                                "developer"
                        )

                );

                game.setKategori(

                        result.getString(
                                "kategori"
                        )

                );

                list.add(
                        game
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