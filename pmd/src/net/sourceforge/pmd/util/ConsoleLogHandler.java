/**
 * BSD-style license; for more info see http://pmd.sourceforge.net/license.html
 */
package net.sourceforge.pmd.util;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.logging.Handler;
import java.util.logging.LogRecord;

/**
 * Log to the console without using a formatter.
 * 
 * @author Wouter Zelle
 */
public class ConsoleLogHandler extends Handler {

    public void publish(LogRecord logRecord) {
        System.out.println(logRecord.getMessage());
        if (logRecord.getThrown() != null) {
            // Use the same channel, to make sure that the stacktrace comes
            // after the message on the console (using printStackTrace
            // directly messes things up)
            StringWriter stringWriter = new StringWriter();
            PrintWriter printWriter = new PrintWriter(stringWriter, true);
            logRecord.getThrown().printStackTrace(printWriter);
            System.out.println(stringWriter.toString());
        }
    }
    
    public void close() throws SecurityException {
        return;
    }

    public void flush() {
        return;
    }
}