package org.example.CSVJSONXML;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.bean.ColumnPositionMappingStrategy;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.exceptions.CsvValidationException;
import org.w3c.dom.*;
import org.xml.sax.SAXException;


import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.awt.*;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.List;


public class Main {
    public static void main(String[] args) throws IOException, ParserConfigurationException, TransformerException, SAXException {
        String fileName = "data.csv";
        String[] columnMapping = {"id", "firstName", "lastName", "country", "age"};

        // Создаем запись
        String[] employee1 = "1,John,Smith,USA,25".split(",");
        String[] employee2 = "2,Ivan,Petrov,RU,23".split(",");
// Создаем экземпляр CSVWriter
        try (CSVWriter writer = new CSVWriter(new FileWriter(fileName))) {
// Записываем запись в файл
            writer.writeNext(employee1);
            writer.writeNext(employee2);
        } catch (IOException e) {
            e.printStackTrace();
        }
        List<Employee> list = parseCSV(columnMapping, fileName);
        System.out.println(list);
        String json = listToJson(list);
        String nameJSON1 = "data.json";
        String nameJSON2 = "data2.json";
        writeString(json, nameJSON1);

        createXML();
        List<Employee> listFromXML = parseXML("data.xml");
        String jsonFromXML = listToJson(listFromXML);
        writeString(jsonFromXML, nameJSON2);
    }

    private static List<Employee> parseXML(String nameXML) throws ParserConfigurationException, IOException, SAXException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document doc = builder.parse(new File(nameXML));
        Node root = doc.getDocumentElement();
        System.out.println("Корневой элемент: " + root.getNodeName());
        List <Employee> employeeList = read(root);
        return null;
    }
        private static List <Employee> read (Node node){
            List <Employee> employeeList = null;
            NodeList nodeList = node.getChildNodes();
            for (int i = 0; i < nodeList.getLength(); i++) {
                Node node_ = nodeList.item(i);
                if (Node.ELEMENT_NODE == node_.getNodeType()) {
                    System.out.println("Текущий узел: " + node_.getNodeName());
                    Element employee = (Element) node_;
                    NamedNodeMap map = employee.getAttributes();
                    for (int a = 0; a < map.getLength(); a++) {
                        String attrName = map.item(a).getNodeName();
                        String attrValue = map.item(a).getNodeValue();
                        System.out.println("Аттрибут: " + attrName + "; значение: " + attrValue);
                    }
                    employee.getElementsByTagName("lastName").item(0).getTextContent();
                    read(node_);
                }
            }
            return employeeList;
        }

        private static void createXML () throws ParserConfigurationException, TransformerException {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.newDocument();

            Element staff = document.createElement("staff");
            document.appendChild(staff);
            Element employee1 = document.createElement("employee");
            staff.appendChild(employee1);
            Element id1 = document.createElement("id");
            employee1.appendChild(id1);
            id1.appendChild(document.createTextNode("1"));
            Element firstName1 = document.createElement("firstName");
            employee1.appendChild(firstName1);
            firstName1.appendChild(document.createTextNode("John"));
            Element lastName1 = document.createElement("lastName");
            employee1.appendChild(lastName1);
            lastName1.appendChild(document.createTextNode("Smith"));
            Element country1 = document.createElement("country");
            employee1.appendChild(country1);
            country1.appendChild(document.createTextNode("USA"));
            Element age1 = document.createElement("age");
            employee1.appendChild(age1);
            age1.appendChild(document.createTextNode("25"));

            Element employee2 = document.createElement("employee");
            staff.appendChild(employee2);
            Element id2 = document.createElement("id");
            employee2.appendChild(id2);
            id2.appendChild(document.createTextNode("2"));
            Element firstName2 = document.createElement("firstName");
            employee2.appendChild(firstName2);
            firstName2.appendChild(document.createTextNode("Ivan"));
            Element lastName2 = document.createElement("lastName");
            employee2.appendChild(lastName2);
            lastName2.appendChild(document.createTextNode("Petrov"));
            Element country2 = document.createElement("country");
            employee2.appendChild(country2);
            country2.appendChild(document.createTextNode("RU"));
            Element age2 = document.createElement("age");
            employee2.appendChild(age2);
            age2.appendChild(document.createTextNode("23"));

            DOMSource domSource = new DOMSource(document);
            StreamResult streamResult = new StreamResult(new File("data.xml"));
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.transform(domSource, streamResult);
        }

        private static void writeString (String json, String nameOfFile){
            try (FileWriter file = new FileWriter(nameOfFile)) {
                file.write(json);
                file.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        private static String listToJson (List < Employee > list) {
            GsonBuilder builder = new GsonBuilder();
            Gson gson = builder.create();
            Type listType = new TypeToken<List<Employee>>() {
            }.getType();
            String json = gson.toJson(list, listType);
            return json;
        }

        private static List<Employee> parseCSV (String[]columnMapping, String fileName){
            List<Employee> data = null;
            try (CSVReader reader = new CSVReader(new FileReader(fileName))) {
                ColumnPositionMappingStrategy<Employee> strategy = new ColumnPositionMappingStrategy<>();
                strategy.setType(Employee.class);
                strategy.setColumnMapping(columnMapping);
                CsvToBean<Employee> csv = new CsvToBeanBuilder<Employee>(reader).withMappingStrategy(strategy).build();
                data = csv.parse();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return data;
        }

    }
