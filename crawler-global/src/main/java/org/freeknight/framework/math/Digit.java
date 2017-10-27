
package org.freeknight.framework.math;

public final class Digit
{
	/**
	 * 将字符串转化为 int 类型数值, 若转化失败, 则返回默认值.
	 * 
	 * @param src
	 *          待转换的字符串.
	 * @param defaultValue
	 *          默认值. 转换失败时的默认值.
	 * @return
	 */
	public static final int parseInt (
			final String src, final int defaultValue )
	{
		return parseInt ( src, defaultValue, 10 );
	}

	/**
	 * 将字符串转化为 int 类型数值, 若转化失败, 则返回默认值.
	 * 
	 * @param src
	 *          待转换的字符串.
	 * @param defaultValue
	 *          默认值. 转换失败时的默认值.
	 * @param radix
	 *          进制. 默认10进制转换.
	 * @return
	 */
	public static final int parseInt (
			final String src, final int defaultValue, final int radix )
	{
		int result = defaultValue;

		if ( src == null || src.trim ( ).isEmpty ( ) )
		{
			return result;
		}

		try
		{
			result = Integer.parseInt ( src, radix );
		}
		catch ( final NumberFormatException ignore )
		{
		}

		return result;
	}

	/**
	 * 将字符串转化为 long 类型数值, 若转化失败, 则返回默认值.
	 * 
	 * @param src
	 *          待转换的字符串.
	 * @param defaultValue
	 *          默认值. 转换失败时的默认值.
	 * @return
	 */
	public static final long parseLong (
			final String src, final long defaultValue )
	{
		return parseLong ( src, defaultValue, 10 );
	}

	/**
	 * 将字符串转化为 long 类型数值, 若转化失败, 则返回默认值.
	 * 
	 * @param src
	 *          待转换的字符串.
	 * @param defaultValue
	 *          默认值. 转换失败时的默认值.
	 * @param radix
	 *          进制. 默认10进制转换.
	 * @return
	 */
	public static final long parseLong (
			final String src, final long defaultValue, final int radix )
	{
		long result = defaultValue;

		if ( src == null || src.trim ( ).isEmpty ( ) )
		{
			return result;
		}

		try
		{
			result = Long.parseLong ( src, radix );
		}
		catch ( final NumberFormatException ignore )
		{
		}

		return result;
	}

	private Digit ( ) {
		throw new AssertionError ( "Uninstantiable class." );
	}
}
