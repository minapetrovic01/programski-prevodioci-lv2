
public class SyntaxTableObjectRule extends SintaxTableObject {
	public int k;
    public int left;
    public int size;
    //redukciono pravilo
    public SyntaxTableObjectRule(int k, int left, int size) {
        super("rk");
        this.k = k;
        this.left = left;
        this.size = size;
    }
}
