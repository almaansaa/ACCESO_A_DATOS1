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

public class ActualizarBolsa {
    private static final String RUTA_FICHERO = "src/FICHEROS/";
    private static final String FICHERO_BOLSA = "Bolsa.xml";
    private static final String FICHERO_DATOS = "datos.xml";
    private static final String FICHERO_SALIDA = "bolsaactualizada.xml";

    public static void main(String[] args) {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            factory.setIgnoringComments(true);
            factory.setIgnoringElementContentWhitespace(true);
            DocumentBuilder builder = factory.newDocumentBuilder();

            Document docBolsa = builder.parse(new File(RUTA_FICHERO + FICHERO_BOLSA));
            Document docDatos = builder.parse(new File(RUTA_FICHERO + FICHERO_DATOS));

            Element raizBolsa = docBolsa.getDocumentElement();
            NodeList operaciones = docDatos.getElementsByTagName("Operacion");

            for (int i = 0; i < operaciones.getLength(); i++) {
                Element opElem = (Element) operaciones.item(i);
                String id = opElem.getAttribute("id");

                if (id.equals("A")) {
                    String indice = opElem.getElementsByTagName("Indice").item(0).getTextContent();
                    String nombre = opElem.getElementsByTagName("Nombre").item(0).getTextContent();
                    String simbolo = opElem.getElementsByTagName("Simbolo").item(0).getTextContent();
                    String precio = opElem.getElementsByTagName("Precio").item(0).getTextContent();

                    // comprobar si la empresa ya existe o no
                    NodeList empresas = docBolsa.getElementsByTagName("Empresa");
                    boolean existe = false;
                    for (int j = 0; j < empresas.getLength(); j++) {
                        Element emp = (Element) empresas.item(j);
                        String nombreEmp = emp.getElementsByTagName("Nombre").item(0).getTextContent();
                        if (nombreEmp.equalsIgnoreCase(nombre)) {
                            existe = true;
                            break;
                        }
                    }

                    if (existe) {
                        System.out.println("La empresa " + nombre + " ya existe");
                    } else {
                        // se añaden las empresas dadas de alta al final, construyendolas de esta manera:

                        Element nuevaEmpresa = docBolsa.createElement("Empresa");
                        nuevaEmpresa.setAttribute("indice", indice);

                        Element elNombre = docBolsa.createElement("Nombre");
                        elNombre.setTextContent(nombre);
                        nuevaEmpresa.appendChild(elNombre);

                        Element elSimbolo = docBolsa.createElement("Simbolo");
                        elSimbolo.setTextContent(simbolo);
                        nuevaEmpresa.appendChild(elSimbolo);

                        Element elPrecio = docBolsa.createElement("Precio");
                        elPrecio.setTextContent(precio);
                        nuevaEmpresa.appendChild(elPrecio);

                        raizBolsa.appendChild(nuevaEmpresa);
                        System.out.println("Dado de alta: " + nombre);
                    }

                } else if (id.equals("B")) {
                    String nombreBaja = opElem.getElementsByTagName("Nombre").item(0).getTextContent();
                    NodeList empresas = docBolsa.getElementsByTagName("Empresa");
                    boolean encontrada = false;

                    for (int j = 0; j < empresas.getLength(); j++) {
                        Element emp = (Element) empresas.item(j);
                        String nombreEmp = emp.getElementsByTagName("Nombre").item(0).getTextContent();

                        if (nombreEmp.equalsIgnoreCase(nombreBaja)) {
                            // elimina todo lo que hay dentro de la etiqueta <Empresa> de aquella la cual su nombre es igual al dado para eliminar
                            emp.getParentNode().removeChild(emp);
                            encontrada = true;
                            System.out.println("Baja hecha: " + nombreBaja);
                            break;
                        }
                    }

                    if (!encontrada) { // si no hay una empresa con ese nombre, lo detecta
                        System.out.println("La empresa " + nombreBaja + " no existe");
                    }

                } else if (id.equals("M")) {
                    String nombreMod = opElem.getElementsByTagName("Nombre").item(0).getTextContent();
                    String nuevoPrecio = opElem.getElementsByTagName("Precio").item(0).getTextContent();
                    NodeList empresas = docBolsa.getElementsByTagName("Empresa");
                    boolean encontrada = false;

                    for (int j = 0; j < empresas.getLength(); j++) {
                        Element emp = (Element) empresas.item(j);
                        String nombreEmp = emp.getElementsByTagName("Nombre").item(0).getTextContent();

                        if (nombreEmp.equalsIgnoreCase(nombreMod)) {
                            Element precioElem = (Element) emp.getElementsByTagName("Precio").item(0);
                            precioElem.setTextContent(nuevoPrecio); // lo cambia al nuevo precio
                            encontrada = true;
                            System.out.println("Modificación hecha: " + nombreMod);
                            break;
                        }
                    }

                    if (!encontrada) { // si no detecta la empresa por su nombre, dice que no existe
                        System.out.println("La empresa " + nombreMod + " no existe");
                    }
                }
            }

            // guarda en bolsaactualizada.xml
            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            transformer.transform(new DOMSource(docBolsa), new StreamResult(new File(RUTA_FICHERO + FICHERO_SALIDA)));

        } catch (Exception e) {
            System.out.println("Error al procesar el fichero.");
        }
    }
}