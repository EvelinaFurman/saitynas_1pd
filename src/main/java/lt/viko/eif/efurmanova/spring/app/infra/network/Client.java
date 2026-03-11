package lt.viko.eif.efurmanova.spring.app.infra.network;

import java.io.OutputStream;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

public class Client {

    public static void main(String[] args) throws Exception {
        final String host = "127.0.0.1";
        int port = 8080;
        String orderPath = "order.xml";
        String invoicePath = "invoice.xml";

        String xml = Files.readString(Path.of(orderPath), StandardCharsets.UTF_8);

        try (Socket socket = new Socket(host, port)) {
            OutputStream out = socket.getOutputStream();
            out.write(xml.getBytes(StandardCharsets.UTF_8));
            out.flush();
            socket.shutdownOutput();

            String resp = new String(socket.getInputStream().readAllBytes(), StandardCharsets.UTF_8);
            Files.writeString(Path.of(invoicePath), resp, StandardCharsets.UTF_8);
            System.out.println(resp);
        }
    }
}