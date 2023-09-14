package com.practice;

public class Uniqe_char_String {

	public static void main(String[] args) {
		String s="abcdeeff";
		UniqeCharacter(s);
	}

	private static void UniqeCharacter(String s) {
		String uniqe="";
		for(int i=0;i<s.length();i++)
		{
			if(uniqe.indexOf(s.charAt(i))==-1)
			{
				uniqe=uniqe+s.charAt(i);
			}
		}
		System.out.println(uniqe);
		
	}

}
