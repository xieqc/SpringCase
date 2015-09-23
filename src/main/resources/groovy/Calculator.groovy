package com.xie.springcase.script;
/**
 * Created by xieqinchao on 15-9-21.
 */
class Calculator implements ICalculator {
    @Override
    int add(int x, int y) {
        return x + y;
    }
}
