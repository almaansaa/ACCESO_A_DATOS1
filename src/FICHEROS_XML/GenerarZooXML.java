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

public class GenerarZooXML {
    private static final String RUTA_FICHERO = "src/FICHEROS/";
    private static final String NOMBRE_FICHERO = "Zoo.xml";

    public static void main(String[] args) {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document documento = builder.newDocument();

            // crea la raiz <zoo>
            Element zoo = documento.createElement("zoo");
            documento.appendChild(zoo);

            // crea el elemento <felinos>
            Element felinos = documento.createElement("felinos");
            zoo.appendChild(felinos);

            // arrays con nombres, continentes y pesos de cada uno
            String[] nombres = {"Leon", "Tigre", "Lince", "PanteraTigris"};
            String[] continentes = {"Africa", "Asia", "Europa", "America"};
            String[] pesos = {"155", "215", "20", "95"};

            for (int i = 0; i < nombres.length; i++) {
                Element felino = documento.createElement("felino");
                felinos.appendChild(felino);

                Element nombre = documento.createElement("nombre");
                // setTextContext es para añadir texto dentro del elemento
                nombre.setTextContent(nombres[i]);
                felino.appendChild(nombre);

                Element continente = documento.createElement("continente");
                continente.setTextContent(continentes[i]);
                felino.appendChild(continente);

                Element peso = documento.createElement("peso");
                peso.setTextContent(pesos[i]);
                felino.appendChild(peso);
            }

            // guarda el XML
            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            transformer.transform(new DOMSource(documento), new StreamResult(new File(RUTA_FICHERO + NOMBRE_FICHERO)));

        } catch (Exception e) {
            System.out.println("Error al crear el documento XML");
        }
    }
}