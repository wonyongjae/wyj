package thread;

import java.util.ArrayList;

class FastLibrary {
    public ArrayList<String> books = new ArrayList<>();

    public FastLibrary() {
        books.add("태백산맥 1");
        books.add("태백산맥 2");
        books.add("태백산맥 3");
        books.add("태백산맥 4");
        // books.add("태백산맥 5");
        // books.add("태백산맥 6");
        // books.add("태백산맥 7");
        // books.add("태백산맥 8");
    }

    public synchronized String lendBook() throws InterruptedException {
        Thread t = Thread.currentThread();

        while (books.size() == 0) {
            // return null;
            System.out.println(t.getName() + " waiting start");
            wait();
            System.out.println(t.getName() + " waiting end");

        }
        String title = books.remove(0);
        System.out.println(t.getName() + ":" + title + " lend");
        return title;
    }

    public synchronized void returnBook(String title) {
        Thread t = Thread.currentThread();
        if (title == null)
            return;

        books.add(title);
        notifyAll();
        System.out.println(t.getName() + ":" + title + " return");
    }
}

class Student extends Thread {

    public void run() {
        try {
            String title = LibraryMain.library.lendBook();
            sleep(5000);
            LibraryMain.library.returnBook(title);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }
}

public class LibraryMain {

    public static FastLibrary library = new FastLibrary();

    public static void main(String[] args) {
        Student std1 = new Student();
        Student std2 = new Student();
        Student std3 = new Student();
        Student std4 = new Student();
        Student std5 = new Student();
        Student std6 = new Student();

        std1.start();
        std2.start();
        std3.start();
        std4.start();
        std5.start();
        std6.start();

    }
}
