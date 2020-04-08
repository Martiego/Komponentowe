package pl.martiego;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

public class IOXml<T> implements Preservation<T> {
    private XStream xStream;

    public IOXml() {
        this.xStream = new XStream(new DomDriver());
    }

    @Override
    public void save(String path, T object) {

        try (DataOutputStream dataOutputStream = new DataOutputStream(new FileOutputStream(path))) {
            dataOutputStream.writeChars(xStream.toXML(object));
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public T load(String path) {
        T result = null;

        try {
            String content = Files.readString(Paths.get(path), StandardCharsets.UTF_16);
            result = (T) xStream.fromXML(content);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

        return result;
    }
}
