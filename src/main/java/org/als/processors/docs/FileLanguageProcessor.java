package org.als.processors.docs;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

import com.detectlanguage.DetectLanguage;
import com.detectlanguage.Result;
import com.detectlanguage.errors.APIError;
import org.apache.poi.xwpf.usermodel.*;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STHighlightColor;


public class FileLanguageProcessor {
    private final DocxHandler docxHandler;
    private final List<XWPFParagraph> paragraphList;
    private final List<XWPFHeaderFooter> headerFooterList;

    private List<XWPFParagraph> enParagraphList;
    private List<XWPFHeaderFooter> enHeaderFooterList;

    public FileLanguageProcessor( String filePath ) throws IOException {
        this.docxHandler = new DocxHandler(filePath);
        XWPFDocument doc = new XWPFDocument(Files.newInputStream(docxHandler.getOriginalDocxFile().toPath()));
        this.paragraphList = doc.getParagraphs();
        this.headerFooterList = new ArrayList<>();
        this.headerFooterList.addAll(doc.getHeaderList());
        this.headerFooterList.addAll(doc.getFooterList());
    }

    private void processDocument() {
        this.enParagraphList = new ArrayList<>();
        this.enHeaderFooterList = new ArrayList<>();

        for( XWPFParagraph paragraph : paragraphList ) {
            if( isEnglish(paragraph.getParagraphText()) ) {
                for( XWPFRun run : paragraph.getRuns() ) {
                    run.setTextHighlightColor(STHighlightColor.CYAN.toString());
                }
                this.enParagraphList.add(paragraph);
            }
        }

        for( XWPFHeaderFooter headerFooter : headerFooterList ) {
            if( isEnglish(headerFooter.getText()) ) {
                for( XWPFParagraph paragraph : headerFooter.getParagraphs() ) {
                    for( XWPFRun run : paragraph.getRuns() ) {
                        run.setTextHighlightColor(STHighlightColor.CYAN.toString());
                    }
                }
                this.enHeaderFooterList.add(headerFooter);
            }
        }

        generateEsDocxFile();
        generateEnDocxFile();
    }

    private boolean isEnglish( String text ) {
        String LANG_EN = "en";
        Result result = getDetectedLanguage(text);

        return result.language.equals(LANG_EN);
    }

    private void generateEsDocxFile() {
        try( XWPFDocument esDoc = new XWPFDocument(Files.newInputStream(docxHandler.getEsDocxFile().toPath())) ) {
            for( XWPFParagraph paragraph : esDoc.getParagraphs() ) {
                String pText = paragraph.getParagraphText();
                for( XWPFParagraph enP : enParagraphList ) {
                    String enText = enP.getParagraphText();

                    if( pText.equalsIgnoreCase(enText) ) {
                        esDoc.removeBodyElement(esDoc.getPosOfParagraph(paragraph));
                        break;
                    }
                }
            }

            for( XWPFHeader header : esDoc.getHeaderList() ) {
                String text = header.getText();
                for( XWPFHeaderFooter enHeaderFooter : enHeaderFooterList ) {
                    String enText = enHeaderFooter.getText();
                    if( text.equalsIgnoreCase(enText) ) {
                        for( XWPFParagraph p : header.getParagraphs() ){
                            header.removeParagraph(p);
                        }
                        break;
                    }
                }
            }

            for( XWPFFooter footer : esDoc.getFooterList() ) {
                String text = footer.getText();
                for( XWPFHeaderFooter enHeaderFooter : enHeaderFooterList ) {
                    String enText = enHeaderFooter.getText();
                    if( text.equalsIgnoreCase(enText) ) {
                        for( XWPFParagraph p : footer.getParagraphs() ){
                            footer.removeParagraph(p);
                        }
                        break;
                    }
                }
            }

            FileOutputStream out = new FileOutputStream(docxHandler.getEsDocxFile());
            esDoc.write(out);
            out.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void generateEnDocxFile() {
        try( XWPFDocument enDoc = new XWPFDocument(Files.newInputStream(docxHandler.getEnDocxFile().toPath())) ) {
            for( XWPFParagraph paragraph : enDoc.getParagraphs() ) {
                if( shouldEnParagraphBeRemoved(paragraph) ) {
                    enDoc.removeBodyElement(enDoc.getPosOfParagraph(paragraph));
                }
            }

            for( XWPFHeader header : enDoc.getHeaderList() ) {
                if( shouldEnHeaderFooterBeRemoved(header) ) {
                    removeHeaderFooter(header);
                }
            }

            for( XWPFFooter footer : enDoc.getFooterList() ) {
                if( shouldEnHeaderFooterBeRemoved(footer) ) {
                    removeHeaderFooter(footer);
                }
            }

            FileOutputStream out = new FileOutputStream(docxHandler.getEnDocxFile());
            enDoc.write(out);
            out.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private boolean shouldEnParagraphBeRemoved(XWPFParagraph paragraph) {
        for( XWPFParagraph p : enParagraphList ) {
            if( p.getParagraphText().equalsIgnoreCase(paragraph.getParagraphText()) ){
                return false;
            }
        }

        return true;
    }
    private boolean shouldEnHeaderFooterBeRemoved( XWPFHeaderFooter headerFooter ) {
        for( XWPFHeaderFooter enHeaderFooter : enHeaderFooterList ) {
            if( enHeaderFooter.getText().equalsIgnoreCase(headerFooter.getText()) ) {
                return false;
            }
        }

        return true;
    }
    private void removeHeaderFooter( XWPFHeaderFooter headerFooter ) {
        for( XWPFParagraph p : headerFooter.getParagraphs() ) {
            headerFooter.removeParagraph(p);
        }
    }

    public Result getDetectedLanguage( String text ){
        DetectLanguage.apiKey = "9d5ca7e9c11c9b482538cb87397232e1";

        List<Result> results = null;
        try {
            results = DetectLanguage.detect(text);

            return results.get(0);
        } catch (APIError e) {
            throw new RuntimeException(e);
        }
    }


    public static void main( String[] args ) throws IOException, APIError {
        FileLanguageProcessor flp = new FileLanguageProcessor("/home/betoche_1/Documents/translations/11363-es.docx");
        flp.processDocument();
    }
}
