//package jMDIForm;
////import gson;
//
//import com.google.gson.Gson;
//import java.io.FileNotFoundException;
//import java.io.FileReader;
//import java.io.FileWriter;
//import java.io.IOException;
//import java.io.PrintWriter;
//import java.util.Arrays;
//import java.util.Iterator;
//import java.util.List;
//import org.json.simple.JSONArray;
//import org.json.simple.JSONObject;
//import org.json.simple.JSONValue;
//
//import org.json.simple.parser.*;
//public class JSONoperations {//пока что только примеры того, как должны выглядеть
//    private static final String filePath = "C:\\Users\\katerina\\Desktop\\jsonTestFile.json";
//     
//public void jsonreader(){
// //1
//    try {
//       FileReader reader = new FileReader(filePath);
// 
//            JSONParser jsonParser = new JSONParser();
//            JSONObject jsonObject = (JSONObject) jsonParser.parse(reader);
// 
//            // получение строки из объекта
//            String firstName = (String) jsonObject.get("firstname");
//            System.out.println("The first name is: " + firstName);
// 
//            // получение номера из объекта
//            long id =  (long) jsonObject.get("id");
//            System.out.println("The id is: " + id);
// 
//            // получение массива
//            JSONArray lang= (JSONArray) jsonObject.get("languages");
//             
//            // берем элементы массива
//            for(int i=0; i<lang.size(); i++){
//                System.out.println("The " + i + " element of the array: "+lang.get(i));
//            }
//            Iterator i = lang.iterator();
// 
//            // берем каждое значение из массива json отдельно
//            while (i.hasNext()) {
//                JSONObject innerObj = (JSONObject) i.next();
//                System.out.println("language "+ innerObj.get("lang") +
//                        " with level " + innerObj.get("knowledge"));
//            }
//            // обрабатываем структуру в объекте
//            JSONObject structure = (JSONObject) jsonObject.get("job");
//            System.out.println("Into job structure, name: " + structure.get("name"));
// 
//        } catch (FileNotFoundException ex) {
//            ex.printStackTrace();
//        } catch (IOException ex) {
//            ex.printStackTrace();
//        } catch (ParseException ex) {
//            ex.printStackTrace();
//        } catch (NullPointerException ex) {
//            ex.printStackTrace();
//        }
//   
//   
//   ///2
//   try {
//            // конвертируем строку с Json в JSONObject для дальнейшего его парсинга
//            JSONObject weatherJsonObject = (JSONObject) JSONValue.parseWithException(resultJson);
// 
//            // получаем название города, для которого смотрим погоду
//            System.out.println("Название города: " + weatherJsonObject.get("name"));
// 
//            // получаем массив элементов для поля weather
//            /* ... "weather": [
//            {
//                "id": 500,
//                    "main": "Rain",
//                    "description": "light rain",
//                    "icon": "10d"
//            }
//            ], ... */
//            JSONArray weatherArray = (JSONArray) weatherJsonObject.get("weather");
//            // достаем из массива первый элемент
//            JSONObject weatherData = (JSONObject) weatherArray.get(0);
// 
//            // печатаем текущую погоду в консоль
//            System.out.println("Погода на данный момент: " + weatherData.get("main"));
//            // и описание к ней
//            System.out.println("Более детальное описание погоды: " + weatherData.get("description"));
// 
//        } catch (org.json.simple.parser.ParseException e) {
//            e.printStackTrace();
//        }
//}   
//public void jsonsaver(){
//    //1
////    Object o = new JSONParser().parse(new FileReader(File.json));
////
////                        JSONObject j = (JSONObject) o;
////
////                        String Name = (String) j.get("Name");
////
////                        String College = (String ) j.get("College");
////                        System.out.println("Name :" + Name);
////
////                        System.out.println("College :" +College);
////2
//Company companies = new Company("Google", 140000,
//            Arrays.asList("Mountain View", "Los Angeles", "New York"));
// 
//        String path = "/app/json/companies.json";
// 
//        try (PrintWriter out = new PrintWriter(new FileWriter(path))) {
//            Gson gson = new Gson();
//            String jsonString = gson.toJson(companies);
//            out.write(jsonString);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//}
//}
