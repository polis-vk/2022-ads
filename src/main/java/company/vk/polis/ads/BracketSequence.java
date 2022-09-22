package company.vk.polis.ads;

import java.io.PrintWriter;

public class BracketSequence { //Ex4

    private static final Stack stack = new Stack();

    public BracketSequence() {
    }

    public static void solveBracketSequence(String sequence, final PrintWriter out) {
        for (int i = 0; i < sequence.length(); i++) {
            if (checkBracket(sequence.charAt(i))) {
                out.print("no");
                return;
            }
        }

        out.print(stack.isEmpty() ? "yes" : "no");
    }

    private static boolean checkBracket(int codeBracket) {
        switch (codeBracket) {
            case '{':
            case '(':
            case '[':
                stack.push(codeBracket);
                return false;
            case '}':
                if (stack.isEmpty() || Integer.parseInt(stack.pop()) != '{') {
                    return true;
                }
                break;
            case ']':
                if (stack.isEmpty() || Integer.parseInt(stack.pop()) != '[') {
                    return true;
                }
                break;
            case ')':
                if (stack.isEmpty() || Integer.parseInt(stack.pop()) != '(') {
                    return true;
                }
                break;
            default:
                return true;
        }

        return false;
    }
}
