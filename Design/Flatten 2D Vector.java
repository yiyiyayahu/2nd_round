/*
Implement an iterator to flatten a 2d vector.

For example,
Given 2d vector =

[
  [1,2],
  [3],
  [4,5,6]
]
By calling next repeatedly until hasNext returns false, the order of elements returned by next should be: [1,2,3,4,5,6].

Hint:

How many variables do you need to keep track?
Two variables is all you need. Try with x and y.
Beware of empty rows. It could be the first few rows.
To write correct code, think about the invariant to maintain. What is it?
The invariant is x and y must always point to a valid point in the 2d vector. Should you maintain your invariant ahead of time or right when you need it?
Not sure? Think about how you would implement hasNext(). Which is more complex?
Common logic in two different places should be refactored into a common method.
*/


/*
我觉得我这个写法好别扭啊。。。看看别人怎么写的
*/

public class Vector2D {
    int x = 0;
    int y = -1;
    List<List<Integer>> list;
    public Vector2D(List<List<Integer>> vec2d) {
        list = vec2d;
    }

    public int next() {
        return list.get(x).get(y);
    }

    public boolean hasNext() {
        return getNext() != null;
    }
    
    public Integer getNext() {
        if(x >= list.size()) return null;
        List<Integer> l = list.get(x);
        y++;
        while(y >= l.size()) {
            x ++;
            y = 0;
            if(x >= list.size()) return null;
            l = list.get(x);
        }
        return l.get(y);
    }
}

/**
 * Your Vector2D object will be instantiated and called as such:
 * Vector2D i = new Vector2D(vec2d);
 * while (i.hasNext()) v[f()] = i.next();
 */
