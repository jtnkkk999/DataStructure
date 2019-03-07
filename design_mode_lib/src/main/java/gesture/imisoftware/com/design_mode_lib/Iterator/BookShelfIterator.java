package gesture.imisoftware.com.design_mode_lib.Iterator;

public class BookShelfIterator implements Iterator{
    private BookShelf mBookShelf;
    private int index;
    public BookShelfIterator(BookShelf bookShelf){
        this.mBookShelf = bookShelf;
        index = 0;
    }
    @Override
    public boolean hasNext() {
        if(index<mBookShelf.getLength()){
            return true;
        }
        return false;
    }

    @Override
    public Book next() {
        return mBookShelf.getBookAt(index++);
    }
}
