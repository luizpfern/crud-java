package testes;

import dao.ClienteDAO;
import models.Cliente;
import java.util.ArrayList;

public class Teste {
    public static void main(String[] args) {
        ClienteDAO cliente = new ClienteDAO();
        
        ArrayList<Cliente> lista = cliente.getAllClientes();
        
        System.out.println(lista.get(0).getNome());
        System.out.println(lista.get(1).getNome());
        System.out.println(lista.get(2).getNome());
        
        cliente.remover(2);
    }
}
