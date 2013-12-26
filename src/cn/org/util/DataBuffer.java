package cn.org.util;

import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import com.qq.netutil.nio.mina2.core.buffer.IoBuffer;

public class DataBuffer
{

	public static String DEFAULT_CHARSET= "utf-8";
	private IoBuffer buffer;

	private DataBuffer()
	{
	}

	public static DataBuffer allocate(int i)
	{
		DataBuffer skymobibuffer = new DataBuffer();
		skymobibuffer.buffer = IoBuffer.allocate(i);
		skymobibuffer.buffer.setAutoExpand(true);
		return skymobibuffer;
	}

	public static DataBuffer wrap(ByteBuffer bytebuffer)
	{
		return wrap(bytebuffer, true);
	}

	public static DataBuffer wrap(ByteBuffer bytebuffer, boolean flag)
	{
		DataBuffer skymobibuffer = new DataBuffer();
		skymobibuffer.buffer = IoBuffer.wrap(bytebuffer);
		skymobibuffer.buffer.setAutoExpand(flag);
		return skymobibuffer;
	}

	public static DataBuffer wrap(byte abyte0[])
	{
		return wrap(abyte0, true);
	}

	public static DataBuffer wrap(byte abyte0[], boolean flag)
	{
		DataBuffer skymobibuffer = new DataBuffer();
		skymobibuffer.buffer = IoBuffer.wrap(ByteBuffer.wrap(abyte0));
		skymobibuffer.buffer.setAutoExpand(flag);
		return skymobibuffer;
	}

	public static DataBuffer wrap(byte abyte0[], int i, int j)
	{
		return wrap(abyte0, i, j, true);
	}

	public static DataBuffer wrap(byte abyte0[], int i, int j, boolean flag)
	{
		DataBuffer skymobibuffer = new DataBuffer();
		skymobibuffer.buffer = IoBuffer.wrap(ByteBuffer.wrap(abyte0, i, j));
		skymobibuffer.buffer.setAutoExpand(flag);
		return skymobibuffer;
	}

	public ByteBuffer buf()
	{
		return buffer.buf();
	}

	public int capacity()
	{
		return buffer.capacity();
	}

	public int position()
	{
		return buffer.position();
	}

	public DataBuffer position(int i)
	{
		buffer.position(i);
		return this;
	}

	public int limit()
	{
		return buffer.limit();
	}

	public DataBuffer limit(int i)
	{
		buffer.limit(i);
		return this;
	}

	public DataBuffer mark()
	{
		buffer.mark();
		return this;
	}

	public int markValue()
	{
		return buffer.markValue();
	}

	public DataBuffer reset()
	{
		buffer.reset();
		return this;
	}

	public DataBuffer clear()
	{
		buffer.clear();
		return this;
	}

	public DataBuffer flip()
	{
		buffer.flip();
		return this;
	}

	public DataBuffer rewind()
	{
		buffer.rewind();
		return this;
	}

	public int remaining()
	{
		return buffer.remaining();
	}

	public boolean hasRemaining()
	{
		return buffer.hasRemaining();
	}

	public DataBuffer duplicate()
	{
		DataBuffer skymobibuffer = new DataBuffer();
		skymobibuffer.buffer = buffer.duplicate();
		return skymobibuffer;
	}

	public DataBuffer slice()
	{
		DataBuffer skymobibuffer = new DataBuffer();
		skymobibuffer.buffer = buffer.slice();
		return skymobibuffer;
	}

	public DataBuffer slice(int i)
	{
		DataBuffer skymobibuffer = new DataBuffer();
		skymobibuffer.buffer = buffer.slice();
		skymobibuffer.limit(i);
		return skymobibuffer;
	}

	public DataBuffer sliceNew()
	{
		return sliceNew(limit() - position());
	}

	public DataBuffer sliceNew(int i)
	{
		return wrap(array(), position(), i);
	}

	public byte[] array()
	{
		return buffer.array();
	}

	public byte[] arrayToPosition()
	{
		int i = position();
		rewind();
		return getByteArray(i);
	}

	public int arrayOffset()
	{
		return buffer.arrayOffset();
	}

	public byte get()
	{
		return buffer.get();
	}

	public short getUnsigned()
	{
		return buffer.getUnsigned();
	}

	public DataBuffer put(byte byte0)
	{
		buffer.put(byte0);
		return this;
	}

	public byte get(int i)
	{
		return buffer.get(i);
	}

	public short getUnsigned(int i)
	{
		return buffer.getUnsigned(i);
	}

	public DataBuffer put(int i, byte byte0)
	{
		buffer.put(i, byte0);
		return this;
	}

	public DataBuffer get(byte abyte0[], int i, int j)
	{
		buffer.get(abyte0, i, j);
		return this;
	}

	public DataBuffer get(byte abyte0[])
	{
		buffer.get(abyte0);
		return this;
	}

