import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;

import java.util.*;

public class Application {

    //Лаботраторная работа по Эксертным системам (ЭС) #1
    

    public static void main(String[] args) {
        Set<String> basicIngrediaents = new HashSet<>(Arrays.asList("Сахар", "Мука", "Мясо", "Овощи", "Фрукты", "Яйца", "Молоко"));

        Scanner sc = new Scanner(System.in);

        BiMap<Set<String>, String> lhmap = HashBiMap.create();
        lhmap.put(new HashSet<>(Arrays.asList("яйца", "молоко")), "вареные яйца");

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

        Stack<String> stack = new Stack<>();

        for (String i: listOfInput) {
            stack.push(i);
        }

        Set<String> tempSet = new HashSet<>();

        while (stack.size() >= 1){
            tempSet.add(stack.pop());
            if (lhmap.get(tempSet) != null){
                stack.push(lhmap.get(tempSet));
                tempSet.clear();
            }
        }

        if (tempSet.size() != numberOfIngredients) {
            System.out.println("Результат: " + Arrays.toString(tempSet.toArray()));
        } else {
            System.out.println("Из таких ингредиентов нельзя ничего изготовить!");
        }

        //_______________________________________________________________________________________________________

        System.out.println("Какое блюдо разложить?: ");

        String dishName = sc.next();

        if(lhmap.inverse().get(dishName) != null) {
            stack.clear();
            tempSet.clear();
            stack.push(dishName);
            while (stack.size() != 0) {
                String ingredient = stack.peek();
                if (lhmap.inverse().get(ingredient) != null) {
                    stack.pop();
                    Set<String> loopSet = lhmap.inverse().get(ingredient);
                    for (String stock : loopSet) {
                        stack.push(stock);
                    }
                } else {
                    tempSet.add(ingredient);
                    stack.pop();
                }
            }

            System.out.println("Результат: " + Arrays.toString(tempSet.toArray()));
        } else {
            System.out.println("Такого блюда нет!");
        }
    }
}
