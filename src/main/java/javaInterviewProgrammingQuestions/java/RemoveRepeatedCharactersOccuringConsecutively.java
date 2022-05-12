package javaInterviewProgrammingQuestions.java;

/*
 * Remove the repetitive characters that occurs consecutively
 * Input String: weelccoommee hhoommee
 * Output String: welcome home
 */
public class RemoveRepeatedCharactersOccuringConsecutively {

	public static void main(String[] args) {
		String input = "weelccoommee hhoommee java !!!!";
		System.out.println("input string: "+input);
		String output="";
		int len=input.length();
		for (int i = 0; i <input.length()-1; i++) {
			if(input.charAt(i)==input.charAt(i+1)) {
				output=output+input.charAt(i);
				i=i+1;
			}else if(input.charAt(i)!=input.charAt(i+1)|| input.charAt(i)==' ') {
				output=output+input.charAt(i);
			}
		}
		if(input.charAt(len-1)!=input.charAt(len-2)) {
			output=output+input.charAt(len-1);
		}
		System.out.println(output);
	}
}