	public byte[] getByteArray(int i)
	{
		byte abyte0[] = new byte[i];
		get(abyte0);
		return abyte0;
	}

	public DataBuffer put(ByteBuffer bytebuffer)
	{
		buffer.put(bytebuffer);
		return this;
	}

	public DataBuffer put(byte abyte0[], int i, int j)
	{
		buffer.put(abyte0, i, j);
		return this;
	}

	public DataBuffer put(byte abyte0[])
	{
		buffer.put(abyte0);
		return this;
	}

	public DataBuffer compact()
	{
		buffer.compact();
		return this;
	}

	public ByteOrder order()
	{
		return buffer.order();
	}

	public DataBuffer order(ByteOrder byteorder)
	{
		buffer.order(byteorder);
		return this;
	}

	public char getChar()
	{
		return buffer.getChar();
	}

	public DataBuffer putChar(char c)
	{
		buffer.putChar(c);
		return this;
	}

	public char getChar(int i)
	{
		return buffer.getChar(i);
	}

	public DataBuffer putChar(int i, char c)
	{
		buffer.putChar(i, c);
		return this;
	}

	public short getShort()
	{
		return buffer.getShort();
	}

	public int getUnsignedShort()
	{
		return buffer.getUnsignedShort();
	}

	public DataBuffer putShort(short word0)
	{
		buffer.putShort(word0);
		return this;
	}

	public DataBuffer putUnsigned(short word0)
	{
		buffer.put(toUnsigned(word0));
		return this;
	}

	public short getShort(int i)
	{
		return buffer.getShort(i);
	}

	public int getUnsignedShort(int i)
	{
		return buffer.getUnsignedShort(i);
	}

	public DataBuffer putShort(int i, short word0)
	{
		buffer.putShort(i, word0);
		return this;
	}

	public DataBuffer putUnsigned(int i, short word0)
	{
		buffer.put(i, toUnsigned(word0));
		return this;
	}

	public int getInt()
	{
		return buffer.getInt();
	}

	public long getUnsignedInt()
	{
		return buffer.getUnsignedInt();
	}

	public int getMediumInt()
	{
		return buffer.getMediumInt();
	}

	public int getUnsignedMediumInt()
	{
		return buffer.getUnsignedMediumInt();
	}

	public int getMediumInt(int i)
	{
		return buffer.getMediumInt(i);
	}

	public int getUnsignedMediumInt(int i)
	{
		return buffer.getUnsignedMediumInt(i);
	}

	public DataBuffer putMediumInt(int i)
	{
		buffer.putMediumInt(i);
		return this;
	}

	public DataBuffer putMediumInt(int i, int j)
	{
		buffer.putMediumInt(i, j);
		return this;
	}

	public DataBuffer putInt(int i)
	{
		buffer.putInt(i);
		return this;
	}

	public DataBuffer putUnsignedShort(int i)
	{
		buffer.put(toUnsignedShort(i));
		return this;
	}

	public int getInt(int i)
	{
		return buffer.getInt(i);
	}

	public long getUnsignedInt(int i)
	{
		return buffer.getUnsignedInt(i);
	}

	public DataBuffer putUnsignedShort(int i, int j)
	{
		byte abyte0[] = toUnsignedShort(j);
		for (int k = 0; k < abyte0.length; k++)
			buffer.put(i + k, abyte0[k]);

		return this;
	}

	public DataBuffer putInt(int i, int j)
	{
		buffer.putInt(i, j);
		return this;
	}

	public long getLong()
	{
		return buffer.getLong();
	}

	public DataBuffer putLong(long l)
	{
		buffer.putLong(l);
		return this;
	}

	public DataBuffer putUnsignedInt(long l)
	{
		buffer.put(toUnsignedInt(l));
		return this;
	}

	public long getLong(int i)
	{
		return buffer.getLong(i);
	}

	public DataBuffer putLong(int i, long l)
	{
		buffer.putLong(i, l);
		return this;
	}

	public DataBuffer putUnsignedInt(int i, long l)
	{
		byte abyte0[] = toUnsignedInt(l);
		for (int j = 0; j < abyte0.length; j++)
			buffer.put(i + j, abyte0[j]);

		return this;
	}

	public float getFloat()
	{
		return buffer.getFloat();
	}

	public DataBuffer putFloat(float f)
	{
		buffer.putFloat(f);
		return this;
	}

	public float getFloat(int i)
	{
		return buffer.getFloat(i);
	}

	public DataBuffer putFloat(int i, float f)
	{
		buffer.putFloat(i, f);
		return this;
	}

	public double getDouble()
	{
		return buffer.getDouble();
	}

	public DataBuffer putDouble(double d)
	{
		buffer.putDouble(d);
		return this;
	}

	public double getDouble(int i)
	{
		return buffer.getDouble(i);
	}

	public DataBuffer putDouble(int i, double d)
	{
		buffer.putDouble(i, d);
		return this;
	}

	public String getPrefixedString()
	{
		return getPrefixedString(1);
	}

