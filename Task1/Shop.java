package Task1;

/**
 * Created by Alexander on 19.06.2020.
 */
public class Shop extends Thread{
    String name = "Магазин";
    public Shop(Integer i) {
        this.name += "_" + i ;
    }

    public void run(){
        try{
            Product product = Storage.getProduct(name);
        }catch (InterruptedException e){
            System.out.println(e);
        }
    }
}
