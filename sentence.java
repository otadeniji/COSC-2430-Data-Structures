  
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class sentence {
	
	ArrayList<String>words = new ArrayList<String>();
	ArrayList<Integer>FrequencyOfWord = new ArrayList<Integer>();
	String S = "";
	String[] slash;
	
	
	boolean found_most_frequent = false;
	
	ArrayList<String> mostFrequentwords = new ArrayList<String>();
	int maximumFrequency;
	
	sentence(String sent){
		S = sent;
		DoWork();
	}
	
	String getSentence() {
		return S;
	}
	
	int getMaxFrequency() {
		return maximumFrequency;
	}
	
	int getArrayLength() {
		return words.size();
	}
	
	String getWord(int index) {
		return words.get(index);
	}
	int getFrequencyOfWord(int index) {
		return FrequencyOfWord.get(index);
	}
	
	
	void _assignMostFrequent() {
		found_most_frequent = true;
		maximumFrequency = 0;
		for(String word : words) {
			int this_frequency = getFrequencyOfWord(word);
			if(this_frequency > maximumFrequency) {
				mostFrequentwords.clear();
				maximumFrequency = this_frequency;
				mostFrequentwords.add(word);
			} else if (this_frequency == maximumFrequency) {
				mostFrequentwords.add(word);
			}
		}
	}
	
	ArrayList<String> getMostFrequent() {
		if(! found_most_frequent) {
			_assignMostFrequent();
		}
		return mostFrequentwords;
	}
	
	void printMostFrequent() {
		System.out.printf("Printing most frequent words for sentence '%s'\n", S);
		for(String wrd: getMostFrequent()) {
			System.out.printf("'%s' has a frequency of '%d'\n", wrd, maximumFrequency);
		}
	}
	
	int getFrequencyOfWord(String wrd) {
		for(int i = 0; i < words.size(); i++) {
			if(words.get(i).equals(wrd)) {
				return FrequencyOfWord.get(i);
			}
		}
		return 0;
	}
	
	void print_word_frequencies() {
		System.out.printf("Printing frequencies for sentence: %s\n", S);

		for(int ii = 0; ii < words.size(); ii++) {
			System.out.printf("Word: '%s' has a count of: '%d'\n", words.get(ii), FrequencyOfWord.get(ii));
		}
	}
	
	
			Pattern bt = Pattern.compile("but the");
			Pattern iw = Pattern.compile("it was");
			Pattern im = Pattern.compile("in my");
	
	void DoWork() {
		
		Matcher bt_m = bt.matcher(S);
		while(bt_m.find()) {
			if(words.contains("but the")) {
				for(int i = 0; i < words.size(); i++) {
					if(words.get(i).equals("but the")) {
						FrequencyOfWord.set(i, FrequencyOfWord.get(i) + 1);
					}
				}
			} else {
				words.add("but the");
				FrequencyOfWord.add(1);
			}
		}
		Matcher iw_m = iw.matcher(S);
		while(iw_m.find()) {
			if(words.contains("it was")) {
				for(int i = 0; i < words.size(); i++) {
					if(words.get(i).equals("it was")) {
						FrequencyOfWord.set(i, FrequencyOfWord.get(i) + 1);
					}
				}
			} else {
				words.add("it was");
				FrequencyOfWord.add(1);
			}
		}
		Matcher im_m = im.matcher(S);
		while(im_m.find()) {
			if(words.contains("in my")) {
				
				

				for(int i = 0; i < words.size(); i++) {
					words.add("in my");
					FrequencyOfWord.add(1);
				}
			}
		}
		
		
		
		
		slash = S.split(" ");
		for(int i = 0; i < slash.length; i++) {
			if(words.contains(slash[i])) {
				for(int j = 0; j < words.size(); j++) {
					if(words.get(j).equals(slash[i])) {
						FrequencyOfWord.set(j, FrequencyOfWord.get(j) + 1);
					}
				}
			} else {
				words.add(slash[i]);
				FrequencyOfWord.add(1);
			}
		}
	}
	
	
}
