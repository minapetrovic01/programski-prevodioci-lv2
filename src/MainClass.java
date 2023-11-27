import java.io.IOException;
import java.util.Stack;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MainClass {
	private static final int SK = 0;
    private static final int RK = 1;
    private static final int ACC = 2;
    private static final int ERR = 3;
    
    private static final SyntaxTable table = new SyntaxTable();
    
    public static void main(String args[]) {
        Stack<Integer> stack = new Stack();

        stack.push(sym.EOF);
        
        boolean acc = false;
        boolean err = false;
        
        System.out.print("Mina");
        
        try {
            MPLexer lexer = new MPLexer(new java.io.FileReader("src/testinput.txt"));
        
            int next = lexer.next_token().m_index;
            int[] k = new int[1], left = new int[1], size = new int[1];
            
            do {
                int a = action(stack.peek(), next, k, left, size);
                System.out.printf("Next: %d\nAction: %d\n", next, a);
                
                switch (a) {
                    case SK -> {
                        stack.push(next);
                        stack.push(k[0]);
                        next = lexer.next_token().m_index;
                    }
                    case RK -> {
                        for (int i = 0; i < 2 * size[0]; i++) stack.pop();
                        int top = stack.peek();
                        stack.push(left[0]);
                        System.out.printf("tr: %d %d\n", top, left[0]);
                        stack.push(((SyntaxTableObjectTransition) table.get(top, left[0])).state);
                    }
                    case ACC -> acc = true;
                    case ERR -> err = true;
                }
                
                printStack(stack);
            } while (!(acc || err));
            
        } catch (IOException ex) {
            Logger.getLogger(MainClass.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (acc) {
            System.out.println("Accepted.");
        } else if (err) {
            System.out.println("Error.");
        } else {
            System.out.println("???.");
        }
        printStack(stack);
    }
    private static int action(int top, int next, int[] k, int[] left, int[] size) {
        int ret = ERR;
        SintaxTableObject s = table.get(top, next);
        
        if (s instanceof SyntaxTableObjectShift shift) {
            k[0] = shift.k;
            ret = SK;
        } else if (s instanceof SyntaxTableObjectRule rule) {
            k[0] = rule.k;
            left[0] = rule.left;
            size[0] = rule.size;
            ret = RK;
        } else if (s.type.equals("acc")) {
            ret = ACC;
        }
        
        return ret;
    }
    
    private static void printStack(Stack<Integer> stack) {
        System.out.println("Stack:");
        for (int i = 0; i < stack.size(); i++) {
            System.out.printf("%2d ", stack.get(i));
            if (i % 2 == 0) System.out.println();
        }
    }
}
