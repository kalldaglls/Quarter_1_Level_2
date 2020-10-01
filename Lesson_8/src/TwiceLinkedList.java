public class TwiceLinkedList implements GeekbrainsList {
    private Node head;//может добавить tail?

    @Override
    public void add(String o) {
        if (head == null) {
            head = new Node(null,o,null);//Должно быть именно {null,o,null}
            return;
        }

        //head.setNext(new Node(head.getPrevious(), o, null));
       add(head, o);// Разобраться, что здесь писать!!!!Может не пихать здесь head?
    }


    private void add(Node current, String o) {
        if (current.getNext() == null) {//Как работает эта проверка?
            Node next = new Node(o);
            current.setNext(next);
            next.setPrevious(current);
           // current.setNext(new Node(current.previous,o));
            return;
        }
        add(current.getNext(), o);
    }

    /*
    private void add(Node current, String o, Node next, Node previous) {
        if (current.getNext() == null) {
            current.setNext(new Node(previous);
            return;
        }
        add(,o, new Node(o));//{prev,o,next}
    }

     */





    @Override
    public void remove(String o) {
        if (head == null) {
            return;
        } else {
            if (head.getVal().equals(o)) {
                Node previous = head.getPrevious();
                head = head.getNext();
                head.setPrevious(previous);
                return;
            }
        }

        remove(head, head.getNext(), o);
    }

    private void remove(Node previous, Node current, String o) {
        if (current == null) {
            return;
        }

        Node next = current.getNext();
        previous.setNext(next);
        if (next != null) {
            next.setPrevious(previous);
        }


        remove(current, current.getNext(), o);
    }

    private static class Node {
        private String val;
        private Node next;
        private Node previous;

        public Node(String val) {
            this(val, (Node) null );
        }

        public Node(String val, Node next) {
            this.val = val;
            this.next = next;
        }

        public Node(Node previous, String val) {
            this.val = val;
            this.previous = previous;
        }

        public Node(Node previous, String val, Node next) {
            this.val = val;
            this.next = next;
            this.previous = previous;
        }


        public String getVal() {
            return val;
        }

        public Node getNext() {
            return next;
        }

        public Node setNext(Node next) {
            this.next = next;
            return next;
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
                    "previous=" + ((previous != null) ? previous.getVal() : null) +
                    ", val='" + val + '\'' +
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
