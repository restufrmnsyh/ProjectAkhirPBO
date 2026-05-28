package Model.User;

public class Admin
extends User {

    public Admin(
            String username
    ){

        super(
                username,
                "admin"
        );

    }

    @Override
    public void menu(){

        System.out.println(

                "Menu Admin"

        );

    }

}