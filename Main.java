public class Main {
    public static void main(String[] args) {
        System.out.print("Hello!\n");

        //задание и печать комплексных чисел
        Complex a = new Complex((float) -4.2,(float)-3.5);
        Complex b = new Complex((float)6.12,(float)-4);

        System.out.print("Complex numbers:\n");
        a.print();
        System.out.print("\n");
        b.print();
        System.out.print("\n");

        Complex pl = new Complex();
        Complex mn = new Complex();
        Complex ml = new Complex();
        Complex div = new Complex();

        try {
            // попытка действий с комплексными числами
            pl = a.plus(b);
            mn = a.minus(b);
            ml = a.multiply(b);
            div = a.divide(b);

            Main.print_(a,b,pl,"+", "Addition: ");
            Main.print_(a,b,mn,"-", "Subtraction: ");
            Main.print_(a,b,ml,"*", "Multiplication: ");
            Main.print_(a,b,div,"/", "Division: ");
        }
        catch (ArithmeticException e){
            System.out.print(e.getMessage());
               System.exit(0);
        }
        System.out.print("\n\n");

        //задание 3-мерных массивов для матриц 3*3
        Complex[][] n1 = {{new Complex(12,0), new Complex(4,0), new Complex(-2,0)},
                {new Complex(0, -0), new Complex(4,0), new Complex(-56,0)},
                {new Complex(-1,0), new Complex(-6,0), new Complex(-7, -0)}};

        Complex[][] n2 = {{new Complex(21,0), new Complex(42,0), new Complex(76,0)},
                {new Complex(-56, -0), new Complex(-112,0), new Complex(6,0)},
                {new Complex(3,0), new Complex(7,0), new Complex(10, -0)}};

        /*Complex[][] n1 = {{new Complex(12,0), new Complex(4,0)},
                {new Complex(0, -0), new Complex(4,0)}};

        Complex[][] n2 = {{new Complex(4,0), new Complex(2,0)},
                {new Complex(-4, -0), new Complex(-6,0)}};*/

        Matrix m = new Matrix(3, 3, n1);
        Matrix n = new Matrix(3, 3, n2);

        //действия с матрицами
        System.out.print("Matrix 1:\n");
        m.print();
        System.out.print("Matrix 2:\n");
        n.print();

        try {
            System.out.print("Matrix 1 + Matrix 2:\n");
            Matrix res1 = m.add(n);
            res1.print();

            System.out.print("Matrix 1 - Matrix 2:\n");
            Matrix res2 = m.subtract(n);
            res2.print();

            System.out.print("Matrix 1 * Matrix 2:\n");
            Matrix res3 = m.multilpy(n);
            res3.print();

            System.out.print("Det of Matrix 1 = ");
            m.determinant().print();
            System.out.print("\nDet of Matrix 2 = ");
            n.determinant().print();

            System.out.print("\n\nMatrix 1 reversed:\n");
            Matrix res4 = m.reverse();
            res4.print();

            System.out.print("Matrix 2 reversed:\n");
            Matrix res5 = n.reverse();
            res5.print();

            System.out.print("Matrix 1 / Matrix 2:\n");
            Matrix res6 = m.multilpy(n.reverse());
            res6.print();
        }
        catch (IllegalArgumentException | ArithmeticException e){
            System.out.print(e.getMessage());
            System.exit(0);
        }
    }

    //адекватный вывод действий с комплексными числами (сложение, вычитание и тд)
    public static void print_(Complex num1, Complex num2, Complex res, String sign, String action){
        System.out.print("\n" + action + "(");
        num1.print();
        System.out.print(") " + sign + " (");
        num2.print();
        System.out.print(") = ");
        res.print();
    }
}
