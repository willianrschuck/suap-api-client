import java.util.function.Consumer;

public abstract class RequestHandler {

    private Consumer<Error> errorConsumer;

    public abstract void execute();

    protected final void handleError(Throwable e) {
        e.printStackTrace();
        if (errorConsumer != null) {
            errorConsumer.accept(new Error(e.getMessage()));
        }
    }

    public final RequestHandler onError(Consumer<Error> r) {
        errorConsumer = r;
        return this;
    }

}
