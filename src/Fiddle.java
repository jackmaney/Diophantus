import java.util.Comparator;
import java.util.Hashtable;





public class Fiddle {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		Hashtable<String, Integer> hash = new Hashtable<>();
		
		hash.put("blah", 1);
		hash.put("blarg",-17);
		
		for (String k : hash.keySet()) {
			System.out.println(k + "," + hash.get(k));
		}
		
		
		StringBuffer s = new StringBuffer();
		
		s.append("Blah!");
		s.append(" blargen...");
		
		System.out.println(s.toString());
		
		
	}
	

}
