package gesture.imisoftware.com.design_mode_lib.Iterator;

import java.util.ArrayList;

/**
 * 书架类：1.通过ArrayList存储book
 *        2.添加书本
 *        3.查找某个书本
 *        4.获取书架种书本的本数
 */
public class BookShelf implements Aggregate {

    private final ArrayList<Book> mBooks;

    public BookShelf() {
        mBooks = new ArrayList<>();
    }
    public void appendBook(Book book){
        mBooks.add(book);
    }
    public int getLength(){
        return mBooks.size();
    }

    public Book getBookAt(int index){
        return mBooks.get(index);
    }

    @Override
    public Iterator iterator() {
        return new BookShelfIterator(this);
    }
}
