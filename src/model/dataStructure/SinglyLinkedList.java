package model.dataStructure;

public class SinglyLinkedList<E>{

    private static class Node<E> {
        private E element;
        private Node<E> next;

        public Node(E element,Node next) {
            this.element = element;
            this.next = next;
        }

        public E getElement() {
            return element;
        }

        public Node<E> getNext() {
            return next;
        }

        public void setNext(Node<E> next) {
            this.next = next;
        }
    }
    private Node<E> head;
    private Node<E> tail;
    private int size;

    public Node<E> getHead() {
        return head;
    }

    public void setHead(Node<E> head) {
        this.head = head;
    }

    public Node<E> getTail() {
        return tail;
    }

    public void setTail(Node<E> tail) {
        this.tail = tail;
    }

    public void setSize(int size) {
        this.size = size;
    }


    public SinglyLinkedList() {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }
    public void addFirst(E e){
        setHead(new Node<>(e,getHead()));
        if (isEmpty()){
            setTail(getHead());
        }
        setSize(getSize()+1);
    }
    public E removeFirst(){
        if (isEmpty()){
            return null ;
        }
        E answer = getHead().getElement() ;
        setHead(getHead().getNext());
        setSize(getSize()-1);
        if (isEmpty()){
            setTail(null);
        }
        return answer ;
    }

    public boolean isEmpty() {
        return size == 0;
    }
    public E first(){
        if (isEmpty()){
            return null ;
        }
        return getHead().getElement() ;
    }

    public void addLast(E e) {
        Node<E> newNode = new Node<>(e,null);
        if (isEmpty()) {
            head = newNode;
        } else {
            tail.next = newNode;
        }
        tail = newNode;
        size++;
    }

    public int getSize() {
        return size;
    }
}

