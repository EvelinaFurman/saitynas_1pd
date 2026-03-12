package lt.viko.eif.efurmanova.spring.app.infra.network;

public class Client {

    public static void main(String[] args) throws Exception {

        FileReaderWriter fileRW = new FileReaderWriter();
        final String host = "127.0.0.1";
        int port = 8080;
        String orderPath = "order.xml";
        String invoicePath = "invoice.xml";

        SocketClient socketClient = new SocketClient(host, port);
        String xml = fileRW.readFile(orderPath);
        String response = socketClient.send(xml);
        fileRW.writeFile(invoicePath, response);

        System.out.println(response);
    }
}
