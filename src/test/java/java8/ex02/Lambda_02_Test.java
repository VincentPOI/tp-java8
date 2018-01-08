package java8.ex02;

import java8.data.Account;
import java8.data.Data;
import java8.data.Person;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;


/**
 * Exercice 02 - Map
 */
public class Lambda_02_Test {

    // tag::PersonToAccountMapper[]

    interface PersonMapper<T>{
    	T map(Person p);
    }
    // end::PersonToAccountMapper[]
    

    // tag::map[]
    private <T> List<T> map(List<Person> personList, PersonMapper<T> mapper) {
    	List<T> list = new ArrayList<T>(); 
		for (Person p : personList) {			
				list.add(mapper.map(p));
		}
		return list;
    }
    // end::map[]


    // tag::test_map_person_to_account[]
    @Test
    public void test_map_person_to_account() throws Exception {

        List<Person> personList = Data.buildPersonList(100);

        PersonMapper<Account> personToAccountMapper = p -> new Account(p, 100);
        List<Account> result = map(personList, personToAccountMapper);

        assert result.size() == personList.size();
        for (Account account : result) {
            assert account.getBalance().equals(100);
            assert account.getOwner() != null;
        }
    }
    // end::test_map_person_to_account[]

    // tag::test_map_person_to_firstname[]
    @Test
    public void test_map_person_to_firstname() throws Exception {
    	
        List<Person> personList = Data.buildPersonList(100);
        PersonMapper<String> firstNameMapper = p -> p.getFirstname();
        // TODO transformer la liste de personnes en liste de prénoms
        List<String> result = map(personList, firstNameMapper);

        assert result.size() == personList.size();
        for (String firstname : result) {
            assert firstname.startsWith("first");
        }
    }
    // end::test_map_person_to_firstname[]
}