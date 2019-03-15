package feature.java11;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import static java.util.function.Predicate.not;

/**
 *
 * @author Panyaprach Tularak
 */
public class ReadFiles {
    
    public static void main(String[] args) throws IOException {
        String separator = System.getProperty("file.separator");
        String dir = String.join(separator, "src", "main", "resources", "to_read.txt");
        Path path = Path.of(dir);
        String content = Files.readString(path);
        content.lines()
                //if not static import 
                //.filter(Predicate.not(String::isBlank))
                .filter(not(String::isBlank))
                .forEach(System.out::println);
    }
    
}
