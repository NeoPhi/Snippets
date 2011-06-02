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

import static org.junit.Assert.assertSame;

import java.util.concurrent.Executor;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JMock;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(JMock.class)
public class CaptureArgumentUnitTest
{
    private Mockery context = new JUnit4Mockery();

    @Test
    public void getArgument_hasValue_isCorrect() throws Exception
    {
        final Executor executor = context.mock(Executor.class);
        final CaptureArgument<Runnable> captureArgument = new CaptureArgument<Runnable>();

        context.checking(new Expectations()
        {
            {
                oneOf(executor).execute(with(captureArgument));
            }
        });

        Runnable runnable = context.mock(Runnable.class);
        executor.execute(runnable);

        assertSame(runnable, captureArgument.getArgument());
    }
}
