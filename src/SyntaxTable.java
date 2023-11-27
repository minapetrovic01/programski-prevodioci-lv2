
public class SyntaxTable {
	 private SintaxTableObject[][] table;
	    
	 private final int SIZE_X = 35;
	 private final int SIZE_Y = 20;

	 public SyntaxTable() {
	        table = new SintaxTableObject[SIZE_Y][SIZE_X];  
	        
	        for (int i = 0; i < SIZE_Y; i++) {
	            for (int j = 0; j < SIZE_X; j++) {
	                table[i][j] = new SintaxTableObject("err");
	            }
	        }
	        
	        // Accept
	        table[1][sym.EOF] = new SintaxTableObject("acc");
	        
	        // Shift
	        table[0][sym.SELECT] = new SyntaxTableObjectShift(2);
	        table[2][sym.BEGIN] = new SyntaxTableObjectShift(3);
	        table[3][sym.CASE] = new SyntaxTableObjectShift(6);
	        table[4][sym.END] = new SyntaxTableObjectShift(7);
	        table[6][sym.ID] = new SyntaxTableObjectShift(9);
	        table[9][sym.STATES] = new SyntaxTableObjectShift(10);
	        table[10][sym.SELECT] = new SyntaxTableObjectShift(2);
	        table[10][sym.ID] = new SyntaxTableObjectShift(12);
	        table[12][sym.ASSIGN] = new SyntaxTableObjectShift(13);
	        table[13][sym.ID] = new SyntaxTableObjectShift(14);
	        table[13][sym.CONST] = new SyntaxTableObjectShift(17);
	        table[14][sym.SEMICOLON] = new SyntaxTableObjectShift(15);
	        table[17][sym.SEMICOLON] = new SyntaxTableObjectShift(18);
	        
	        // Rule
	        table[5][sym.END] = table[5][sym.CASE] = new SyntaxTableObjectRule(3, sym.CL, 1);
	        table[7][sym.END] = table[7][sym.CASE] = table[7][sym.EOF]= new SyntaxTableObjectRule(1, sym.SS, 4);
	        table[8][sym.END] = table[8][sym.CASE] = new SyntaxTableObjectRule(2, sym.CL, 2);
	        table[11][sym.END] = table[11][sym.CASE] = new SyntaxTableObjectRule(4, sym.C, 4);
	        table[15][sym.END] = table[15][sym.CASE] = new SyntaxTableObjectRule(6, sym.S, 4);
	        table[16][sym.END] = table[16][sym.CASE] = new SyntaxTableObjectRule(5, sym.S, 1);
	        table[18][sym.END] = table[18][sym.CASE] = new SyntaxTableObjectRule(7, sym.S, 4);
	                
	        // Transition    
	        table[0][sym.SS] = new SyntaxTableObjectTransition(1);
	        table[3][sym.CL] = new SyntaxTableObjectTransition(4);
	        table[3][sym.C] = new SyntaxTableObjectTransition(5);
	        table[4][sym.C] = new SyntaxTableObjectTransition(8);
	        table[10][sym.S] = new SyntaxTableObjectTransition(11);
	        table[10][sym.SS] = new SyntaxTableObjectTransition(16);
	        
	        
	    }
	    
	    public SintaxTableObject get(int i, int j) {
	        return table[i][j];
	    }
}
