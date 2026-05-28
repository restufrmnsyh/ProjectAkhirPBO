package Controller;

import Model.Transaksi.DAOTransaksi;
import Model.Transaksi.ModelTransaksi;

import java.util.List;

public class ControllerTransaksi {

    DAOTransaksi dao;

    public ControllerTransaksi(){

        dao =
        new DAOTransaksi();

    }

    public void topup(
            ModelTransaksi transaksi
    ){

        dao.topup(
                transaksi
        );

    }

    public List<ModelTransaksi>
    tampilTransaksi(){

        return dao.getAll();
    }
    public List<ModelTransaksi>
    tampilTransaksiUser(
            int id_user
    ){

        return dao.getByUser(
                id_user
        );
    }

}