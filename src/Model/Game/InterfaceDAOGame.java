package Model.Game;

import java.util.List;

public interface InterfaceDAOGame {

    public void insert(
            ModelGame game
    );

    public void update(
            ModelGame game
    );

    public void delete(
            int id_game
    );

    public List<ModelGame> getAll();

}