package pl.komponentowe.data;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Klasa odpowiedzialna za zapisywanie ustawien do pliku XML.
 * Do poprawnego dzialania klasy, potrzebne sa biblioteki XStream.
 *
 * @author Patryk Kolanek
 * @author Kacper Swiercz
 *
 * @see <a href="http://x-stream.github.io/">XStream</a>
 */
public class IOXml implements Preservation {
    /** Pole XStream */
    private XStream xStream;

    public IOXml() {
        this.xStream = new XStream(new DomDriver());
    }

    /**
     * Metoda wykonujaca zapis ustawien do pliku xml.
     *
     * @param path Sciezka do pliku xml.
     * @param object Obiekt zapisywany do pliku xml.
     */
    @Override
    public void save(String path, Object object) {
        try (DataOutputStream dataOutputStream = new DataOutputStream(new FileOutputStream(path))) {
            dataOutputStream.writeChars(xStream.toXML((Settings) object));
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    /**
     * Metoda wykonujaca odczyt z pliku xml.
     *
     * @param path Sciezka do pliku xml.
     * @return Ustawienia.
     */
    @Override
    public Object load(String path) {
        Settings result = null;

        try {
            String content = Files.readString(Paths.get(path), StandardCharsets.UTF_16);
            result = (Settings) xStream.fromXML(content);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

        return result;
    }
}
