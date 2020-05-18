package pl.komponentowe.data;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Set;

public class IOXml implements Preservation{
    private XStream xStream;

    public IOXml() {
        this.xStream = new XStream(new DomDriver());
    }

    @Override
    public void save(String path, Object object) {
        try (DataOutputStream dataOutputStream = new DataOutputStream(new FileOutputStream(path))) {
            dataOutputStream.writeChars(xStream.toXML((Settings) object));
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public Object load(String path) {
        Settings result = new Settings();

        try {
            String content = Files.readString(Paths.get(path), StandardCharsets.UTF_16);
            result = (Settings) xStream.fromXML(content);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

        return result;
    }
}
