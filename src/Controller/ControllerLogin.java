package Controller;

import Model.User.DAOUser;
import Model.User.ModelUser;


public class ControllerLogin {

    DAOUser dao;

    public ControllerLogin(){

        dao =
        new DAOUser();

    }

    public ModelUser login(

            String username,

            String password

    ){

        return dao.login(

                username,

                password

        );

    }
    public void register(
        ModelUser user
    ){

        dao.register(
                user
        );

    }

}