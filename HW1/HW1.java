import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;


public class HW1 {

	public static void main(String[] arg) throws Exception{
		
		
		String Input_File = arg[0];
		String Output_File = arg[1];
				
		
		BufferedReader reader = new BufferedReader(new FileReader(Input_File));
		
		String line;
		
		ArrayList<String>Lines = new ArrayList<String>();

		while((line = reader.readLine()) != null) {
			String[] content = line.split("\\.");
			for(int i = 0; i < content.length; i++) {
				Lines.add(content[i].toString().toLowerCase().trim());
			}
		}
		
		ArrayList<String>editedLines = Lines;
		
		
		ArrayList<String>specialWords = new ArrayList<String>();
			specialWords.add("the");
			specialWords.add("of");
			specialWords.add("was");
			specialWords.add("but the");
			specialWords.add("it was");
			specialWords.add("in my");
			
		
		ArrayList<sentence>Sentences = new ArrayList<sentence>();
		for(int i = 0; i < editedLines.size(); i++) {
			Sentences.add(new sentence(editedLines.get(i)));
		}
		
		int m1 = 0;
		int m2 = 0;
		int m3 = 0;
		int highestFrequency = 0;
		int ArrayLength = 0;
		
		
		ArrayList<String>words = new ArrayList<String>();
		ArrayList<Integer>wordCount = new ArrayList<Integer>();
		
		
		
		for(int i = 0; i < Sentences.size(); i++) {
			ArrayLength = Sentences.get(i).getArrayLength();
			sentence currentSentence = Sentences.get(i);
			
			for(int j = 0; j < ArrayLength; j++) {
				
				if(words.contains(currentSentence.getWord(j))) {
					
					int currentIndex = 0;
					int currentFrequency = 0;
					int addFrequency = 0;
					for(int k = 0; k < words.size(); k++) {
						if(words.get(k).equals(currentSentence.getWord(j))) {
							currentIndex = k;
						}
					}
					currentFrequency = wordCount.get(currentIndex);
					addFrequency = currentSentence.getFrequencyOfWord(currentSentence.getWord(j));
					
					
					wordCount.set(currentIndex, currentFrequency + addFrequency);
				} else {
					words.add(Sentences.get(i).getWord(j));
					wordCount.add(Sentences.get(i).getFrequencyOfWord(j));
				}
			}
		}
		
		
		for(int i = 0; i < wordCount.size(); i++) {
			
			if(wordCount.get(i) > m3) {
				
				m3 = wordCount.get(i);
			}
			if(wordCount.get(i) > m2) {
				m3 = m2;
				m2 = wordCount.get(i);
			}
			if(wordCount.get(i) > m1) {
				m2 = m1;
				m1 = wordCount.get(i);
			}
		}
		
		
		for(int i = 0; i < Sentences.size(); i++) {
			ArrayLength = Sentences.get(i).getArrayLength();
			for(int j = 0; j < ArrayLength; j++) {
				if(Sentences.get(i).getFrequencyOfWord(j) > highestFrequency) {
					highestFrequency = Sentences.get(i).getFrequencyOfWord(j);
				}
			}
		}
		

		
				
				
				
				
				FileWriter a = new FileWriter(Output_File + "_" + Integer.toString(1) + ".txt");
				BufferedWriter b = new BufferedWriter(a);
				for(int i = 0; i < wordCount.size(); i++) {
					if(wordCount.get(i) == m1) {
						b.write(words.get(i) + ":" + wordCount.get(i));
						b.newLine();
					}
				}
				b.close();
				
				
						
						a = new FileWriter(Output_File + "_" + Integer.toString(2) + ".txt");
						b = new BufferedWriter(a);
						for(int i = 0; i < wordCount.size(); i++) {
							if(wordCount.get(i) == m3) {
								b.write(words.get(i) + ":" + wordCount.get(i));
								b.newLine();
							}
						}
						b.close();
						
				
						
						a = new FileWriter(Output_File + "_" + Integer.toString(3) + ".txt");
						b = new BufferedWriter(a);
						for(int i = 0; i < Sentences.size(); i++) {
								for(int j = 0; j < Sentences.get(i).getArrayLength(); j++) {
									if(Sentences.get(i).getFrequencyOfWord(j) == highestFrequency) {
										b.write(Sentences.get(i).getWord(j) + ":" + highestFrequency + ":" + Sentences.get(i).getSentence());
										b.newLine();
									}
								}
						}
						b.close();
						int max = 0;
					
				
						for(int p = 4; p < 10; p++) {
							String wrd = specialWords.get(p - 4);
							a = new FileWriter(Output_File + "_" + p + ".txt");
							b = new BufferedWriter(a);
							max = 0;
							for(int i = 0; i < Sentences.size(); i++) {
								for(int j = 0; j < Sentences.get(i).getArrayLength(); j++) {
									if(Sentences.get(i).getWord(j).equals(wrd)){
										if(Sentences.get(i).getFrequencyOfWord(j) > max) {
											max = Sentences.get(i).getFrequencyOfWord(j);
										}
									}
								}
							}
							for(int i = 0; i < Sentences.size(); i++) {
								if (max == 0) {
									b.write(wrd + max + ":" + Sentences.get(i).getSentence());
									b.newLine();
								} else {
								for(int j = 0; j < Sentences.get(i).getArrayLength(); j++) {
									if(Sentences.get(i).getWord(j).equals(wrd) && Sentences.get(i).getFrequencyOfWord(j) == max){
										b.write(wrd + max + ":" + Sentences.get(i).getSentence());
										b.newLine();
									}
								}}
							}
							b.close();
						}
			
		reader.close();
		

		
	}

}
