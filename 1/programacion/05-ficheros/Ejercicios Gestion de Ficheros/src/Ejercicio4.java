import java.io.File;
import java.io.FileNotFoundException;
import java.util.stream.Stream;

public class Ejercicio4 {
    public static boolean borraTodo(File f) throws FileNotFoundException {
        if (!f.exists())
            throw new FileNotFoundException();

        else if (f.isFile())
            return f.delete();

        else if (f.isDirectory()) {
            Stream.of(f.list()).map(File::new).forEach(File::delete);
            return f.delete();
        } else
            System.err.println("Ocurrio algo inesperado");
            return false;
    }

    public static void main(String[] args) throws FileNotFoundException{
        System.out.println(borraTodo(new File("aa")));
    }
}
