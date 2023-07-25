import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Random;

public class PrizeDraws {
    private ToysList toys;
    private Integer prizeCount;

    public PrizeDraws(ToysList toys, Integer prizeCount) {
        this.toys = toys;
        this.prizeCount = prizeCount;
    }

    public void setPrizeToys(ToysList toys){
        this.toys = toys;
    }

    public ToysList getPrizeToys() {
        return this.toys;
    }

    public void setPrizeCount(Integer prizeCount){
        this.prizeCount = prizeCount;
    }

    public Integer getPrizeCount() {
        return this.prizeCount;
    }

    public ToysList choosePrizeToys() throws Exception{
        ToysList oldPrizeQueue = new ToysList();
        String fileName = "prize.txt";
        oldPrizeQueue.getListFromFile(fileName);// выгружаем уже имеющуюс очередь игрушек
        Toy [] toyArr = this.toys.toysToArr(); 
        int len = toyArr.length;
        int chance [] = new int [len];
        int count = 0;
        
        Deque <Toy> prizeListQuene = new LinkedList<>(oldPrizeQueue.getToys());
        for (int i = 0; i < len; i++){
            int importance = toyArr[i].getToyImportance();
            count = importance + count; // Считаем количество элементов воображаемого массива
            chance [i] = importance;
        }

        Random random = new Random();
        for (int randomNumsCount = 0; randomNumsCount < this.prizeCount; randomNumsCount++) {
            int index = random.nextInt(count); // Выбираем случайный индекс из воображаемого массива
            for (int i = 0; i < len; i++) { // Ищем элемент, которому принадлежит этот индекс
                if (toyArr[i].getToyQuantity() > 0){
                    index -= chance[i];
                    if(index < 0) {
                        prizeListQuene.push(toyArr[i]);
                        toyArr[i].setToyQuantity(toyArr[i].getToyQuantity() - 1);
                        break;
                    }
                }
            }
        }
        this.toys.printToys();
        this.toys.listToFile("toys.txt");
        ArrayList<Toy> prizeArrList = new ArrayList<>(prizeListQuene);
        ToysList prizeList = new ToysList();
        prizeList.setToys(prizeArrList);
        return prizeList;
    }

    public Toy giveoutToy () throws Exception{
        String prizeFileName = "prize.txt";
        Deque<Toy> giveoutQueue = new LinkedList<>(this.toys.getToys());
        Toy giveoutOneToy = giveoutQueue.pop();
        ArrayList<Toy> giveoutArrList = new ArrayList<>(giveoutQueue);
        this.toys.setToys(giveoutArrList);
        //this.toys.getToys().remove(this.toys.getToys().size() - 1);
        this.toys.listToFile(prizeFileName);
        return giveoutOneToy;
    }
}

