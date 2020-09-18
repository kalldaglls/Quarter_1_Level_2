public class TwiceLinkedList implements GeekbrainsList {
    private Node head;

    @Override
    public void add(String o) {
        if (head == null) {
            head = new Node(o);//Может {null,o,ссылка}? Или {null,o,null}?
            return;
        }

        addLast(head, o);// Разобраться, что здесь писать!!!!Может не пихать здесь head?
    }

    private void addLast (Node current,String o) {
        if (current.getNext() == current.getPrevious()) {
            current.setNext(new Node(o,current.getPrevious()));//переделать на {previous,o}. И проверить, чо вообще делает данный метод?
            return;
        }
        addLast(current.getNext(), o);
    }


    private void add(Node previous, Node current, String o) {//равен методу выше?
        if (current == previous) {
            current.setNext(new Node(current.getPrevious(),o));
            return;
        }
        add(current.getPrevious(), current, o);//{prev,o,next}
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
        private Node previous;

        public Node(String val) {
            this(val, (Node) null);
        }

        public Node(String val, Node next) {
            this.val = val;
            this.next = next;
        }

        public Node(Node previous, String val) {
            this.val = val;
            this.previous = previous;
        }

        public Node(String val, Node previous, Node next) {
            this.val = val;
            this.next = next;
            this.previous = previous;
        }

        public Node(String val, String o) {
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

        public Node getPrevious() {
            return previous;
        }

        public void setPrevious(Node previous) {
            this.previous = previous;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "val='" + val + '\'' +
                    ", next=" + next +
                    ", previous=" + previous +
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
