package lt.viko.eif.efurmanova.spring.app.infra.xml;

public interface XmlReader<T> {
    T transformToPojo(String xml);

}