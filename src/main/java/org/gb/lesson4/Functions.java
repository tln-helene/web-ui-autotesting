package org.gb.lesson4;

public class Functions {
    // статический метод, чтобы не нужно было создавать объект - экземпляр класса
    public static boolean isPalindrome(String word) {
        if (word.length() < 2) {
            return true;  // пустое слово тоже считаем палиндромом
        }

        if (word.charAt(0) != word.charAt(word.length()-1)) {
            return false;
        }

        return isPalindrome(word.substring(1, word.length() - 1)); // рекурсивно
    }
}
