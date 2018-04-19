import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FileReader {

    List parseFile(String filePath) throws IOException {
        Stream<String> stream = Files.lines(Paths.get(filePath));
        return stream.filter(line -> !line.isEmpty()).map(String::toUpperCase).map(String::trim)
                        .collect(Collectors.toList());
    }
}
