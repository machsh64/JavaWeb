package test.sch;

/**
 * @program: WebCode
 * @author: Ren
 * @create: 2022-11-09 08:23
 * @description:
 **/
public class Chinese extends People{

    public Chinese(double height, double weight) {
        super(height, weight);
    }

    @Override
    public void speakHello(){
        System.out.println("中式问候");
    }

    public void averageHeight(){
        System.out.println("中国人平均身高：" + height + "cm");
    }

    public void averageWeight(){
        System.out.println("中国人平均体重：" + weight + "kg");
    }
}
