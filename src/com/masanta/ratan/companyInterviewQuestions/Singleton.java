package com.masanta.ratan.companyInterviewQuestions;

public class Singleton {

        private static Singleton instance;

        public static Singleton getInstance() {
            if(instance != null){
                return instance;
            }
            synchronized (Singleton.class) {
                if (instance == null) {
                    instance = new Singleton();
                    return instance;
                }
            }
            return instance;
        }


        // Stop cloning


}
