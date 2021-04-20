package de.pkro;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.StandardCharsets;

public class Main {

  public static void main(String[] args) {

    try(FileOutputStream binFile = new FileOutputStream("data.dat");
        FileChannel binChannel = binFile.getChannel()) {

      ByteBuffer buffer = ByteBuffer.allocate(100);

      byte[] outputBytes = "Hello World!".getBytes();
      byte[] outputBytes2 = "Nice to meet you".getBytes();

      // chained version
      //buffer.put(outputBytes).putInt(245).putInt(-98765).put(outputBytes2).putInt(1000);

      // unchained version
      //buffer.put(outputBytes);
      //buffer.putInt(245);
      //buffer.putInt(-98765);
      //buffer.put(outputBytes2);
      //buffer.putInt(1000);

      // saving the start position of each integer
      buffer.put(outputBytes);
      long int1Pos = outputBytes.length;
      buffer.putInt(245);
      long int2Pos = int1Pos + Integer.BYTES;
      buffer.putInt(-98765);
      buffer.put(outputBytes2);
      long int3Pos = int2Pos + Integer.BYTES + outputBytes2.length;
      buffer.putInt(1000);

      // flip (set pointer to 0) so writing starts from the beginning
      // if the flip call were removed, the write operation would write NULL / empty as the
      // pointer is after the last value of the buffer at this point
      buffer.flip();
      binChannel.write(buffer);

      /***************************************
       * Reading
       */
      RandomAccessFile ra = new RandomAccessFile("data.dat", "rwd");
      FileChannel channel = ra.getChannel();
      ByteBuffer readBuffer = ByteBuffer.allocate(100);
      channel.read(readBuffer);
      // we switch from writing to the buffer to reading, so we
      // reset the file pointer
      //readBuffer.flip();

      // get the ints in reverse from the already filled readBuffer
      // Solution 1:
      System.out.println(readBuffer.getInt((int) int3Pos));
      System.out.println(readBuffer.getInt((int) int2Pos));
      System.out.println(readBuffer.getInt((int) int1Pos));
      readBuffer.flip(); // reset so the rest of the code still works

      // Solution 2
      ByteBuffer myIntBuffer = ByteBuffer.allocate(Integer.BYTES);
      channel.position(int3Pos);
      channel.read(myIntBuffer); // offset is now at end of intBuffer
      myIntBuffer.flip(); // so we reset it before reading
      System.out.println("This should be 1000: " + myIntBuffer.getInt());
      myIntBuffer.flip(); // if something doesn't work, flip()...
      channel.position(int2Pos);
      channel.read(myIntBuffer);
      myIntBuffer.flip();
      System.out.println("This should be -98765: " + myIntBuffer.getInt());


      // we just reuse outputBytes.length because we are supposed to know
      // how long the string to read is when reading it in, not because
      // outputBytes has any meaning here in the "read" section.
      byte[] inputString = new byte[outputBytes.length];
      readBuffer.get(inputString);
      System.out.println("inputString = " + new String(inputString));
      System.out.println("int1 = " + readBuffer.getInt());
      System.out.println("int2 = " + readBuffer.getInt());
      byte[] inputString2 = new byte[outputBytes2.length];
      readBuffer.get(inputString2);
      System.out.println("inputString2 = " + new String(inputString2));
      System.out.println("int3 = " + readBuffer.getInt());

      /***********************************
       * Writing in a non-sequential fashion
       */
      byte[] outputString = "Another string!".getBytes();
      long str1Pos = 0;
      long newInt1Pos = outputString.length;
      long newInt2Pos = newInt1Pos + Integer.BYTES;
      byte[] outputString2 = "And yet another!".getBytes();
      long str2Pos = newInt2Pos+Integer.BYTES;
      long newInt3Pos = str2Pos + outputString2.length;
      ByteBuffer intBuffer = ByteBuffer.allocate(Integer.BYTES);
      intBuffer.putInt(1245);
      intBuffer.flip();
      binChannel.position(newInt1Pos);
      binChannel.write(intBuffer);
      intBuffer.flip();

      intBuffer.putInt(-9999);
      intBuffer.flip();
      binChannel.position(newInt2Pos);
      binChannel.write(intBuffer);
      intBuffer.flip();

      intBuffer.putInt(1000000);
      intBuffer.flip();
      binChannel.position(newInt3Pos);
      binChannel.write(intBuffer);

      binChannel.position(str1Pos);
      binChannel.write(ByteBuffer.wrap(outputString));
      // no flip necessary as the buffer isn't reused but created on the fly with .wrap
      binChannel.position(str2Pos);
      binChannel.write(ByteBuffer.wrap(outputString2));

      /****
       * Copying files
       */
      RandomAccessFile copyFile = new RandomAccessFile("datacopy.dat", "rw");
      FileChannel copyChannel = copyFile.getChannel();
      channel.position(0); // so we copy the whole file, is still set from the previous code

      //Variant 1: transferFrom
      //long numTransferred = copyChannel.transferFrom(channel, 0, channel.size());

      // Variant 2: transferTo
      long numTransferred = channel.transferTo(0, channel.size(), copyChannel);
      System.out.println("Num transferred = " + numTransferred);

      channel.close();
      ra.close();
      copyChannel.close();
    } catch(IOException e) {
      e.printStackTrace();
    }
  }
}