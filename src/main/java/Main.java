import FactoryPerson.AbstractFactory;
import FactoryPerson.FactoryProducer;
import FactoryPerson.Person;
import com.google.common.collect.Ordering;
import org.omg.CORBA.ORBPackage.InvalidName;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {
    public static void main(String[] args) throws InvalidName {


        AbstractFactory personFactory = FactoryProducer.getFactory("Person");

        List<Person> toSort = new ArrayList<>();

        for ( int i = 0; i < 1000; i++ ) {
            Person newperson = personFactory.getPerson();
            toSort.add(newperson);
        }

        Ordering<Person> byLength = new OrderingByLenght();
        Collections.sort(toSort, byLength);

        for ( Person person : toSort){
            System.out.println(person);
        }

    }

    private static class OrderingByLenght extends Ordering<Person> {
        @Override
        public int compare(@Nullable Person left, @Nullable Person right) {
            if ( left.getName().compareTo(right.getName()) == 0 ) {
                if ( left.getSurname().compareTo(right.getSurname()) == 0) {
                    return left.getEmail().compareTo(right.getEmail());
                }
                else return  left.getSurname().compareTo(right.getSurname());
            }
            else return  left.getName().compareTo(right.getName());
        }
    }
}
