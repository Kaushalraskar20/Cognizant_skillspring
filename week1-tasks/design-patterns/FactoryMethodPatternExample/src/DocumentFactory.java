/**
 * DocumentFactory - the abstract "Creator" in the Factory Method pattern.
 * Each concrete subclass decides WHICH Document subclass actually gets
 * instantiated. Client code only ever talks to this abstract type.
 */
public abstract class DocumentFactory {
    public abstract Document createDocument();
}
