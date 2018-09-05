package com.cll.toy.filebrowewrlib.filebrower;

/**
 * Created by cll on 2018-09-05.
 */

public abstract class test2 {

    private void start(){
//        String s = test1.class.getSimpleName()

    }

    String aa;

    public String getAa() {
        return aa;
    }

    public void setAa(String aa) {
        this.aa = aa;
        ss();
    }

    private void ss(){
        String s = "aa";
    }

    abstract void aa();
    class jj extends test2{

        @Override
        void aa() {

        }
    }
}
