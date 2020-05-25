package src.ConversionCode;

/*
* Date: May 21, 2020
* Authors: Shahrukh Qureshi
* Description: This class contains all of the code for the document conversions
*/

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import com.aspose.pdf.Document;
import com.aspose.pdf.SaveFormat;

import org.apache.poi.xwpf.usermodel.XWPFDocument;

import fr.opensagres.poi.xwpf.converter.pdf.PdfConverter;
import fr.opensagres.poi.xwpf.converter.pdf.PdfOptions;

public class Conversions {

    private static Document document;

    private static String converted = "ConvertedFile";

    // This method converts a pdf file to a doc
    public static boolean pdfToDoc(final String file) {

        try {

            document = new Document(file);

            document.save(converted + ".doc", SaveFormat.Doc);

            document.close();

            return true;

        } catch (final Exception e) {
            return false;
        }

    } // pdfToDoc Method

    // This method converts a docx file to a pdf
    public static boolean docxToPdf(final String file) {

        try {

            FileInputStream fileInputStream = new FileInputStream(file);
            XWPFDocument doc = new XWPFDocument(fileInputStream);
            PdfOptions pdfOptions = PdfOptions.create();
            FileOutputStream out = new FileOutputStream(new File(converted + ".pdf"));
            PdfConverter.getInstance().convert(doc, out, pdfOptions);

            fileInputStream.close();
            doc.close();
            out.close();

            return true;

        } catch (final Exception e) {
            return false;
        }

    } // docToPdf Method

} // Conversions Class