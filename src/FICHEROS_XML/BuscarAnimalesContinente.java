package FICHEROS_XML;

import java.io.File;
import java.util.Scanner;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class BuscarAnimalesContinente {
    private static final String RUTA_FICHERO = "src/FICHEROS/";
    private static final String NOMBRE_FICHERO = "Zoo.xml";

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Introduce el continente que quieres buscar: ");
        String continenteBuscado = sc.nextLine();

        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            factory.setIgnoringComments(true);
            factory.setIgnoringElementContentWhitespace(true);
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document documento = builder.parse(new File(RUTA_FICHERO + NOMBRE_FICHERO));

            NodeList listaFelinos = documento.getElementsByTagName("felino");

            System.out.println("Felinos en " + continenteBuscado + ":");
            for (int i = 0; i < listaFelinos.getLength(); i++) {
                Element felino = (Element) listaFelinos.item(i);
                String continente = felino.getElementsByTagName("continente").item(0).getTextContent();

                if (continente.equalsIgnoreCase(continenteBuscado)) {
                    String nombre = felino.getElementsByTagName("nombre").item(0).getTextContent();
                    String peso = felino.getElementsByTagName("peso").item(0).getTextContent();

                    System.out.println(nombre + ": " + peso + " kg");
                }
            }

        } catch (Exception e) {
            System.out.println("Error al leer el documento XML");
        }
        sc.close();
    }
}