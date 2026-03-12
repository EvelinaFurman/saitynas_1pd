package lt.viko.eif.efurmanova.spring.app.infra.xml;

public interface XmlWriter<T> {
    String transformToXml(T Object);
}
