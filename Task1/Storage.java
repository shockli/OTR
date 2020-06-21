package Task1;

import java.util.ArrayList;

/**
 * Created by Alexander on 19.06.2020.
 */
public class Storage {
    //вместительность склада
    private static final Integer storageSize = 5;

    //само хранилище продуктов
    static volatile ArrayList<Product> storageProducts = new ArrayList<Product>() {{
        for(int i = 0; i < storageSize; i++){
            add(new Product());
        }
    }};

    //очередь запросов, работа с очередью ведется синхронно
    static volatile ArrayList<String> queue = new  ArrayList<String>();

    public static Product getProduct(String shopName) throws InterruptedException{
        Product productForShiping = getExistProduct(shopName);
        if(productForShiping != null){
            return productForShiping;
        }else{
            //добавляем магазин в очередь на получение
            queue.add(shopName);
            System.out.println("На складе закончились товары, запрашиваю с удаленного склада товар для магазина " + shopName);
            storageProducts.add(RemoteStorage.getProduct());
            //если магазин первый в очереди и склад не пустой, то ему первее всех вернется товар (даже если этот товар был заказан другому магазину)
            while(true){
                //если текущий склад не пустой и если подошла очередь выдачи магазину
                if(shopName.equals(queue.get(0)) && !storageProducts.isEmpty()){
                    return getOrderedProduct(shopName);
                }
            }
        }
    }
    //получение с локального склада вынесено в сихронизированный метод для соблюдения порядка вывода сообщений в коносль
    private static synchronized Product getExistProduct(String shopName){
        System.out.println(shopName + " запрашивает товар со склада");
        if(!storageProducts.isEmpty() && queue.isEmpty()){
            Product product = storageProducts.get(0);
            storageProducts.remove(0);
            System.out.println(shopName + " получает товар со склада");
            return product;
        }else{
            return null;
        }
    }

    //получение заказанного породукта вынесено в синхронизированный метод чтобы избежать расхождений потоков во время вывода сообщения в консоль
    private static synchronized Product getOrderedProduct(String shopName){
        queue.remove(0);
        Product product = storageProducts.get(0);
        storageProducts.remove(0);
        System.out.println(shopName + " получает товар со склада");
        return product;
    }

}
