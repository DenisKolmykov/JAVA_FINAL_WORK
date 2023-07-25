import java.util.Scanner;

public class MenuView {
    private Scanner scanner;
    
    public MenuView() {
        this.scanner = new Scanner(System.in);
    }

    // главное меню
    public String mainMenu(){
        System.out.println("ГЛАВНОЕ МЕНЮ:");
        System.out.println("--------------------");
        System.out.println("1. В магазин");
        System.out.println("2. Розыгрыш");
        
        System.out.println("0. Выход");
        System.out.print("--> ");
        String choice = scanner.nextLine();
        return choice;
    }
    
    // подменю "МАГАЗИН"
    public String subMenuShop () {
        System.out.println("меню МАГАЗИН:");
        System.out.println("--------------------");
        System.out.println("1. Добавить новую игрушку");
        System.out.println("2. Редактировать данные об игрушке");
        System.out.println("3. Показать список игрушек");
        
        System.out.println("0. Выход в ГЛАВНОЕ МЕНЮ");
        System.out.print("--> ");
        String choice = scanner.nextLine();
        return choice;
    }

    // подменю "РОЗЫГРЫШ"
    public String subMenuPrizeDraws() {
        System.out.println("меню РОЗЫГРЫШ:");
        System.out.println("--------------------");
        System.out.println("1. НАЧАТЬ РОЗЫГРЫШ призовой игрушки");
        System.out.println("2. СПИСОК ПРИЗОВЫХ игрушек к выдаче");
        System.out.println("3. ВЫДАТЬ призовую игрушку");
        System.out.println("4. СПИСОК ВЫДАННЫХ игрушек");
        
        System.out.println("0. Выход в ГЛАВНОЕ МЕНЮ");
        System.out.print("--> ");
        String choice = scanner.nextLine();
        return choice;
    }

     // метод ввода наименования игрушки
     public String inputToyName() {
        System.out.print("Введите наименование игрушки: ");
        return scanner.nextLine();
    }
    
    // метод ввода параметров (количества, веса или ID) игрушек
    public Integer inputToyParam(String param) { // param1="количество, шт", param2="вес, %"
        Integer intParam = 0;
        boolean flag = true;
        while(flag) {
            System.out.printf("Введите %s: ", param);
            String inputParam = scanner.nextLine();
            try {
                intParam = Integer.parseInt(inputParam);
                flag = false;
            } 
            catch (NumberFormatException | NullPointerException nfe) {
                System.out.println("Вы ввели не число! повторите ввод");
            }
        }
        return intParam;
    }

    // меню редактирования параметров игрушки
    public String editParam (Integer toyId) {
        System.out.println("Выберете параметр для РЕДАКТИРОВАНИЯ:");
        System.out.println("--------------------");
        System.out.println("1. Редактировать ВЕС");
        System.out.println("2. Редактировать НАИМЕНОВАНИЕ");
        System.out.println("3. Редактировать КОЛИЧЕСТВО");
        
        System.out.println("0. Выход в ГЛАВНОЕ МЕНЮ");
        System.out.print("--> ");
        String choice = scanner.nextLine();
        return choice;
    }

    // меню для формирования уcловий розыгрыша
    public Integer prizeConditions () {
        System.out.println("меню УСЛОВИЯ РОЗЫГРЫША\n");
        System.out.println("--------------------");

        
        //System.out.println("0. Выход в ГЛАВНОЕ МЕНЮ");
        //System.out.print("--> ");

        Integer prizeCount = 0;
        boolean flag = true;
        while(flag) {
            System.out.print("Укажите количество игрушек для розыгрыша: ");
            String strPrizeCount = scanner.nextLine();
            try {
                prizeCount = Integer.parseInt(strPrizeCount);
                flag = false;
            } 
            catch (NumberFormatException | NullPointerException nfe) {
                System.out.println("Вы ввели не число! повторите ввод");
            }
        }
        return prizeCount;
    }

}
