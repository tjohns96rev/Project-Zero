package com.revature;

import io.javalin.Javalin;

public class MainDriver {

	public static void main(String[] args) {
		Javalin app = Javalin.create().start(7000);
	}
}