	public String getPrefixedString(int i)
	{
		return getPrefixedString(i, DEFAULT_CHARSET);
	}

	public String getPrefixedString(String charset)
	{
		return getPrefixedString(1, charset);
	}

	public String getPrefixedString(int i, String charset)
	{
		return bufferToPrefixedString(i, charset);
	}

	public String getFixupLengthString(int i)
	{
		return getFixupLengthString(i, DEFAULT_CHARSET);
	}

	public String getFixupLengthString(int i, String charset)
	{
		if (i <= 0)
			throw new IllegalArgumentException("length must be larger than 0");
		else
			return bufferToFixupLengthString(i, charset);
	}

	public DataBuffer putPrefixedString(String s)
	{
		return putPrefixedString(s, 2);
	}

	public DataBuffer putPrefixedString(String s, int i)
	{
		return putPrefixedString(s, i, DEFAULT_CHARSET);
	}

	public DataBuffer putPrefixedString(String s, String charset)
	{
		return putPrefixedString(s, 1, DEFAULT_CHARSET);
	}

	public DataBuffer putPrefixedString(String s, int i, String charset)
	{
		int j;
		switch (i)
		{
		case 1: // '\001'
			j = 255;
			break;

		case 2: // '\002'
			j = 65535;
			break;

		default:
			throw new IllegalArgumentException("prefixLength must be 1 or 2");
		}
		if (s.length() > j)
			throw new IllegalArgumentException("The specified string is too long");
		byte abyte0[] = stringToByte(s, charset);
		switch (i)
		{
		case 1: // '\001'
			put((byte)abyte0.length);
			break;

		case 2: // '\002'
			putShort((short)abyte0.length);
			break;
		}
		return put(abyte0);
	}

	public DataBuffer putFixupLengthString(String s)
	{
		return putFixupLengthString(s, DEFAULT_CHARSET);
	}

	public DataBuffer putFixupLengthString(String s, String charset)
	{
		return put(stringToByte(s, charset));
	}

	public DataBuffer putFixupLengthString(String s, int i)
	{
		return putFixupLengthString(s, i, DEFAULT_CHARSET);
	}

	public DataBuffer putFixupLengthString(String s, int i, String charset)
	{
		if (i <= 0)
			throw new IllegalArgumentException("length must be larger than 0");
		byte abyte0[] = stringToByte(s, charset);
		if (abyte0.length == i)
			return put(abyte0);
		if (abyte0.length > i)
		{
			throw new IllegalArgumentException("The specified string is too long");
		} else
		{
			put(abyte0);
			return skip(i - abyte0.length);
		}
	}

	public String getString()
	{
		return getString(DEFAULT_CHARSET);
	}

	public String getString(String charset)
	{
		int i = position();
		int j;
		for (j = 0; get() != 0; j++);
		position(i);
		String s = getFixupLengthString(j, charset);
		position(i + j + 1);
		return s;
	}

	public DataBuffer putString(String s)
	{
		return putString(s,DEFAULT_CHARSET);
	}

	public DataBuffer putString(String s, String charset)
	{
		return putFixupLengthString(s, charset).put((byte)0);
	}

	public String getHexDump()
	{
		return buffer.getHexDump();
	}

	public String getHexDump(int i)
	{
		return buffer.getHexDump(i);
	}

	public DataBuffer skip(int i)
	{
		buffer.skip(i);
		return this;
	}

	private String bufferToPrefixedString(int i, String charset)
	{
		if (i < 1 || i > 2)
			throw new IllegalArgumentException("prefixLength must be 1 or 2");
		else
			return bufferToFixupLengthString(i != 1 ? getUnsignedShort() : ((int) (getUnsigned())), charset);
	}

	private String bufferToFixupLengthString(int i, String charset)
	{
		return byteArrayToString(getByteArray(i), charset);
	}

	private String byteArrayToString(byte abyte0[], String charset)
	{
		
		try 
		{
			return (new String(abyte0, DEFAULT_CHARSET)).trim();
		} 
		catch (UnsupportedEncodingException e) 
		{
			e.printStackTrace();
		}
		return null;
	}

	private byte[] stringToByte(String s, String charset)
	{
		try 
		{
			return s.getBytes(charset);
		} 
		catch (UnsupportedEncodingException e)
		{
			e.printStackTrace();
		}
		return new byte[0];
	}

	private byte toUnsigned(short word0)
	{
		return toHH(word0, 1)[0];
	}

	private byte[] toUnsignedShort(int i)
	{
		return toHH(i, 2);
	}

	private byte[] toUnsignedInt(long l)
	{
		return toHH(l, 4);
	}

	private byte[] toHH(long l, int i)
	{
		byte abyte0[] = new byte[i];
		for (int j = i - 1; j >= 0; j--)
			abyte0[j] = (byte)(int)(l >> (i - j - 1) * 8 & 255L);

		return abyte0;
	}
}
