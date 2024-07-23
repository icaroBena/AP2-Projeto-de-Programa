import java.io.IOException;
import java.net.Socket;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ConnectionManager {
    private Map<String, Socket> activeConnections;
    private Map<String, Status> userStatusMap;

    public ConnectionManager() {
        activeConnections = new HashMap<>();
        userStatusMap = new HashMap<>();
    }

    public void addConnection(String login, Socket socket) {
        activeConnections.put(login, socket);
        userStatusMap.put(login, Status.ONLINE);
    }

    public void removeConnection(String login) {
        activeConnections.remove(login);
        userStatusMap.put(login, Status.OFFLINE);
    }

    public Socket getConnection(String login) {
        return activeConnections.get(login);
    }

    public void killConnection(String login) {
        Socket socket = activeConnections.get(login);
        if (socket != null) {
            try {
                socket.close();
                activeConnections.remove(login);
                userStatusMap.put(login, Status.OFFLINE);
                System.out.println("Conexão fechada para o usuário: " + login);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Usuário não está conectado: " + login);
        }
    }

    public void killConnections(List<String> logins) {
        for (String login : logins) {
            killConnection(login);
        }
    }

    public void printStatus() {
        userStatusMap.forEach((login, status) -> System.out.println(login + ": " + status));
    }
}
