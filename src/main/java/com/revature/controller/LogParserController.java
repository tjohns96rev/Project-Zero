package com.revature.controller;

import com.revature.service.LogParser;

import io.javalin.http.Context;

public class LogParserController {
    public LogParser lp = new LogParser();

    public void getLogMetrics(Context ctx) {
        ctx.result(lp.getLogMetrics());
        ctx.status(200);
    }
}
