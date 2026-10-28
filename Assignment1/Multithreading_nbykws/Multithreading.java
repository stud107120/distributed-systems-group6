package Multithreading_nbykws;
class Thread1 extends Thread
{
    private Counter zaehler;
    private boolean sync;
    public Thread1() {}
    public Thread1(Counter z, boolean b)
    {
        zaehler = z;
        sync = b;
    }

    public void run()
    {
        if(zaehler == null)
            System.out.println("Thread 1 is running");
        else if (sync == false)
        {
            System.out.println("Thread 1 started counting unsynchronized to 10.000");
            for (int i = 0; i < 10000; i++)
            {
                zaehler.increment();
            }
        }
        else if (sync == true)
        {
            System.out.println("Thread 1 started counting synchronized to 10.000");
            for (int i = 0; i < 10000; i++)
            {
                zaehler.increment_s();
            }
        }
    }
}

class Thread2 extends Thread
{
    private Counter zaehler;
    private boolean sync;
    public Thread2(){}
    public Thread2(Counter z, boolean b)
    {
        zaehler = z;
        sync = b;
    }

    public void run()
    {
        if(zaehler == null)
            System.out.println("Thread 2 is running");
        else if (sync == false)
        {
            System.out.println("Thread 2 started counting unsynchronized to 10.000");
            for (int i = 0; i < 10000; i++)
            {
                zaehler.increment();
            }
        }
        else if (sync == true)
        {
            System.out.println("Thread 2 started counting synchronized to 10.000");
            for (int i = 0; i < 10000; i++)
            {
                zaehler.increment_s();
            }
        }
    }
}

class Counter
{
    private int c = 0;

    public void increment ()
    {
        int b = c ;
        b = b + 1;
        c = b ;
    }
    public void decrement ()
    {
        int d = c ;
        d = d - 1;
        c = d ;
    }
    public int value ()
    {
        return c ;
    }

    public synchronized void increment_s()
    {
        increment();
    }
    public synchronized void decrement_s()
    {
        decrement();
    }
    public synchronized int value_s()
    {
        return c;
    }
}

public class Multithreading
{
    public static void main(String[] args)
    {
        twoThreads();
        unsynchronizedCounting();
        synchronizedCounting();
    }

    public static void twoThreads()
    {
        //Simple Thread Programs
        Thread1 thr1 = new Thread1();
        Thread2 thr2 = new Thread2();

        thr1.start();
        thr2.start();
    }

    public static void unsynchronizedCounting()
    {
        Counter zaehler = new Counter();
        Thread1 thr1 = new Thread1(zaehler, false);
        Thread2 thr2 = new Thread2(zaehler, false);

        thr1.start();
        thr2.start();
        try
        {
            thr1.join();
            thr2.join();
        } catch (InterruptedException e)
        {
            e.printStackTrace();
        }

        System.out.println("Result of thread 1 and 2 counting unsynchronized: " + zaehler.value());
    }


    public static void synchronizedCounting()
    {
        Counter zaehler = new Counter();
        Thread1 thr1 = new Thread1(zaehler, true);
        Thread2 thr2 = new Thread2(zaehler, true);

        thr1.start();
        thr2.start();
        try
        {
            thr1.join();
            thr2.join();
        } catch (InterruptedException e)
        {
            e.printStackTrace();
        }

        System.out.println("Result of thread 1 and 2 counting synchronized: " + zaehler.value());
    }
}
