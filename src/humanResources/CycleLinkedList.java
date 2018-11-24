package humanResources;

import java.util.Collection;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Set;

public class CycleLinkedList<X> implements Set<X> {
    private Node head;
    private int size;

    CycleLinkedList() {
        this.head = new Node(null, head);
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
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
            if (x.equals(o)){
                return true;
            }
        }
        return false;
    }

    @Override
    public Iterator<X> iterator() {
        return new Iterator<X>() {
            int count = 0;
            X element;
            Node buffer = head;
            @Override
            public boolean hasNext() {
                if (buffer == null){
                    return false;
                }
                if (buffer.equals(head) && count != 0){
                    return false;
                }
                return count >= size;
            }

            @Override
            public X next() {
                if (!hasNext()){
                    throw new NoSuchElementException();
                }
                count ++;
                element = buffer.element;
                buffer = buffer.next;
                return element;
            }
        };
    }

    @Override
    public Object[] toArray() {
        Object[] result = new Object[size];
        for (int i = 0; i < size(); i++) {
            result[i] = getEl(i);
        }
        return result;
    }

    @Override
    public <T> T[] toArray(T[] a) {
        int i = 0;
        Object[] array = a;
        Node x;
        if (head != null) {
            for (x = head; x.next != head; x = x.next){
                array[i++] = x.element;
                array[i] = x.element;
            }
            if (a.length < size){
                a[size] = null;
            }
        }
        return a;
    }

    public boolean add(X elementToAdd) {
        if (elementToAdd == null) return false;

        if (head.element == null) {
            head = new Node(elementToAdd, null);
            head.next = head;
            size++;
            return true;
        } else {
            Node newNode = head;
            while (newNode.next != head) { //ищем последний элемент (следующий после которого - head)
                newNode = newNode.next;  //перебираем все элементы начиная с head
            }
            newNode.next = new Node(elementToAdd, head);//next после найденного - последний элемент, равный null.
            size++;
            return true;
        }
    }

    @Override
    public boolean remove(Object o) {
        for (X x: this){
            if (o.equals(x)){
                removeEl(x);
                size--;
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        for (X x: this){
            if (!c.equals(x)){
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
    public boolean retainAll(Collection<?> c) {
        for (X x: this){
            if(!c.contains(x)){
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        for (X x: this){
            if (c.contains(x)){
                removeEl(x);
                return false;
            }
        }
        return true;
    }

    @Override
    public void clear() {
        head.next = null;
        head = null;
        size = 0;
    }

    public void addAll(X[] mass){
        for (X x: mass) {
            add(x);
        }
    }

    public X getEl(int index) {
        if (index >= size) {
            return null;
        }

        Node newNode = head;
        for (int i = 0; i < index; i++) {
            newNode = newNode.next;
        }
        return newNode.element;
    }

    public int getIndex(X element) {
        int count = 0;
        Node temp = head;
        while (temp.element != element) {
            temp = temp.next;
            count++;
        }
        return count;
    }

    public boolean remove(int index) {
        if (size < 0) {
            return false;
        }
        if (size < index-1) {
            return false;
        }

        Node beforeDelete = head;
        Node afterDelete = head;
        Node delete = head;
        for (int i = 0; i < index - 1; i++) {
            beforeDelete = beforeDelete.next;
        }
        for (int i = 0; i < index + 1; i++) {
            afterDelete = afterDelete.next;
        }
        for (int i = 0; i < index; i++) {
            delete = delete.next;
        }
        delete.next = null;
        delete.element = null;

        beforeDelete.next = afterDelete;
        return true;
    }

    public boolean removeEl(X element){
        if (size < 0) {
            return false;
        }

        Node beforeDelete = head;
        Node afterDelete = head;
        Node delete = head;
        for (int i = 0; i < getIndex(element) - 1; i++) {
            beforeDelete = beforeDelete.next;
        }
        for (int i = 0; i < getIndex(element) + 1; i++) {
            afterDelete = afterDelete.next;
        }
        for (int i = 0; i < getIndex(element); i++) {
            delete = delete.next;
        }
        delete.element = null;
        delete.next = null;
        beforeDelete.next = afterDelete;
        return true;
    }


    private class Node {
        X element;
        Node next;

        Node(X element, Node next) {
            this.element = element;
            this.next = next;
        }
    }
}