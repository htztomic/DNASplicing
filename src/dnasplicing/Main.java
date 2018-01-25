package dnasplicing;

import static java.lang.System.*;

public class Main {

	public static void main(String[] args) {
		LinkedDnaStrand oldList = new LinkedDnaStrand("GATCTGATCTGAT");
		DnaStrand newList = oldList.cutSplice("GAT", "TTAAGG");
		out.println(newList.toString());
		out.println(newList.getAppendCount());

	}

}
