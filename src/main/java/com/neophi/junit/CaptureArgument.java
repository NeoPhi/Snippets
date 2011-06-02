/*
 * Copyright (c) 2011 Daniel Rinehart <danielr@neophi.com> [http://danielr.neophi.com/]
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package com.neophi.junit;

import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;

/**
 * Matcher that requires the argument to be of a particular type and will save the value it matches. Handy when a factory pattern is too heavy but you
 * can still assert something about the item. A sample use case would be:
 * 
 * <pre>
 * <code>
 * final MBeanServer mBeanServer = context.mock(MBeanServer.class);
 * final Object objectToRegister = new Object();
 * final CaptureArgument&lt;ObjectName&gt; captureArgument = new CaptureArgument&lt;ObjectName&gt;();
 * context.checking(new Expectations() {{
 *     oneOf(mBeanServer).registerMBean(with(same(objectToRegister)), with(captureArgument));
 * }});
 * unitUnderTest.doSomething(objectToRegister);
 * assertEquals("BeanDomain", captureArgument.getArgument().getDomain());
 * </code>
 * </pre>
 * 
 * In this case the unitUnderTest creates an ObjectName instance that we want to capture and inspect.
 */
public class CaptureArgument<T> extends BaseMatcher<T>
{
    private T argument;

    /**
     * Returns the value that the matcher was given.
     * 
     * @return Captured argument
     */
    public T getArgument()
    {
        return argument;
    }

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    public boolean matches(final Object item)
    {
        argument = (T) item;
        return true;
    }

    /**
     * {@inheritDoc}
     */
    public void describeTo(Description description)
    {
        description.appendText("capturing an argument");
    }
}
