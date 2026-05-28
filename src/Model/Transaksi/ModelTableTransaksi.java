package Model.Transaksi;

import java.util.List;

import javax.swing.table.AbstractTableModel;

public class ModelTableTransaksi
extends AbstractTableModel {

    List<ModelTransaksi> dataTransaksi;

    String kolom[] = {

        "ID",
        "GAME",
        "NOMINAL",
        "USER GAME",
        "PEMBAYARAN",
        "TANGGAL",
        "STATUS"

    };

    public ModelTableTransaksi(

            List<ModelTransaksi> dataTransaksi

    ){

        this.dataTransaksi =
        dataTransaksi;

    }

    @Override
    public int getRowCount(){

        return dataTransaksi.size();

    }

    @Override
    public int getColumnCount(){

        return kolom.length;

    }

    @Override
    public Object getValueAt(

            int row,

            int column

    ){

        ModelTransaksi transaksi =

        dataTransaksi.get(
                row
        );

        switch(column){

            case 0:
                return transaksi.getId_transaksi();

            case 1:
                return transaksi.getNama_game();

            case 2:
                return transaksi.getNama_nominal();

            case 3:
                return transaksi.getUser_game();

            case 4:
                return transaksi.getMetode_pembayaran();

            case 5:
                return transaksi
                .getTanggal();

             case 6:
                return transaksi
                .getStatus_pembayaran();
    
            default:
                return null;

        }

    }

    @Override
    public String getColumnName(
            int column
    ){

        return kolom[column];

    }

}