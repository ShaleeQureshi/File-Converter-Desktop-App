package src.ConversionCode;

/*
* Date: May 21, 2020
* Authors: Shahrukh Qureshi
* Description: This class contains all of the code for the document conversions / async code
*
* Method List:
* 1. void conversion(final String type, final String file, final String path) = This method opens a new thread for an async task to begin
* 2. boolean pdfToDoc(final String file, final String path) = This method converts a pdf file to a doc
* 3. docxToPdf(final String file, final String path) = This method converts a docx file to a pdf
* 4. registerEvent(final EventListener eventListener) = This method sets the EventListener
* 5. loading(final String type, final String file, final String path) = This method displays a loading screen while the conversion processes
* 6. onEvent(final String type, final String file, final String path) = This method runs in the background and calls the conversion method that is needed
*
* Class List:
* public class = Conversions
* local class = PerformTask
*
* Interface List:
* EventListener
*/

// Import Statements
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import com.aspose.pdf.Document;
import com.aspose.pdf.SaveFormat;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import fr.opensagres.poi.xwpf.converter.pdf.PdfConverter;
import fr.opensagres.poi.xwpf.converter.pdf.PdfOptions;
import src.GUI.Pages.LoadingFrame;
import src.GUI.ResultFrames.ConversionFailure;
import src.GUI.ResultFrames.ConversionSuccess;

public class Conversions {

    private static Document document; // Object used in the conversion process

    private static String converted = "ConvertedFile"; // Name of the new file

    private EventListener eventListener;

    // This method opens a new thread for an async task to begin
    public static void conversion(final String type, final String file, final String path) {

        final Conversions conversions = new Conversions();
        final EventListener eventListener = new PerformTask();

        conversions.registerEvent(eventListener);
        conversions.loading(type, file, path);

    } // conversion Method

    // This method converts a pdf file to a doc
    boolean pdfToDoc(final String file, final String path) {

        try {

            document = new Document(file);

            document.save(path + "/" + converted + ".doc", SaveFormat.Doc);

            document.close();

            System.out.println(path);

            return true; // If the file is converted we return true

        } catch (final Exception e) {
            return false; // If an error occurs we return false
        }

    } // pdfToDoc Method

    // This method converts a docx file to a pdf
    boolean docxToPdf(final String file, final String path) {

        try {

            final FileInputStream fileInputStream = new FileInputStream(file);
            final XWPFDocument doc = new XWPFDocument(fileInputStream);
            final PdfOptions pdfOptions = PdfOptions.create();
            final FileOutputStream out = new FileOutputStream(new File(path + "/" + converted + ".pdf"));
            PdfConverter.getInstance().convert(doc, out, pdfOptions);

            fileInputStream.close();
            doc.close();
            out.close();

            return true; // If the file is converted we return true

        } catch (final Exception e) {
            return false; // If an error occurs we return false
        }

    } // docToPdf Method

    // This method sets the EventListener
    public void registerEvent(final EventListener eventListener) {
        this.eventListener = eventListener;
    } // registerEvent Method

    // This method displays a loading screen while the conversion processes
    public void loading(final String type, final String file, final String path) {

        // Creating a new thread
        new Thread(new Runnable() {

            @Override
            public void run() {

                new LoadingFrame();

                // try {Thread.sleep(100);} catch (Exception e) {}
                if (eventListener != null) {

                    eventListener.onEvent(type, file, path);

                }
            } // run Method
        }).start(); // Starting the thread
    }

} // Conversions Class

// This interface allows for classes to impliment methods required for an async
// task
interface EventListener {

    void onEvent(String type, String file, String path); // This can be any method as long as it has the same parameters

} // EventListener Interface

// This class performs a task in the background (Async)
class PerformTask implements EventListener {

    // Variables to represent types of conversions
    final private String docx = "DOCX";
    final private String pdf = "PDF";

    // This method runs in the background and calls the conversion method that is
    // needed
    @Override
    public void onEvent(final String type, final String file, final String path) {

        // Creating an instance of a class
        final Conversions conv = new Conversions();
        boolean converted = true;

        // If the conversion is pdf to doc the following will occur
        if (type.equals(pdf)) {
            converted = conv.pdfToDoc(file, path);
        }
        // If the conversion is docx to pdf the following will occur
        else if (type.equals(docx)) {
            converted = conv.docxToPdf(file, path);
        }

        LoadingFrame.frameLoading.dispose(); // Removing the loading screen once the data has been processed

        if (!converted) {
            new ConversionFailure();
        } else {
            new ConversionSuccess();
        }

    } // onEvent Method

} // PerformTask Method