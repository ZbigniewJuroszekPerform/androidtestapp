package com.sample.perform.app.utli;

import com.azimolabs.conditionwatcher.Instruction;


public class NewsInstruction extends Instruction {
    @Override
    public String getDescription() {
        return "Wait for news visibility";
    }

    @Override
    public boolean checkCondition() {
        return false;
    }
}
