/**
 * Test class for the Factory Method pattern.
 * Demonstrates creating different document types WITHOUT the client
 * code ever calling "new WordDocument()" etc. directly - it only ever
 * talks to DocumentFactory and Document.
 */
public class FactoryMethodTest {
    public static void main(String[] args) {

        DocumentFactory wordFactory = new WordDocumentFactory();
        Document word = wordFactory.createDocument();
        word.open();

        DocumentFactory pdfFactory = new PdfDocumentFactory();
        Document pdf = pdfFactory.createDocument();
        pdf.open();

        DocumentFactory excelFactory = new ExcelDocumentFactory();
        Document excel = excelFactory.createDocument();
        excel.open();
    }
}
