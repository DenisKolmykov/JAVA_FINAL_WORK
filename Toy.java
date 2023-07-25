/*
В программе должен быть минимум один класс со следующими свойствами:
id игрушки,
текстовое название,
количество
частота выпадения игрушки (вес в % от 100)
 
Метод добавление новых игрушек и 
возможность изменения веса (частоты выпадения игрушки)
 */


public class Toy {
    private Integer toyId = setToyID();
    private String toyName;
    private Integer toyQuantity;
    private Integer toyImportance;

    // static для счетчика ID при каждом новом пользователе
    public static Integer countID;
    static{
        countID = 1;
    }

    public Toy(){
        countID++;
    }

    private Integer setToyID() {
        return this.toyId = countID;
    }

    public Integer setToyID(Integer id) {
        return this.toyId = id;
    }

    public Integer getToyID() {
        return this.toyId;
    }
    //-----------

    public void setToyName(String name) {
        this.toyName = name;
    }

    public String getToyName() {
        return this.toyName;
    }

    public void setToyQuantity(Integer quantity) {
        this.toyQuantity = quantity;
    }
    
    public Integer getToyQuantity() {
        return this.toyQuantity;
    }

    public void setToyImportance(Integer importance) {
        this.toyImportance = importance;
    }

    public Integer getToyImportance() {
        return this.toyImportance;
    }

    public void printToy() {
        System.out.printf("#%d %s, вес: %d, в наличии: %d шт. \n",this.toyId, this.toyName, this.toyImportance, this.toyQuantity);
    }

    @Override
        public String toString (){
            return this.toyId + ";" + this.toyName + ";" + this.toyImportance + ";" + this.toyQuantity + "\n";
        }
    

    public Toy cloneToy() throws CloneNotSupportedException {
        Toy clone = new Toy();
        clone.toyId = this.toyId;
        clone.toyName = this.toyName;
        clone.toyImportance = this.toyImportance;
        clone.toyQuantity = this.toyQuantity;
        return clone;
    }

}
