package FICHEROS_XML;

import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class LeerZoo100Kg {
    private static final String RUTA_FICHERO = "src/FICHEROS/";
    private static final String NOMBRE_FICHERO = "Zoo.xml";

    public static void main(String[] args) {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            //ignorar comentarios y espacios en blanco
            factory.setIgnoringComments(true);
            factory.setIgnoringElementContentWhitespace(true);
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document documento = builder.parse(new File(RUTA_FICHERO + NOMBRE_FICHERO));

            // coge los elementos <felino> y los mete en una lista llamada listaFelinos. importante poner felino la f en minúscula
            NodeList listaFelinos = documento.getElementsByTagName("felino");
            System.out.println("Animales que pesan más de 100kg:");

            // bucle para leer el documento xml (desde el elemento felino)
            for (int i = 0; i < listaFelinos.getLength(); i++) {
                Element felino = (Element) listaFelinos.item(i);
                String nombre = felino.getElementsByTagName("nombre").item(0).getTextContent();

                // cojo el peso actual del animal y lo paso a double para el if
                double peso = Double.parseDouble(felino.getElementsByTagName("peso").item(0).getTextContent());

                if (peso > 100) {
                    System.out.println(nombre + ": " + peso + " kg");
                }
            }
        } catch (Exception e) {
            System.out.println("Error al leer el documento XML");
        }
    }
}