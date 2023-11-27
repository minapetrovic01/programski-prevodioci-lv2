import java.util.Hashtable;
public class KWTable {

	private Hashtable<String, Integer> mTable;
	public KWTable()
	{
		//inicijalizacija hash tabele koja pamti kljucne reci
		mTable = new Hashtable<String, Integer>();
		mTable.put("program", new Integer(sym.PROGRAM));
		mTable.put("integer", new Integer(sym.INTEGER));
		mTable.put("char", new Integer(sym.CHAR));
		mTable.put("real", new Integer(sym.REAL));
		mTable.put("boolean", new Integer(sym.BOOLEAN));
		mTable.put("begin", new Integer(sym.BEGIN));
		mTable.put("end", new Integer(sym.END));
		mTable.put("select", new Integer(sym.SELECT));
		mTable.put("case", new Integer(sym.CASE));
		mTable.put("and", new Integer(sym.AND));
		mTable.put("or", new Integer(sym.OR));


		
	}
	
	public int find(String keyword)
	{
		//vraca id kljucne reci
		Object symbol = mTable.get(keyword);
		if (symbol != null)
			return ((Integer)symbol).intValue();
		//ako je nema u tabeli kljucnih reci radi se o id-u
		return sym.ID;
	}
	
}
