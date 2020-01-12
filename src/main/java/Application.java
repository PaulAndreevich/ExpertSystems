import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;

import java.util.*;
import java.util.stream.Collectors;

public class Application {

    Set<String> basicIngrediaents = new HashSet<>(Arrays.asList("Сахар", "Мука", "Мясо", "Овощи", "Фрукты", "Яйца", "Молоко"));
    public static Map<Set<String>, String> lhmap = new HashMap<>();

    static {
        lhmap.put(new HashSet<>(Arrays.asList("яйца", "вода")), "вареные яйца");
        lhmap.put(new HashSet<>(Arrays.asList("яйца", "масло")), "яичница");
        lhmap.put(new HashSet<>(Arrays.asList("яйца", "мука")), "выпечка");
        lhmap.put(new HashSet<>(Arrays.asList("яйца", "молоко")), "омлет");
        lhmap.put(new HashSet<>(Arrays.asList("сахар", "молоко")), "омлет");
        lhmap.put(new HashSet<>(Arrays.asList("овес", "молоко")), "омлет");
        lhmap.put(new HashSet<>(Arrays.asList("омлет", "молоко")), "омлет");


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

        lhmap.put(new HashSet<>(Arrays.asList("торт", "шоколад")), "панчо");

        lhmap.put(new HashSet<>(Arrays.asList("панчо", "манго")), "фернандо");
    }


    public static void Lab1(){
        //Лаботраторная работа по Эксертным системам (ЭС) #2
        Scanner sc = new Scanner(System.in);

        BiMap<Set<String>, String> lhmap = HashBiMap.create();

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
    }

    private static Set<String> contains(String item) {
        Set<String> res = new HashSet<>();
        System.out.println(item + " ");
        lhmap.keySet().forEach(k -> {
            if(k.contains(item)) {
                System.out.print(k.stream()
                                    .filter(s -> !s.equals(item))
                                    .collect(Collectors.toSet()));
                res.addAll(k);
                res.addAll(contains(lhmap.get(k)));
            }
        }
        );
        res.remove(item);
        return res;
    }

    public static <T> Set<T> mergeSet(Set<T> a, Set<T> b)
    {
        return new HashSet<T>() {{
            addAll(a);
            addAll(b);
        } };
    }

    private static List<Set<String>> rec(String ingredient, Integer iteration, String resultDish) {
        List<Set<String>> res = new ArrayList<>();
        for (BiMap.Entry<Set<String>, String> entry: lhmap.entrySet()) {
            Set<String> key = entry.getKey();
            String value = entry.getValue();
            if(key.contains(ingredient) && (iteration - (key.size() - 1)) >= 0) {
                if(value.equals(resultDish)){
                    key.remove(ingredient);
                    List<Set<String>> tempRes2 = new ArrayList<>();
                    tempRes2.add(key);
                    return tempRes2;
                }
                List<Set<String>> tempRes = rec(lhmap.get(key), iteration - 1, resultDish);
                if (!tempRes.isEmpty()) {
                    key.remove(ingredient);
                    tempRes.forEach((strings -> strings.addAll(key)));
                    res = tempRes;
                }
            }
        }
        return res;
    }
    
    public static void Lab2(){
        //Лаботраторная работа по Эксертным системам (ЭС) #2
        List<Set<String>> result = null;
        Scanner sc = new Scanner(System.in);
        System.out.println("Исходный ингредиент:");
        String initialIngredient = sc.next();
        System.out.println("Количество переменных:");
        Integer iteration = sc.nextInt();
        System.out.println("Искомый результат:");
        String resultDish = sc.next();

        System.out.print("Ингредиенты: ");
        if (iteration != 0 && initialIngredient != null && !initialIngredient.isEmpty() && resultDish != null && !resultDish.isEmpty()){

            do {
                if (result != null){
                    lhmap.remove(result.get(0));
                }
                result = rec(initialIngredient, iteration, resultDish);
                if (result.size() == 0){
                    break;
                }
                System.out.print(result);
                result.get(0).add(initialIngredient);
            } while (lhmap.containsKey(result.get(0)));

            /*if (!result.isEmpty()) {
                System.out.println("К вашему ингредиенту " + initialIngredient);
                System.out.print("Ингредиенты: " + result);
            } else {
                System.out.print("Ваше блюдо не найдено или количество переменных не соответствует рецепту");
            }*/
        } else {
            System.out.print("Неверный ввод, пожалуйста повторите");
        }

    }

    public static void main(String[] args) {
        //Lab1();
        Lab2();
    }
}
