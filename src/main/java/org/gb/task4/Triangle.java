package org.gb.task4;

public class Triangle {

    // функция считает площадь треугольника по трём сторонам (по формуле Герона)
    // S = SQRT( p(p-a)(p-b)(p-c) ) , где p - полупериметр  (a+b+c)/2
    public static double sTriangle(int a, int b, int c)
            throws TriangleSideIsNegativeException, TriangleSidesAreIncorrectException, TriangleImpossibleToCalcException {

        // если задана хотя бы одна отрицательная длина стороны треугольника
        if ((a < 0) || (b < 0) || (c < 0)) {
            throw new TriangleSideIsNegativeException();
        }

        // если стороны заданы некорректно - одна сторона длинее суммы двух других
        if ((a + b < c) || (a + c < b) || (b + c < a)) {
            throw new TriangleSidesAreIncorrectException();
        }

        // если хотя бы одна из сторон равна 0, площадь равна 0  (здесь останутся только вырожденные треугольники, некорректные случаи обработаны выше)
        if ((a == 0) || (b == 0) || (c == 0))
            return 0;

        double s;
        double p = 0.5 * (a + b + c ); // полупериметр
        // деление на 2 не используем, т.к. если в делении участвуют два целых числа, то частное округляется до целого числа,
        // даже если результат присваивается переменной float или double

        try {
            s = Math.sqrt(p * (p - a) * (p - b) * (p - c));  // формула Герона
            // для вырожденных треугольников, у которых сторона равна сумме двух других, вернётся 0
        }
	    catch (Exception e) {
            throw new TriangleImpossibleToCalcException(); // на всякий случай, но получить это исключения не должны
        }
        return s;
    }

/*
    public static void main(String[] args) {
        try {
            System.out.println("a = 3, b = 4, c = 5,  S =  " + sTriangle(3, 4, 5));
            System.out.println("a = 13, b = 15, c = 14,  S =  " + sTriangle(13, 15, 14));
            System.out.println("a = 5, b = 10, c = 14,  S =  " + sTriangle(5, 10, 14));
        }
        catch (Exception e) {
            System.out.println("Ошибка.");
        }
    }
 */
}
