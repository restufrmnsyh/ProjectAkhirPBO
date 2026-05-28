package Model.Transaksi;

import java.util.List;

public interface InterfaceDAOTransaksi {

    public void topup(
            ModelTransaksi transaksi
    );

    public List<ModelTransaksi> getAll();

}