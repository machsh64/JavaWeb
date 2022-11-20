package test;

/**
 * @program: WebCode
 * @author: Ren
 * @create: 2022-11-16 00:19
 * @description:
 **/
public class Bank {
    int savedMoney;
    double year;
    double interest;
    double interestRate = 0.29;
    double rate;

    public Bank(int savedMoney,double year,double rate){
        this.savedMoney = savedMoney;
        this.year = year;
        this.rate = rate;
    }

    public double computerInterest() {
        interest = year * interestRate * savedMoney;
        return interest;
    }

    public void setInterestRate() {
        interestRate = rate;
    }
}

class ConstructionBank extends Bank {
    double year;

    public ConstructionBank(int savedMoney, double year,double rate) {
        super(savedMoney, year,rate);
        this.year = year;
    }

    @Override
    public double computerInterest() {
        super.year = (int) year;
        double r = year - (int) year;
        int day = (int) (r * 1000);
        double yearInterest = super.computerInterest();
        double dayInterest = day * 0.0001 * savedMoney;
        interest = yearInterest + dayInterest;
        System.out.println(savedMoney + "元存在建设银行" + (int) year + "年零" + day + "天的利息：" + interest + "元");
        return interest;
    }
}

class BankOfDalian extends Bank {
    double year;

    public BankOfDalian(int savedMoney, double year,double rate) {
        super(savedMoney, year,rate);
        this.year = year;
    }

    @Override
    public double computerInterest() {
        super.year = (int) year;
        double r = year - (int) year;
        int day = (int) (r * 1000);
        double yearInterest = super.computerInterest();
        double dayInterest = day * 0.00012 * savedMoney;
        interest = yearInterest + dayInterest;
        System.out.println(savedMoney + "元存在大连银行" + (int) year + "年零" + day + "天的利息：" + interest + "元");
        return interest;
    }
}

class SaveMoney {
    public static void main(String[] args) {
        double interest1 = getInterest(new ConstructionBank(8000,8.236,0.035));

    }

    public static double getInterest(Bank bank) {
        double comInter = bank.computerInterest();
        bank.year = (int) bank.year;
        return comInter;
    }
}