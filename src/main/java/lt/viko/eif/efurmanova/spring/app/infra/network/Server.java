package lt.viko.eif.efurmanova.spring.app.infra.network;

import lt.viko.eif.efurmanova.spring.app.service.OrderService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.persistence.autoconfigure.EntityScan;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

@EntityScan(basePackages = "lt.viko.eif.efurmanova.spring.app")
@EnableJpaRepositories(basePackages = "lt.viko.eif.efurmanova.spring.app.repository")
@SpringBootApplication(scanBasePackages = "lt.viko.eif.efurmanova.spring.app")
public class Server {

    public static final int PORT = 8080;

    public static void main(String[] args) {
        try {
            ConfigurableApplicationContext context = SpringApplication.run(Server.class, args);
            System.setProperty("javax.xml.accessExternalDTD", "file");

            try (ServerSocket serverSocket = new ServerSocket(PORT)) {
                System.out.println("Server listening on port " + PORT);

                while (true) {
                    try (Socket socket = serverSocket.accept()) {
                        System.out.println("Client connected: " + socket.getRemoteSocketAddress());

                        try {
                            String requestXml = new String(socket.getInputStream().readAllBytes(), StandardCharsets.UTF_8);
                            System.out.println("Received bytes: " + requestXml.getBytes(StandardCharsets.UTF_8).length);

                            OrderService orderService = context.getBean(OrderService.class);
                            String responseXml = orderService.processOrder(requestXml);

                            writeAll(socket.getOutputStream(), responseXml);
                        } catch (Exception e) {
                            writeAll(socket.getOutputStream(), e.getMessage());
                            System.out.println("Error response sent: " + e.getMessage());
                        }
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void writeAll(OutputStream out, String s) throws IOException {
        out.write(s.getBytes(StandardCharsets.UTF_8));
        out.flush();
    }
}