package hillstations;

class Hillstationegs{
    void famousfood(){System.out.println("This hill station has local cuisine.");
    }
    void famousfor(){ System.out.println("This is a popular hill station.");
    }
}
class Manali extends Hillstationegs{
    @Override
    void famousfood(){ System.out.println("Manali: Famous for Siddu and Trout fish curry.");}
    @Override
    void famousfor(){
        System.out.println("Manali: Famous for Rohtang Pass and skiing.");
    }
}
class Ooty extends Hillstationegs{
    @Override
    void famousfood(){
        System.out.println("Ooty: Famous for homemade chocolate and Nilgiri tea.");
    }
    @Override
    void famousfor(){System.out.println("Ooty: Famous for tea gardens and Nilgiri Mountain Railway.");
    }
}
class Darjeeling extends Hillstationegs{
    @Override
    void famousfood(){
        System.out.println("Darjeeling: Famous for first-flush tea and momos.");
    }
    @Override
    void famousfor(){
        System.out.println("Darjeeling: Famous for Kanchenjunga views and the toy train.");
    }
}

public class Hillstation{
    public static void main(String[] args) {
        Hillstationegs h;
        h=new Manali();
        h.famousfood();
        h.famousfor();

        System.out.println();
        h=new Ooty();
        h.famousfood();
        h.famousfor();

        System.out.println();
        h=new Darjeeling();
        h.famousfood();
        h.famousfor();
    }
}