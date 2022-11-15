package test.sch;

/**
 * @program: WebCode
 * @author: Ren
 * @create: 2022-11-09 08:23
 * @description:
 **/
public class People {
    protected double height;
    protected double weight;

    public People(double height,double weight){
        this.height = height;
        this.weight = weight;
    }

    public static void main(String[] args) {
        show(new Chinese(1.92,181));
        System.out.println();
        show(new American(1.45,45));
        System.out.println();
        show(new BeijingMan(1.63,200));
    }

    public static void show (People people){
        people.speakHello();
        people.averageHeight();
        people.averageWeight();
    }

    public void speakHello(){
        System.out.println("我是人类");
    }

    public void averageHeight(){
        System.out.println("身高：" + height);
    }

    public void averageWeight(){
        System.out.println("体重：" + weight);
    }
}
