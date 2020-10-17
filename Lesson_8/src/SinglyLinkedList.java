public class SinglyLinkedList implements GeekbrainsList {
    private Node head;

    @Override
    public void add(String o) {
        if (head == null) {
            head = new Node(o);
            System.out.println("head= " + head);
            return;
        }

        add(head, o);
        System.out.println("head next= " + head);
    }

    private void add(Node current, String o) {
        if (current.getNext() == null) {
            current.setNext(new Node(o));
            System.out.println("current next 1 = " + current.getNext());
            return;
        }
        add(current.getNext(), o);
        System.out.println("current next 2 = " + current.getNext());
    }

    @Override
    public void remove(String o) {
        if (head == null) {
            return;
        } else {
            if (head.getVal().equals(o)) {
                head = head.getNext();
                return;
            }
        }

        remove(head, head.getNext(), o);
    }

    private void remove(Node prev, Node current, String o) {
        if (current == null) {
            return;
        }

        if (current.getVal().equals(o)) {
            prev.setNext(current.getNext());
            return;
        }

        remove(current, current.getNext(), o);
    }

    private static class Node {
        private String val;
        private Node next;

        public Node(String val) {
            this(val, null);
        }

        public Node(String val, Node next) {
            this.val = val;
            this.next = next;
        }

        public String getVal() {
            return val;
        }

        public Node getNext() {
            return next;
        }

        public void setNext(Node next) {
            this.next = next;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "val='" + val + '\'' +
                    ", next=" + next +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "{" +
                head +
                '}';
    }
}

