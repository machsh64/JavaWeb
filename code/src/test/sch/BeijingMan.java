package test.sch;

/**
 * @program: WebCode
 * @author: Ren
 * @create: 2022-11-09 08:24
 * @description:
 **/
public class BeijingMan extends Chinese{

    public BeijingMan(double height, double weight) {
        super(height, weight);
        beijingOpera();
    }

    public void beijingOpera(){
        System.out.println("花脸，青衣，花旦和老生");
    }

    public void averageHeight(){
        System.out.println("北京人平均身高：" + height +"cm");
    }

    public void averageWeight(){
        System.out.println("北京人平均体重：" + weight+"kg");
    }
}
