import java.util.*;

public class Calculator {

    public static void main(String[] args) {
        String expression = "5.6%+（1.2%*2）";

        double result = calculate(expression);

        System.out.println(result);         // 0.08
        System.out.println(result * 100 + "%"); // 8.0%
    }

    public static double calculate(String s) {
        s = s.replace("（", "(")
             .replace("）", ")")
             .replace(" ", "");

        Deque<Double> nums = new ArrayDeque<>();
        Deque<Character> ops = new ArrayDeque<>();

        int n = s.length();

        for (int i = 0; i < n; ) {

            char c = s.charAt(i);

            // 数字
            if (Character.isDigit(c) || c == '.') {

                int j = i;

                while (j < n &&
                        (Character.isDigit(s.charAt(j))
                                || s.charAt(j) == '.')) {
                    j++;
                }

                double num = Double.parseDouble(
                        s.substring(i, j)
                );

                // 处理百分号
                if (j < n && s.charAt(j) == '%') {
                    num /= 100;
                    j++;
                }

                nums.push(num);

                i = j;
            }

            // 左括号
            else if (c == '(') {
                ops.push(c);
                i++;
            }

            // 右括号
            else if (c == ')') {

                while (!ops.isEmpty()
                        && ops.peek() != '(') {

                    calc(nums, ops);
                }

                ops.pop(); // 弹出 (

                i++;
            }

            // 运算符
            else if (c == '+' ||
                     c == '-' ||
                     c == '*' ||
                     c == '/') {

                while (!ops.isEmpty()
                        && ops.peek() != '('
                        && priority(ops.peek())
                        >= priority(c)) {

                    calc(nums, ops);
                }

                ops.push(c);

                i++;
            }

            else {
                throw new RuntimeException(
                        "非法字符：" + c
                );
            }
        }

        // 处理剩余运算
        while (!ops.isEmpty()) {
            calc(nums, ops);
        }

        return nums.pop();
    }

    private static void calc(
            Deque<Double> nums,
            Deque<Character> ops) {

        double b = nums.pop();
        double a = nums.pop();

        char op = ops.pop();

        double result = switch (op) {
            case '+' -> a + b;
            case '-' -> a - b;
            case '*' -> a * b;
            case '/' -> a / b;
            default -> throw new RuntimeException();
        };

        nums.push(result);
    }

    private static int priority(char op) {

        if (op == '+' || op == '-') {
            return 1;
        }

        if (op == '*' || op == '/') {
            return 2;
        }

        return 0;
    }
}