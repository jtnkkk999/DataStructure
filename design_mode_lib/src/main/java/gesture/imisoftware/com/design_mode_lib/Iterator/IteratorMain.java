package gesture.imisoftware.com.design_mode_lib.Iterator;

public class IteratorMain {
    public static void main(String[] args){
        BookShelf bookShelf = new BookShelf();
        bookShelf.appendBook(new Book("a"));
        bookShelf.appendBook(new Book("b"));
        bookShelf.appendBook(new Book("c"));
        bookShelf.appendBook(new Book("d"));
        Iterator iterator = bookShelf.iterator();
        while(iterator.hasNext()){
            System.out.println(iterator.next().getName());
        }
    }
}
