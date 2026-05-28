package Model.User;

public interface InterfaceDAOUser {

    public ModelUser login(
            String username,
            String password
    );

}