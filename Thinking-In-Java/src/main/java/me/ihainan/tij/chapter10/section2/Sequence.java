package me.ihainan.tij.chapter10.section2;

/**
 * 验证内部类可以链接到外部类的无聊程序
 */

interface Selector {
    public boolean end();

    public Object current();

    public void next();
}

public class Sequence {
    private Object[] items;
    private int next = 0;

    public void add(Object object) {
        if (next < items.length) {
            items[next++] = object;
        }
    }

    public Sequence(int size) {
        items = new Object[size];
    }

    class SequenceSelector implements Selector {
        int i = 0;

        @Override
        public boolean end() {
            return i == items.length;
        }

        @Override
        public Object current() {
            return items[i];
        }

        @Override
        public void next() {
            if (i < items.length) {
                i++;
            }
        }
    }

    public SequenceSelector selector() {
        return new SequenceSelector();
    }

    public static void main(String[] args) {
        Sequence sequence = new Sequence(10);
        for (int i = 0; i < 10; ++i) {
            sequence.add(new Integer(i));
        }

        SequenceSelector selector = sequence.selector();
        while (!selector.end()) {
            System.out.println(selector.current());
            selector.next();
        }
    }
}
/* Output:
0
1
2
3
4
5
6
7
8
9
*/