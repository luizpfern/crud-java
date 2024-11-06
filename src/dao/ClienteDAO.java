package dao;

import java.util.ArrayList;
import java.sql.*;
import models.Cliente;
import controller.GeraConexao;

public class ClienteDAO {
    private Connection conexao;
    private ArrayList<Cliente> listaClientes;
    
    public ArrayList<Cliente> getAllClientes() {
        this.listaClientes = new ArrayList();
        
        GeraConexao geraConexao = new GeraConexao();        
        this.conexao = geraConexao.getConnection();
        
        String query = "SELECT * FROM cliente";
        
        try {
            Statement st = this.conexao.createStatement();
            ResultSet resultSet = st.executeQuery(query);
            
            while (resultSet.next()) {
                Cliente clienteObj = new Cliente(
                    resultSet.getInt("id"),
                    resultSet.getString("nome"),
                    resultSet.getString("email")
                );
                listaClientes.add(clienteObj);
            }
            st.close();
        } catch(SQLException e) {
            System.out.println("Erro SQL: "+e.getMessage());
        } catch (Exception e) {
            System.out.println("Erro: "+e.getMessage());
        }
        geraConexao.fechaConexao();
        return listaClientes;
    }
    
    public ArrayList<Cliente> getClientesFiltro(String nome) {
        this.listaClientes = new ArrayList();
        
        GeraConexao geraConexao = new GeraConexao();        
        this.conexao = geraConexao.getConnection();
        
        String query = "SELECT * FROM cliente";
        if (nome.length() > 0) query += " WHERE nome ilike '%' || ? || '%'";
        query += " ORDER BY ID DESC";
        
        try {
            PreparedStatement st = this.conexao.prepareStatement(query);
            if (nome.length() > 0)st.setString(1,nome);
            ResultSet resultSet = st.executeQuery();
            
            while (resultSet.next()) {
                Cliente clienteObj = new Cliente(
                    resultSet.getInt("id"),
                    resultSet.getString("nome"),
                    resultSet.getString("email")
                );
                listaClientes.add(clienteObj);
            }
            st.close();
        } catch(SQLException e) {
            System.out.println("Erro SQL: "+e.getMessage());
        } catch (Exception e) {
            System.out.println("Erro: "+e.getMessage());
        }
        geraConexao.fechaConexao();
        return listaClientes;
    }
    
    public boolean salvar(Cliente cliente) {
        GeraConexao geraConexao = new GeraConexao();
        geraConexao.abreConexao();
        
        this.conexao = geraConexao.getConnection();
        
        String query = "INSERT INTO CLIENTE (nome,email) values (?,?)";
        
        try {
            PreparedStatement st = this.conexao.prepareStatement(query);
            st.setString(1, cliente.getNome());
            st.setString(2, cliente.getEmail());
            st.executeUpdate();
            st.close();
            
        } catch(SQLException e) {
            System.out.println("Erro SQL: "+e.getMessage());
        } catch (Exception e) {
            System.out.println("Erro: "+e.getMessage());
        }
        geraConexao.fechaConexao();
        return true;
    }
    
    public boolean atualizar(Cliente cliente) {
        GeraConexao geraConexao = new GeraConexao();
        geraConexao.abreConexao();
        
        this.conexao = geraConexao.getConnection();
        
        String query = "UPDATE CLIENTE SET nome=?,email=? where id=?";
        
        try {
            PreparedStatement st = this.conexao.prepareStatement(query);
            st.setString(1, cliente.getNome());
            st.setString(2, cliente.getEmail());
            st.setInt(3, cliente.getId());
            st.executeUpdate();
            st.close();
            
        } catch(SQLException e) {
            System.out.println("Erro SQL: "+e.getMessage());
        } catch (Exception e) {
            System.out.println("Erro: "+e.getMessage());
        }
        geraConexao.fechaConexao();
        return true;
    }
    
    public boolean remover(int id) {
        GeraConexao geraConexao = new GeraConexao();
        geraConexao.abreConexao();
        
        this.conexao = geraConexao.getConnection();
        
        String query = "DELETE FROM CLIENTE WHERE ID = ?";
        
        try {
            PreparedStatement st = this.conexao.prepareStatement(query);
            st.setInt(1, id);
            st.executeUpdate();
            st.close();
            
        } catch(SQLException e) {
            System.out.println("Erro SQL: "+e.getMessage());
        } catch (Exception e) {
            System.out.println("Erro: "+e.getMessage());
        }
        geraConexao.fechaConexao();
        return true;
    }
}
