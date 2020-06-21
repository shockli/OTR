package Task1;

/**
 * Created by Alexander on 19.06.2020.
 */
public class RemoteStorage {
    public static Product getProduct() throws InterruptedException{
        Double randomSleep = Math.random()*10000;
        Thread.sleep(randomSleep.longValue());
        System.out.println("Удаленный склад передает товар главному складу за " + randomSleep/1000 + " сек.");
        return new Product();
    }
}
