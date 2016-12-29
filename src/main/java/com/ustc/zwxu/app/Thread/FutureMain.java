package com.ustc.zwxu.app.Thread;

import java.io.DataInputStream;
import java.io.EOFException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;

public class FutureMain {

	public static void main(String args[]) {
        long start = System.currentTimeMillis();

        Content content1 = Retriever.retrieve("http://www.baidu.com/");
        Content content2 = Retriever.retrieve("http://www.sohu.com/");
        Content content3 = Retriever.retrieve("http://www.163.com/");

        saveToFile("baidu.html", content1);
        saveToFile("sohu.html", content2);
        saveToFile("163.html", content3);

        long end = System.currentTimeMillis();

        System.out.println("Elapsed time = " + (end - start) + "msec.");
    }

    // 将content的内容输出到名为filename的档案里
    private static void saveToFile(String filename, Content content) {
        byte[] bytes = content.getBytes();
        try {
            System.out.println(Thread.currentThread().getName() + ": Saving to " + filename);
            FileOutputStream out = new FileOutputStream(filename);
            for (int i = 0; i < bytes.length; i++) {
                out.write(bytes[i]);
            }
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

interface Content {
    public abstract byte[] getBytes();
}

class Retriever {
    public static Content retrieve(final String urlstr) {
        //return new SyncContentImpl(urlstr);
        
        final AsyncContentImpl future = new AsyncContentImpl();

        new Thread() {
            public void run() {
                future.setContent(new SyncContentImpl(urlstr));
            }
        }.start();

        return future;
    }
}

class AsyncContentImpl implements Content {

	private SyncContentImpl synccontent;
    private boolean ready = false;
    public synchronized void setContent(SyncContentImpl synccontent) {
        this.synccontent = synccontent;
        this.ready = true;
        notifyAll();
    }
    public synchronized byte[] getBytes() {
        while (!ready) {
            try {
                wait();
            } catch (InterruptedException e) {
            }
        }
        return synccontent.getBytes();
    }

}

class SyncContentImpl implements Content {
    private byte[] contentbytes;
    public SyncContentImpl(String urlstr) {
        System.out.println(Thread.currentThread().getName() + ": Getting " + urlstr);
        try {
            URL url = new URL(urlstr);
            DataInputStream in = new DataInputStream(url.openStream());
            byte[] buffer = new byte[1];
            int index = 0;
            try {
                while (true) {
                    int c = in.readUnsignedByte();
                    if (buffer.length <= index) {
                        byte[] largerbuffer = new byte[buffer.length * 2];
                        System.arraycopy(buffer, 0, largerbuffer, 0, index);
                        buffer = largerbuffer;
                        // System.out.println("Enlarging buffer to " + buffer.length);
                    }
                    buffer[index++] = (byte)c;
                    // System.out.print("Getting " + index + " bytes from " + urlstr);
                }
            } catch (EOFException e) {
            } finally {
                in.close();
            }
            contentbytes = new byte[index];
            System.arraycopy(buffer, 0, contentbytes, 0, index);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public byte[] getBytes() {
        return contentbytes;
    }
}