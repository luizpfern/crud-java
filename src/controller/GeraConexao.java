package controller;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.DriverManager;
import javax.swing.JOptionPane;

public class GeraConexao {
    private String url, dbName, user, password;
    private Connection conexao;

    public GeraConexao() {
        this.dbName = "postgres";
        this.url = "jdbc:postgresql://localhost:5432/" + this.dbName;
        this.user = "postgres";
        this.password = "password";
        this.conexao = null;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    
    public Connection getConnection() {        
        try {
            this.conexao = DriverManager.getConnection(this.url, this.user, this.password);
        } catch (SQLException e) {
            System.out.println("Erro SQL: "+e.getMessage());
            JOptionPane.showMessageDialog(null,"Erro ao conectar com banco de dados. Verifique usuário e senha");

        } catch (Exception e) {
            System.out.println("Erro: "+e.getMessage());
        }
        return this.conexao;
    }
    
    public void abreConexao() {
        GeraConexao geraConexao = new GeraConexao();
        this.conexao = geraConexao.getConnection();
    }
    
    public void fechaConexao() {
        try {
            this.conexao.close();
        } catch(SQLException e) {
            System.out.println("Erro SQL: "+e.getMessage());
        } catch(Exception e) {
            System.out.println("Erro: "+e.getMessage());
        }
    }
}
