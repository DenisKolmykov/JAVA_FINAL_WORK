import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class ToysList {
    private ArrayList<Toy> toys;

    public void setToys(ArrayList<Toy> toys) {
        this.toys = toys;
    }

    public ArrayList<Toy> getToys() {
        return this.toys;
    }

    public void inputNewToyToList (Toy newToy){
        this.toys.add(newToy);
    }

    public Toy [] toysToArr() {
        Toy [] toysListToArr = new Toy [this.toys.size()];
        int i = 0;
        for (Toy toy : this.toys) {
            toysListToArr[i] = toy;
            i++;
        }
        return toysListToArr;
    }

    public void printToys() {

        System.out.println("СПИСОК:");
        for (Toy toy : this.toys) {
            toy.printToy();
        }
        System.out.println("-------конец списка--------\n");
    }

    // метод записи в файл СПИСКА ЦЕЛИКОМ (перезапись файла)
    public void listToFile (String fileName) throws Exception{ 
        FileWriter writer = new FileWriter(fileName);
        // Записываем список объектов в файл
        for (Toy toy : this.toys) {
            writer.write(toy.toString());
            writer.flush();
        }
        writer.close();
    }

    // метод ДОЗАПИСИ в файл строки в конец файла)
    public void toyAddToFile (String fileName, Toy newToy) throws Exception{ 
        FileWriter writer = new FileWriter(fileName, true);
        writer.write(newToy.toString());   
        writer.close();
    }

    // получение списка из файла
    public void getListFromFile(String fileName){
        // Открываем файл для чтения
        File file = new File(fileName);
        // Создаем список для хранения объектов Toy
        ArrayList<Toy> toysLis = new ArrayList<>();
        // Читаем содержимое файла построчно
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            // Проходим по строкам файла
            String line;
            while ((line = reader.readLine()) != null) {
                // Разделяем строку на массив по разделителю “;”
                String[] data = line.split(";");
                // Создаем объект Toy и добавляем его в список
                Toy toy = new Toy();
                toy.setToyID(Integer.parseInt(data[0]));
                toy.setToyName(data[1]);
                toy.setToyImportance(Integer.parseInt(data[2]));
                toy.setToyQuantity(Integer.parseInt(data[3]));
                toysLis.add(toy);
            }
            this.toys = toysLis;
        } catch (IOException e) {
            e.printStackTrace();
        }
        
    }

    // удаление Toy из списка toys
    public void deliteToyFromToys(Toy deleteToy){
        this.toys.remove(deleteToy);
    }
}

