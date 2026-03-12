package lt.viko.eif.efurmanova.spring.app.infra.network;

import java.net.Socket;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

public class SocketClient {

    private final String host;
    private final int port;

    public SocketClient(String host, int port) {
        this.host = host;
        this.port = port;
    }

    public String send(String message) throws Exception {
        try (Socket socket = new Socket(host, port);
             OutputStream out = socket.getOutputStream()) {

            out.write(message.getBytes(StandardCharsets.UTF_8));
            out.flush();
            socket.shutdownOutput();

            return new String(socket.getInputStream().readAllBytes(), StandardCharsets.UTF_8);
        }
    }
}