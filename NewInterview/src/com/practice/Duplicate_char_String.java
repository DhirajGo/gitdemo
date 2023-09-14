package com.practice;

public class Duplicate_char_String {

	public static void main(String[] args) {
		String s="coconut";
		char[]a=s.toCharArray();
		for(int i=0;i<s.length()-1;i++)
		{
			for(int j=i+1;j<s.length();j++)
			{
				if(a[i]==a[j])
				{
					System.out.println(a[j]);
				}
			}
		}

	}

}
