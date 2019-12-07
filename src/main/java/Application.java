import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;

import java.util.*;

public class Application {

    //Лаботраторная работа по Эксертным системам (ЭС) #1


    public static void main(String[] args) {
        Set<String> basicIngrediaents = new HashSet<>(Arrays.asList("Сахар", "Мука", "Мясо", "Овощи", "Фрукты", "Яйца", "Молоко"));

        Scanner sc = new Scanner(System.in);

        BiMap<Set<String>, String> lhmap = HashBiMap.create();
        lhmap.put(new HashSet<>(Arrays.asList("яйца", "вода")), "вареные яйца");

        lhmap.put(new HashSet<>(Arrays.asList("яйца", "масло")), "яичница");
        lhmap.put(new HashSet<>(Arrays.asList("яйца", "мука")), "выпечка");
        lhmap.put(new HashSet<>(Arrays.asList("яйца", "молоко")), "омлет");
        lhmap.put(new HashSet<>(Arrays.asList("яйца", "сахар")), "крем");
        lhmap.put(new HashSet<>(Arrays.asList("яйца", "мясо")), "котлеты");

        lhmap.put(new HashSet<>(Arrays.asList("яичница", "мясо")), "яйчница с мясом");
        lhmap.put(new HashSet<>(Arrays.asList("яичница", "овощи")), "яйчница с овощами");

        lhmap.put(new HashSet<>(Arrays.asList("омлет", "овощи")), "испанский омлет");
        lhmap.put(new HashSet<>(Arrays.asList("омлет", "мясо")), "омлет с мясом");

        lhmap.put(new HashSet<>(Arrays.asList("выпечка", "мясо")), "пирог с мясом");
        lhmap.put(new HashSet<>(Arrays.asList("выпечка", "фрукты")), "шарлотка");
        lhmap.put(new HashSet<>(Arrays.asList("выпечка", "сахар")), "плюшка");

        lhmap.put(new HashSet<>(Arrays.asList("крем", "выпечка")), "пирожное");
        lhmap.put(new HashSet<>(Arrays.asList("крем", "выпечка", "фрукты")), "торт");

        System.out.println("Сколько ингредиентов будете вводить? : ");

        Integer numberOfIngredients = sc.nextInt();

        System.out.println("Введите количество ингредиентов: ");

        List<String> listOfInput = new ArrayList<>();

        for(int i = 0; i < numberOfIngredients; i++){
            String nextIngredient = sc.next();
            listOfInput.add(nextIngredient);
        }

        Collections.reverse(listOfInput);

        Set<String> set = new HashSet<>();

        set.addAll(listOfInput);

        Set<String> tempSet = new HashSet<>();

        boolean hasNewValues = true;
        while (hasNewValues){
            hasNewValues = false;
            int oldSize = set.size();
            lhmap.forEach((strings, s) -> {
                if (set.containsAll(strings) && !set.contains(s)){
                    set.add(s);
                }
            });
            if (set.size() > oldSize){
                hasNewValues = true;
            }
        }

        set.removeAll(listOfInput);

        if (tempSet.size() != numberOfIngredients) {
            System.out.println("Результат: " + Arrays.toString(set.toArray()));
        } else {
            System.out.println("Из таких ингредиентов нельзя ничего изготовить!");
        }

        //_______________________________________________________________________________________________________

        System.out.println("Какое блюдо разложить?: ");

        String dishName = sc.next();
        Set<String> res = new HashSet<>();
        res.add(dishName);

        hasNewValues = true;

        while (hasNewValues){
            hasNewValues = false;
            int oldSize = res.size();
            lhmap.forEach((strings, s) -> {
                if (res.contains(s)){
                    res.addAll(strings);
                }
            });

            if (oldSize != res.size()){
                hasNewValues = true;
            }
        }

        res.remove(dishName);
        System.out.println(res);
//
//        if(lhmap.inverse().get(dishName) != null) {
//            lhmap.forEach((strings, s) -> {
//
//                    }
//
//            );
//
//            tempSet.clear();
//            res.push(dishName);
//            while (res.size() != 0) {
//                String ingredient = res.peek();
//                if (lhmap.inverse().get(ingredient) != null) {
//                    res.pop();
//                    Set<String> loopSet = lhmap.inverse().get(ingredient);
//                    for (String stock : loopSet) {
//                        res.push(stock);
//                    }
//                } else {
//                    tempSet.add(ingredient);
//                    res.pop();
//                }
//            }
//
//            System.out.println("Результат: " + Arrays.toString(tempSet.toArray()));
//        } else {
//            System.out.println("Такого блюда нет!");
//        }
    }
}
