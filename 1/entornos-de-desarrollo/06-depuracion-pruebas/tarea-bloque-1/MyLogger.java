package ed.ud6.debugger.test;

import java.io.IOException;
import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Formatter;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class MyLogger {
    private static final Logger LOG = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
    private static final String[] PERMITTED_LEVELS = {
            "INFO",
            "WARNING"
    };

    private static String filename;
    private static boolean isCustomFilename;

    private static FileHandler fileTxt;

    private static FileHandler fileHTML;

    private static void setFilename() throws IOException {
        filename = LOG.getLevel().getName().toLowerCase();
        LOG.info("Cambiando el nombre del archivo a " + filename);
        setupFiles();
    }

    public static void setFilename(String s) throws IOException {
        LOG.info("Cambiando el nombre del archivo a " + s);
        filename = s;
        isCustomFilename = true;
        setupFiles();
    }

    public static void setLevel(int level) throws IOException {
        try {
            LOG.warning("Cambiamos el nivel de LOG a " + PERMITTED_LEVELS[level]);
            Level newLevel = Level.parse(PERMITTED_LEVELS[level]);
            LOG.setLevel(newLevel);

            if (!isCustomFilename)
                setFilename();
        } catch (ArrayIndexOutOfBoundsException e) {
            LOG.warning("Var opcion = " + (level + 1) + " no valida");
            System.out.println("La opcion no es valida");
        }
    }

    public static void setup() throws IOException {
        // suppress the logging output to the console
        Logger rootLogger = Logger.getLogger("");
        Handler[] handlers = rootLogger.getHandlers();
        if (handlers[0] instanceof ConsoleHandler) {
            rootLogger.removeHandler(handlers[0]);
        }

        LOG.setLevel(Level.INFO);
        isCustomFilename = false;
        setFilename(); // to level name
    }

    private static void setupFiles() throws IOException {
        if (fileTxt != null) {
            fileTxt.flush();
            fileTxt.close();
            LOG.removeHandler(fileTxt);
        }

        if (fileHTML != null) {
            fileHTML.flush();
            fileHTML.close();
            LOG.removeHandler(fileHTML);
        }

        String output = "log/";

        fileTxt = new FileHandler(output + filename + ".txt", true);
        fileHTML = new FileHandler(output + filename + ".html", true);

        // create a TXT formatter
        Formatter formatterTxt = new SimpleFormatter();
        fileTxt.setFormatter(formatterTxt);
        LOG.addHandler(fileTxt);

        // create an HTML formatter
        Formatter formatterHTML = new MyHtmlFormatter();
        fileHTML.setFormatter(formatterHTML);
        LOG.addHandler(fileHTML);
    }

    public static void prueba() {

        String path = "logs/";
        String name = "log";

        FileHandler fileTxt = new FileHandler(path + name + ".txt", true);
        Formatter formatterTxt = new SimpleFormatter();
        fileTxt.setFormatter(formatterTxt);
        LOG.addHandler(fileTxt);

        FileHandler fileHtml = new FileHandler(path + name + ".html", true);
        Formatter formatterHtml = new MyHtmlFormatter();
        fileHtml.setFormatter(formatterHtml);
        LOG.addHandler(fileHtml);

        // corres tu progrma

        /// Case 4
        String entrada = new Scanner(System.in).nextLine();

        fileTxt.flush();
        fileTxt.close();
        LOG.removeHandler(fileTxt);

        fileHtml.flush();
        fileHtml.close();
        LOG.removeHandler(fileHtml);

        fileTxt = new FileHandler(path + entrada + ".txt", true);
        fileTxt.setFormatter(formatterTxt);
        LOG.addHandler(fileTxt);

        fileHtml = new FileHandler(path + entrada + ".html", true);
        fileHtml.setFormatter(formatterHtml);
        LOG.addHandler(fileHtml);
    }
}