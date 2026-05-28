package Model.User;

import Model.Connector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DAOUser implements InterfaceDAOUser {

    Connection conn;

    public DAOUser(){

        conn = Connector.Connect();

    }

    @Override
    public ModelUser login(
            String username,
            String password
    ){

        ModelUser user = null;

        try{

            String query = "SELECT * FROM user WHERE username=? AND password=?";

            PreparedStatement statement =
            conn.prepareStatement(
                    query
            );

            statement.setString(
                    1,
                    username
            );

            statement.setString(
                    2,
                    password
            );

            ResultSet result =
            statement.executeQuery();

            if(result.next()){

                user =
                new ModelUser();

                user.setId_user(

                        result.getInt(
                                "id_user"
                        )

                );

                user.setUsername(

                        result.getString(
                                "username"
                        )

                );

                user.setPassword(

                        result.getString(
                                "password"
                        )

                );

                user.setNama_lengkap(

                        result.getString(
                                "nama_lengkap"
                        )

                );

                user.setEmail(

                        result.getString(
                                "email"
                        )

                );

                user.setRole(

                        result.getString(
                                "role"
                        )

                );

            }

        }

        catch(Exception e){

            System.out.println(
                    e.getMessage()
            );

        }

        return user;

    }
    
    public void register(
            ModelUser user
    ){

        try{

            String query =

            "INSERT INTO user "
            +
            "(username,password,role)"
            +
            " VALUES (?,?,?)";

            PreparedStatement statement =

            conn.prepareStatement(
                    query
            );

            statement.setString(
                    1,
                    user.getUsername()
            );

            statement.setString(
                    2,
                    user.getPassword()
            );

            statement.setString(
                    3,
                    "user"
            );

            statement.executeUpdate();

            System.out.println(
                    "Register Berhasil"
            );

        }

        catch(Exception e){

            System.out.println(
                    e.getMessage()
            );

        }

    }

}