package lt.viko.eif.efurmanova.spring.app.infra;

public interface Server {
    void start();

    void stop();

    boolean isRunning();

    int getPort();
}