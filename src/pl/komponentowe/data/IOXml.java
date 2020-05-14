package pl.komponentowe.data;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

public class IOXml implements Preservation {
    private XStream xStream;

    public IOXml() {
        this.xStream = new XStream(new DomDriver());
    }

    @Override
    public void save(String path, ArrayList<Trip> object) {
        try (DataOutputStream dataOutputStream = new DataOutputStream(new FileOutputStream(path))) {
            dataOutputStream.writeChars(xStream.toXML(object));
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public ArrayList<Trip> load(String path) {
        ArrayList<Trip> result = new ArrayList<>();

        try {
            String content = Files.readString(Paths.get(path), StandardCharsets.UTF_16);
            result = (ArrayList<Trip>) xStream.fromXML(content);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

        return result;
    }
}
