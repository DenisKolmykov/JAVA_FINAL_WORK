
public class MenuWork {
    private MenuView view;

    public MenuWork(MenuView view) {
        this.view = view;
    }

    public void mainMenuPresenter () throws Exception {
        boolean flag;
        System.out.print("\033[H\033[2J");
        System.out.flush();

        do {
            flag = true;
            String choice = view.mainMenu();
            switch (choice) {
                case "0":
                    System.out.println ("!выполнение программы завершено!");
                    flag = false;
                break;

                case "1":
                    // В магазин
                    subMenuShopPresenter ();
                    break;
                
                case "2":
                    // Розыгрыш
                    subMenuPrizeDrawsPresenter ();
                    break;

                default:
                System.out.println("! Не корректное значение !\n! Повторите ввод !");
            }
        } while (flag);
    }

    private void subMenuShopPresenter () throws Exception {
        System.out.print("\033[H\033[2J");
        System.out.flush();
        
        boolean flag;
        do {
            flag = true;
            String choice = view.subMenuShop();
            ToysList toys = new ToysList(); // список всех игрушек магазина
            String fileName = "toys.txt";
            toys.getListFromFile(fileName); // выгружаем из файла

            switch (choice) {
                case "0":
                    flag = false;
                    System.out.print("\033[H\033[2J");
                    System.out.flush();
                    break;

                case "1":
                    // Добавить новую игрушку
                    System.out.print("\033[H\033[2J");
                    System.out.flush();

                    System.out.println("ДОБАВЛЕНИЕ НОВОЙ ИГРУШКИ:");
                    System.out.println("--------------------");
                    Toy newToy = new Toy();
                    newToy.setToyName(view.inputToyName());
                    newToy.setToyQuantity(view.inputToyParam("КОЛИЧЕСТВО"));
                    newToy.setToyImportance(view.inputToyParam("ВЕС"));
                    toys.toyAddToFile(fileName, newToy); //  дозапись в файл (в конец)
                    System.out.printf("! Игрушка с ID#%d успешно добавлена !\n\n", newToy.getToyID());
                    break;
                
                case "2":
                    // Редактировать данные об игрушке
                    subSubMenuEdit(toys);
                    break;
                
                case "3":
                    // Показать список игрушек
                    System.out.print("\033[H\033[2J");
                    System.out.flush();

                    toys.printToys();
                    break;
                    
                default:
                System.out.println("! Не корректное значение !\n! Повторите ввод !");
            }
        } while (flag);
    }

