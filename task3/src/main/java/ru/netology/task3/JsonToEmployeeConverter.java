package ru.netology.task3;

import ru.netology.task1.Employee;

import com.google.gson.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class JsonToEmployeeConverter {
    public static void main(String[] args) {
        String fileName = "data2.json";
        String json = readString(fileName);
        List<Employee> list = jsonToList(json);
        for (Employee emp : list) {
            System.out.println(emp);
        }
    }

    private static String readString(String fileName) {
        StringBuilder sb = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }

    private static List<Employee> jsonToList(String json) {
        List<Employee> list = new ArrayList<>();
        JsonElement root = new JsonParser().parse(json);
        if (root.isJsonArray()) {
            JsonArray array = root.getAsJsonArray();
            Gson gson = new GsonBuilder().create();
            for (JsonElement elem : array) {
                Employee emp = gson.fromJson(elem, Employee.class);
                list.add(emp);
            }
        }
        return list;
    }
}