import java.io.*;
import java.io.Serializable;


public class MyBuffer<T> implements QueueInterface<T> , Reverser, Serializable, SaveRestore
{   
    //CONSTRUCTORS
    private int capacity = 3;
    private int front, rear, count;
    private T[] queue;
    public MyBuffer(int i)
    {
        @SuppressWarnings("unchecked")
        T[] buff = (T[])new Object[capacity];
        queue = buff;
        // front and rear set to -1 to show queue is empty
        //both values represent the same spot on array since it is circular
        front = 0;
        rear = -1;
        count = 0;
    }

    public void enqueue(T newEntry)
    {
        //checks if the array is full
      
        if (count == capacity)
        {
            int newCap = capacity * 2;
            @SuppressWarnings("unchecked")
            T[] doubQueue = (T[]) new Object[newCap];
            int newCount = 0;
            for (int i = 0; i < count; i++)
            {
                
                doubQueue[newCount] = queue[(i + front) % capacity];
                newCount++;
            }
            queue = doubQueue; 
            front = 0;
            rear = newCount -1;
            capacity = newCap; 
        }
        rear = (rear+1) % capacity;
        queue[rear] = newEntry;
        count++;

    }

    public T dequeue() throws EmptyQueueException
    {
        
        if (count == 0)
        {
            throw new EmptyQueueException();
        }
        else
        {
            
            T dequeued = queue[front];
            queue[front] = null;
            front = (front + 1) % capacity;
            count--;
            return dequeued;
        } 
        
    }

    public void clear()
    {
        count = 0;
        front = -1;
        rear= -1;

    }
             
    public boolean isEmpty()
    {
        return count == 0 || front == -1 && rear == -1;
    }

    public T getFront()
    {
        T queueFront = queue[front];
        return queueFront;
    }

    public String toString()
    {
        String ans = ("Size: " + count +" Capacity: " +capacity+" Contents: \n");
        for (int i = 0; i< count; i++)
        {
            ans += (queue[(i + front)  % capacity] +" ");
        }
        return ans;
    }

    public boolean saveToFile(String filename)
    {
        try
        {
            ObjectOutputStream oos = new ObjectOutputStream(
                new FileOutputStream(filename));
            for (int i=0; i< count; i++)
            {
                oos.writeObject(queue[(i + front) % capacity]);
            }
            oos.close();
            return true;
        }
        catch (IOException e)
        {
            System.out.println("IO Error");
            return false;
        }
    }

    public boolean restoreFromFile(String filename)
    {
        @SuppressWarnings("unchecked")
        T[] tempQue = (T[]) new Object[capacity];
        try
        {
            ObjectInputStream ois = new ObjectInputStream(
                new FileInputStream(filename));
            
            
            while (true)
            {
                for (int i = 0; i < tempQue.length; i++)
                {
                    
                    tempQue[i] =(T) ois.readObject();
                    System.out.println("Read in "+ tempQue[i]);
                }
                queue = tempQue;
                return true;
            }   
             
        }
        catch (EOFException e1)
        {
            System.out.println("All data has been read in");
            return false;
        }
        catch (IOException e2)
        {
            System.out.println("Cannot create object from serialized form");
            return false;
        }
        catch (ClassNotFoundException e3)  
        {
            System.out.println("Class not found");
            return false;
        } 
    }

    public void reverse()
    {
        @SuppressWarnings("unchecked")
        T[] revQue = (T[]) new Object[capacity];
        int j = capacity;
        for(int i=0; i< count; i++)  
        {
            revQue[j-1] = queue[i];
            j = j-1;
        }
        queue = revQue;
    }
    /*private T[] doubleCapacity()
    {
        int newCap = capacity * 2;
        front = (front + 1) % newCap;
        queue = Arrays.copyOf(queue, newCap);
        return queue;
    }
              /*int newFront = front;
            int newRear = rear;
            int newCount = 0;
            while (newFront != -1)
            {
                
                doubQueue[newCount] = queue[(newRear + newCount) % newCap];
                newRear = (newRear + 1) % newCap;
                newRear++;
            }
            queue = doubQueue;
            count = newCount;
            front = newFront;
            rear = newRear;*/

}
