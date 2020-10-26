package com.yingxue.lesson.task;

public class Model {
    private static Model model = null;
    public static Model getModel(){
        if (model==null){
            model = new Model();
        }
        return model;
    }


    private static  Model model2 = new Model();
    public static  Model getInstance(){
        String a = "cccc";
        return model2;
    }

}
