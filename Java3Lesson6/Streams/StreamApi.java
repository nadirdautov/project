package Streams;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class StreamApi {
    public static void main(String[] args) {
        StreamApi myArrays = new StreamApi();
        Integer[] myInt;
        try {
            myInt = myArrays.GetAllAfterLast4(new Integer[]{1, 4, 3, 5, 4, 7, 2});
            System.out.println(Arrays.toString(myInt));
        } catch (RuntimeException e) {
            System.out.println(e);
        }
        Boolean checkResult = myArrays.CheckArray1And4(new Integer[] {2,3,5,4,3,2});
        System.out.println(checkResult);
    }
    // Даже менять ничего не буду, очень хорошо написано
    public Integer[] GetAllAfterLast4(Integer[] array) throws RuntimeException {
        // Записали в новый массив через asList
        List<Integer> myArray = Arrays.asList(array);
        // Назовём последним индексом 4, через метод lastIndexOf, который принимает на вход о - элемент для поиска
        int last4 = myArray.lastIndexOf(4);
        // В ином случае lastIndexOf выдаёт -1, то есть не найдя последний элемент, прокидываем RTE
        if (last4 == -1) {
            throw new RuntimeException("Входной массив должен содержать хотя бы одну четверку");
        }
        // Через метод subList, который в свою очередь принимает начало и конец массива обозначаем новый
        List<Integer> newArrayList = myArray.subList(last4+1, myArray.size());
        // возвращаем его, я понял что мы создаем новый массив чисел, (new Integer[0]) выступает как точка отсчета
        return newArrayList.toArray(new Integer[0]);
    }

    public Boolean CheckArray1And4(Integer[] array) {
        // Записали в новый массив
        List<Integer> myArray = Arrays.asList(array);
        // Создаём ArrayList с размерностью в 2
        ArrayList<Integer> valuesToCheck = new ArrayList<>(2);
        // Добавляем в него 1 и 4
        valuesToCheck.add(1);
        valuesToCheck.add(4);
        // И через метод containsAll, то есть все будут у нас одинаковыми цифрами или значениями передаём в новый массив
        return myArray.containsAll(valuesToCheck);
    }

}
