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

public class GestionAlumnosDOM {
    private static final String RUTA_FICHERO = "src/FICHEROS/";
    private static final String FICHERO_ORIGEN = "alumnos.xml";
    private static final String FICHERO_SALIDA = "alumnos_medias.xml";

    public static void main(String[] args) {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            factory.setIgnoringComments(true);
            factory.setIgnoringElementContentWhitespace(true);
            DocumentBuilder builder = factory.newDocumentBuilder();

            Document documento = builder.parse(new File(RUTA_FICHERO + FICHERO_ORIGEN));

            Document docSalida = builder.newDocument();
            Element raizSalida = docSalida.createElement("resultados");
            docSalida.appendChild(raizSalida);

            NodeList alumnos = documento.getElementsByTagName("alumno");
            double sumaMediasClase = 0;
            int totalAlumnos = alumnos.getLength();

            for (int i = 0; i < totalAlumnos; i++) {
                Element alumno = (Element) alumnos.item(i);
                String dni = alumno.getAttribute("dni"); // dni es un atributo en el xml

                // definir las variables pasando a double
                double practica1 = Double.parseDouble(alumno.getElementsByTagName("practica1").item(0).getTextContent());
                double practica2 = Double.parseDouble(alumno.getElementsByTagName("practica2").item(0).getTextContent());
                double examen = Double.parseDouble(alumno.getElementsByTagName("examen").item(0).getTextContent());
                double practicaFinal = Double.parseDouble(alumno.getElementsByTagName("final").item(0).getTextContent());

                // media: 30% practicas (media de practica1 y practica2), 40% examen, 30% practica final
                double notaPracticas = (practica1 + practica2) / 2.0;
                double notaMedia = (notaPracticas * 0.30) + (examen * 0.40) + (practicaFinal * 0.30);
                sumaMediasClase += notaMedia; //voy sumando la nota media una y otra vez a la variable de la suma total de toda la clase

                System.out.println(dni + " = " + notaMedia);

                // contenido del nuevo xml: alumnos_medias.xml
                Element elAlumno = docSalida.createElement("alumno");
                Element elDni = docSalida.createElement("dni");
                elDni.setTextContent(dni);
                elAlumno.appendChild(elDni);

                Element elNota = docSalida.createElement("nota_media");
                elNota.setTextContent(String.valueOf(notaMedia));
                elAlumno.appendChild(elNota);

                raizSalida.appendChild(elAlumno);
            }

            double mediaTotalClase = (sumaMediasClase / totalAlumnos);
            System.out.println("Media total del módulo Acceso a datos=" + mediaTotalClase);

            // guarda en alumnos_medias.xml
            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            transformer.transform(new DOMSource(docSalida), new StreamResult(new File(RUTA_FICHERO + FICHERO_SALIDA)));

        } catch (Exception e) {
            System.out.println("Error al procesar el documento XML");
        }
    }
}