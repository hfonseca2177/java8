package com.hfonseca.java8;

public class DefaultInterfaceMethodcatalog {

    /*
    Lets say you have a interface like this:

    interface Phone{
        void call();
    }

    And a implementation

    class AndroidPhone implements Phone{

    public void call(){
        //implementation
    }

    }

     */

    //if you need to add another method and don't want to impact all existing implementations
    //you can write the new method direct on interface with a 'default' modifier
    interface Phone{
        //existing method
        void call();
        //new method
        default void sendMenssage(String message){
           System.out.print(message);
        }
    }

    //existing implemented class that o don't want to change
    class AndroidPhone implements Phone{
        public void call(){
            //implementation
        }
    }

    public static void main(String[] args) {
        //creating and instance
        Phone myAndroidPhone = new DefaultInterfaceMethodcatalog().new AndroidPhone();
        //calling Default method
        myAndroidPhone.sendMenssage("Hello new method");
    }

}
