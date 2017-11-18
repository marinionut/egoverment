package ro.ionutmarin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import ro.ionutmarin.service.ReportService;
import ro.ionutmarin.util.PdfKeeper;

/**
 * Created by ionut on 11/18/2017.
 */
@RequestMapping("/report")
@RestController
public class ReportController {
    @Autowired
    ReportService reportService;

    PdfKeeper pdfKeeper = new PdfKeeper();

    @RequestMapping(value = "/pdf", method = RequestMethod.GET)
    public @ResponseBody
    PdfKeeper getPdfPath() {
        pdfKeeper.setPdfName(reportService.getReport());
        return pdfKeeper;
    }

}