    private void subMenuPrizeDrawsPresenter () throws Exception {
        System.out.print("\033[H\033[2J");
        System.out.flush();

        boolean flag;
        do {
            flag = true;
            String choice = view.subMenuPrizeDraws();
            ToysList toys = new ToysList(); // список всех игрушек магазина
            String fileName = "toys.txt";
            String prizeFileName = "prize.txt";
            String giveoutFileName = "giveout.txt";

            toys.getListFromFile(fileName); // выгружаем из файла

            switch (choice) {
                case "0":
                    flag = false;
                    System.out.print("\033[H\033[2J");
                    System.out.flush();
                    break;

                case "1":
                    // НАЧАТЬ РОЗЫГРЫШ призовой игрушки 
                    System.out.print("\033[H\033[2J");
                    System.out.flush();
                    System.out.println("!!!ИДЕТ РОЗЫГРЫШ!!!");              
                    Integer prizeCount = view.prizeConditions (); //задаем количество игрушек для призов
                    PrizeDraws newPrizeList = new PrizeDraws(toys, prizeCount);
                    
                    ToysList prizeListQuene = newPrizeList.choosePrizeToys();
                    prizeListQuene.listToFile(prizeFileName); // запись в файл очереди призовых игрушек
                    prizeListQuene.printToys();
                    System.out.println("----розыгрыш ЗАВЕРШЕН-----");
                    break;
                
                case "2":
                    // СПИСОК ПРИЗОВЫХ игрушек к выдаче
                    System.out.print("\033[H\033[2J");
                    System.out.flush();

                    System.out.println("ПРИЗОВЫE ИГРУШКИ: ");
                    // метод чтения из файла списка призов
                    ToysList printPrizeQueue = new ToysList();
                    printPrizeQueue.getListFromFile(prizeFileName);
                    printPrizeQueue.printToys(); // печать списка
                    break;
                                   
                case "3":
                    // ВЫДАТЬ призовую игрушку
                    System.out.print("\033[H\033[2J");
                    System.out.flush();
                    
                    int giveoutCount = 1;
                    ToysList giveoutToys = new ToysList();
                    //giveoutToys.getListFromFile(giveoutFileName);
                    ToysList prizeToyList = new ToysList();
                    prizeToyList.getListFromFile(prizeFileName);
                    PrizeDraws giveoutList = new PrizeDraws(prizeToyList, giveoutCount);
                    Toy giveoutOneToy = giveoutList.giveoutToy ();
                    giveoutToys.toyAddToFile(giveoutFileName,giveoutOneToy);
                    System.out.println("Была выдана игрушка:");
                    giveoutOneToy.printToy();
                    System.out.println("-----------------");
                    break;
                
                case "4":
                    // СПИСОК ВЫДАННЫХ игрушек
                    System.out.print("\033[H\033[2J");
                    System.out.flush();

                    System.out.println("ВЫДАННЫЕ призовые ИГРУШКИ: ");
                    // метод чтения из файла списка призов
                    ToysList printGiveoutToys = new ToysList();
                    printGiveoutToys.getListFromFile(giveoutFileName);
                    printGiveoutToys.printToys(); // печать списка
                    break;

                default:
                System.out.println("! Не корректное значение !\n! Повторите ввод !");
                flag = false;
            }
        } while (flag);
    }

    private void subSubMenuEdit(ToysList toys) throws Exception{
        System.out.print("\033[H\033[2J");
        System.out.flush();        
        
        boolean flag;
        Integer inputToyId = view.inputToyParam("ID");
        Toy findToy = findToyForWork(toys, inputToyId);
        String fileName = "toys.txt";
        if (findToy != null) {
            findToy.printToy();
            do {
                flag = true;
                String choice = view.editParam (inputToyId);

                switch (choice) {
                    case "0":
                        break;

                    case "1":
                    // Редактировать ВЕС  
                        findToy.setToyImportance(view.inputToyParam("ВЕС"));
                        toys.listToFile(fileName);
                        System.out.printf("ВЕС игрушки с ID#%d изменен, данные сохранены\n\n",findToy.getToyID());
                        break;
                        
                    case "2":
                    // Редактировать НАИМЕНОВАНИЕ
                        findToy.setToyName(view.inputToyName());
                        toys.listToFile(fileName);
                        System.out.printf("ВЕС игрушки с ID#%d изменен, данные сохранены\n\n",findToy.getToyID());
                        break;
                        
                    case "3":
                    // Редактировать КОЛИЧЕСТВО
                    findToy.setToyQuantity(view.inputToyParam("КОЛИЧЕСТВО"));
                    toys.listToFile(fileName);
                    System.out.printf("ВЕС игрушки с ID#%d изменен, данные сохранены\n\n",findToy.getToyID());
                    break;

                    default:
                        System.out.println("! Не корректное значение !\n! Повторите ввод !");
                        flag = false;
                }
            } while (!flag);
        } 
        else {
            System.out.printf("! Игрушка с ID#%d отсутствует в магазине !\n", inputToyId);
        }
    }

    // метод поиска игрушки по ID
    public Toy findToyForWork(ToysList toys, Integer findId) {
        for (Toy toy : toys.getToys()){
            if (toy.getToyID() == findId) {
                return toy;
            }
        }
        return null;
    }

}
