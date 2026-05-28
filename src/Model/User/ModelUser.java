package Model.User;

public class ModelUser {

    private int id_user;
    private String username;
    private String password;
    private String nama_lengkap;
    private String email;
    private String role;

    public ModelUser(){}

    public ModelUser(
            int id_user,
            String username,
            String password,
            String nama_lengkap,
            String email,
            String role
    ){

        this.id_user=id_user;
        this.username=username;
        this.password=password;
        this.nama_lengkap=nama_lengkap;
        this.email=email;
        this.role=role;

    }

    public int getId_user(){
        return id_user;
    }

    public void setId_user(int id_user){
        this.id_user=id_user;
    }

    public String getUsername(){
        return username;
    }

    public void setUsername(String username){
        this.username=username;
    }

    public String getPassword(){
        return password;
    }

    public void setPassword(String password){
        this.password=password;
    }

    public String getNama_lengkap(){
        return nama_lengkap;
    }

    public void setNama_lengkap(
            String nama_lengkap
    ){
        this.nama_lengkap=nama_lengkap;
    }

    public String getEmail(){
        return email;
    }

    public void setEmail(
            String email
    ){
        this.email=email;
    }

    public String getRole(){
        return role;
    }

    public void setRole(
            String role
    ){
        this.role=role;
    }

}