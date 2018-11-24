package humanResources;

import java.util.*;


public class LinkedList<X> implements List<X> {
    private Node head;
    private int size;

    public LinkedList(){
        head = new Node(null, null);
        head.next = head;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public X getEl(int index){
        if (index > size){
            throw new IndexOutOfBoundsException();
        }

        Node result = head;
        for (int i = 0; i < index - 1; i++) {
            result = result.next;
        }
        return result.element;
    }

    public Node getNode(int index){
        if (index > size){
            throw new IndexOutOfBoundsException();
        }

        Node result = head;
        for (int i = 0; i < index - 1; i++) {
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
        return null;
    }

    @Override
    public boolean add(X x) {
        if (head.element == null){
            head.element = x;
            head.next = head;
            size++;
            return true;
        } else {
            Node newNode = head;
            for (int i = 0; i < size; i++) {
                newNode = newNode.next;
            }
            newNode.element = x;
            newNode.next = head;
            size++;
            return true;
        }
    }

    @Override
    public boolean remove(Object o) {
        if (!this.contains(o)){
            throw new NoSuchElementException("Element does not exist");
        }
        Node delete = head;
        Node afterDelete = head;
        Node beforeDelete = head;
        for (int i = 0; i < indexOf(o); i++) {
            delete = delete.next;
        }
        for (int i = 0; i < indexOf(o) + 1; i++) {
            afterDelete = afterDelete.next;
        }
        for (int i = 0; i < indexOf(o) - 1; i++) {
            beforeDelete = beforeDelete.next;
        }

       // delete = new Node(null, null);
        afterDelete.next = beforeDelete;
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
            size += c.size();
            return true;
        }
        return false;
    }

    @Override
    public boolean addAll(int index, Collection<? extends X> c) {
        if (index > size){
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
        if (index > size){
            throw new IndexOutOfBoundsException();
        }

        int count = 0;
        Node result = head;
        for (X x: this) {
            count++;
            result = result.next;
            if (count == index){
                return result.element;
            }
        }
        return null;
    }

    @Override
    public X set(int index, X element) {
        if (index > size){
            throw new IndexOutOfBoundsException();
        }

        X result = null;
        Node buffer = head;
        for (int i = 0; i < size; i++) {
            buffer = buffer.next;
            if (i == index){
                result = buffer.element;
                buffer.element = element;
            }
        }
        return result;
    }

    @Override
    public void add(int index, X element) {
        if (index > size){
            throw new IndexOutOfBoundsException();
        }

        if (getNode(index).next == head){
            Node newNode = new Node(element, head);
            getNode(index).next = newNode;
            size++;
        }

        Node newNode;
        Node afterAdded = head;
        Node beforeAdded = head;

        for (int i = 0; i < index + 1; i++) {
            afterAdded = afterAdded.next;
        }

        for (int i = 0; i < index; i++) {
            beforeAdded = beforeAdded.next;
        }

        newNode = new Node(element, afterAdded);
        beforeAdded.next = newNode;
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
                return (contains(getEl(index - 1)));
            }

            @Override
            public X previous() {
                return getEl(index - 1);
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
            result.add(getEl(i));
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