package pl.komponentowe.data;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

public class IOXml<T> implements Preservation<T> {
    private XStream xStream;

    public IOXml() {
        this.xStream = new XStream(new DomDriver());
    }

    @Override
    public void save(String path, ArrayList<T> object) {
        try (DataOutputStream dataOutputStream = new DataOutputStream(new FileOutputStream(path))) {
            dataOutputStream.writeChars(xStream.toXML(object));
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public ArrayList<T> load(String path) {
        ArrayList<T> result = new ArrayList<>();

        try {
            String content = Files.readString(Paths.get(path), StandardCharsets.UTF_16);
            result = (ArrayList<T>) xStream.fromXML(content);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

        return result;
    }
}
