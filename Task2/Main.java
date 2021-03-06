package Task2;

/**
 * Created by Alexander on 21.06.2020.
 */
/*
Дана m - последовательность символов произвольной длины, состоящая из прописных символов латинского алфавита. Необходимо получить последовательность n, таким образом, что все подряд повторяющиеся символы будут заменены на единичный символов последовательности.
Например: m = aabcccccaaah
Ответ: n = abcah
Задачу необходимо решить наиболее оптимальным способом.
*/
public class Main {
    public static void main(String[] args) {
        String input = "aabcccccaaah";
        //любой символ, повторяющийся больше одного раза последовательно, будет заменен на свой экземпляр
        //работает с любыми символами
        System.out.println(input.replaceAll("(.)\\1{1,}", "$1"));
    }
}
