package test.sch;

/**
 * @program: WebCode
 * @author: Ren
 * @create: 2022-11-09 08:24
 * @description:
 **/
public class American extends People{

    public American(double height, double weight) {
        super(height, weight);
        americanBoxing();
    }

    public void americanBoxing(){
        System.out.println("直拳，勾拳，混合拳");
    }

    @Override
    public void speakHello(){
        System.out.println("now do yo do?");
    }

    public void averageHeight(){
        System.out.println("美国人平均身高：" + height + "cm");
    }

    public void averageWeight(){
        System.out.println("美国人平均体重：" + weight + "kg");
    }
}
