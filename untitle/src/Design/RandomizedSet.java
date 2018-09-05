package Design;

import java.sql.Time;
import java.util.*;

public class RandomizedSet {
    ArrayList<Integer> arrayList=new ArrayList();
    Map<Integer,Integer> map=new HashMap<>();
    /** Initialize your data structure here. */
    public RandomizedSet() {

    }

    /** Inserts a value to the set. Returns true if the set did not already contain the specified element. */
    public boolean insert(int val) {
        if(!arrayList.contains(val)){
            map.put(val,arrayList.size());
            arrayList.add(val);
            return true;
        }
        else return false;
    }

    /** Removes a value from the set. Returns true if the set contained the specified element. */
    public boolean remove(int val) {
        if(arrayList.contains(val)){
            int valPos=map.get(val);
            int lastval=arrayList.get(arrayList.size()-1);
            map.put(lastval,valPos);
            arrayList.remove(valPos);
            arrayList.add(valPos,lastval);
            arrayList.remove(arrayList.size()-1);
            map.remove(val);

            return true;
        }
        return false;
    }

    /** Get a random element from the set. */
    public int getRandom() {
        Random random=new Random();
        return arrayList.get(random.nextInt(arrayList.size()));
    }
}
