package dnasplicing;

public class LinkedDnaStrand implements DnaStrand {
	private DnaSequenceNode top;
	int count;
	int appendCount;

	public LinkedDnaStrand(String dna) {
		DnaSequenceNode first = new DnaSequenceNode(dna);
		top = first;
		top.next = null;
		appendCount = 0;
		top.previous = null;
		count = 1;
		// TODO Auto-generated constructor stub
	}

	@Override
	public long getNucleotideCount() {
		// TODO Auto-generated method stub
		return toString().length();
	}

	public String toString() {
		StringBuilder str = new StringBuilder(top.dnaSequence);
		DnaSequenceNode cur;
		cur = top;
		while (cur.next != null) {
			cur = cur.next;
			str.append(cur.dnaSequence);
		}
		return str.toString();

	}

	public String toStrings() {
		StringBuilder str = new StringBuilder(top.dnaSequence);
		DnaSequenceNode cur;
		str.append(",");
		cur = top;
		while (cur.next.next != null) {
			cur = cur.next;
			str.append(cur.dnaSequence);
			str.append(",");

		}
		cur = cur.next;
		str.append(cur.dnaSequence);
		return str.toString();

	}

	@Override
	public void append(String dnaSequence) {
		DnaSequenceNode cur;
		DnaSequenceNode newNode;
		cur = top;
		if (top == null) {
			DnaSequenceNode appendingNode = new DnaSequenceNode(dnaSequence);
			top = appendingNode;
			top.next = null;
			top.previous = null;
		}
		if (top != null) {
			DnaSequenceNode appendingNode = new DnaSequenceNode(dnaSequence);
			newNode = appendingNode;
			while (cur.next != null) {
				cur = cur.next;
			}
			cur.next = newNode;
			newNode.previous = cur;
			appendCount++;
		}
		count++;
		// TODO Auto-generated method stub

	}

	@Override
	public DnaStrand cutSplice(String enzyme, String splicee) {
		// TODO Auto-generated method stub
		LinkedDnaStrand newList = null;
		int enzymeLength = enzyme.length();
		int stringLength = top.dnaSequence.length();
		int startingValue = 0;
		boolean first = true;
		for (int i = 0; i <= (stringLength - enzymeLength); i++) {
			if (top.dnaSequence.regionMatches(i, enzyme, 0, enzymeLength) && first == true) {
				if (i != 0) {
					newList = new LinkedDnaStrand(top.dnaSequence.substring(startingValue, i));
					startingValue = i + enzymeLength;
					newList.append(splicee);
				} else {
					newList = new LinkedDnaStrand(splicee);
					startingValue = i + enzymeLength;
				}
				first = false;
			} else if (top.dnaSequence.regionMatches(i, enzyme, 0, enzymeLength)) {
				newList.append(top.dnaSequence.substring(startingValue, i));
				startingValue = i + enzymeLength;
				newList.append(splicee);
			}
			if (i == (stringLength - enzymeLength) && (i + enzymeLength) != (startingValue)) {
				newList.append(top.dnaSequence.substring(startingValue, stringLength));
			}
		}

		return newList;
	}

	@Override
	public DnaStrand createReversedDnaStrand() {
		// TODO Auto-generated method stub
		StringBuilder reversed = new StringBuilder(toStrings());
		String newString = reversed.reverse().toString();
		String parts[] = newString.split(",");
		LinkedDnaStrand newRStrand = new LinkedDnaStrand(parts[0]);
		for (int i = 1; i < parts.length; i++) {
			newRStrand.append(parts[i]);
		}
		return newRStrand;
	}

	@Override
	public int getAppendCount() {
		// TODO Auto-generated method stub
		return appendCount;
	}

	@Override
	public DnaSequenceNode getFirstNode() {
		// TODO Auto-generated method stub
		return top;
	}

	@Override
	public int getNodeCount() {
		// TODO Auto-generated method stub
		return count;
	}

}
