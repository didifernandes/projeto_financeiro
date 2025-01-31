package connectionDatabase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoBanco {
    private static final String URL = "jdbc:mysql://localhost:3306/controle_financeiro?useSSL=false&serverTimezone=UTC";
    private static final String USUARIO = "root"; // Substitua pelo seu usuário
    private static final String SENHA = "123456"; // Substitua pela sua senha

    public static Connection getConnection() {
        Connection conexao = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            conexao = DriverManager.getConnection(URL, USUARIO, SENHA);
            System.out.println("Conexão estabelecida com sucesso!");
        } catch (ClassNotFoundException e) {
            System.out.println("Driver não encontrado: " + e.getMessage());
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("Erro na conexão: " + e.getMessage());
            e.printStackTrace();
        }
        return conexao;
    }
}

