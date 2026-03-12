package lt.viko.eif.efurmanova.spring.app.infra.network;

import org.springframework.stereotype.Component;

import java.net.ServerSocket;
import java.net.Socket;

public class SocketListener {

    private final int port;

    public SocketListener(int port) {
        this.port = port;
    }
    public void start(SocketHandler handler) throws Exception {
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Server listening on port " + port);

            while (true) {
                try (Socket socket = serverSocket.accept()) {
                    System.out.println("Client connected: " + socket.getRemoteSocketAddress());
                    handler.handle(socket);
                }
            }
        }
    }

}
