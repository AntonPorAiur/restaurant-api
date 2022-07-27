package mx.restaurante.to;


import lombok.Data;

@Data
public class PedidoTO {

    private long idPedido;
    private long idPlatillo;
    private int cantidad;

}
