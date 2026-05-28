package Controller;

import Model.Game.DAOGame;
import Model.Game.ModelGame;

import java.util.List;

public class ControllerGame {

    DAOGame dao;

    public ControllerGame(){

        dao =
        new DAOGame();

    }

    public void tambahGame(
            ModelGame game
    ){

        dao.insert(
                game
        );

    }

    public void editGame(
            ModelGame game
    ){

        dao.update(
                game
        );

    }

    public void hapusGame(
            int id_game
    ){

        dao.delete(
                id_game
        );

    }

    public List<ModelGame> tampilGame(){

        return dao.getAll();

    }

}