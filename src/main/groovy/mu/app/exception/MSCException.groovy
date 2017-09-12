package main.groovy.mu.app.exception

class MSCException extends Exception {

    /**
     * Returns a cute formatted stack trace.
     *
     * @param throwable
     * @return
     */
    static String getStringTrace(Throwable throwable = null){
        Writer writer = new StringWriter()
        PrintWriter printWriter = new PrintWriter(writer)

        throwable = throwable ?: this
        throwable.printStackTrace(printWriter);
        return writer.toString();
    }

}
