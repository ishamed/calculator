package model.dataStructure;

public class LinkedStack <E> implements Stack<E>{
    private SinglyLinkedList<E> list ;
    {
        list = new SinglyLinkedList<>();
    }

    public SinglyLinkedList<E> getList() {
        return list;
    }

    public void setList(SinglyLinkedList<E> list) {
        this.list = list;
    }

    @Override
    public int size() {
        return getList().getSize() ;
    }

    @Override
    public boolean isEmpty() {
        return getList().isEmpty();
    }

    @Override
    public void push(E e) {
        getList().addFirst(e);
    }

    @Override
    public E pop() {
        return getList().removeFirst();
    }

    @Override
    public E top() {
        return getList().first();
    }
}
