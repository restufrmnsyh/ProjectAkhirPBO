package Model.Transaksi;

public class ModelTransaksi {

    private int id_transaksi;

    private int id_user;

    private int id_game;

    private int id_nominal;

    private String nama_game;

    private String nama_nominal;

    private String user_game;
    private String metode_pembayaran;
    private String tanggal;

    private String status_pembayaran;

    public ModelTransaksi(){}

    public int getId_transaksi(){
        return id_transaksi;
    }

    public void setId_transaksi(
            int id_transaksi
    ){
        this.id_transaksi=id_transaksi;
    }

    public int getId_user(){
        return id_user;
    }

    public void setId_user(
            int id_user
    ){
        this.id_user=id_user;
    }

    public int getId_game(){
        return id_game;
    }

    public void setId_game(
            int id_game
    ){
        this.id_game=id_game;
    }

    public int getId_nominal(){
        return id_nominal;
    }

    public void setId_nominal(
            int id_nominal
    ){
        this.id_nominal=id_nominal;
    }

    public String getNama_game(){

        return nama_game;

    }

    public void setNama_game(
            String nama_game
    ){

        this.nama_game = nama_game;

    }

    public String getNama_nominal(){

        return nama_nominal;

    }

    public void setNama_nominal(
            String nama_nominal
    ){

        this.nama_nominal = nama_nominal;

    }

    public String getUser_game(){
        return user_game;
    }

    public void setUser_game(
            String user_game
    ){
        this.user_game=user_game;
    }
    
    public String getMetode_pembayaran(){
        return metode_pembayaran;
    }

    public void setMetode_pembayaran(
            String metode_pembayaran
    ){
        this.metode_pembayaran =
        metode_pembayaran;
    }

    public String getTanggal(){
        return tanggal;
    }

    public void setTanggal(
            String tanggal
    ){
        this.tanggal=tanggal;
    }

    public String getStatus_pembayaran(){
        return status_pembayaran;
    }

    public void setStatus_pembayaran(
            String status
    ){
        this.status_pembayaran=status;
    }

}