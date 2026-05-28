package Model.User;

public class UserGame
extends User {

    public UserGame(
            String username
    ){

        super(
                username,
                "user"
        );

    }

    @Override
    public void menu(){

        System.out.println(

                "Menu User"

        );

    }

}