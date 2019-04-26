import java.util.Vector;

public class Word {
	public String line;
	public String word ;
	public Vector<String> wordClass = new Vector<String>(2) ;		
	public Vector<String> meaning = new Vector<String>(2);
	public int n ;				// the number of array
	public int size = 0;
	public int Samesize = 1;
	public int initNum ;

	public Word(String line) {

		if(line.equals("")) {
			n = -1;
		}
		else{			
			this.line = line;
//			String [] splited = line.split(" ",3);
//			word = line.split("\\(")[0];
			int a = line.indexOf("(");
			int b = line.indexOf(")");
			
			word = line.substring(0,a-1);
			
			wordClass.add( line.substring(a,b) );
			if( b != line.length() - 1 )
				meaning.add( line.substring( b+1 ) );
			else
				meaning.add(" ");
			n=size;
			initNum = n ;
			size++;
		}
	}	

	public String toString() {
		String out = line;
		for( int i = 1; i < size ; i++ ) 
			out += "\n" + wordClass.elementAt(i) + " " + meaning.elementAt(i) ;

		return out;
	}

	public String GetWord() {
		return word;
	}

	public String GetFullWord() {
		return wordClass+")";
	}

}
