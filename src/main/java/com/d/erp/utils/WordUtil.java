package com.d.erp.utils;

import lombok.Cleanup;
import org.apache.poi.util.IOUtils;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.xmlbeans.XmlException;
import org.apache.xmlbeans.XmlOptions;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTBody;
import org.springframework.util.FileCopyUtils;

import java.io.*;

public class WordUtil {


    public static void main(String[] args) throws IOException, XmlException {
       byte[] src = IOUtils.toByteArray(new FileInputStream(new File("d:/2.docx")));
       byte[] doc = IOUtils.toByteArray(new FileInputStream(new File("d:/1.docx")));
       byte[] result = mergeDoc(src , doc);
       FileCopyUtils.copy(result , new File("d:/test.docx"));
    }


    private static byte[] mergeDoc(byte[] src ,byte[] doc) throws IOException, XmlException {
        XWPFDocument srcDocument =  new XWPFDocument(new ByteArrayInputStream(src));
        XWPFDocument target =  new XWPFDocument(new ByteArrayInputStream(doc));
        appendBody(srcDocument.getDocument().getBody(), target.getDocument().getBody());
        @Cleanup
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        srcDocument.write(os);
        return os.toByteArray();
    }

    /**
     * 合并文档内容
     * @param src 目标文档
     * @param append 要合并的文档
     * @throws Exception
     */
    private static void appendBody(CTBody src, CTBody append) throws IOException, XmlException {
        XmlOptions optionsOuter = new XmlOptions();
        optionsOuter.setSaveOuter();
        String appendString = append.xmlText(optionsOuter);
        String srcString = src.xmlText();
        String prefix = srcString.substring(0, srcString.indexOf(">") + 1);
        String mainPart = srcString.substring(srcString.indexOf(">") + 1,
                srcString.lastIndexOf("<"));
        String sufix = srcString.substring(srcString.lastIndexOf("<"));
        String addPart = appendString.substring(appendString.indexOf(">") + 1,
                appendString.lastIndexOf("<"));
        CTBody makeBody = CTBody.Factory.parse(prefix + mainPart + addPart + sufix);
        src.set(makeBody);
    }
}
