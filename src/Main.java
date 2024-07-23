import java.io.IOException;
import java.net.Socket;
import java.util.Arrays;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        // Cria uma instância do gerenciador de conexões
        ConnectionManager connectionManager = new ConnectionManager();

        // Simula a adição de conexões de usuários
        try {
            // Simulação de sockets
            Socket socket1 = new Socket("localhost", 8080);
            Socket socket2 = new Socket("localhost", 8080);
            Socket socket3 = new Socket("localhost", 8080);
            System.out.println(socket1.getRemoteSocketAddress());
            connectionManager.addConnection("user1", socket1);
            connectionManager.addConnection("user2", socket2);
            connectionManager.addConnection("user3", socket3);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Verifica o status dos usuários
        System.out.println("Status dos usuários:");
        connectionManager.printStatus();

        // Fecha a conexão de um único usuário
        System.out.println("Fechando a conexão de user1...");
        connectionManager.killConnection("user1");

        // Verifica o status dos usuários após fechar a conexão de user1
        System.out.println("Status dos usuários após fechar a conexão de user1:");
        connectionManager.printStatus();

        // Fecha conexões de múltiplos usuários
        List<String> logins = Arrays.asList("user2", "user3");
        System.out.println("Fechando as conexões de user2 e user3...");
        connectionManager.killConnections(logins);

        // Verifica o status dos usuários após fechar múltiplas conexões
        System.out.println("Status dos usuários após fechar múltiplas conexões:");
        connectionManager.printStatus();
    }
}
