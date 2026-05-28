package Model.Game;

public class ModelGame {

    private int id_game;

    private String nama_game;

    private String developer;

    private String kategori;

    public ModelGame(){}

    public ModelGame(

            int id_game,

            String nama_game,

            String developer,

            String kategori

    ){

        this.id_game=id_game;

        this.nama_game=nama_game;

        this.developer=developer;

        this.kategori=kategori;

    }

    public int getId_game(){

        return id_game;

    }

    public void setId_game(
            int id_game
    ){

        this.id_game=id_game;

    }

    public String getNama_game(){

        return nama_game;

    }

    public void setNama_game(
            String nama_game
    ){

        this.nama_game=nama_game;

    }

    public String getDeveloper(){

        return developer;

    }

    public void setDeveloper(
            String developer
    ){

        this.developer=developer;

    }

    public String getKategori(){

        return kategori;

    }

    public void setKategori(
            String kategori
    ){

        this.kategori=kategori;

    }

}