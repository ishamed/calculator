package model.dataStructure;

public class LinkedQueue <E> implements Queue<E>{
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
    public E first() {
        return getList().first() ;
    }

    @Override
    public void enqueue(E e) {
        getList().addLast(e);
    }

    @Override
    public E dequeue() {
        return getList().removeFirst();
    }
}
