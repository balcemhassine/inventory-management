package com.tbs.inventorymanagement.service;

import com.lowagie.text.DocumentException;
import com.tbs.inventorymanagement.model.ReportObj;
import com.tbs.inventorymanagement.model.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;
import org.xhtmlrenderer.pdf.ITextRenderer;

import java.io.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Service
public class CsvAndPdfFileService {

    @Autowired
    private SpringTemplateEngine defaultTemplateEngine;
    private final String HEADER = "transactionId,transactionType,quantity,date,product";

    private String transactionConvertToCSV(Transaction tr) {
        return String.join(",",
                String.valueOf(tr.getTransactionId()),
                tr.getTransactionType().toString(),
                String.valueOf(tr.getQuantity()),
                tr.getDate().toString(),
                tr.getProduct().getProductName()
                );
    }

    public void transactionsListToCsvFile(List<Transaction> data, String fileName) throws IOException {
        File csvOutputFile = new File(fileName);
        try (PrintWriter pw = new PrintWriter(csvOutputFile)) {
            pw.println(HEADER);
            data.stream()
                    .map(line -> transactionConvertToCSV(line))
                    .forEach(pw::println);
        }
    }


    public String parseThymeleafTemplate(ReportObj reportObj) {


        Context context = new Context();
        context.setVariable("date", LocalDate.now());

        context.setVariable("totalRevenue", reportObj.getTotalRevenue());
        context.setVariable("revenueByCategory", reportObj.getRevenueByCategory());
        context.setVariable("revenueByProduct", reportObj.getRevenueByProduct());
        context.setVariable("mostProfitableProduct", reportObj.getMostProfitableProduct());
        context.setVariable("bestSellingProduct", reportObj.getBestSellingProduct());
        context.setVariable("bestSellingProducts", reportObj.getBestSellingProducts());


        return defaultTemplateEngine.process("report", context);
    }

    public void generatePdfFromHtml(String html,String path) throws DocumentException, IOException {
        String outputFolder = path;
        OutputStream outputStream = new FileOutputStream(outputFolder);

        ITextRenderer renderer = new ITextRenderer();
        renderer.setDocumentFromString(html);
        renderer.layout();
        renderer.createPDF(outputStream);

        outputStream.close();
    }
}
