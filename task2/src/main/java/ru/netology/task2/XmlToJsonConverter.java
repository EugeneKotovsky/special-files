package ru.netology.task2;

import org.w3c.dom.*;
import ru.netology.task1.Employee;
import ru.netology.task1.JsonHelper;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class XmlToJsonConverter {
    public static void main(String[] args) {
        String xmlFile = "data.xml";
        String jsonFile = "data2.json";

        List<Employee> list = parseXML(xmlFile);
        String json = JsonHelper.listToJson(list);
        JsonHelper.writeString(json, jsonFile);
    }

    private static List<Employee> parseXML(String fileName) {
        List<Employee> employees = new ArrayList<>();
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(new File(fileName));

            Element root = doc.getDocumentElement();
            NodeList nodeList = root.getChildNodes();

            for (int i = 0; i < nodeList.getLength(); i++) {
                Node node_ = nodeList.item(i);
                if (node_.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) node_;
                    Employee emp = new Employee();

                    emp.setId(Long.parseLong(getTagValue(element, "id")));
                    emp.setFirstName(getTagValue(element, "firstName"));
                    emp.setLastName(getTagValue(element, "lastName"));
                    emp.setCountry(getTagValue(element, "country"));
                    emp.setAge(Integer.parseInt(getTagValue(element, "age")));
                    employees.add(emp);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return employees;
    }

    private static String getTagValue(Element element, String tagName) {
        NodeList nodeList = element.getElementsByTagName(tagName);
        if (nodeList.getLength() > 0) {
            return nodeList.item(0).getTextContent();
        }
        return "";
    }
}