package FICHEROS_XML;

import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class CrearZoo2 {
    private static final String RUTA_FICHERO = "src/FICHEROS/";
    private static final String FICHERO_ORIGEN = "Zoo.xml";
    private static final String FICHERO_DESTINO = "Zoo2.xml";

    public static void main(String[] args) {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            factory.setIgnoringComments(true);
            factory.setIgnoringElementContentWhitespace(true);
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document documento = builder.parse(new File(RUTA_FICHERO + FICHERO_ORIGEN));

            NodeList listaFelinos = documento.getElementsByTagName("felino");

            for (int i = 0; i < listaFelinos.getLength(); i++) {
                Element felino = (Element) listaFelinos.item(i);
                Element pesoElem = (Element) felino.getElementsByTagName("peso").item(0);

                // cojo el peso actual del animal y lo paso a double para luego restarle 5
                double pesoActual = Double.parseDouble(pesoElem.getTextContent());
                double nuevoPeso = pesoActual - 5;

                // vuelvo a pasarlo a string para añadirlo al XML
                pesoElem.setTextContent(String.valueOf((int) nuevoPeso));
            }

            // guarda en Zoo2.xml
            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            transformer.transform(new DOMSource(documento), new StreamResult(new File(RUTA_FICHERO + FICHERO_DESTINO)));

            // leer Zoo2.xml
            for (int i = 0; i < listaFelinos.getLength(); i++) {
                Element felino = (Element) listaFelinos.item(i);

                // mete en la variable "nombre" el contenido del primer elemento llamado <nombre> (item index 0 es el primer elemento con ese nombre)
                String nombre = felino.getElementsByTagName("nombre").item(0).getTextContent();
                // lo mismo con el peso
                String peso = felino.getElementsByTagName("peso").item(0).getTextContent();

                System.out.println(nombre + ": " + peso + " kg"); // muestra las variables
            }

        } catch (Exception e) {
            System.out.println("Error al procesar el documento XML");
        }
    }
}
