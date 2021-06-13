import java.util.function.Consumer;

public abstract class RequestHandler<T> {

    private Consumer<Error> errorConsumer;

    public abstract T execute();

    protected final void handleError(Throwable e) {
        e.printStackTrace();
        if (errorConsumer != null) {
            errorConsumer.accept(new Error(e.getMessage()));
        }
    }

    public final RequestHandler<T> onError(Consumer<Error> r) {
        errorConsumer = r;
        return this;
    }

}
