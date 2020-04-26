package pl.komponentowe.data;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import pl.komponentowe.data.Preservation;

import java.io.DataOutputStream;
import java.io.FileOutputStream;

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
//            String content = Files.readString(Paths.get(path), StandardCharsets.UTF_16);
//            result = (T) xStream.fromXML(content);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

        return result;
    }
}
