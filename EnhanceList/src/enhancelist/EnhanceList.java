/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package enhancelist;

import core.*;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

/**
 *
 * @author Bojan
 */
public class EnhanceList<E> extends DLL<E> implements List<E> {

    private int size;
    private int distance;
    DLLNode<E>[] array;

    public EnhanceList() {
        this(10);
    }

    public EnhanceList(int sizeOfArray) {
        super();
        size = 0;
        distance = 1;
        array = new DLLNode[sizeOfArray];
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
        return find((E) o) != null;
    }

    @Override
    public Iterator<E> iterator() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Object[] toArray() {
        Object[] arr = new Object[size];
        DLLNode<E> node = first;
        for (int i = 0; (i < size) && (node != null); i++, node = node.succ) {
            arr[i] = node.element;
        }
        return arr;
    }

    @Override
    public <T> T[] toArray(T[] a) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean add(E e) {
        insertLast(e);
        size++;
        if (size > array.length) {
            float dist = (size - 1) / (array.length - 1 * 1.0f);
            if (dist == (int) dist && dist > distance) {
                distance = (int) dist;
                int br = 1;
                for (int i = 1; i < array.length; i++) {
                    for (int j = 0; j < br; j++) {
                        array[i] = array[i].succ;
                    }
                    br++;
                }
            }
        } else {
            array[size - 1] = getLast();
        }

        return true;
    }

    @Override
    public boolean remove(Object o) {
        DLLNode<E> node = find((E) o);
        if (node == null) {
            return false;
        }
        delete(node);
        return true;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean addAll(int index, Collection<? extends E> c) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private DLLNode<E> getNode(int index) {
        if (index >= size) {
            return null;
        }
        int x = index / distance;
        if (x >= array.length) {
            DLLNode<E> node = array[array.length - 1];
            int br = index % distance;
            for (int i = 0; i < br; i++) {
                node = node.succ;
            }
            return node;
        } else {
            DLLNode<E> node = array[x];
            int br = index % distance;
            for (int i = 0; i < br; i++) {
                node = node.succ;
            }
            return node;
        }
    }

    @Override
    public E get(int index) {
        return getNode(index).element;
    }

    @Override
    public E set(int index, E element) {
        DLLNode<E> node = getNode(index);
        if (node == null) {
            return null;
        }
        E e = node.element;
        node.element = element;
        return e;
    }

    @Override
    public void add(int index, E element) {
        size++;
        insertBefore(element, getNode(index));

        if (size > array.length) {
            if (index == 0) {
                array[0] = getFirst();
                for (int i = 1; i < array.length; i++) {
                    array[i] = array[i].pred;
                }
                return;
            }
            float dist = (size - 1) / (array.length - 1 * 1.0f);
            if (dist == (int) dist && dist > distance) {
                distance = (int) dist;
                int br = 0;
                for (int i = 1; i < array.length; i++) {
                    if (i != index / distance + 1) {
                        br++;
                    }
                    for (int j = 0; j < br; j++) {
                        array[i] = array[i].succ;
                    }
                }
            } else {
                int br = 1;
                for (int i = index / distance + 1; i < array.length; i++) {
                    for (int j = 0; j < br; j++) {
                        array[i] = array[i].pred;
                    }
                    br++;
                }
            }
        } else {
            DLLNode<E> node = getFirst();
            for (int i = 0; i < size; i++, node = node.succ) {
                array[i] = node;
            }
        }
    }

    @Override
    public E remove(int index) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int indexOf(Object o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int lastIndexOf(Object o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ListIterator<E> listIterator() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ListIterator<E> listIterator(int index) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<E> subList(int fromIndex, int toIndex) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < array.length; i++) {
            if (array[i] != null) {
                sb.append(array[i].element.toString());
                sb.append(" <-> ");
            } else {
                break;
            }
        }
        return super.toString() + " array " + sb.toString();
    }
}
