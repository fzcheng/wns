package prj.com.util.tag;

import java.io.Serializable;
import java.util.Set;
import java.util.TreeSet;

/**
 * cmd命令计数器
 * @author hhj
 * Created on 2008-8-15 下午03:00:41
 */
public class Counter implements java.util.Iterator, Serializable {

    boolean wrap = false;

    // Attributes ----------------------------------------------------
    long first = 1;
    long current = first;
    long interval = 1;
    long last = -1;


    public void setAdd(long addition) {
        current += addition;
    }

    public void setCurrent(long current) {
        this.current = current;
    }

    public long getCurrent() {
        return current;
    }

    public void setFirst(long first) {
        this.first = first;
        current = first;
    }

    public long getFirst() {
        return first;
    }

    public void setInterval(long interval) {
        this.interval = interval;
    }

    public long getInterval() {
        return interval;
    }

    public void setLast(long last) {
        this.last = last;
    }

    public long getLast() {
        return last;
    }

    // Public --------------------------------------------------------
    public String getNext() {
    	if(current==Long.MAX_VALUE)
    		current = first;
        long next = current;
        current += interval;

        if (wrap && (current > last)) {
            current -= ((1 + last) - first);
        }

        return Long.toHexString(next);
    }

    public String getPrevious() {
        current -= interval;

        if (wrap && (current < first)) {
            current += (last - first + 1);
        }

        return Long.toHexString(current);
    }

    public void setWrap(boolean wrap) {
        this.wrap = wrap;
    }

    public boolean isWrap() {
        return wrap;
    }

    public boolean hasNext() {
        return ((last == -1) || wrap) ? true : (current <= last);
    }

    public Object next() {
        //return new Long(getNext());
    	return getNextLong();
    }
    
    public Long getNextLong() {
    	if(current==Long.MAX_VALUE)
    		current = first;
        long next = current;
        current += interval;

        if (wrap && (current > last)) {
            current -= ((1 + last) - first);
        }

        return next;
    }

    public void remove() {
        // Do nothing
    }
    
    public static void main(String[] str){
    	Counter c = new Counter();
    	Set<String> set = new TreeSet<String>();
    	for(int i =0;i<Long.MAX_VALUE;i++){
    	   set.add(c.getNext());
    	   System.out.println(i+1==set.size());
        }
    	
    	
    }
}
