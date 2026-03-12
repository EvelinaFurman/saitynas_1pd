package lt.viko.eif.efurmanova.spring.app.infra.network;

import lt.viko.eif.efurmanova.spring.app.service.OrderService;

import java.io.OutputStream;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class SocketHandler {

    private final OrderService orderService;

    public SocketHandler(OrderService orderService) {
        this.orderService = orderService;
    }

    public void handle(Socket socket) throws Exception {

            String requestXml = new String(socket.getInputStream().readAllBytes(), StandardCharsets.UTF_8);
            String responseXml;
        try{
            responseXml = orderService.processOrder(requestXml);
        }catch (Exception e) {
            responseXml = e.getMessage();
        }
        try (OutputStream out = socket.getOutputStream()) {
            out.write(responseXml.getBytes(StandardCharsets.UTF_8));
            out.flush();
        }
    }
}
