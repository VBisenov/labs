package humanResources;

import java.util.*;


public class LinkedList<X> implements List<X> {
    private Node head;
    private int size;

    public LinkedList(){
        head = new Node(null, null);
    }

    public void setSize(int size) {
        this.size = size;
    }

    private Node getNode(int index){
        if (index >= size) {
            throw new IndexOutOfBoundsException();
        }
        //todo возвращается head, т.к. при первой итерации index == i
        Node result = head;
        for (int i = 0; i < index; i++) {
            result = result.next;
        }
        return result;
    }

    public int getIndex(Object o){
        int count = 0;
        for (X x: this) {
            count++;
            if (o.equals(x)){
                return count;
            }
        }
        return -1;
    }

    public boolean addAll (X[] array){
        for (X el: array) {
                add(el);
        }
        return true;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean contains(Object o) {
        for (X x: this){
            if (o.equals(x)){
                return true;
            }
        }
        return false;
    }

    @Override
    public Iterator<X> iterator() {
        return this.listIterator();
    }

    @Override
    public Object[] toArray() {
        Object[] result = new Object[size];
        int count = 0;
        for (X x: this){
            result[count++] = x;
        }
        return result;
    }

    @Override
    public <T> T[] toArray(T[] a) {
        int i = 0;
        Object[] result = a;
        for (Node x = head; x != null; x = x.next)
            result[i++] = x.element;

        if (a.length > size)
            a[size] = null;

        return a;
    }

    @Override
    public boolean add(X x) {
        if (head.element == null){
            head.element = x;
            head.next = null;
            size++;
        } else {
            Node buffer = head;
            while (buffer.next != null) {
                buffer = buffer.next;
            }
            buffer.next = new Node(x, null);
            size++;
        }
        return true;
    }

//    @Override
//    public boolean remove(Object o) {
//        if (!this.contains(o)){
//            throw new NoSuchElementException("Element does not exist");
//        }
//        Node delete = head;
//        Node afterDelete = head;
//        Node beforeDelete = head;
//        for (int i = 0; i < indexOf(o); i++) {
//            delete = delete.next;
//        }
//        for (int i = 0; i < indexOf(o) + 1; i++) {
//            afterDelete = afterDelete.next;
//        }
//        for (int i = 0; i < indexOf(o) - 1; i++) {
//            beforeDelete = beforeDelete.next;
//        }
//
//       // delete = new Node(null, null);
//        afterDelete.next = beforeDelete;
//        size--;
//        return true;
//    }


    @Override
    public boolean remove(Object o) {
        int index = indexOf(o);
        if (index == 0){
            head = head.next;
        }
        Node buffer = getNode(index - 1);
        buffer.next = buffer.next.next;
        size--;
        return true;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        for (int i = 0; i < size; i++) {
            if (!c.contains(get(i))){
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean addAll(Collection<? extends X> c) {
        for (X x: c){
            add(x);
            return true;
        }
        return false;
    }

    @Override
    public boolean addAll(int index, Collection<? extends X> c) {
        if (index >= size){
            throw new IndexOutOfBoundsException();
        }

        return false;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        for (int i = 0; i < size; i++) {
            if (c.contains(get(i))){
                remove(get(i));
                size--;
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        for (int i = 0; i < size; i++) {
            if (!c.contains(get(i))){
                remove(get(i));
                size--;
                return true;
            }
        }
        return false;
    }

    @Override
    public void clear() {
        head = null;
        size = 0;
    }

    @Override
    public X get(int index) {
        return this.getNode(index).element;
    }

    @Override
    public X set(int index, X element) {
        if (index >= size){
            throw new IndexOutOfBoundsException();
        }

        return this.getNode(index).element = element;
    }

    @Override
    public void add(int index, X element) {
        if (index >= size){
            throw new IndexOutOfBoundsException();
        }

        if (getNode(index).next == null){
            Node newNode = new Node(element, null);
            size++;
        }

        Node buffer = head;
        for (int i = 0; i < index - 1; i++) {
            buffer = buffer.next;
        }
        Node newNode = new Node(element, buffer.next.next);
        buffer.next = newNode;
        size++;
    }

    @Override
    public X remove(int index) {
        int count = 0;
        Node temp = head;
        X element = null;
        for (int i = 0; i < size; i++) {
            count++;
            temp = temp.next;
            if (index == count){
                element = temp.element;
                remove(temp);
            }
        }
        return element;
    }

    @Override
    public int indexOf(Object o) {
        Node result = head;
        for (int i = 0; i < size; i++) {
            if (result == o){
                return i;
            }
        }
        return -1;
    }

//    @Override
//    public int lastIndexOf(Object o) {
//        int count = size;
//        for (int i = size; i > 0; i--) {
//            count--;
//            if (getNode(i).equals(o)){
//                return count;
//            }
//        }
//        return -1;
//    }


    @Override
    public int lastIndexOf(Object o) {
        Node temp = head;
        for (int i = 0; i < size; i++) {
            temp = temp.next;
            if (o.equals(temp)){
                return getIndex(temp);
            }
        }
        return -1;
    }

    @Override
    public ListIterator<X> listIterator() {
        return listIterator(0);
    }

    @Override
    public ListIterator<X> listIterator(int index) {
        return new ListIterator<X>() {

            Node temp = head;
            int count = 0;
            X element;
            {
                while (count < index) {
                    temp = temp.next;
                    count++;
                }
            }


            @Override
            public boolean hasNext() {
                return temp != null;
            }

            @Override
            public X next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                count++;
                element = temp.element;
                temp = temp.next;
                return element;
            }

            @Override
            public boolean hasPrevious() {
                return (contains(get(index - 1)));
            }

            @Override
            public X previous() {
                return get(index - 1);
            }

            @Override
            public int nextIndex() {
                return index + 1;
            }

            @Override
            public int previousIndex() {
                return index - 1;
            }

            @Override
            public void remove() {
                LinkedList.this.remove(this.element);
            }

            @Override
            public void set(X x) {
                LinkedList.this.set(index, x);
            }

            @Override
            public void add(X x) {
                LinkedList.this.add(index, x);
            }
        };
    }

    @Override
    public List<X> subList(int fromIndex, int toIndex) {
        if (toIndex < fromIndex){
            throw new IllegalArgumentException();
        }
        List<X> result = new LinkedList<>();

        Node buffer = getNode(fromIndex);
        for (int i = fromIndex; i < toIndex; i++) {
            buffer = buffer.next;
            result.add(get(i));
        }
        return result;
    }


    private class Node{
        public X element;
        public Node next;

        public Node(X element, Node next){
            this.element = element;
            this.next = next;
        }
    }
}