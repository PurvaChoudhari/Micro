package com.demo.department.entity;

public class person {
	
	private int id;
	private String name;
	
	
	public person()
	{
		
	}
	public person(int id,String name)
	{
		this.id=id;
		this.name=name;
	}
	
	
	
	public static void main(String[] args) {
		
		person p1=new person();
		person p=new person(1,"piyu");
		System.out.println(p);
		
		
		for(int i=1;i<=5;i++)
		{
			for(int j=1;j<=i;j++)
			{
				System.out.print(i);
			}
			System.out.println();
		}
	}

}
