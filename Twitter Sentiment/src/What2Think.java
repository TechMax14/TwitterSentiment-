import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import twitter4j.TwitterException;

public class What2Think {

	public static void main(String[] args) throws TwitterException {
		GUI gui = new GUI();
		DecimalFormat df = new DecimalFormat("#0.##");
		Stack<String> s1 = new Stack<String>();
		Stack<String> s2 = new Stack<String>();
		Stack<String> s3 = new Stack<String>();
		Stack<String> s4 = new Stack<String>();
		Stack<String> s0 = new Stack<String>();
		Stack<String> sO = new Stack<String>();
		List<String> q = new ArrayList<String>();
		int c0 = 0;
		int c1 = 0;
		int c2 = 0;
		int c3 = 0;
		int c4 = 0;
		int cOther = 0;
		int j = 0;
		int total = 0;
		String topic = gui.searchFor.getText();
		int tweetNum = Integer.parseInt(gui.range.getText());
		ArrayList<String> tweets = TwitterSent.getTweets(topic);
		NLP.init();
		GUI.showMessage("Live Tweets:\n");
		for(String tweet : tweets) {
			GUI.showMessage("(" + (j+1) + ") " + tweet + " | [" + NLP.findSentiment(tweet) + "]\n");
			if (NLP.findSentiment(tweet) == 0) { c0++; s0.push("█");}
			else if (NLP.findSentiment(tweet) == 1) { c1++; s1.push("█"); }
			else if (NLP.findSentiment(tweet) == 2) { c2++; s2.push("█"); }
			else if (NLP.findSentiment(tweet) == 3) { c3++; s3.push("█"); }
			else if (NLP.findSentiment(tweet) == 4) { c4++; s4.push("█"); }
			else { cOther++; sO.push("█"); }
			total++;				j++;
			if(j >= tweetNum) break;
		}
		String title = topic.substring(0, 1).toUpperCase() + topic.substring(1);
		GUI.showMessage("\n----------------------------------------\n");
		GUI.showMessage("	             [ '"+title+"' Sentiment Graph ]   \n");
//		GUI.showMessage("Sentiment Scores: |-1| |0| |1| |2| |3| |Other|\n");
//		GUI.showMessage("Sentiment Gragh:  _________________________\n");
		for(int i = 0; i < total; i++) {
			//GUI.showMessage("--------------   |" + emptyStack(sN) + "| |" + emptyStack(s0) + "| |" + emptyStack(s1) + "| "
			//				+ "|" + emptyStack(s2) + "| |" + emptyStack(s3) + "| |" + emptyStack(sO) + "|\n");
			
			if((i+1) >= 100) {
				q.add("---------- "+(i+1)+"   "
										+ " |" + emptyStack(s0) + "|  "
										+ " |" + emptyStack(s1) + "|  "
										+ " |" + emptyStack(s2) + "|  " 
										+ " |" + emptyStack(s3) + "|  "
										+ " |" + emptyStack(s4) + "|  "
										+ " |" + emptyStack(sO) + "|\n");
			} else if(((i+1) >= 10) && ((i+1) < 100)) {
				q.add("----------- "+(i+1)+"   "
										+ " |" + emptyStack(s0) + "|  "
										+ " |" + emptyStack(s1) + "|  "
										+ " |" + emptyStack(s2) + "|  " 
										+ " |" + emptyStack(s3) + "|  "
										+ " |" + emptyStack(s4) + "|  "
										+ " |" + emptyStack(sO) + "|\n");
			} else 
				q.add("------------ "+(i+1)+"   "
										+ " |" + emptyStack(s0) + "|  "	
										+ " |" + emptyStack(s1) + "|  "
										+ " |" + emptyStack(s2) + "|  "
										+ " |" + emptyStack(s3) + "|  "
										+ " |" + emptyStack(s4) + "|  "
										+ " |" + emptyStack(sO) + "|\n");
		
		}
		for(int k = (total-1); k >= 0; k--) {
			
			GUI.showMessage(q.get(k));
		}
		GUI.showMessage("‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾\n");
		GUI.showMessage("Sentiment Scores:   | 0 |  | 1 |  | 2 |  | 3 |  | 4 | |Other|\n");
		GUI.showMessage("Sentiment Count:    | " + c0 + " | "
										  + " | " + c1 + " | "
										  + " | " + c2 + " | "
										  + " | " + c3 + " | "
										  + " | " + c4 + " | "
										  + "|   "+ cOther + "   |   "
										  + "   Total: [" + total + "] | Goal: [" + tweetNum + "]");
		GUI.showMessage("\n-----------------------------------------\n");
		GUI.showMessage("Out of " + total + " tweets involving the keyword '" + title + "':\n");
		GUI.showMessage("[ "+ df.format(((double) c0/total)*100) + "% ] are very negative\n");
		GUI.showMessage("[ "+ df.format(((double) c1/total)*100) + "% ] are negative\n");
		GUI.showMessage("[ "+ df.format(((double) c2/total)*100) + "% ] are neutral\n");
		GUI.showMessage("[ "+ df.format(((double) c3/total)*100) + "% ] are positive\n");
		GUI.showMessage("[ "+ df.format(((double) c4/total)*100) + "% ] are very positive\n");
		GUI.showMessage("[ "+ df.format(((double) cOther/total)*100) + "% ] are something else");

	}

	private static String emptyStack(Stack<String> s) {
		if (s.empty())
			return "◽";
		return (String) s.pop();
	}
	
}