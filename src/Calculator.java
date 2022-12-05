import java.util.Scanner;

public class Calculator {

    public static String calc(String input){

        Converter converter = new Converter();
        String[] actions = {"+", "-", "/", "*"};
        String[] regexActions = {"\\+", "-", "/", "\\*"};

        int actionIndex = -1;
        for (int i = 0; i < actions.length; i++) {
            if (input.contains(actions[i])) {
                actionIndex = i;
                break;
            }
        }
        //Если не нашли арифметического действия, завершаем программу
        if (actionIndex == -1) {
            throw new RuntimeException("Количество операндов должно быть два");
        }
        //Делим строчку по найденному арифметическому знаку

        String[] arr = input.split("[+\\-*/]");
        if (arr.length != 2) {
            throw new RuntimeException("Количество операндов должно быть два");
        }
        String[] data = input.split(regexActions[actionIndex]);

        //Определяем, находятся ли числа в одном формате (оба римские или оба арабские)
        if (converter.isRoman(data[0]) == converter.isRoman(data[1])) {
            int a, b;
            //Определяем, римские ли это числа
            boolean isRoman = converter.isRoman(data[0]);
            if (isRoman) {
                //если римские, то конвертируем их в арабские
                a = converter.romanToInt(data[0]);
                b = converter.romanToInt(data[1]);

            } else {
                //если арабские, конвертируем их из строки в число
                a = Integer.parseInt(data[0]);
                b = Integer.parseInt(data[1]);
            }
            if (a < 1 || a > 10 || b < 1 || b > 10) {
                throw new RuntimeException("Числа должны быть от 1 до 10");
            }
            //арифметическое действие
            int result = switch (actions[actionIndex]) {
                case "+" -> a + b;
                case "-" -> a - b;
                case "*" -> a * b;
                default -> a / b;
            };
            if (isRoman) {
                //если числа были римские, возвращаем результат в римском числе
                System.out.println(converter.intToRoman(result));
            } else {
                //если числа были арабские, возвращаем результат в арабском числе
                System.out.println(result);
            }
        } else {
            throw new RuntimeException("Числа должны быть в одном формате");
        }

        return input;
    }

    public static void main(String[] args) {

        Scanner scn = new Scanner(System.in);
        System.out.print("Введите выражение: ");
        String exp = scn.nextLine().toUpperCase().replaceAll("\\s", "");

        Calculator.calc(exp);

    }
}
