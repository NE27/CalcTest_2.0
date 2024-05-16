package org.example;
import java.util.Scanner;

public class Main {

    // Обновляемая логическая переменная, для определения отношения числа к римской системе счисления.
    static boolean updateRomanValue;

    // Массив римских чисел - от 1 до 100.
    static String[] roman = {
            "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X",
            "XI", "XII", "XIII", "XIV", "XV", "XVI", "XVII", "XVIII", "XIX", "XX",
            "XXI", "XXII", "XXIII", "XXIV", "XXV", "XXVI", "XXVII", "XXVIII", "XXIX", "XXX",
            "XXXI", "XXXII", "XXXIII", "XXXIV", "XXXV", "XXXVI", "XXXVII", "XXXVIII", "XXXIX", "XL",
            "XLI", "XLII", "XLIII", "XLIV", "XLV", "XLVI", "XLVII", "XLVIII", "XLIX", "L",
            "LI", "LII", "LIII", "LIV", "LV", "LVI", "LVII", "LVIII", "LIX", "LX",
            "LXI", "LXII", "LXIII", "LXIV", "LXV", "LXVI", "LXVII", "LXVIII", "LXIX", "LXX",
            "LXXI", "LXXII", "LXXIII", "LXXIV", "LXXV", "LXXVI", "LXXVII", "LXXVIII", "LXXIX", "LXXX",

            "LXXXI", "LXXXII", "LXXXIII", "LXXXIV", "LXXXV", "LXXXVI", "LXXXVII", "LXXXVIII", "LXXXIX", "XC",
            "XCI", "XCII", "XCIII", "XCIV", "XCV", "XCVI", "XCVII", "XCVIII", "XCIX", "C"
    };

    // Метод для преобразования арабского числа в римское.
    static String ArabicToRoman(int num) {
        if (num > 0 && num <= roman.length) {
            return roman[num - 1];
        }
        return null;
    }

    // Метод для проверки принадлежности чисел к одной системе счисления.
    static void validateNumbers(boolean isFirstRoman, boolean isSecondRoman) {
        if (isFirstRoman != isSecondRoman) {
            throw new IllegalArgumentException("Ошибка: Используются одновременно разные системы счисления");
        }
    }

    // Метод для проверки допустимого диапазона чисел.
    static void validateNumberRange(int num) {

        if (num < 1 || num > 10) {
            throw new IllegalArgumentException("Ошибка: Некорректный ввод, допустимые числа в диапазоне (1-10)");
        }

    }

    // Метод для выполнения арифметической операции.
    static String arithmetic(int firstNum, int secondNum, char operation, boolean isRoman) {
        int result = 0;
        switch (operation) {
            case '+':
                result = firstNum + secondNum;
                break;
            case '-':
                if (firstNum <= secondNum && isRoman) {
                    throw new IllegalArgumentException("Ошибка: Недопустимый результат для римской системы счисления (ноль или отрицательное число)");
                }
                result = firstNum - secondNum;
                break;
            case '*':
                result = firstNum * secondNum;
                break;
            case '/':
                result = firstNum / secondNum;
                break;
        }
        // Возвращение результата в зависимости от системы счисления.
        return isRoman ? ArabicToRoman(result) : String.valueOf(result);
    }

    // Метод для обновления переменной, если на входе число в римской системе счисления.
    static void IsRoman (boolean r){
        updateRomanValue = r;
    }

    // Метод для присваиваивания значения операнду.
    static int AssignValue (String num){
        int number = 0;
        boolean Roman = false;
        int count = 0;

        for (int i = 0; i < 10; i++) {
            ++count;

            if (num.equals(String.valueOf(count))) {
                number = count;
            } else if (num.equals(roman[i])) {
                number = count;
                Roman = true;
            }
        }

        // Вызываем метод обновления логической переменной для римских чисел.
        IsRoman(Roman);

        // Вызываем метод проверки допустимного диапазона.
        validateNumberRange(number);

        return number;
    }

    // Метод проверки наличия операции в строке и присваивания "символа" операции - переменной.
    static char AssignOperationValue (String in){
        int operationCount = 0;
        char operation = ' ';
        in = in.trim();

        for (int i = 0; i < in.length(); i++) {
            char c = in.charAt(i);
            if ("+-/*".indexOf(c) != -1) {
                if (operationCount == 0) {
                    operation = c;
                    operationCount++;
                } else {
                    throw new IllegalArgumentException("Ошибка: Некорректрный ввод, строка должна содержать только одну операцию.");
                }
            }
        }
        if (operationCount == 0) {
            throw new IllegalArgumentException("Ошибка: В строке отсутствует операция, либо ипользована недопустимая.");
        }
        return operation;
    }

    // Метод для заполнения массива из операндов.
    static String[] Numbers (String in){

        char operation = AssignOperationValue(in);

        //Получаем массив строк, используя символ операции, как "делитель".
        String[] num = in.split(String.format("\\%s", operation));

        //проверяем количество операндов.
        if (num.length != 2) {
            throw new IllegalArgumentException("Ошибка: Формат ввода должен быть \"Число Операция Число\"");
        }
        return num;
    }



    // Основной метод.
    public static String calc(String input) {

        // Инициализируем необходимые переменные.
        int first_num;
        int second_num;
        boolean [] TwoRomans = new boolean[2];

        // получаем операцию.
        char operation = AssignOperationValue(input);

        // Получаем массив строк.
        String[] num = Numbers(input);

        // Обрезаем пробелы до и после операндов.
        num[0] = num[0].trim();
        num[1] = num[1].trim();

        // Присваиваем значение первому операнду.
        first_num = AssignValue(num[0]);
        TwoRomans[0]=updateRomanValue;

        // Присваиваем значение второму операнду.
        second_num = AssignValue(num[1]);
        TwoRomans[1]=updateRomanValue;

        // Вызываем метод проверки системы счисления.
        validateNumbers(TwoRomans[0], TwoRomans[1]);

        // Возвращаем результат вычисления.
        return arithmetic(first_num, second_num, operation, TwoRomans[0]);


    }

    public static void main(String[] args) {

        // Создаем объект класса сканнер для ввода.
        Scanner sc = new Scanner(System.in);
        String inputLine = sc.nextLine();

        // Вызываем метод для вычисления, с выводом возможных ошибок на экран.
        try {
            System.out.println(calc(inputLine));
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }

        // Закрываем поток считывания.
        sc.close();
    }
}