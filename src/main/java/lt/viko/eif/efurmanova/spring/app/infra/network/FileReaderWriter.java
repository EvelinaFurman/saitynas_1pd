package lt.viko.eif.efurmanova.spring.app.infra.network;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.charset.StandardCharsets;

public class FileReaderWriter {

    public String readFile(String path) throws Exception {
        return Files.readString(Path.of(path), StandardCharsets.UTF_8);
    }

    public void writeFile(String path, String content) throws Exception {
        Files.writeString(Path.of(path), content, StandardCharsets.UTF_8);
    }
}