import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Stream;

public class Ejercicio3 {
    public static void eliminaExtensiones(@NotNull File folder) {
        Stream.of(folder.listFiles())
                .filter(File::isFile)
                .forEach(Ejercicio3::eliminaExtension);
    }

    // TODO mirar la implementacion optima si podria ser con getParent para no
    //  utilizar toda la ruta a la hora de iterar para eliminar
    public static void eliminaExtension(@NotNull File filename) {
        StringBuilder sb = new StringBuilder(filename.getPath());
        for (int i = 0; i < sb.length(); i++) {
            if (sb.charAt(i) == '.') {
                sb.delete(i, sb.length());
                break;
            }
        }
        filename.renameTo(new File(sb.toString()));
    }

    public static Path cambiaNombre(File f, String name) throws IOException {
        Path parent = f.toPath().getParent();
        Path updated = parent.resolveSibling(name);
        Files.move(f.toPath(), updated);
        return updated;
    }

    public static void main(String[] args) throws IOException{
    }
}
