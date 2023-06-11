package st.tiy.lpq.components;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Component
public class FileSystem {

    private final String baseFilePath;

    public FileSystem( @Value("${fileSystem.baseFilePath}") String baseFilePath) {
        this.baseFilePath = baseFilePath;
    }

    public void saveFile(byte[] bytes,String ... filePathParts) {
        Path path = Paths.get(baseFilePath, filePathParts);
        try {
            Files.createDirectories(path.getParent());
            Files.write(path, bytes);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
