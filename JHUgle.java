import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.regex.Pattern;
import java.util.Deque;
import java.util.ArrayDeque;
import java.io.IOException;
import java.util.Iterator;
import java.util.Scanner;

public final class JHUgle {

    private JHUgle() {}

    public static void main(String[] args) throws IOException {
        Map<String, ArrayList<String>> map = new BinarySearchTreeMap<>();
        Deque<ArrayList<String>> stack = new ArrayDeque<ArrayList<String>>();

        Pattern pattern = Pattern.compile("\\s+");

        FileReader fr = null;
        BufferedReader reader = null;
        try {
            String FILENAME = args[0];
            fr = new FileReader(FILENAME);
            reader = new BufferedReader(fr);
        } catch (Exception e) {
            System.err.println("Invalid Input File.");
            System.exit(0);
        }

        String line;
        String lastSite = "";
        while ((line = reader.readLine()) != null) {
            String[] words = pattern.split(line);
            if (words[0].length() == 0) {
                continue;
            }
            for (String word: words) {
                try { 
                    if (word.charAt(6) == '/') {
                        //code for what to do with websites:
                        lastSite = word;
                        continue;
                    }
                } catch (StringIndexOutOfBoundsException e) {

                }
                //code for what to do with words:
                if (!map.has(word)) {
                    ArrayList<String> newList = new ArrayList<String>();
                    newList.add(lastSite);
                    map.insert(word, new ArrayList<String>(newList));
                } else {
                    map.get(word).add(lastSite);
                }
            }
        }
        reader.close();
        fr.close();

        Scanner scan = new Scanner(System.in);
        System.out.println("Index Created");
        while (true) {
            System.out.print("> ");
            String command = scan.next();
            if (command.equals("!")) {
                System.exit(0);
            } else if (command.equals("?")) {
                if (stack.isEmpty()) {
                    continue;
                }
                for (int i = 0; i < stack.peek().size(); i++) {
                    System.out.println(stack.peek().get(i));
                }
	    
            } else if (command.equals("&&")) {
                ArrayList<String> firstOut = null;
                ArrayList<String> secondOut = null;
                try {
                    firstOut = stack.pop();
		} catch (java.util.NoSuchElementException e) {
                    System.err.println("Not enough elements on stack to AND: 0 elements");
		}
		try {
                    secondOut =stack.pop();
		    firstOut.retainAll(secondOut);
		    stack.push(firstOut);
		} catch (java.util.NoSuchElementException e) {
		    stack.push(firstOut);
                    System.err.println("Not enough elements on stack to AND: 1 element");
		}
            } else if (command.equals("||")) {
                ArrayList<String> firstOut = null;
                ArrayList<String> secondOut = null;
                try {
                    firstOut = stack.pop();
		} catch (java.util.NoSuchElementException e) {
                    System.err.println("Not enough elements on stack to OR: 0 elements");
		}
		try {
                    secondOut = stack.pop();
		    int size1 = firstOut.size();
		    int size2 = secondOut.size();
		    for (int i = 0; i < size2; i++) {
                        boolean toAdd = true;
			for (int j = 0; j < size1; j++) {
                            if (secondOut.get(i).equals(firstOut.get(j))) {
                                toAdd = false;
				break;
			    }
			}
			if (toAdd) {
                            firstOut.add(secondOut.get(i));
			}
		    }
		    stack.push(firstOut);
		} catch (java.util.NoSuchElementException e) {
                    stack.push(firstOut);
		    System.err.println("Not enough elements on stack to OR: 1 element");
		}          
	    } else {
	        try {
                    stack.push(new ArrayList<String>(map.get(command)));
	        } catch (IllegalArgumentException e) {
                    stack.push(new ArrayList<String>());
	        }
	    }
	}
    }
}

