package lt.viko.eif.efurmanova.spring.app.infra.network;

import lt.viko.eif.efurmanova.spring.app.service.OrderService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.persistence.autoconfigure.EntityScan;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EntityScan(basePackages = "lt.viko.eif.efurmanova.spring.app")
@EnableJpaRepositories(basePackages = "lt.viko.eif.efurmanova.spring.app.repository")
@SpringBootApplication(scanBasePackages = "lt.viko.eif.efurmanova.spring.app")
public class Server {

    public static final int PORT = 8080;

    public static void main(String[] args) throws Exception {
        System.setProperty("javax.xml.accessExternalDTD", "file");
        try {
            ConfigurableApplicationContext context = SpringApplication.run(Server.class, args);

            OrderService orderService = context.getBean(OrderService.class);

            SocketListener listener = new SocketListener(8080);
            SocketHandler handler = new SocketHandler(orderService);
            listener.start(handler);

        }catch (Exception e){
            e.printStackTrace();
}}}