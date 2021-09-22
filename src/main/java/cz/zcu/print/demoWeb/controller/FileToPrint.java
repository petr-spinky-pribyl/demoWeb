package cz.zcu.print.demoWeb.controller;

public class FileToPrint {
	private int index;
	private String name;

 	public FileToPrint(int index, String name) {
		super();
		this.index = index;
		this.name = name;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
